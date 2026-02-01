package org.patchbukkit.registry;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.jspecify.annotations.Nullable;

public class PatchBukkitRegistryAccess implements RegistryAccess {

    @Override
    public <T extends Keyed> @Nullable Registry<T> getRegistry(Class<T> type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegistry'");
    }

    @Override
    public <T extends Keyed> Registry<T> getRegistry(RegistryKey<T> registryKey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegistry'");
    }
}
