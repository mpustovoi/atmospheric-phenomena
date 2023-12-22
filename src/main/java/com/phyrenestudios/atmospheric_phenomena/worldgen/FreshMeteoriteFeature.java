package com.phyrenestudios.atmospheric_phenomena.worldgen;


import com.mojang.serialization.Codec;
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

public class FreshMeteoriteFeature extends AbstractMeteoriteFeature {
    public FreshMeteoriteFeature(Codec<NoneFeatureConfiguration> p_i49915_1_) {
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
        return APLootTables.OVERWORLD_METEOR;
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


}
