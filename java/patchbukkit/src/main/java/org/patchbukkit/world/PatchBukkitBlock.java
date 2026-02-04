package org.patchbukkit.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Locale;

public final class PatchBukkitBlock {
    private PatchBukkitBlock() {
    }

    public static Block create(PatchBukkitWorld world, int x, int y, int z, String blockKey) {
        Material material = resolveMaterial(blockKey);
        return (Block) Proxy.newProxyInstance(
            PatchBukkitBlock.class.getClassLoader(),
            new Class<?>[]{Block.class},
            new Handler(world, x, y, z, material, blockKey)
        );
    }

    static Material resolveMaterial(String blockKey) {
        if (blockKey == null || blockKey.isBlank()) {
            return Material.AIR;
        }

        Material material = Material.matchMaterial(blockKey);
        if (material != null) {
            return material;
        }

        String key = blockKey.contains(":") ? blockKey : "minecraft:" + blockKey;
        material = Material.matchMaterial(key);
        if (material != null) {
            return material;
        }

        String legacy = blockKey.replace("minecraft:", "").toUpperCase(Locale.ROOT);
        material = Material.getMaterial(legacy);
        return material != null ? material : Material.AIR;
    }

    private static final class Handler implements InvocationHandler {
        private final PatchBukkitWorld world;
        private final int x;
        private final int y;
        private final int z;
        private final Material material;
        private final String blockKey;

        private Handler(PatchBukkitWorld world, int x, int y, int z, Material material, String blockKey) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.material = material;
            this.blockKey = blockKey;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            String name = method.getName();
            switch (name) {
                case "getWorld":
                    return world;
                case "getX":
                    return x;
                case "getY":
                    return y;
                case "getZ":
                    return z;
                case "getLocation":
                    return new Location(world, x, y, z);
                case "getType":
                    return material;
                case "getBlockData":
                    return material.createBlockData();
                case "getState":
                    return PatchBukkitBlockState.create(world, x, y, z, material, blockKey);
                case "isEmpty":
                    return material.isAir();
                case "isLiquid":
                    return material.isLiquid();
                case "getRelative":
                    return getRelative(args);
                case "getChunk":
                    throw new UnsupportedOperationException("Unimplemented method 'getChunk'");
                    throw new UnsupportedOperationException("Unimplemented method '" + name + "'");
                case "equals":
                    return proxy == args[0];
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "toString":
                    return "PatchBukkitBlock{" + blockKey + " @ " + x + "," + y + "," + z + "}";
                default:
                    throw new UnsupportedOperationException("Unimplemented method '" + name + "'");
            }
        }

        private Block getRelative(Object[] args) {
            if (args == null || args.length == 0) {
                return create(world, x, y, z, blockKey);
            }
            if (args.length == 1 && args[0] instanceof BlockFace face) {
                return create(world, x + face.getModX(), y + face.getModY(), z + face.getModZ(), "minecraft:air");
            }
            if (args.length == 2 && args[0] instanceof BlockFace face && args[1] instanceof Integer distance) {
                return create(
                    world,
                    x + face.getModX() * distance,
                    y + face.getModY() * distance,
                    z + face.getModZ() * distance,
                    "minecraft:air"
                );
            }
            if (args.length == 3
                && args[0] instanceof Integer dx
                && args[1] instanceof Integer dy
                && args[2] instanceof Integer dz) {
                return create(world, x + dx, y + dy, z + dz, "minecraft:air");
            }
            throw new UnsupportedOperationException("Unimplemented method 'getRelative'");
        }
    }
}
