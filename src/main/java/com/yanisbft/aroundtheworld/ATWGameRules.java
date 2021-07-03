package com.yanisbft.aroundtheworld;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ATWGameRules {
    public static final GameRules.Key<GameRules.IntRule> TRAVELER_USE_COOLDOWN = register("travelerUseCooldown", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(0, 0));

    private static <T extends GameRules.Rule<T>> GameRules.Key<T> register(String name, GameRules.Category category, GameRules.Type<T> type) {
        return GameRuleRegistry.register(name, category, type);
    }

    public static void init() {
    }
}
