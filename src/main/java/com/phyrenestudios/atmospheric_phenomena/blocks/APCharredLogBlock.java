package com.phyrenestudios.atmospheric_phenomena.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class APCharredLogBlock extends AbstractCharredLogBlock {

    public APCharredLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    boolean spawnFire() {
        return false;
    }

    @Override
    float damageChance() {
        return 0.0f;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }
}
