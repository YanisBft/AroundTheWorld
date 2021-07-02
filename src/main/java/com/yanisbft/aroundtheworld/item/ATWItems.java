package com.yanisbft.aroundtheworld.item;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import com.yanisbft.aroundtheworld.block.ATWBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class ATWItems {
    public static final Item TRAVELER = register(ATWBlocks.TRAVELER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(ATWItemGroups.MAIN));
    public static final Item TRAVELER_KEY = register("traveler_key", new TravelerKeyItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(ATWItemGroups.MAIN)));

    public static final Item PLAINS_BIOME_EMBLEM = registerBiomeEmblem("plains_biome_emblem", BiomeKeys.PLAINS);
    public static final Item FOREST_BIOME_EMBLEM = registerBiomeEmblem("forest_biome_emblem", BiomeKeys.FOREST);
    public static final Item DESERT_BIOME_EMBLEM = registerBiomeEmblem("desert_biome_emblem", BiomeKeys.DESERT);

    private static Item register(Block block, FabricItemSettings settings) {
        return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, settings));
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, AroundTheWorld.id(name), item);
    }

    private static Item registerBiomeEmblem(String name, RegistryKey<Biome> biomeKey) {
        return register(name, new BiomeEmblemItem(new FabricItemSettings().group(ATWItemGroups.MAIN), biomeKey));
    }

    public static void init() {
    }
}
