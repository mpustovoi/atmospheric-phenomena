package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OverworldMeteoriteFeature extends Feature<NoneFeatureConfiguration> {
    public OverworldMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel levelIn = context.level();
        BlockPos posIn = context.origin();
        RandomSource rand = levelIn.getRandom();
        BlockState target = levelIn.getBlockState(posIn.below());
        if (!target.is(APTags.Blocks.VALID_METEORITE_SPAWN)) return false;
        if (ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_BLOCKS).isEmpty()) return false;

        int size = 2 + rand.nextInt(2);

        List<BlockPos> centerList = Arrays.asList(posIn, posIn.offset(rand.nextInt(size-1)+1, rand.nextInt(size-1)-1, rand.nextInt(size-1)+1));
        BlockPos centerPos = getCenterPos(centerList);
        buildCrater(levelIn, centerPos.above(size+3), size + 8, levelIn.getBlockState(centerPos.below(5)), Blocks.GLASS, levelIn.getFluidState(posIn).is(FluidTags.WATER));
        buildMeteor(levelIn, rand, centerList, size);
        return true;
    }

    private void buildCrater(WorldGenLevel levelIn, BlockPos posIn, int radius, BlockState groundmass, Block tektite, boolean waterlog) {
        for (BlockPos blockpos : BlockPos.betweenClosed(posIn.offset(-radius, -radius, -radius), posIn.offset(radius, radius, radius))) {
            if (!levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;

            if (blockpos.distSqr(posIn) > radius*radius) continue;
            if (blockpos.distSqr(posIn) > (radius-1)*(radius-1)) {
                float chance = levelIn.getRandom().nextFloat();
                if (chance < 0.3) {
                    levelIn.setBlock(blockpos, tektite.defaultBlockState(), 3);
                } else if (chance < 0.4) {
                    levelIn.setBlock(blockpos, Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
                } else if (chance < 0.7) {
                    levelIn.setBlock(blockpos, groundmass, 3);
                }
            } else {
                levelIn.setBlock(blockpos, waterlog ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
            }

        }
    }

    private void buildMeteor(WorldGenLevel reader, RandomSource rand, List<BlockPos> centerList, int size) {

        Optional<Block> meteor = ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_BLOCKS).getRandomElement(rand);
        Optional<Block> rareMeteor = ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_BLOCKS).getRandomElement(rand);
        Optional<Block> coreMeteor = ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_CORE_BLOCKS).getRandomElement(rand);
        Optional<Block> rareCoreMeteor = ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS).getRandomElement(rand);
        if (rareMeteor.isPresent() && rand.nextFloat() < 0.1) meteor = rareMeteor;
        if (rareCoreMeteor.isPresent() && rand.nextFloat() < 0.1) coreMeteor = rareCoreMeteor;
        if (coreMeteor.isEmpty() || rand.nextFloat() < 0.2) coreMeteor = meteor;

        int j = 1 + rand.nextInt(size);
        int k = 1 + rand.nextInt(size);
        int l = 1 + rand.nextInt(size);
        float f = (float)(j + k + l) * 0.333F + 0.75F;
        for (BlockPos center : centerList) {
            center = center.below(size);
            for(BlockPos blockpos : BlockPos.betweenClosed(center.offset(-size, -size, -size), center.offset(size, size, size))) {
                if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)*0.3) {
                    reader.setBlock(blockpos.below(1), coreMeteor.get().defaultBlockState(), 3);
                } else if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)) {
                    reader.setBlock(blockpos.below(1), meteor.get().defaultBlockState(), 3);
                }
            }
        }

    }

    private double shortestDistance(BlockPos posIn, List<BlockPos> centerList, int size) {
        double minDist = 100D;
        for (BlockPos center : centerList) {
            minDist = Math.min(posIn.distSqr(center.below(size)), minDist);
        }
        return minDist;
    }

    private BlockPos getCenterPos(List<BlockPos> centerList) {
        int Xs = 0;
        int Ys = 0;
        int Zs = 0;

        for (BlockPos center : centerList) {
            Xs += center.getX();
            Ys += center.getY();
            Zs += center.getZ();
        }
        return new BlockPos(Xs /centerList.size(), Ys /centerList.size(), Zs /centerList.size());
    }


}
