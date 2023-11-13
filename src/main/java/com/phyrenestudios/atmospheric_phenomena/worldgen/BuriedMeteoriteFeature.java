package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.WeightedCollection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BuriedMeteoriteFeature extends Feature<NoneFeatureConfiguration> {
    public BuriedMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        WorldGenLevel levelIn = context.level();
        BlockPos posIn = context.origin();
        RandomSource rand = levelIn.getRandom();
        BlockState target = levelIn.getBlockState(posIn.below());
        if (!target.is(APTags.Blocks.VALID_METEORITE_SPAWN)) return false;

        int size = 2;

        List<BlockPos> centerList = getCenters(rand, posIn, size, 3);
        centerList = burriedPositions(centerList);
        buildMeteor(levelIn, rand, centerList, size);
        return true;
    }

    private void buildMeteor(WorldGenLevel levelIn, RandomSource rand, List<BlockPos> centerList, int size) {
        Block meteor = meteorBlockCollection().getRandomElement();
        Block coreMeteor;
        if (rand.nextFloat() < Config.solidCoreMeteoriteChance) {
            coreMeteor = meteor;
        } else {
            coreMeteor = meteorCoreBlockCollection().getRandomElement();
        }

        for (BlockPos center : centerList) {
            int j = 1 + rand.nextInt(size);
            int k = 1 + rand.nextInt(size);
            int l = 1 + rand.nextInt(size);
            float f = (float)(j + k + l) * 0.333F + 0.75F;
            center = center.below(size);
            for(BlockPos blockpos : BlockPos.betweenClosed(center.offset(-size, -size, -size), center.offset(size, size, size))) {
                if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)*0.3) {
                    levelIn.setBlock(blockpos.below(1), coreMeteor.defaultBlockState(), 3);
                } else if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)) {
                    levelIn.setBlock(blockpos.below(1), meteor.defaultBlockState(), 3);
                }
            }
        }

    }

    private WeightedCollection<Block> meteorBlockCollection() {
        WeightedCollection<Block> blockCollection = new WeightedCollection<>();

        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_BLOCKS)) {
            blockCollection.add(Config.meteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_BLOCKS)) {
            blockCollection.add(Config.rareMeteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_BLOCKS)) {
            blockCollection.add(Config.ultraRareMeteoriteChance, blk);
        }
        return blockCollection;
    }
    private WeightedCollection<Block> meteorCoreBlockCollection() {
        WeightedCollection<Block> blockCollection = new WeightedCollection<>();

        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_CORE_BLOCKS)) {
            blockCollection.add(Config.meteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS)) {
            blockCollection.add(Config.rareMeteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_CORE_BLOCKS)) {
            blockCollection.add(Config.ultraRareMeteoriteChance, blk);
        }
        return blockCollection;
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
        Direction dir1 = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
        Direction dir2 = rand.nextBoolean() ? Direction.EAST : Direction.WEST;
        Direction dir3 = rand.nextBoolean() ? Direction.UP : Direction.DOWN;

        //BlockPos endPos = posIn.offset(rand.nextInt(size*2)-size, rand.nextInt(size*2)-size, rand.nextInt(size*2)-size);
        //Vector3d path = new Vector3d(endPos.getX()-posIn.getX(), endPos.getY()-posIn.getY(), endPos.getZ()-posIn.getZ());

        for (int i = 1; i <= count; i++) {
            list.add(pos.relative(dir1,rand.nextInt(2)).relative(dir2,rand.nextInt(2)).relative(dir3,rand.nextInt(2)));
        }
        return list;
    }
    private List<BlockPos> burriedPositions(List<BlockPos> centers) {
        List<BlockPos> list = new ArrayList<>();
        for (BlockPos pos : centers) {
            list.add(pos.below(3));
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
