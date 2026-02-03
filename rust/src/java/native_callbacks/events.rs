use std::ffi::c_char;
use std::sync::Arc;

use pumpkin::plugin::EventPriority;
use pumpkin::plugin::player::player_join::PlayerJoinEvent;
use pumpkin_util::text::TextComponent;

use crate::events::handler::PatchBukkitEventHandler;
use crate::java::native_callbacks::{CALLBACK_CONTEXT, utils::get_string};
use crate::proto::patchbukkit::common::RegisterEventRequest;

pub fn ffi_native_bridge_register_event_impl(request: RegisterEventRequest) -> Option<()> {
    let ctx = CALLBACK_CONTEXT.get()?;
    let pumpkin_priority = match request.priority {
        0 => EventPriority::Lowest,
        1 => EventPriority::Low,
        2 => EventPriority::Normal,
        3 => EventPriority::High,
        _ => EventPriority::Highest,
    };

    log::info!(
        "Plugin '{}' registering listener for '{}' (priority={:?}, blocking={})",
        request.plugin_name,
        request.event_type,
        request.priority,
        request.blocking
    );

    let command_tx = ctx.command_tx.clone();
    let context = ctx.plugin_context.clone();

    tokio::task::block_in_place(|| {
        ctx.runtime.block_on(async {
            match request.event_type.as_str() {
                "org.bukkit.event.player.PlayerJoinEvent" => {
                    context
                        .register_event::<
                            pumpkin::plugin::player::player_join::PlayerJoinEvent,
                            PatchBukkitEventHandler<pumpkin::plugin::player::player_join::PlayerJoinEvent>,
                        >(
                            Arc::new(PatchBukkitEventHandler::new(
                                request.plugin_name.clone(),
                                command_tx.clone(),
                            )),
                            pumpkin_priority,
                            request.blocking,
                        )
                        .await;
                }
                _ => {
                    log::warn!(
                        "Unsupported Bukkit event type '{}' from plugin '{}'",
                        request.event_type, request.plugin_name
                    );
                }
            }
        });
    });

    Some(())
}

pub extern "C" fn rust_call_event(
    event_type_ptr: *const c_char,
    event_data_ptr: *const c_char,
) -> bool {
    let event_type = get_string(event_type_ptr);
    let event_data_json = get_string(event_data_ptr);

    let Some(ctx) = CALLBACK_CONTEXT.get() else {
        log::error!("CallbackContext not initialized when calling event");
        return false;
    };

    log::debug!("Java calling event '{event_type}' with data: {event_data_json}");

    let event_data: serde_json::Value = match serde_json::from_str(&event_data_json) {
        Ok(v) => v,
        Err(e) => {
            log::error!("Failed to parse event data JSON: {e}");
            return false;
        }
    };

    let context = ctx.plugin_context.clone();

    tokio::task::block_in_place(|| {
        ctx.runtime.block_on(async {
            if event_type.as_str() == "org.bukkit.event.player.PlayerJoinEvent" {
                let player_uuid_str = event_data["playerUuid"].as_str().unwrap_or("");
                let join_message_str = event_data["joinMessage"].as_str().unwrap_or("");
                if let Ok(uuid) = uuid::Uuid::parse_str(player_uuid_str)
                    && let Some(player) = context.server.get_player_by_uuid(uuid)
                {
                    let pumpkin_event = PlayerJoinEvent::new(
                        player,
                        TextComponent::from_legacy_string(join_message_str),
                    );
                    context.server.plugin_manager.fire(pumpkin_event).await;
                    return true;
                }
                false
            } else {
                log::warn!("Unknown event type for Pumpkin: {event_type}");
                false
            }
        })
    })
}
