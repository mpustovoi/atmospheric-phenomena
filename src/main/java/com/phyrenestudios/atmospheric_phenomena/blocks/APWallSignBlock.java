package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.block_entities.APSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class APWallSignBlock extends WallSignBlock {

    public APWallSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return new APSignBlockEntity(p_154556_, p_154557_);
    }

}
