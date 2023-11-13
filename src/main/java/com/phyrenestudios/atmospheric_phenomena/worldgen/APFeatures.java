package com.phyrenestudios.atmospheric_phenomena.worldgen;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, AtmosphericPhenomena.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OVERWORLD_METEORITE = FEATURES.register("overworld_meteorite", () -> new OverworldMeteoriteFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BURIED_METEORITE = FEATURES.register("buried_meteorite", () -> new BuriedMeteoriteFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FRESH_METEORITE = FEATURES.register("fresh_meteorite", () -> new FreshMeteoriteFeature(NoneFeatureConfiguration.CODEC));
    //public static final RegistryObject<Feature<NoneFeatureConfiguration>> LARGE_CRATER = FEATURES.register("large_crater", () -> new LargeCraterFeature(NoneFeatureConfiguration.CODEC));


    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_OVERWORLD_METEORITE = createKey("overworld_meteorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_BURIED_METEORITE = createKey("buried_meteorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_FRESH_METEORITE = createKey("fresh_meteorite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_LARGE_CRATER= createKey("large_crater");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, CONFIGURED_OVERWORLD_METEORITE, OVERWORLD_METEORITE.get(), new NoneFeatureConfiguration());
        register(context, CONFIGURED_BURIED_METEORITE, BURIED_METEORITE.get(), new NoneFeatureConfiguration());
        register(context, CONFIGURED_FRESH_METEORITE, FRESH_METEORITE.get(), new NoneFeatureConfiguration());
       // register(context, CONFIGURED_LARGE_CRATER, LARGE_CRATER.get(), new NoneFeatureConfiguration());
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration) {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AtmosphericPhenomena.MODID, name));
    }

}
