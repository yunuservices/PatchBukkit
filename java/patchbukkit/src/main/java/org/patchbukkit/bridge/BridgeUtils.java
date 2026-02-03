package org.patchbukkit.bridge;

import java.util.UUID;

public class BridgeUtils {
    public static UUID convertUuid(patchbukkit.common.UUID uuid) {
        return UUID.fromString(uuid.getValue());
    }

    public static patchbukkit.common.UUID convertUuid(UUID uuid) {
        return patchbukkit.common.UUID.newBuilder().setValue(uuid.toString()).build();
    }
}
