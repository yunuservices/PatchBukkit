use std::sync::Arc;

use pumpkin::plugin::{Context, EventPriority};
use tokio::sync::Mutex;

use crate::{events::join::PatchBukkitJoinHandler, plugin::manager::PluginManager};

pub mod join;

pub async fn register_handlers(plugin_manager: Arc<Mutex<PluginManager>>, server: &Arc<Context>) {
    server
        .register_event(
            Arc::new(PatchBukkitJoinHandler { plugin_manager }),
            EventPriority::Highest,
            true,
        )
        .await;
}
