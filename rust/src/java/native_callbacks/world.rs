use std::ffi::{CString, c_char};

use pumpkin::command::args::entities::{EntitySelectorType, TargetSelector};

use crate::java::native_callbacks::{CALLBACK_CONTEXT, utils::get_string};

pub extern "C" fn rust_get_world(uuid_ptr: *const c_char) -> *const c_char {
    let uuid_str = get_string(uuid_ptr);
    if let Some(ctx) = CALLBACK_CONTEXT.get() {
        let uuid = uuid::Uuid::parse_str(&uuid_str).unwrap();
        let entity = ctx
            .plugin_context
            .server
            .select_entities(&TargetSelector::new(EntitySelectorType::Uuid(uuid)), None);
        if entity.len() == 1 {
            let entity = entity.first().unwrap().get_entity();
            let world = entity.world.load();

            let cstring = match CString::new(world.dimension.minecraft_name) {
                Ok(s) => s,
                Err(_) => return std::ptr::null(),
            };
        }
    }

    std::ptr::null()
}
