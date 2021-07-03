package com.yanisbft.aroundtheworld;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class ATWTags {
    public static final Tag<Item> BIOME_EMBLEMS = register("biome_emblems");
    public static final Tag<Item> DISABLED_BIOME_EMBLEMS = register("disabled_biome_emblems");

    private static Tag<Item> register(String name) {
        return TagRegistry.item(AroundTheWorld.id(name));
    }

    public static void init() {
    }
}
