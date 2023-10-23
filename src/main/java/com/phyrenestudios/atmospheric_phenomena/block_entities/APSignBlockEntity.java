package com.phyrenestudios.atmospheric_phenomena.block_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class APSignBlockEntity extends SignBlockEntity {

    public APSignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super( p_155700_, p_155701_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return APBlockEntities.AP_SIGN.get();
    }

}
