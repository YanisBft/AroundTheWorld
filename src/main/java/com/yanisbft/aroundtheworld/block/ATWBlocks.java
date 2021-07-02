package com.yanisbft.aroundtheworld.block;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import com.yanisbft.aroundtheworld.block.TravellerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class ATWBlocks {
    public static final Block TRAVELLER = register("traveller", new TravellerBlock(FabricBlockSettings.of(Material.STONE)));

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, AroundTheWorld.id(name), block);
    }

    public static void init() {
    }
}
