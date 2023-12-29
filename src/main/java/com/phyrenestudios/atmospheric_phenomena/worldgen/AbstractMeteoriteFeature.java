package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.block_entities.CapsuleBlockEntity;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.CapsuleBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
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
import java.util.Map;

public abstract class AbstractMeteoriteFeature extends Feature<NoneFeatureConfiguration> {
    public AbstractMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return generate(context.level(), context.origin());
    }

    public boolean generate(WorldGenLevel levelIn, BlockPos surfacePos) {
        if (surfacePos.getY() <= levelIn.getMinBuildHeight() || surfacePos.getY() > levelIn.getMaxBuildHeight()) return false;
        if (!canSpawn(levelIn, surfacePos)) return false;
        RandomSource rand = levelIn.getRandom();
        int size = 2;
        FeatureUtils.populateBlockCollections();

        List<BlockPos> centerList = getCenters(rand, surfacePos, size, 3);
        BlockPos centerPos = getCenterPos(centerList);
        if (generateCrater()) buildCrater(levelIn, surfacePos, centerPos, size + 6);
        Block coreBlock = buildMeteor(levelIn, rand, centerList, centerPos, size);
        if (rand.nextDouble() < Config.meteoriteCrateSpawnChance) placeCapsule(levelIn, rand, centerPos, coreBlock);
        return true;
    }

    abstract boolean canSpawn(WorldGenLevel levelIn, BlockPos posIn);
    abstract boolean generateCrater();
    abstract int burrialDepth(RandomSource rand);
    abstract ResourceLocation getLoottable();

    protected void buildCrater(WorldGenLevel levelIn, BlockPos surfacePos, BlockPos centerPos, int radius) {
        boolean waterlog = false;
        BlockState surface = levelIn.getBlockState(surfacePos.below());
        BlockState groundmass = levelIn.getBlockState(centerPos.below(5));

        for (BlockPos blockpos : BlockPos.betweenClosed(centerPos.offset(-radius, 0, -radius), centerPos.offset(radius, radius, radius))) {
            if (outsideCrater(centerPos, blockpos, radius)) continue;
            if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                waterlog = true;
                break;
            }
        }

        Block glass = FeatureUtils.meteorStrewnBlockCollection.getRandomElement();
        if (glass == null) glass = groundmass.getBlock();
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
            if (levelIn.getBlockState(blockpos).is(BlockTags.FEATURES_CANNOT_REPLACE)) continue;
            if (levelIn.getBlockState(blockpos).is(Blocks.WATER)) {
                if (blockpos.getY() > levelIn.getSeaLevel()) levelIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 3);
                continue;
            }
            if (insideCrater(blockpos, centerPos.above(radius-2), radius)) {
                if (!Config.meteoriteDestroyAll && !levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;
                levelIn.setBlock(blockpos, waterlog && blockpos.getY() < levelIn.getSeaLevel() ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 3);
                continue;
            }

            if (blockpos.getY() == levelIn.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, blockpos.getX(), blockpos.getZ()) && insideCraterWall(blockpos, centerPos.above(radius-2), radius) && isReplaceable(levelIn, blockpos) && !isReplaceable(levelIn, blockpos.below())) {
                setCraterBlock(levelIn, blockpos, glass, surface, groundmass, centerPos,true);
                continue;
            }

            if (levelIn.getBlockState(blockpos).is(Blocks.AIR)) continue;
            if (!levelIn.getBlockState(blockpos).is(APTags.Blocks.VALID_METEORITE_SPAWN)) continue;
            setCraterBlock(levelIn, blockpos, glass, surface, groundmass, centerPos,false);

        }
    }

    protected boolean outsideCrater(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) > (radius)*(radius);
    }
    protected boolean insideCraterWall(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) >= (radius-1)*(radius-1);
    }
    protected boolean insideCrater(BlockPos centerPos, BlockPos posIn, int radius) {
        return posIn.distSqr(centerPos) < (radius-1)*(radius-1);
    }
    protected boolean isReplaceable(WorldGenLevel levelIn, BlockPos posIn) {
        return levelIn.getBlockState(posIn).is(Blocks.AIR) || levelIn.getBlockState(posIn).is(Blocks.WATER) || levelIn.getBlockState(posIn).canBeReplaced();
    }

    protected void setCraterBlock(WorldGenLevel levelIn, BlockPos posIn, Block glass, BlockState surface, BlockState groundmass, BlockPos centerPos, boolean override) {
        if (levelIn.getRandom().nextFloat() < Config.strewnBlockFrequency) {
            levelIn.setBlock(posIn, glass.defaultBlockState(), 3);
            return;
        }
        int chance = levelIn.getRandom().nextInt(3);
        if (chance == 1 && !groundmass.is(Blocks.WATER)) {
            levelIn.setBlock(posIn, groundmass, 3);
        } else if (chance == 2) {
            levelIn.setBlock(posIn, surface, 3);
        } else if (override) {
            levelIn.setBlock(posIn, surface, 3);
        }
    }

    protected Block buildMeteor(WorldGenLevel levelIn, RandomSource rand, List<BlockPos> centerList, BlockPos centerPos, int size) {
        Block meteor = FeatureUtils.meteorBlockCollection.getRandomElement();
        Block coreMeteor = rand.nextFloat() < Config.solidCoreMeteoriteChance ? meteor : FeatureUtils.meteorCoreBlockCollection.getRandomElement();

        float f = 1 + rand.nextInt(size) + 0.5F;
        for (BlockPos blockpos : BlockPos.betweenClosed(centerPos.offset(-2*size, -2*size, -2*size), centerPos.offset(2*size, 2*size, 2*size))) {
            if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)*0.3) {
                if (levelIn.getBlockState(blockpos).is(BlockTags.FEATURES_CANNOT_REPLACE)) continue;
                levelIn.setBlock(blockpos, coreMeteor.defaultBlockState(), 3);
            } else if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)) {
                if (levelIn.getBlockState(blockpos).is(BlockTags.FEATURES_CANNOT_REPLACE)) continue;
                levelIn.setBlock(blockpos, meteor.defaultBlockState(), 3);
            }
        }

        return coreMeteor;
    }

    protected static final Map<Block, Block> capsuleMap = Maps.newHashMap((new ImmutableMap.Builder())
            .put(APBlocks.METEORIC_ICE.get(), CapsuleBlocks.FROZEN_CAPSULE.getCapsule())
            .put(APBlocks.LONSDALEITE_MATRIX.get(), CapsuleBlocks.STUDDED_CAPSULE.getCapsule())
            .put(APBlocks.QUARTZ_MATRIX.get(), CapsuleBlocks.CRYSTALLINE_CAPSULE.getCapsule())
            .put(APBlocks.DEBRIS_MATRIX.get(), CapsuleBlocks.ANCIENT_CAPSULE.getCapsule())
            .put(APBlocks.GOLDEN_MATRIX.get(), CapsuleBlocks.GILDED_CAPSULE.getCapsule()).build());

    public void placeCapsule(WorldGenLevel levelIn, RandomSource rand, BlockPos posIn, Block coreBlock) {
        Block capsuleBlock = capsuleMap.containsKey(coreBlock) ? capsuleMap.get(coreBlock) : CapsuleBlocks.PLATED_CAPSULE.getCapsule();
        levelIn.setBlock(posIn, capsuleBlock.defaultBlockState(), 2);
        CapsuleBlockEntity.setLootTable(levelIn, rand, posIn, getLoottable());
    }

    protected double shortestDistance(BlockPos posIn, List<BlockPos> centerList, int size) {
        double minDist = size*size*size;
        for (BlockPos center : centerList) {
            minDist = Math.min(posIn.distSqr(center), minDist);
        }
        return minDist;
    }

    protected List<BlockPos> getCenters(RandomSource rand, BlockPos posIn, int size, int count) {
        List<BlockPos> list = new ArrayList<>();
        BlockPos pos = posIn.below(burrialDepth(rand));
        for (int i = 1; i <= count; i++) {
            list.add(pos.offset(rand.nextInt(3)-1, rand.nextInt(3)-1, rand.nextInt(3)-1));
        }
        return list;
    }

    protected BlockPos getCenterPos(List<BlockPos> centerList) {
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
