package com.yanisbft.aroundtheworld.block.entity;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import com.yanisbft.aroundtheworld.block.ATWBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class ATWBlockEntities {
    public static final BlockEntityType<TravelerBlockEntity> TRAVELER = register("traveler", FabricBlockEntityTypeBuilder.create(TravelerBlockEntity::new, ATWBlocks.TRAVELER));

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, AroundTheWorld.id(name), builder.build());
    }

    public static void init() {
    }
}
