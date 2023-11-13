package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID)
public class APEvents {

    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinLevelEvent event) {
        EntityJoinWorldHandler.onLightningEvent(event);
    }

    @SubscribeEvent
    public static void tickEvent(TickEvent.LevelTickEvent event) {
        TickEventHandler.levelTickEvent(event);
    }

    @SubscribeEvent
    public static void test(OnDatapackSyncEvent event) {
        FeatureUtils.populateBlockCollections();
    }
}
