package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LargeCraterFeature extends Feature<NoneFeatureConfiguration> {
    public LargeCraterFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel levelIn = context.level();
        BlockPos posIn = context.origin();
        RandomSource rand = levelIn.getRandom();
        int radius = 5 + rand.nextInt(10);

        buildCrater(levelIn, rand, posIn.above(radius), radius);
        return true;
    }

    private void buildCrater(WorldGenLevel levelIn, RandomSource rand, BlockPos posIn, int radius) {
        boolean waterlog = false;
        /*
        for (BlockPos blockpos : BlockPos.betweenClosed(posIn.offset(-radius, -radius, -radius), posIn.offset(radius, radius, radius))) {
            if (blockpos.distSqr(posIn) > (radius)*(radius)) continue;
            if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                waterlog = true;
                break;
            }
        }

         */

        for (BlockPos blockpos : BlockPos.betweenClosed(posIn.offset(-radius, -radius, -radius), posIn.offset(radius, radius, radius))) {
            if (levelIn.getBlockState(blockpos).is(Blocks.AIR) || levelIn.getBlockState(blockpos).is(Blocks.WATER)) continue;
            if (blockpos.distSqr(posIn) > radius*radius) continue;

            if (!levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN) || blockpos.distSqr(posIn) < (radius-1)*(radius-1)) {
                levelIn.setBlock(blockpos, waterlog && blockpos.getY() < levelIn.getSeaLevel() ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
            }


        }
    }


}
