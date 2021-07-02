package com.yanisbft.aroundtheworld.client;

import com.yanisbft.aroundtheworld.client.screen.TravelerScreen;
import com.yanisbft.aroundtheworld.screen.ATWScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ATWClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ATWScreenHandlers.TRAVELER, TravelerScreen::new);
    }
}
