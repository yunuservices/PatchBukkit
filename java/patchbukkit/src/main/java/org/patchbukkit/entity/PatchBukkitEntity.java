package org.patchbukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.patchbukkit.PatchBukkitServer;

public class PatchBukkitEntity implements Entity {

    protected final PatchBukkitServer server;
    protected Entity entity;
    private final EntityType entityType;

    public PatchBukkitEntity(
        final PatchBukkitServer server,
        final Entity entity
    ) {
        this.server = server;
        this.entity = entity;
        this.entityType = CraftEntityType.minecraftToBukkit(entity.getType());
    }
}
