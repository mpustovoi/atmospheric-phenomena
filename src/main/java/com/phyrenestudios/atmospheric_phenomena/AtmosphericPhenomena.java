package com.phyrenestudios.atmospheric_phenomena;

import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import com.phyrenestudios.atmospheric_phenomena.block_entities.APBlockEntities;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.AbstractCharredLogBlock;
import com.phyrenestudios.atmospheric_phenomena.client.model.CometModel;
import com.phyrenestudios.atmospheric_phenomena.client.model.MeteorModel;
import com.phyrenestudios.atmospheric_phenomena.client.renderer.entity.APBoatRenderer;
import com.phyrenestudios.atmospheric_phenomena.client.renderer.entity.CometRenderer;
import com.phyrenestudios.atmospheric_phenomena.client.renderer.entity.MeteorRenderer;
import com.phyrenestudios.atmospheric_phenomena.entities.APBoat;
import com.phyrenestudios.atmospheric_phenomena.entities.APEntityTypes;
import com.phyrenestudios.atmospheric_phenomena.init.*;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import com.phyrenestudios.atmospheric_phenomena.recipe.APRecipeSerializers;
import com.phyrenestudios.atmospheric_phenomena.recipe.APRecipeTypes;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APFeatures;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AtmosphericPhenomena.MODID)
public class AtmosphericPhenomena {
    public static final String MODID = "atmospheric_phenomena";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AtmosphericPhenomena() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        APBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        APBlocks.BLOCKS.register(modEventBus);
        APCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        APEntityTypes.ENTITY_TYPES.register(modEventBus);
        APFeatures.FEATURES.register(modEventBus);
        APItems.ITEMS.register(modEventBus);
        APParticleTypes.PARTICLE_TYPES.register(modEventBus);
        APRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        APRecipeTypes.RECIPE_TYPES.register(modEventBus);
        APSoundEvents.SOUNDS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        WoodType.register(APWoodTypes.CHARRED);
        AbstractCharredLogBlock.populateMaps();
        event.enqueueWork(() -> {
            APGameRules.initializeGamerules();

            AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
            AxeItem.STRIPPABLES.put(APBlocks.CHARRED_LOG.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
            AxeItem.STRIPPABLES.put(APBlocks.CHARRED_WOOD.get(), APBlocks.STRIPPED_CHARRED_WOOD.get());
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                    Sheets.addWoodType(APWoodTypes.CHARRED);
                    APBlockEntities.registerBlockEntityRenders();
            });
        }
    }


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(APEntityTypes.METEOR.get(), MeteorRenderer::new);
            event.registerEntityRenderer(APEntityTypes.COMET.get(), CometRenderer::new);
            event.registerEntityRenderer(APEntityTypes.AP_BOAT.get(), m -> new APBoatRenderer(m, false));
            event.registerEntityRenderer(APEntityTypes.AP_CHEST_BOAT.get(), m -> new APBoatRenderer(m, true));
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(MeteorModel.LAYER_LOCATION, MeteorModel::createBodyLayer);
            event.registerLayerDefinition(CometModel.LAYER_LOCATION, CometModel::createBodyLayer);
            for (APBoat.Type boatType : APBoat.Type.values()) {
                event.registerLayerDefinition(APBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
                event.registerLayerDefinition(APBoatRenderer.createChestBoatModelName(boatType), ChestBoatModel::createBodyModel);
            }
        }

        @SubscribeEvent
        public static void entityAttributes(EntityAttributeCreationEvent event) {

        }

    }

}
