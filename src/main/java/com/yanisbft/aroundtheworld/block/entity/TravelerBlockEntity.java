package com.yanisbft.aroundtheworld.block.entity;

import com.mojang.authlib.GameProfile;
import com.yanisbft.aroundtheworld.screen.TravelerScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class TravelerBlockEntity extends LockableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;
    private GameProfile owner;
    private long lastUsed;

    public TravelerBlockEntity(BlockPos pos, BlockState state) {
        super(ATWBlockEntities.TRAVELER, pos, state);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.aroundtheworld.traveler");
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return this.inventory.isEmpty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        if (nbt.contains("Owner") && nbt.get("Owner") != null) {
            this.owner = NbtHelper.toGameProfile(nbt.getCompound("Owner"));
        }
        this.lastUsed = nbt.getLong("LastUsed");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        if (this.owner != null) {
            nbt.put("Owner", NbtHelper.writeGameProfile(new NbtCompound(), this.owner));
        }
        nbt.putLong("LastUsed", this.lastUsed);
        return nbt;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot == 0 ? this.inventory.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot == 0) {
            this.inventory.set(slot, stack);
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return this.world.getBlockEntity(this.pos) == this;
    }

    public GameProfile getOwner() {
        return this.owner;
    }

    public void setOwner(GameProfile owner) {
        this.owner = owner;
    }

    public long getLastUsed() {
        return this.lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new TravelerScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }
}
