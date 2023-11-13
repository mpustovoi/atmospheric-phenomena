package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

public abstract class AbstractCharredLogBlock extends RotatedPillarBlock {
    private static Map<Block, Block> coolingMap;
    private static Map<Block, Block> heatingMap;

    public static void populateMaps() {
        coolingMap = Maps.newHashMap((new ImmutableMap.Builder())
                .put(APBlocks.BURNING_LOG.get(), APBlocks.SMOULDERING_LOG.get())
                .put(APBlocks.SMOULDERING_LOG.get(), APBlocks.CHARRED_LOG.get())
                .put(APBlocks.BURNING_WOOD.get(), APBlocks.SMOULDERING_WOOD.get())
                .put(APBlocks.SMOULDERING_WOOD.get(), APBlocks.CHARRED_WOOD.get()).build());

        heatingMap = Maps.newHashMap((new ImmutableMap.Builder())
                .put(APBlocks.SMOULDERING_LOG.get(), APBlocks.BURNING_LOG.get())
                .put(APBlocks.CHARRED_LOG.get(), APBlocks.SMOULDERING_LOG.get())
                .put(APBlocks.SMOULDERING_WOOD.get(), APBlocks.BURNING_WOOD.get())
                .put(APBlocks.CHARRED_WOOD.get(), APBlocks.SMOULDERING_WOOD.get())
                .put(APBlocks.STRIPPED_CHARRED_LOG.get(), APBlocks.SMOULDERING_LOG.get())
                .put(APBlocks.STRIPPED_CHARRED_WOOD.get(), APBlocks.SMOULDERING_WOOD.get()).build());
    }

    public AbstractCharredLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState stateIn, Level levelIn, BlockPos posIn, Player playerIn, InteractionHand handIn, BlockHitResult p_60508_) {

        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if (itemstack.is(Items.FLINT_AND_STEEL) && heatingMap.containsKey(stateIn.getBlock())) {
            levelIn.setBlockAndUpdate(posIn, heatingMap.get(stateIn.getBlock()).withPropertiesOf(stateIn));
            levelIn.playSound(playerIn, posIn, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, levelIn.getRandom().nextFloat() * 0.4F + 0.8F);
            levelIn.gameEvent(playerIn, GameEvent.BLOCK_PLACE, posIn);
            if (playerIn instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerIn, posIn, itemstack);
                itemstack.hurtAndBreak(1, playerIn, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(handIn);
                });
            }
            return InteractionResult.SUCCESS;
        }
        if (itemstack.getItem() instanceof ShovelItem && coolingMap.containsKey(stateIn.getBlock())) {
            levelIn.setBlockAndUpdate(posIn, coolingMap.get(stateIn.getBlock()).withPropertiesOf(stateIn));
            levelIn.playSound(playerIn, posIn, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, levelIn.getRandom().nextFloat() * 0.4F + 0.8F);
            levelIn.gameEvent(playerIn, GameEvent.BLOCK_PLACE, posIn);
            if (playerIn instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerIn, posIn, itemstack);
                itemstack.hurtAndBreak(1, playerIn, (p_41300_) -> {
                    p_41300_.broadcastBreakEvent(handIn);
                });
            }
            return InteractionResult.SUCCESS;
        }

        return super.use(stateIn, levelIn, posIn, playerIn, handIn, p_60508_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_49921_) {
        return true;
    }

    @Override
    public void randomTick(BlockState stateIn, ServerLevel levelIn, BlockPos posIn, RandomSource rand) {
        super.randomTick(stateIn, levelIn, posIn, rand);
        if (isHeated(levelIn, posIn)) {
            if (heatingMap.containsKey(stateIn.getBlock())) {
                levelIn.setBlockAndUpdate(posIn, heatingMap.get(stateIn.getBlock()).withPropertiesOf(stateIn));
                return;
            }
        } else {
            if (coolingMap.containsKey(stateIn.getBlock()) && (rand.nextFloat() < 0.4 || levelIn.isRainingAt(posIn))) {
                levelIn.setBlockAndUpdate(posIn, coolingMap.get(stateIn.getBlock()).withPropertiesOf(stateIn));
                return;
            }
        }

        if (spawnFire()) {
            for (Direction dir : Direction.values()) {
                if (dir.equals(Direction.DOWN)) continue;
                if (rand.nextFloat() < Config.burningLogSpawnFire && BaseFireBlock.canBePlacedAt(levelIn, posIn.relative(dir), dir)) {
                    levelIn.setBlock(posIn.relative(dir), BaseFireBlock.getState(levelIn, posIn.relative(dir)), 11);
                    return;
                }
            }
        }
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState stateIn, Entity entityIn) {
        if (levelIn.getRandom().nextFloat() < damageChance() && !entityIn.isSteppingCarefully() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn)) {
            entityIn.hurt(levelIn.damageSources().hotFloor(), 0.5F);
        }
        super.stepOn(levelIn, posIn, stateIn, entityIn);
    }

    abstract boolean spawnFire();
    abstract float damageChance();

    private boolean isHeated(ServerLevel levelIn, BlockPos posIn) {
        for (Direction dir : Direction.values()) {
            if (levelIn.getBlockState(posIn.relative(dir)).is(Blocks.MAGMA_BLOCK)) return true;
        }
        return false;
    }

}
