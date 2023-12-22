package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTables;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class OverworldMeteoriteFeature extends AbstractMeteoriteFeature {
    public OverworldMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    boolean canSpawn(WorldGenLevel levelIn, BlockPos posIn) {
        return levelIn.getBlockState(posIn.below()).is(APTags.Blocks.VALID_METEORITE_SPAWN);
    }

    @Override
    boolean generateCrater() {
        return true;
    }

    @Override
    int burrialDepth(RandomSource rand) {
        return rand.nextInt(3)+2;
    }

    @Override
    ResourceLocation getLoottable() {
        return APLootTables.OVERWORLD_METEOR;
    }

}
