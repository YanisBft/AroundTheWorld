package com.yanisbft.aroundtheworld.block;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class ATWBlocks {
    public static final Block TRAVELER = register("traveler", new TravelerBlock(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(3.5F, 6.0F).luminance(10)));

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, AroundTheWorld.id(name), block);
    }

    public static void init() {
    }
}
