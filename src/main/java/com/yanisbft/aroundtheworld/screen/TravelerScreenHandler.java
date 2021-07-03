package com.yanisbft.aroundtheworld.screen;

import com.yanisbft.aroundtheworld.item.BiomeEmblemItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class TravelerScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public TravelerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(1));
    }

    public TravelerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ATWScreenHandlers.TRAVELER, syncId);
        checkSize(inventory, 1);
        this.inventory = inventory;

        // traveller inventory
        this.addSlot(new Slot(inventory, 0, 62 + 18, 17 + 18) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return (stack.getItem() instanceof BiomeEmblemItem) && super.canInsert(stack);
            }
        });

        // player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // player hotbar
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();

            if (index == 0) {
                if (!this.insertItem(slotStack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(slotStack, stack);
            } else if (slotStack.getItem() instanceof BiomeEmblemItem) {
                if (!this.insertItem(slotStack, 0, 1, true)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (slotStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, slotStack);
        }

        return stack;
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.inventory.onClose(player);
    }
}
