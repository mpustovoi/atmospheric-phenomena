package com.phyrenestudios.atmospheric_phenomena.worldgen;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.Arrays;
import java.util.List;

public class APPlacements {

    public static final ResourceKey<PlacedFeature> OVERWORLD_METEORITE = createKey("overworld_meteorite");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_METEORITE, configuredFeatureGetter.getOrThrow(APFeatures.CONFIGURED_OVERWORLD_METEORITE), Arrays.asList(RarityFilter.onAverageOnceEvery(40), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(AtmosphericPhenomena.MODID, name));
    }

}
