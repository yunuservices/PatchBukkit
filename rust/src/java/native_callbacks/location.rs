use pumpkin::command::args::entities::{EntitySelectorType, TargetSelector};

use crate::{
    java::native_callbacks::CALLBACK_CONTEXT,
    proto::patchbukkit::common::{Location, Uuid, Vec3, World},
};

#[repr(C)]
pub struct Vec3FFI {
    pub x: f64,
    pub y: f64,
    pub z: f64,
}

pub fn ffi_native_bridge_get_location_impl(entity_uuid: Uuid) -> Option<Location> {
    let ctx = CALLBACK_CONTEXT.get()?;
    let uuid = uuid::Uuid::parse_str(&entity_uuid.value).ok()?;

    let entity = ctx
        .plugin_context
        .server
        .select_entities(&TargetSelector::new(EntitySelectorType::Uuid(uuid)), None);

    if entity.len() == 1 {
        let entity = entity.first().unwrap().get_entity();
        let position = entity.pos.load();
        let world = entity.world.load().uuid;
        let yaw = entity.yaw.load();
        let pitch = entity.pitch.load();

        return Some(Location {
            world: Some(World {
                uuid: Some(Uuid {
                    value: world.to_string(),
                }),
            }),
            position: Some(Vec3 {
                x: position.x,
                y: position.y,
                z: position.z,
            }),
            yaw,
            pitch,
        });
    }

    None
}
