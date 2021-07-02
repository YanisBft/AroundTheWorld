package com.yanisbft.aroundtheworld.item;

import net.minecraft.item.Item;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeEmblemItem extends Item {
    private final RegistryKey<Biome> biomeKey;

    public BiomeEmblemItem(Settings settings, RegistryKey<Biome> biomeKey) {
        super(settings);
        this.biomeKey = biomeKey;
    }

    public RegistryKey<Biome> getBiomeKey() {
        return this.biomeKey;
    }
}
