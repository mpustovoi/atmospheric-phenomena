package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.block_entities.CapsuleBlockEntity;
import com.phyrenestudios.atmospheric_phenomena.blocks.CapsuleBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTables;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class FreshMeteoriteFeature extends Feature<NoneFeatureConfiguration> {
    public FreshMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel levelIn = context.level();
        BlockPos posIn = context.origin();
        RandomSource rand = levelIn.getRandom();
        BlockState target = levelIn.getBlockState(posIn.below());
        if (!target.is(APTags.Blocks.VALID_METEORITE_SPAWN)) return false;
        if (posIn.getY() <= levelIn.getMinBuildHeight() || posIn.getY() > levelIn.getMaxBuildHeight()) return false;

        int size = 2;
        FeatureUtils.populateBlockCollections();

        List<BlockPos> centerList = getCenters(rand, posIn, size, 3);
        BlockPos centerPos = getCenterPos(centerList);
        buildCrater(levelIn, centerPos, size + 6, levelIn.getBlockState(posIn.below()), levelIn.getBlockState(centerPos.below(5)));
        buildMeteor(levelIn, rand, centerList, size);
        if (rand.nextDouble() < Config.meteoriteCrateSpawnChance) setCrate(levelIn, rand, centerPos);
        return true;
    }

    private void buildCrater(WorldGenLevel levelIn, BlockPos centerPos, int radius, BlockState surface, BlockState groundmass) {
        boolean waterlog = false;

        for (BlockPos blockpos : BlockPos.betweenClosed(centerPos.offset(-radius, 0, -radius), centerPos.offset(radius, radius, radius))) {
            if (outsideCrater(centerPos, blockpos, radius)) continue;
            if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                waterlog = true;
                break;
            }
        }

        Block glass = FeatureUtils.meteorStrewnBlockCollection.getRandomElement();
        for (int i = radius-1; i<=radius*2; ++i) {
            for (BlockPos blockpos : BlockPos.betweenClosed(centerPos.offset(-radius, i, -radius), centerPos.offset(radius, i, radius))) {
                if (outsideCrater(centerPos.above(i), blockpos, radius)) continue;
                if (!Config.meteoriteDestroyAll && !levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;
                if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                    if (blockpos.getY() <= levelIn.getSeaLevel()) continue;
                }
                levelIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(),3);
            }
        }
        for (BlockPos blockpos : BlockPos.betweenClosed(centerPos.offset(-radius, -1, -radius), centerPos.offset(radius, radius-2, radius))) {
            if (outsideCrater(centerPos.above(radius-2), blockpos, radius)) continue;
            if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                if (blockpos.getY() > levelIn.getSeaLevel()) levelIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 3);
                continue;
            }
            if (insideCrater(blockpos, centerPos.above(radius-2), radius)) {
                if (!Config.meteoriteDestroyAll && !levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;
                levelIn.setBlock(blockpos, waterlog && blockpos.getY() < levelIn.getSeaLevel() ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
                continue;
            }

            if (blockpos.getY() == levelIn.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, blockpos.getX(), blockpos.getZ()) && insideCraterWall(blockpos, centerPos.above(radius-2), radius) && isReplaceable(levelIn, blockpos)) {
                setCraterBlock(levelIn, blockpos, glass, surface, groundmass, centerPos, true);
                continue;
            }

            if (levelIn.getBlockState(blockpos).is(Blocks.AIR)) continue;
            if (!levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;
            setCraterBlock(levelIn, blockpos, glass, surface, groundmass, centerPos, false);

        }
    }

    private boolean outsideCrater(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) > (radius)*(radius);
    }
    private boolean insideCraterWall(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) >= (radius-1)*(radius-1);
    }
    private boolean insideCrater(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) < (radius-1)*(radius-1);
    }
    private boolean isReplaceable(WorldGenLevel levelIn, BlockPos posIn) {
        return levelIn.getBlockState(posIn).is(Blocks.AIR) || levelIn.getBlockState(posIn).is(Blocks.WATER) || levelIn.getBlockState(posIn).canBeReplaced();
    }

    private void setCraterBlock(WorldGenLevel levelIn, BlockPos posIn, Block glass, BlockState surface, BlockState groundmass, BlockPos centerPos, boolean override) {
        if (levelIn.getRandom().nextFloat() < Config.magmaBlockFrequency && posIn.getY() < centerPos.getY()+1) {
            levelIn.setBlock(posIn, Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
            return;
        }
        if (levelIn.getRandom().nextFloat() < Config.strewnBlockFrequency) {
            levelIn.setBlock(posIn, glass.defaultBlockState(), 3);
            return;
        }
        int chance = levelIn.getRandom().nextInt(3);
        if (chance == 1) {
            levelIn.setBlock(posIn, groundmass, 3);
        } else if (chance == 2) {
            levelIn.setBlock(posIn, surface, 3);
        } else if (override) {
            levelIn.setBlock(posIn, surface, 3);
        }
    }

    private void buildMeteor(WorldGenLevel levelIn, RandomSource rand, List<BlockPos> centerList, int size) {
        Block meteor = FeatureUtils.meteorBlockCollection.getRandomElement();
        Block coreMeteor;
        if (rand.nextFloat() < Config.solidCoreMeteoriteChance) {
            coreMeteor = meteor;
        } else {
            coreMeteor = FeatureUtils.meteorCoreBlockCollection.getRandomElement();
        }

        for (BlockPos center : centerList) {
            int j = 1 + rand.nextInt(size);
            int k = 1 + rand.nextInt(size);
            int l = 1 + rand.nextInt(size);
            float f = (float)(j + k + l) * 0.333F + 0.75F;
            for(BlockPos blockpos : BlockPos.betweenClosed(center.offset(-size-1, -size-1, -size-1), center.offset(size+1, size+1, size+1))) {
                if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)*0.3) {
                    levelIn.setBlock(blockpos, coreMeteor.defaultBlockState(), 3);
                } else if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)) {
                    levelIn.setBlock(blockpos, meteor.defaultBlockState(), 3);
                }
            }
        }

    }

    private void setCrate(WorldGenLevel levelIn, RandomSource rand, BlockPos posIn) {
        levelIn.setBlock(posIn, CapsuleBlocks.PLATED_CAPSULE.getCapsule().defaultBlockState(), 2);
        CapsuleBlockEntity.setLootTable(levelIn, rand, posIn, APLootTables.OVERWORLD_METEOR);
    }
    private double shortestDistance(BlockPos posIn, List<BlockPos> centerList, int size) {
        double minDist = 100D;
        for (BlockPos center : centerList) {
            minDist = Math.min(posIn.distSqr(center.below(size)), minDist);
        }
        return minDist;
    }

    private List<BlockPos> getCenters(RandomSource rand, BlockPos posIn, int size, int count) {
        List<BlockPos> list = new ArrayList<>();
        BlockPos pos = posIn.below(2);
        for (int i = 1; i <= count; i++) {
            list.add(pos.offset(rand.nextInt(3)-1, rand.nextInt(3)-1, rand.nextInt(3)-1));
        }
        return list;
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
