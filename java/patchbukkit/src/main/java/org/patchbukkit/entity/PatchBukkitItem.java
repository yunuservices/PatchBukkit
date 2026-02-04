package org.patchbukkit.entity;

import java.util.UUID;

import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PatchBukkitItem extends PatchBukkitEntity implements Item {
    private ItemStack itemStack;
    private int pickupDelay = 10;
    private boolean unlimitedLifetime = false;
    private boolean canMobPickup = true;
    private boolean canPlayerPickup = true;
    private UUID owner;
    private UUID thrower;

    public PatchBukkitItem(@NotNull UUID uuid, @NotNull ItemStack itemStack) {
        super(uuid, "ITEM");
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void setItemStack(@NotNull ItemStack stack) {
        this.itemStack = stack;
    }

    @Override
    public int getPickupDelay() {
        return pickupDelay;
    }

    @Override
    public void setPickupDelay(int delay) {
        this.pickupDelay = delay;
    }

    @Override
    public void setUnlimitedLifetime(boolean value) {
        this.unlimitedLifetime = value;
    }

    @Override
    public boolean isUnlimitedLifetime() {
        return unlimitedLifetime;
    }

    @Override
    public void setCanMobPickup(boolean value) {
        this.canMobPickup = value;
    }

    @Override
    public boolean canMobPickup() {
        return canMobPickup;
    }

    @Override
    public void setCanPlayerPickup(boolean value) {
        this.canPlayerPickup = value;
    }

    @Override
    public boolean canPlayerPickup() {
        return canPlayerPickup;
    }

    @Override
    public void setOwner(@Nullable UUID owner) {
        this.owner = owner;
    }

    @Override
    public @Nullable UUID getOwner() {
        return owner;
    }

    @Override
    public void setThrower(@Nullable UUID thrower) {
        this.thrower = thrower;
    }

    @Override
    public @Nullable UUID getThrower() {
        return thrower;
    }
}
