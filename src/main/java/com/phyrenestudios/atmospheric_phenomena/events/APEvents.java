package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
        //FeatureUtils.populateBlockCollections();
    }
    @SubscribeEvent
    public static void breakSpeedEvent(PlayerEvent.BreakSpeed event) {
        BreakSpeedHandler.smithingModifiers(event);
    }

    @SubscribeEvent
    public static void itemAttributeModifierEvent(ItemAttributeModifierEvent event) {
        ItemAttributeModifierHandler.smithingModifiers(event);
    }

    @SubscribeEvent
    public static void itemTooltipEvent(ItemTooltipEvent event) {
        ItemTooltipHandler.smithingModifiers(event);
    }



}
