package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class APCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AtmosphericPhenomena.MODID);


    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> AP_TAB = CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.atmospheric_phenomena.main"))
            .icon(() -> MeteorBlocks.CHONDRITE.getMeteorBlock().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (MeteorBlocks base : MeteorBlocks.values()) {
                    output.accept(base.getMeteorBlock()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                }
                output.accept(APItems.KAMACITE.get());
                output.accept(APItems.TAENITE.get());
                output.accept(APItems.TETRATAENITE.get());
                output.accept(APItems.METEORIC_ICE.get());
                output.accept(APItems.RAW_LONSDALEITE.get());
                output.accept(APItems.LONSDALEITE.get());
                output.accept(APItems.LONSDALEITE_BLOCK.get());
                output.accept(APItems.METEORIC_IRON.get());
            }).build());
}
