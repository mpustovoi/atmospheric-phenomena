package com.phyrenestudios.atmospheric_phenomena.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ChiseledMeteoriteBlock extends Block {
    public static final IntegerProperty PHASE = IntegerProperty.create("phase", 0, 7);
    public static final BooleanProperty PHASE_LOCKED = BooleanProperty.create("phase_locked");

    public ChiseledMeteoriteBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(PHASE, 1).setValue(PHASE_LOCKED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PHASE, PHASE_LOCKED);
        //super.createBlockStateDefinition(builder);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(PHASE, context.getLevel().getMoonPhase());
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState stateIn, ServerLevel levelIn, BlockPos posIn, RandomSource random) {
        if (stateIn.getValue(PHASE_LOCKED)) return;
        if (levelIn.getMoonPhase() == stateIn.getValue(PHASE)) return;
        levelIn.setBlockAndUpdate(posIn, stateIn.setValue(PHASE, (levelIn.getMoonPhase())));
        super.randomTick(stateIn, levelIn, posIn, random);
    }

    @Override
    public InteractionResult use(BlockState stateIn, Level levelIn, BlockPos posIn, Player playerIn, InteractionHand handIn, BlockHitResult p_60508_) {
        if (playerIn.getItemInHand(handIn).is(Items.CLOCK)) {
            levelIn.setBlockAndUpdate(posIn, stateIn.cycle(PHASE_LOCKED));
            return InteractionResult.sidedSuccess(levelIn.isClientSide);
        }
        return super.use(stateIn, levelIn, posIn, playerIn, handIn, p_60508_);
    }
}
