package com.phyrenestudios.atmospheric_phenomena.init;

import net.minecraft.world.level.GameRules;

public class APGameRules extends GameRules {

    public static GameRules.Key<GameRules.BooleanValue> RULE_CREATE_IMPACT_CRATERS;

    public static void initializeGamerules() {
        RULE_CREATE_IMPACT_CRATERS = GameRules.register("createImpactCraters", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));
    }
}
