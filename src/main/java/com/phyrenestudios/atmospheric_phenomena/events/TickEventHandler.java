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
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.event.TickEvent;

public class TickEventHandler {

    public static void levelTickEvent(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != LogicalSide.SERVER) return;
        ServerLevel level = (ServerLevel) event.level;
        if (!level.dimension().location().toString().equals("minecraft:overworld")) return;
        RandomSource rand = level.getRandom();
        ServerChunkCache chunkCache = level.getChunkSource();
        for(ChunkHolder chunkholder : chunkCache.chunkMap.getChunks()) {
            LevelChunk levelchunk = chunkholder.getFullChunk();
            if (levelchunk == null /*|| !level.shouldTickBlocksAt(levelchunk.getPos().toLong())*/) continue;
            if (Config.overworldMeteorSpawnSettings.get(0) != 0 && rand.nextInt(Config.overworldMeteorSpawnSettings.get(0)) == 0) {
                spawnMeteor(level, levelchunk, rand);
            } else if (Config.overworldCometSpawnSettings.get(0) != 0 && rand.nextInt(Config.overworldCometSpawnSettings.get(0)) == 0) {
                spawnComet(level, levelchunk, rand);
            }
        }
    }

    private static void spawnMeteor(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldMeteorSpawnSettings.get(1), j, 0);
        MeteorEntity meteor = new MeteorEntity(level, blockpos);
        meteor.setSize(rand.nextInt(Config.overworldMeteorSpawnSettings.get(2),Config.overworldMeteorSpawnSettings.get(3)));
        meteor.setDeltaMovement(meteor.getRandomMotion(rand));
        level.addFreshEntity(meteor);
    }
    private static void spawnComet(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldCometSpawnSettings.get(1), j, 0);
        CometEntity comet = new CometEntity(level, blockpos);
        comet.setSize(rand.nextInt(Config.overworldCometSpawnSettings.get(2),Config.overworldCometSpawnSettings.get(3)));
        comet.setDeltaMovement(comet.getRandomMotion(rand));
        level.addFreshEntity(comet);
    }


}
