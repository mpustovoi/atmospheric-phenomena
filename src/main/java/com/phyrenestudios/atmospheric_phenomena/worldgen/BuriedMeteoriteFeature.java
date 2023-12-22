package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BuriedMeteoriteFeature extends AbstractMeteoriteFeature {
    public BuriedMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    boolean canSpawn(WorldGenLevel levelIn, BlockPos posIn) {
        return true;
    }

    @Override
    boolean generateCrater() {
        return false;
    }

    @Override
    int burrialDepth(RandomSource rand) {
        return rand.nextInt(10)+3;
    }

    @Override
    ResourceLocation getLoottable() {
        return APLootTables.OVERWORLD_METEOR;
    }

}
