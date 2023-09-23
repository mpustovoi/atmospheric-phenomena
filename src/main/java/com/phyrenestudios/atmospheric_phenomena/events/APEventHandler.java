package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID)
public class APEventHandler {

    @SubscribeEvent
    public static void entityJoinWorldEvent(EntityJoinLevelEvent event) {
        EntityJoinWorldHandler.onLightningEvent(event);
    }
}
