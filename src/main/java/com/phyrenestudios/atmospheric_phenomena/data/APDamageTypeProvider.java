package com.phyrenestudios.atmospheric_phenomena.data;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class APDamageTypeProvider {
    protected static void bootstrap(BootstapContext<DamageType> context) {
        context.register(com.phyrenestudios.atmospheric_phenomena.init.APDamageTypes.METEOROID, new DamageType("meteoroid", 0.1F));
    }
}
