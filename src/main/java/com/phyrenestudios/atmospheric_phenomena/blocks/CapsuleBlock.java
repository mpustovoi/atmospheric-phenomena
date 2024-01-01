package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.mojang.serialization.MapCodec;
import com.phyrenestudios.atmospheric_phenomena.block_entities.APBlockEntities;
import com.phyrenestudios.atmospheric_phenomena.block_entities.CapsuleBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CapsuleBlock extends BaseEntityBlock {
    public static final MapCodec<CapsuleBlock> CODEC = simpleCodec(CapsuleBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");
    protected CapsuleBlock(Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos posIn, BlockState stateIn) {
        return new CapsuleBlockEntity(posIn, stateIn);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55933_) {
        p_55933_.add(AXIS);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return this.defaultBlockState().setValue(AXIS, p_49820_.getClickedFace().getAxis());
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
        if (blockentity instanceof CapsuleBlockEntity capsuleBlockEntity) {
            //Containers.dropContents(worldIn, pos, capsuleBlockEntity);
            worldIn.updateNeighbourForOutputSignal(pos, this);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);

    }

    @Override
    public BlockState playerWillDestroy(Level p_56212_, BlockPos p_56213_, BlockState stateIn, Player p_56215_) {
        BlockEntity blockentity = p_56212_.getBlockEntity(p_56213_);
        if (blockentity instanceof CapsuleBlockEntity capsuleBlockEntity) {
            if (!p_56212_.isClientSide && p_56215_.isCreative() && !capsuleBlockEntity.isEmpty()) {
                ItemStack itemstack = new ItemStack(stateIn.getBlock());
                blockentity.saveToItem(itemstack);
                if (capsuleBlockEntity.hasCustomName()) {
                    itemstack.setHoverName(capsuleBlockEntity.getCustomName());
                }
                ItemEntity itementity = new ItemEntity(p_56212_, (double)p_56213_.getX() + 0.5D, (double)p_56213_.getY() + 0.5D, (double)p_56213_.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                p_56212_.addFreshEntity(itementity);
            } else {
                capsuleBlockEntity.unpackLootTable(p_56215_);
            }
        }

        return super.playerWillDestroy(p_56212_, p_56213_, stateIn, p_56215_);
    }

    public void setPlacedBy(Level p_56206_, BlockPos p_56207_, BlockState p_56208_, LivingEntity p_56209_, ItemStack p_56210_) {
        if (p_56210_.hasCustomHoverName()) {
            BlockEntity blockentity = p_56206_.getBlockEntity(p_56207_);
            if (blockentity instanceof CapsuleBlockEntity) {
                ((CapsuleBlockEntity)blockentity).setCustomName(p_56210_.getHoverName());
            }
        }

    }

    public List<ItemStack> getDrops(BlockState p_287632_, LootParams.Builder p_287691_) {
        BlockEntity blockentity = p_287691_.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof CapsuleBlockEntity capsuleBlockEntity) {
            p_287691_ = p_287691_.withDynamicDrop(CONTENTS, (p_56219_) -> {
                for(int i = 0; i < capsuleBlockEntity.getContainerSize(); ++i) {
                    p_56219_.accept(capsuleBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(p_287632_, p_287691_);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader p_304539_, BlockPos p_56203_, BlockState p_56204_) {
        ItemStack itemstack = super.getCloneItemStack(p_304539_, p_56203_, p_56204_);
        p_304539_.getBlockEntity(p_56203_, APBlockEntities.CAPSULE.get()).ifPresent(p_187446_ -> p_187446_.saveToItem(itemstack));
        return itemstack;
    }

    public void appendHoverText(ItemStack p_56193_, @javax.annotation.Nullable BlockGetter p_56194_, List<Component> p_56195_, TooltipFlag p_56196_) {
        super.appendHoverText(p_56193_, p_56194_, p_56195_, p_56196_);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(p_56193_);
        if (compoundtag != null) {
            if (compoundtag.contains("LootTable", 8)) {
                p_56195_.add(Component.literal("???????"));
            }

            if (compoundtag.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundtag, nonnulllist);

                boolean flagFilled = false;
                for (ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        flagFilled = true;
                        break;
                    }
                }
                if (flagFilled) {
                    p_56195_.add(Component.translatable("container.capsule.filled").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
                }
            }
        }

    }

    @Override
    public @Nullable PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
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
