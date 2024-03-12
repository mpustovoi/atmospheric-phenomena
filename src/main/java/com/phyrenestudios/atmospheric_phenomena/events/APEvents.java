package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID)
public class APEvents {

    /*
    @SubscribeEvent
    public static void entityJoinWorldEvent(PlayerInteractEvent.RightClickItem event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getItemStack().is(Items.COMPASS)) {
            event.getEntity().sendSystemMessage(Component.literal("Meteor Time: " + event.getLevel().getData(APAttachmentTypes.METEOR_COUNTDOWN)));
            event.getEntity().sendSystemMessage(Component.literal("Comet Time: " + event.getLevel().getData(APAttachmentTypes.COMET_COUNTDOWN)));
        }
    }

     */

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
