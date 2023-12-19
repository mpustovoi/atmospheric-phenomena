package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.block_entities.MeteorCrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MeteorCrateBlock extends BaseEntityBlock {

    protected MeteorCrateBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos posIn, BlockState stateIn) {
        return new MeteorCrateBlockEntity(posIn, stateIn);
    }

    public boolean hasAnalogOutputSignal(BlockState p_56221_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_56223_, Level p_56224_, BlockPos p_56225_) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container)p_56224_.getBlockEntity(p_56225_));
    }

    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.is(newState.getBlock())) return;
        BlockEntity blockentity = worldIn.getBlockEntity(pos);
        if (blockentity instanceof MeteorCrateBlockEntity meteorCrateBlockEntity) {
            Containers.dropContents(worldIn, pos, meteorCrateBlockEntity);
            worldIn.updateNeighbourForOutputSignal(pos, this);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);

    }
}
