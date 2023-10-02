package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

        int maxConductiveLength = 32;
        conductiveBlocksToCheck.add(startPos);
        while (!conductiveBlocksToCheck.isEmpty() && conductiveBlocks.size() < maxConductiveLength) {
            BlockPos cp = conductiveBlocksToCheck.pop();
            if (conductiveBlocks.contains(cp) || !isConductive(levelIn.getBlockState(cp))) continue;
            conductiveBlocks.add(cp);
            for (Direction dir : Direction.values()) {
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
            if (!vitrifiedState.is(Blocks.AIR)) {
                levelIn.setBlockAndUpdate(pos, vitrifiedState);
            }
        }

        /*
        Iterable<BlockPos> positions = BlockPos.withinManhattan(startPos.below(size),size,size,size);
        for (BlockPos pos : positions) {
            double rand;
            if (startPos.getX() == pos.getX() && startPos.getZ() == pos.getZ()) {
                rand = 1/(1f + Math.abs(startPos.getY() - pos.getY()));
            } else {
                rand = pos.distSqr(new Vec3i(startPos.getX(),startPos.getY(),startPos.getZ()));
            }

            BlockState BLK = levelIn.getBlockState(pos);
            if (levelIn.getRandom().nextFloat() > 1/rand) continue;
            BlockState vitrifiedState = getVitrifiedBlock(BLK);
            if (!vitrifiedState.is(Blocks.AIR)) {
                levelIn.setBlock(pos, vitrifiedState,3);
            } else {
                levelIn.setBlock(pos, vitrifiedState,3);
            }
        }

         */
    }

    private static boolean isConductive(BlockState stateIn) {
        return stateIn.is(APTags.Blocks.LIGHTNING_CONDUCTIVE);
    }
    private static boolean isVitrifyable(BlockState stateIn) {
        return stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS) || stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE) || stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE);
    }

    private static BlockState getVitrifiedBlock(BlockState stateIn) {
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS)) return LightningGlassBlocks.WHITE_LIGHTNING_GLASS.getGlass().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE)) return APBlocks.SOIL_FULGURITE.get().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE)) return APBlocks.SSTONE_FULGURITE.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }

}
