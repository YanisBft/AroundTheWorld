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

    public static final Item PLAINS_EMBLEM = registerBiomeEmblem("plains_emblem", BiomeKeys.PLAINS);
    public static final Item DESERT_EMBLEM = registerBiomeEmblem("desert_emblem", BiomeKeys.DESERT);
    public static final Item MOUNTAINS_EMBLEM = registerBiomeEmblem("mountains_emblem", BiomeKeys.MOUNTAINS);
    public static final Item FOREST_EMBLEM = registerBiomeEmblem("forest_emblem", BiomeKeys.FOREST);
    public static final Item TAIGA_EMBLEM = registerBiomeEmblem("taiga_emblem", BiomeKeys.TAIGA);
    public static final Item SWAMP_EMBLEM = registerBiomeEmblem("swamp_emblem", BiomeKeys.SWAMP);
    public static final Item SNOWY_TUNDRA_EMBLEM = registerBiomeEmblem("snowy_tundra_emblem", BiomeKeys.SNOWY_TUNDRA);
    public static final Item MUSHROOM_FIELDS_EMBLEM = registerBiomeEmblem("mushroom_fields_emblem", BiomeKeys.MUSHROOM_FIELDS);
    public static final Item JUNGLE_EMBLEM = registerBiomeEmblem("jungle_emblem", BiomeKeys.JUNGLE);
    public static final Item BIRCH_FOREST_EMBLEM = registerBiomeEmblem("birch_forest_emblem", BiomeKeys.BIRCH_FOREST);
    public static final Item DARK_FOREST_EMBLEM = registerBiomeEmblem("dark_forest_emblem", BiomeKeys.DARK_FOREST);
    public static final Item SAVANNA_EMBLEM = registerBiomeEmblem("savanna_emblem", BiomeKeys.SAVANNA);
    public static final Item BADLANDS_EMBLEM = registerBiomeEmblem("badlands_emblem", BiomeKeys.BADLANDS);

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
