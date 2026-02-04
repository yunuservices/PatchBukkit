use crate::java::native_callbacks::CALLBACK_CONTEXT;
use crate::proto::patchbukkit::{
    abilities::{Abilities, SetAbilitiesRequest},
    common::Uuid,
};

pub fn ffi_native_bridge_get_abilities_impl(request: Uuid) -> Option<Abilities> {
    let ctx = CALLBACK_CONTEXT.get()?;
    let player_uuid = uuid::Uuid::parse_str(&request.value).ok()?;
    let player = ctx.plugin_context.server.get_player_by_uuid(player_uuid)?;
    let abilities = tokio::task::block_in_place(|| {
        ctx.runtime
            .block_on(async { player.abilities.lock().await })
    });

    Some(Abilities {
        invulnerable: abilities.invulnerable,
        flying: abilities.flying,
        allow_flying: abilities.allow_flying,
        creative: abilities.creative,
        allow_modify_world: abilities.allow_modify_world,
        fly_speed: abilities.fly_speed,
        walk_speed: abilities.walk_speed,
    })
}

pub fn ffi_native_bridge_set_abilities_impl(request: SetAbilitiesRequest) -> Option<bool> {
    let ctx = CALLBACK_CONTEXT.get()?;
    let player_uuid = uuid::Uuid::parse_str(&request.uuid?.value).ok()?;
    let abilities = request.abilities?;
    let player = ctx.plugin_context.server.get_player_by_uuid(player_uuid)?;

    let mut pumpkin_abilities = tokio::task::block_in_place(|| {
        ctx.runtime
            .block_on(async { player.abilities.lock().await })
    });

    pumpkin_abilities.invulnerable = abilities.invulnerable;
    pumpkin_abilities.flying = abilities.flying;
    pumpkin_abilities.allow_flying = abilities.allow_flying;
    pumpkin_abilities.creative = abilities.creative;
    pumpkin_abilities.allow_modify_world = abilities.allow_modify_world;
    pumpkin_abilities.fly_speed = abilities.fly_speed;
    pumpkin_abilities.walk_speed = abilities.walk_speed;

    let player = player.clone();
    ctx.runtime.spawn(async move {
        player.send_abilities_update().await;
    });

    Some(true)
}
