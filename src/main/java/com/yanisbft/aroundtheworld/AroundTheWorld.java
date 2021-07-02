package com.yanisbft.aroundtheworld;

import com.yanisbft.aroundtheworld.block.ATWBlocks;
import com.yanisbft.aroundtheworld.item.ATWItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class AroundTheWorld implements ModInitializer {
	private static final String MODID = "aroundtheworld";

	@Override
	public void onInitialize() {
		ATWBlocks.init();
		ATWBlockEntities.init();
		ATWItems.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
