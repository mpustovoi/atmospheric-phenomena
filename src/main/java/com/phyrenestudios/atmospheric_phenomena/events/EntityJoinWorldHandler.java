package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EntityJoinWorldHandler {
    public static void onLightningEvent(EntityJoinLevelEvent event) {
        //if (!Config.GENERAL.LIGHTNING_CONVERSION.get()) return;
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof LightningBolt entity)) return;
        Level levelIn = event.getLevel();
        BlockPos startPos = entity.blockPosition().below();

        Set<BlockPos> conductiveBlocks = new HashSet<>();
        Stack<BlockPos> conductiveBlocksToCheck = new Stack<>();
        Set<BlockPos> explodableBlocks = new HashSet<>();
        Set<BlockPos> chargeableBlocks = new HashSet<>();
        Set<BlockPos> tntBlocks = new HashSet<>();

        int maxConductiveLength = 32;
        conductiveBlocksToCheck.add(startPos);
        while (!conductiveBlocksToCheck.isEmpty() && conductiveBlocks.size() < maxConductiveLength) {
            BlockPos cp = conductiveBlocksToCheck.pop();
            if (conductiveBlocks.contains(cp) || !isConductive(levelIn.getBlockState(cp))) continue;
            conductiveBlocks.add(cp);
            for (Direction dir : Direction.values()) {
                if (isExplodable(levelIn.getBlockState(cp.relative(dir)))) {
                    explodableBlocks.add(cp.relative(dir).immutable());
                    continue;
                }
                if (isChargeable(levelIn.getBlockState(cp.relative(dir)))) {
                    chargeableBlocks.add(cp.relative(dir).immutable());
                    continue;
                }
                if (levelIn.getBlockState(cp.relative(dir)).getBlock() instanceof TntBlock) {
                    tntBlocks.add(cp.relative(dir).immutable());
                    continue;
                }
                if (isConductive(levelIn.getBlockState(cp.relative(dir)))) conductiveBlocksToCheck.add(cp.relative(dir).immutable());
            }
        }

        Set<BlockPos> vitrifiedBlocks = new HashSet<>();
        Stack<BlockPos> toCheck = new Stack<>();
        toCheck.addAll(conductiveBlocks);
        toCheck.add(startPos);
        toCheck.add(startPos.north());
        toCheck.add(startPos.south());
        toCheck.add(startPos.east());
        toCheck.add(startPos.west());

        int maxSize = 80;
        while (!toCheck.isEmpty() && vitrifiedBlocks.size() < maxSize) {
            BlockPos cp = toCheck.pop();
            if (vitrifiedBlocks.contains(cp) || (!isConductive(levelIn.getBlockState(cp)) && !isVitrifyable(levelIn.getBlockState(cp)))) continue;
            if (isVitrifyable(levelIn.getBlockState(cp))) vitrifiedBlocks.add(cp);
            for (Direction dir : Direction.values()) {
                if (dir == Direction.UP) continue;
                float vitrificationChance = 0.2f + (dir == Direction.DOWN ? 0.1f : 0.0f);
                if (levelIn.getRandom().nextFloat() < vitrificationChance) toCheck.add(cp.relative(dir).immutable());
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
            if (!ForgeEventFactory.onExplosionStart(levelIn, explosion)) {
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
        return stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS) || stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE) || stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE);
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
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE)) return APBlocks.SSTONE_FULGURITE.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }
    private static BlockState getChargedBlock(BlockState stateIn) {
        if (stateIn.is(APBlocks.QUARTZ_MATRIX.get())) return APBlocks.CHARGED_QUARTZ_MATRIX.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }

}
