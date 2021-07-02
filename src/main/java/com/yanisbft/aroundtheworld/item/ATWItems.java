package com.yanisbft.aroundtheworld.item;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import com.yanisbft.aroundtheworld.block.ATWBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ATWItems {
    public static final Item TRAVELLER = register(ATWBlocks.TRAVELLER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, AroundTheWorld.id(name), item);
    }

    private static Item register(Block block, FabricItemSettings settings) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, settings));
    }

    public static void init() {
    }
}
