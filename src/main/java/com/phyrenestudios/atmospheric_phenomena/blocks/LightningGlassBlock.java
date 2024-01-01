package com.phyrenestudios.atmospheric_phenomena.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;

public class LightningGlassBlock extends TransparentBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty GLOWING = BooleanProperty.create("glowing");

    public LightningGlassBlock(Properties p_53640_) {
        super(p_53640_);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(GLOWING, Boolean.FALSE));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, GLOWING);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_55928_) {
        return this.defaultBlockState().setValue(AXIS, p_55928_.getClickedFace().getAxis());
    }

    @Override
    public InteractionResult use(BlockState stateIn, Level levelIn, BlockPos posIn, Player playerIn, InteractionHand handIn, BlockHitResult p_60508_) {
        if (!stateIn.getValue(GLOWING) && playerIn.getItemInHand(handIn).is(Tags.Items.DUSTS_GLOWSTONE)) {
            if (!playerIn.isCreative()) playerIn.getItemInHand(handIn).shrink(1);
            playerIn.swing(handIn);
            levelIn.setBlockAndUpdate(posIn, stateIn.setValue(GLOWING, true));
        }

        return super.use(stateIn, levelIn, posIn, playerIn, handIn, p_60508_);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.getValue(GLOWING)) {
            return 10;
        }
        return super.getLightEmission(state, level, pos);
    }

    public BlockState rotate(BlockState p_55930_, Rotation p_55931_) {
        return rotatePillar(p_55930_, p_55931_);
    }

    public static BlockState rotatePillar(BlockState p_154377_, Rotation p_154378_) {
        switch (p_154378_) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)p_154377_.getValue(AXIS)) {
                    case X:
                        return p_154377_.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                        return p_154377_.setValue(AXIS, Direction.Axis.X);
                    default:
                        return p_154377_;
                }
            default:
                return p_154377_;
        }
    }
}
