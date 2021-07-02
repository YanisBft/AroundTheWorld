package com.yanisbft.aroundtheworld.screen;

import com.yanisbft.aroundtheworld.AroundTheWorld;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ATWScreenHandlers {
    public static final ScreenHandlerType<TravelerScreenHandler> TRAVELER = register("traveler", TravelerScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerSimple(AroundTheWorld.id(name), factory);
    }

    public static void init() {
    }
}
