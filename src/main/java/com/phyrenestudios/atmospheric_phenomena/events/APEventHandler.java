package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.Config;
import com.phyrenestudios.atmospheric_phenomena.entities.MeteorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID)
public class APEventHandler {

    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinLevelEvent event) {
        EntityJoinWorldHandler.onLightningEvent(event);
    }

    @SubscribeEvent
    public static void test(TickEvent.LevelTickEvent event) {
        if (Config.meteorChance == 0) return;
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != LogicalSide.SERVER) return;
        ServerLevel level = (ServerLevel) event.level;
/*
        ServerChunkCache chunkCache = level.getChunkSource();
        for(ChunkHolder chunkholder : chunkCache.chunkMap.getChunks()) {

        }
        Iterable<ChunkHolder> chunks = chunkCache.chunkMap.getChunks();
        List<ChunkHolder> chunkListTest = new ArrayList<>();
        for (ChunkHolder cnk : level.getChunkSource().chunkMap.getChunks()) {
            chunkListTest.add(cnk);
        }

        if (chunkListTest.isEmpty()) return;

        List<ChunkHolder> chunkStream = StreamSupport.stream(chunks.spliterator(), false).collect(Collectors.toList());
        if (chunkStream.isEmpty()) return;

        ChunkHolder randomChunk = chunkListTest.get(level.getRandom().nextInt(chunkListTest.size()));


 */
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
