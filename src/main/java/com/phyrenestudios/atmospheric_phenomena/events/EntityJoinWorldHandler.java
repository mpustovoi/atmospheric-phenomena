package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EntityJoinWorldHandler {
    public static void onLightningEvent(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof LightningBolt entity)) return;
        if (Config.lightningMaxBlocksConverted == 0) return;
        if (event.getLevel().isClientSide()) return;
        Level levelIn = event.getLevel();
        BlockPos startPos = entity.blockPosition().below();

        Set<BlockPos> conductiveBlocks = new HashSet<>();
        Stack<BlockPos> conductiveBlocksToCheck = new Stack<>();
        Stack<BlockPos> toCheck = new Stack<>();

        int maxConductiveLength = 32;
        conductiveBlocksToCheck.add(startPos);
        while (!conductiveBlocksToCheck.isEmpty() && conductiveBlocks.size() < maxConductiveLength) {
            BlockPos cp = conductiveBlocksToCheck.pop();
            if (conductiveBlocks.contains(cp)) continue;
            conductiveBlocks.add(cp);
            for (Direction dir : Direction.values()) {
                if (isConductive(levelIn.getBlockState(cp.relative(dir)))) {
                    conductiveBlocksToCheck.add(cp.relative(dir).immutable());
                } else {
                    toCheck.add(cp.relative(dir).immutable());
                }
            }
        }
        if (conductiveBlocks.size() > 1) {
            Player playerEnt = levelIn.getNearestPlayer(startPos.getX(), startPos.getY(), startPos.getZ(), 10D, false);
            if (playerEnt instanceof ServerPlayer) ((ServerPlayer) playerEnt).getAdvancements().award(((ServerLevel)levelIn).getServer().getAdvancements().get(new ResourceLocation(AtmosphericPhenomena.MODID,"conductive_line")), "conductive_line");
        }

        Set<BlockPos> vitrifiedBlocks = new HashSet<>();
        Set<BlockPos> explodableBlocks = new HashSet<>();
        Set<BlockPos> chargeableBlocks = new HashSet<>();
        Set<BlockPos> tntBlocks = new HashSet<>();

        toCheck.add(startPos);
        int convertedBlockCount = 0;
        while (!toCheck.isEmpty() && convertedBlockCount < Config.lightningMaxBlocksConverted) {
            BlockPos cp = toCheck.pop();
            boolean converted = false;
            if (!chargeableBlocks.contains(cp) && isChargeable(levelIn.getBlockState(cp))) {
                chargeableBlocks.add(cp.immutable());
                converted = true;
            } else if (!explodableBlocks.contains(cp) && isExplodable(levelIn.getBlockState(cp))) {
                explodableBlocks.add(cp.immutable());
                converted = true;
            } else if (!tntBlocks.contains(cp) && levelIn.getBlockState(cp).getBlock() instanceof TntBlock) {
                tntBlocks.add(cp.immutable());
                converted = true;
            } else if (!vitrifiedBlocks.contains(cp) && isVitrifyable(levelIn.getBlockState(cp))) {
                vitrifiedBlocks.add(cp.immutable());
                converted = true;
            }
            if (converted) {
                convertedBlockCount += 1;
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.UP) continue;
                    float vitrificationChance = 0.2f + (dir == Direction.DOWN ? 0.1f : 0.0f);
                    if (levelIn.getRandom().nextFloat() < vitrificationChance) toCheck.add(cp.relative(dir).immutable());
                }
            }
        }

        for (BlockPos pos : vitrifiedBlocks) {
            BlockState vitrifiedState = getVitrifiedBlock(levelIn.getBlockState(pos));
            if (!vitrifiedState.is(Blocks.AIR)) levelIn.setBlockAndUpdate(pos, vitrifiedState);
        }
        for (BlockPos pos : chargeableBlocks) {
            BlockState chargedState = getChargedBlock(levelIn.getBlockState(pos));
            if (!chargedState.is(Blocks.AIR)) levelIn.setBlockAndUpdate(pos, chargedState);
        }
        for (BlockPos pos : tntBlocks) {
            levelIn.getBlockState(pos).getBlock().onCaughtFire(levelIn.getBlockState(pos), levelIn, pos, Direction.UP, null);
            levelIn.removeBlock(pos, false);
        }
        for (BlockPos pos : explodableBlocks) {
            Explosion explosion = new Explosion(levelIn, null, pos.getX(), pos.getY(), pos.getZ(), 3f, false, Explosion.BlockInteraction.DESTROY);
            if (!EventHooks.onExplosionStart(levelIn, explosion)) {
                if (!levelIn.isClientSide) explosion.explode();
                explosion.finalizeExplosion(true);
                levelIn.removeBlock(pos, false);
            }
        }

    }

    private static boolean isConductive(BlockState stateIn) {
        return stateIn.is(APTags.Blocks.LIGHTNING_CONDUCTIVE);
    }
    private static boolean isVitrifyable(BlockState stateIn) {
        return stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS) || stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE) || stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE) || stateIn.is(BlockTags.LOGS_THAT_BURN);
    }
    private static boolean isExplodable(BlockState stateIn) {
        return stateIn.is(APTags.Blocks.EXPLODES_FROM_CONDUCTIVITY);
    }
    private static boolean isChargeable(BlockState stateIn) {
        return stateIn.is(APBlocks.QUARTZ_MATRIX.get());
    }

    private static BlockState getVitrifiedBlock(BlockState stateIn) {
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS)) return LightningGlassBlocks.WHITE_LIGHTNING_GLASS.getGlass().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE)) return APBlocks.SOIL_FULGURITE.get().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE)) return APBlocks.STONE_FULGURITE.get().defaultBlockState();
        if (stateIn.is(BlockTags.LOGS_THAT_BURN)) return APBlocks.BURNING_LOG.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }
    private static BlockState getChargedBlock(BlockState stateIn) {
        if (stateIn.is(APBlocks.QUARTZ_MATRIX.get())) return APBlocks.CHARGED_QUARTZ_MATRIX.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }

}
