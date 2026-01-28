use std::sync::Arc;

use pumpkin::plugin::{Context, EventPriority};

use crate::{events::join::PatchBukkitJoinHandler, plugin::manager::Plugins};

pub mod join;

pub async fn register_handlers(plugins: Plugins, server: &Arc<Context>) {
    server
        .register_event(
            Arc::new(PatchBukkitJoinHandler { plugins }),
            EventPriority::Highest,
            true,
        )
        .await;
}
