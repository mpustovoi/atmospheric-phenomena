package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface APDamageTypes {
    ResourceKey<DamageType> METEOROID = register("meteoroid");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AtmosphericPhenomena.MODID, name));
    }

    static void bootstrap(BootstapContext<DamageType> context) {
        context.register(APDamageTypes.METEOROID, new DamageType("meteoroid", 0.0F));
    }
}
