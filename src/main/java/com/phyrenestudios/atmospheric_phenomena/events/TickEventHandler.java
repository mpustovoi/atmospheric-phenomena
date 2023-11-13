package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.entities.MeteorEntity;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;

public class TickEventHandler {

    public static void levelTickEvent(TickEvent.LevelTickEvent event) {
        if (Config.meteorChance == 0) return;
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != LogicalSide.SERVER) return;
        ServerLevel level = (ServerLevel) event.level;
        //check if Overworld
        ServerChunkCache chunkCache = level.getChunkSource();
        for(ChunkHolder chunkholder : chunkCache.chunkMap.getChunks()) {
            if (level.getRandom().nextInt(Config.meteorChance) != 0) continue;
            LevelChunk levelchunk = chunkholder.getTickingChunk();
            if (levelchunk != null && level.shouldTickBlocksAt(levelchunk.getPos().toLong())) {
                int i = levelchunk.getPos().getMinBlockX();
                int j = levelchunk.getPos().getMinBlockZ();
                BlockPos blockpos = level.getBlockRandomPos(i, 0, j, 15).above(400);
                MeteorEntity meteor = new MeteorEntity(level, blockpos);
                meteor.setSize(level.getRandom().nextInt(300,700));
                level.addFreshEntity(meteor);
            }
        }

    }

}
