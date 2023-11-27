package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.entities.CometEntity;
import com.phyrenestudios.atmospheric_phenomena.entities.MeteorEntity;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;

public class TickEventHandler {

    public static void levelTickEvent(TickEvent.LevelTickEvent event) {
        if (Config.meteorChance == 0) return;
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != LogicalSide.SERVER) return;
        ServerLevel level = (ServerLevel) event.level;
        if (!level.dimension().location().toString().equals("minecraft:overworld")) return;
        RandomSource rand = level.getRandom();
        ServerChunkCache chunkCache = level.getChunkSource();
        for(ChunkHolder chunkholder : chunkCache.chunkMap.getChunks()) {
            LevelChunk levelchunk = chunkholder.getTickingChunk();
            if (levelchunk == null || !level.shouldTickBlocksAt(levelchunk.getPos().toLong())) return;
            if (rand.nextInt(Config.meteorChance) == 0) {
                spawnMeteor(level, chunkholder.getTickingChunk(), rand);
            } else if (rand.nextInt(Config.cometChance) == 0) {
                spawnComet(level, chunkholder.getTickingChunk(), rand);
            }
        }
    }

    private static void spawnMeteor(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldMeteorSpawnHeight, j, 1);
        MeteorEntity meteor = new MeteorEntity(level, blockpos);
        meteor.setSize(rand.nextInt(400,1000));
        meteor.setDeltaMovement(meteor.getRandomMotion(rand));
        level.addFreshEntity(meteor);
    }
    private static void spawnComet(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldMeteorSpawnHeight, j, 1);
        CometEntity comet = new CometEntity(level, blockpos);
        comet.setSize(rand.nextInt(200,500));
        comet.setDeltaMovement(comet.getRandomMotion(rand));
        level.addFreshEntity(comet);
    }


}
