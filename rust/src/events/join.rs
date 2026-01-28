use std::sync::Arc;

use pumpkin::{
    plugin::{BoxFuture, EventHandler, player::player_join::PlayerJoinEvent},
    server::Server,
};
use pumpkin_api_macros::with_runtime;
use pumpkin_util::text::{TextComponent, color::NamedColor};
use tokio::sync::mpsc;

use crate::java::jvm::commands::JvmCommand;

pub struct PatchBukkitJoinHandler {
    pub command_tx: mpsc::Sender<JvmCommand>,
}

#[with_runtime(global)]
impl EventHandler<PlayerJoinEvent> for PatchBukkitJoinHandler {
    fn handle_blocking<'a>(
        &self,
        _server: &Arc<Server>,
        event: &'a mut PlayerJoinEvent,
    ) -> BoxFuture<'a, ()> {
        Box::pin(async {
            // for (_plugin_name, plugin) in self.plugins.lock().unwrap().iter_mut() {}
            event.join_message =
                TextComponent::text(format!("Welcome, {}!", event.player.gameprofile.name))
                    .color_named(NamedColor::Green);
        })
    }
}
