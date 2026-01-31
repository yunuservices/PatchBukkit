use std::{
    ffi::{CStr, c_char, c_void},
    sync::{Arc, OnceLock},
};

use anyhow::Result;
use j4rs::{InvocationArg, Jvm};
use pumpkin::{entity::player::Abilities, plugin::Context};
use pumpkin_util::text::TextComponent;
use tokio::sync::MutexGuard;

static CALLBACK_CONTEXT: OnceLock<CallbackContext> = OnceLock::new();

struct CallbackContext {
    pub plugin_context: Arc<Context>,
    pub runtime: tokio::runtime::Handle,
}

pub fn init_callback_context(
    plugin_context: Arc<Context>,
    runtime: tokio::runtime::Handle,
) -> Result<()> {
    let context = CallbackContext {
        plugin_context,
        runtime,
    };

    CALLBACK_CONTEXT
        .set(context)
        .map_err(|_| anyhow::anyhow!("Failed to set callback context"))?;
    Ok(())
}

pub fn initialize_callbacks(jvm: &Jvm) -> Result<()> {
    let send_message_addr = rust_send_message as *const () as i64;
    let register_event_addr = rust_register_event as *const () as i64;
    let get_abilities_addr = rust_get_abilities as *const () as i64;
    let set_abilities_addr = rust_set_abilities as *const () as i64;

    jvm.invoke_static(
        "org.patchbukkit.bridge.NativePatchBukkit",
        "initCallbacks",
        &[
            InvocationArg::try_from(send_message_addr)?.into_primitive()?,
            InvocationArg::try_from(register_event_addr)?.into_primitive()?,
            InvocationArg::try_from(get_abilities_addr)?.into_primitive()?,
            InvocationArg::try_from(set_abilities_addr)?.into_primitive()?,
        ],
    )?;

    Ok(())
}

#[unsafe(no_mangle)]
pub extern "C" fn rust_send_message(uuid_ptr: *const c_char, message_ptr: *const c_char) {
    // This into_owned might not seem to make any sense, but it's necessary to ensure that the string is owned by the Rust code and can move into the runtime.
    let uuid_str = unsafe { CStr::from_ptr(uuid_ptr).to_string_lossy().into_owned() };
    let message = unsafe { CStr::from_ptr(message_ptr).to_string_lossy().into_owned() };

    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();

        ctx.runtime.spawn(async move {
            let player = ctx.plugin_context.server.get_player_by_uuid(uuid);
            if let Some(player) = player {
                player
                    .send_system_message(&TextComponent::from_legacy_string(&message))
                    .await;
            }
        });
    }
}

#[repr(C)]
pub struct AbilitiesFFI {
    pub invulnerable: bool,
    pub flying: bool,
    pub allow_flying: bool,
    pub creative: bool,
    pub allow_modify_world: bool,
    pub fly_speed: f32,
    pub walk_speed: f32,
}

impl AbilitiesFFI {
    fn new(abilities: MutexGuard<'_, Abilities>) -> Self {
        Self {
            invulnerable: abilities.invulnerable,
            flying: abilities.flying,
            allow_flying: abilities.allow_flying,
            creative: abilities.creative,
            allow_modify_world: abilities.allow_modify_world,
            fly_speed: abilities.fly_speed,
            walk_speed: abilities.walk_speed,
        }
    }
}

pub extern "C" fn rust_set_abilities(
    uuid_ptr: *const c_char,
    abilities: *mut AbilitiesFFI,
) -> bool {
    let uuid_str = unsafe { CStr::from_ptr(uuid_ptr).to_string_lossy().into_owned() };
    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();
        let player = ctx.plugin_context.server.get_player_by_uuid(uuid);
        if let Some(player) = player {
            tokio::task::block_in_place(|| {
                ctx.runtime.block_on(async {
                    let mut server_abilities = player.abilities.lock().await;
                    unsafe {
                        server_abilities.allow_flying = (*abilities).allow_flying;
                        server_abilities.allow_modify_world = (*abilities).allow_modify_world;
                        server_abilities.creative = (*abilities).creative;
                        server_abilities.fly_speed = (*abilities).fly_speed;
                        server_abilities.flying = (*abilities).flying;
                        server_abilities.invulnerable = (*abilities).invulnerable;
                        server_abilities.walk_speed = (*abilities).walk_speed;
                    }
                })
            });

            return true;
        }
    }

    false
}

pub extern "C" fn rust_get_abilities(uuid_ptr: *const c_char, out: *mut AbilitiesFFI) -> bool {
    let uuid_str = unsafe { CStr::from_ptr(uuid_ptr).to_string_lossy().into_owned() };
    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();
        let player = ctx.plugin_context.server.get_player_by_uuid(uuid);
        if let Some(player) = player {
            let abilities = tokio::task::block_in_place(|| {
                ctx.runtime
                    .block_on(async { AbilitiesFFI::new(player.abilities.lock().await) })
            });

            unsafe {
                *out = abilities;
            }

            return true;
        }
    }

    false
}

#[unsafe(no_mangle)]
pub extern "C" fn rust_register_event(
    listener_ptr: *const c_void, // opaque pointer to Java object
    plugin_ptr: *const c_void,
) {
    // Handle event registration
}
