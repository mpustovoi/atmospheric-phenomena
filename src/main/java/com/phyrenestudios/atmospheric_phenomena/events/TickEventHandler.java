package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.entities.CometEntity;
import com.phyrenestudios.atmospheric_phenomena.entities.MeteorEntity;
import com.phyrenestudios.atmospheric_phenomena.init.APAttachmentTypes;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.event.TickEvent;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TickEventHandler {

    public static void levelTickEvent(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != LogicalSide.SERVER) return;
        ServerLevel level = (ServerLevel) event.level;
        if (!level.dimension().location().toString().equals("minecraft:overworld")) return;

        if (Config.overworldMeteorSpawnSettings.get(0) != 0) {
            RandomSource rand = level.getRandom();
            level.setData(APAttachmentTypes.METEOR_COUNTDOWN, level.getData(APAttachmentTypes.METEOR_COUNTDOWN)-1);
            if (level.getData(APAttachmentTypes.METEOR_COUNTDOWN) <= 0) {
                ServerChunkCache chunkCache = level.getChunkSource();
                List<LevelChunk> chunkList = StreamSupport.stream(chunkCache.chunkMap.getChunks().spliterator(),false).map((chunkHolder -> chunkHolder.getFullChunk())).filter(Objects::nonNull).filter(levelChunk -> level.shouldTickBlocksAt(levelChunk.getPos().toLong())).collect(Collectors.toList());
                LevelChunk targetChunk = chunkList.get(rand.nextInt(chunkList.size()-1));
                spawnMeteor(level, targetChunk, rand);
                level.setData(APAttachmentTypes.METEOR_COUNTDOWN,1200 * rand.nextInt(Config.overworldMeteorSpawnSettings.get(0)-Config.overworldMeteorSpawnSettings.get(1), Config.overworldMeteorSpawnSettings.get(0)+Config.overworldMeteorSpawnSettings.get(1)));
            }
        }

        if (Config.overworldCometSpawnSettings.get(0) != 0) {
            RandomSource rand = level.getRandom();
            level.setData(APAttachmentTypes.COMET_COUNTDOWN, level.getData(APAttachmentTypes.COMET_COUNTDOWN)-1);
            if (level.getData(APAttachmentTypes.COMET_COUNTDOWN) <= 0) {
                ServerChunkCache chunkCache = level.getChunkSource();
                List<LevelChunk> chunkList = StreamSupport.stream(chunkCache.chunkMap.getChunks().spliterator(),false).map((chunkHolder -> chunkHolder.getFullChunk())).filter(Objects::nonNull).filter(levelChunk -> level.shouldTickBlocksAt(levelChunk.getPos().toLong())).collect(Collectors.toList());
                LevelChunk targetChunk = chunkList.get(rand.nextInt(chunkList.size()-1));
                spawnComet(level, targetChunk, rand);
                level.setData(APAttachmentTypes.COMET_COUNTDOWN,1200 * rand.nextInt(Config.overworldCometSpawnSettings.get(0)-Config.overworldCometSpawnSettings.get(1), Config.overworldCometSpawnSettings.get(0)+Config.overworldCometSpawnSettings.get(1)));
            }
       }


    }

    private static void spawnMeteor(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldMeteorSpawnSettings.get(2), j, 0);
        MeteorEntity meteor = new MeteorEntity(level, blockpos);
        meteor.setSize(rand.nextInt(Config.overworldMeteorSpawnSettings.get(3),Config.overworldMeteorSpawnSettings.get(4)));
        meteor.setDeltaMovement(meteor.getRandomMotion(rand));
        level.addFreshEntity(meteor);
    }
    private static void spawnComet(ServerLevel level, LevelChunk levelchunk, RandomSource rand) {
        int i = levelchunk.getPos().getMinBlockX();
        int j = levelchunk.getPos().getMinBlockZ();
        BlockPos blockpos = level.getBlockRandomPos(i, Config.overworldCometSpawnSettings.get(2), j, 0);
        CometEntity comet = new CometEntity(level, blockpos);
        comet.setSize(rand.nextInt(Config.overworldCometSpawnSettings.get(3),Config.overworldCometSpawnSettings.get(4)));
        comet.setDeltaMovement(comet.getRandomMotion(rand));
        level.addFreshEntity(comet);
    }


}
