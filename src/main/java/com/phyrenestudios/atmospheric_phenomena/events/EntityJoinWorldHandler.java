package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public class EntityJoinWorldHandler {
    public static void onLightningEvent(EntityJoinLevelEvent event) {
        //if (!Config.GENERAL.LIGHTNING_CONVERSION.get()) return;
        if (!(event.getEntity() instanceof LightningBolt entity)) return;
        Level levelIn = event.getLevel();
        BlockPos startPos = entity.blockPosition().below();
        int size = levelIn.getRandom().nextInt(2,6);
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
            }
        }
    }

    private static BlockState getVitrifiedBlock(BlockState stateIn) {
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_GLASS)) return APBlocks.GOLDEN_MATRIX.get().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE)) return APBlocks.SOIL_FULGURITE.get().defaultBlockState();
        if (stateIn.is(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE)) return APBlocks.SSTONE_FULGURITE.get().defaultBlockState();
        return Blocks.AIR.defaultBlockState();
    }

}
