package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTables;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class CometFeature extends AbstractMeteoriteFeature {
    public CometFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
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
        return rand.nextInt(4)+2;
    }

    @Override
    ResourceLocation getLoottable() {
        return APLootTables.OVERWORLD_COMET;
    }

    @Override
    protected void setCraterBlock(WorldGenLevel levelIn, BlockPos posIn, Block glass, BlockState surface, BlockState groundmass, BlockPos centerPos, boolean override) {
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

    @Override
    protected Block buildMeteor(WorldGenLevel levelIn, RandomSource rand, List<BlockPos> centerList, BlockPos centerPos, int size) {
        Block meteor = APBlocks.METEORIC_ICE.get();
        for (BlockPos center : centerList) {
            int j = 1 + rand.nextInt(size);
            int k = 1 + rand.nextInt(size);
            int l = 1 + rand.nextInt(size);
            float f = (float)(j + k + l) * 0.333F + 0.75F;
            for(BlockPos blockpos : BlockPos.betweenClosed(center.offset(-size-1, -size-1, -size-1), center.offset(size+1, size+1, size+1))) {
                if (shortestDistance(blockpos, centerList, size) <= (double)(f * f)) {
                    levelIn.setBlock(blockpos, meteor.defaultBlockState(), 3);
                }
            }
        }
        return meteor;
    }

}
