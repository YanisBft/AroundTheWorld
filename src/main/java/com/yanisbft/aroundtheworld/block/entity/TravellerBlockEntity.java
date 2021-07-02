package com.yanisbft.aroundtheworld.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TravellerBlockEntity extends BlockEntity {
    private long lastUsed;

    public TravellerBlockEntity(BlockPos pos, BlockState state) {
        super(ATWBlockEntities.TRAVELLER, pos, state);
    }

    public long getLastUsed() {
        return this.lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }
}
