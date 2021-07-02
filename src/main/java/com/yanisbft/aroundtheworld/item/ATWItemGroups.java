package com.yanisbft.aroundtheworld.item;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import com.yanisbft.aroundtheworld.block.ATWBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ATWItemGroups {
    public static final ItemGroup MAIN = register("main", ATWBlocks.TRAVELER);

    private static ItemGroup register(String name, ItemConvertible icon) {
        return FabricItemGroupBuilder.build(AroundTheWorld.id(name), () -> new ItemStack(icon));
    }

    public static void init() {
    }
}
