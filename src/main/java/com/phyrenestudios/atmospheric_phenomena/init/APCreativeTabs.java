package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class APCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AtmosphericPhenomena.MODID);

    public static final RegistryObject<CreativeModeTab> AP_TAB = CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.atmospheric_phenomena.main"))
            .icon(() -> MeteorBlocks.CHONDRITE.getMeteorBlock().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (MeteorBlocks base : MeteorBlocks.values()) {
                    output.accept(base.getMeteorBlock());
                }
                output.accept(APItems.KAMACITE.get());
                output.accept(APItems.TAENITE.get());
                output.accept(APItems.TETRATAENITE.get());
                for (TektiteBlocks base : TektiteBlocks.values()) {
                    output.accept(base.getTektite());
                }
                output.accept(APItems.METEORIC_ICE.get());
                output.accept(APItems.QUARTZ_MATRIX.get());
                output.accept(APItems.GOLDEN_MATRIX.get());
                output.accept(APItems.LONSDALEITE_MATRIX.get());
                output.accept(APItems.DEBRIS_MATRIX.get());
                output.accept(APItems.SSTONE_FULGURITE.get());
                output.accept(APItems.SOIL_FULGURITE.get());

                for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
                    output.accept(base.getGlass());
                }
                output.accept(APItems.LONSDALEITE.get());
                output.accept(APItems.LONSDALEITE_BLOCK.get());
                output.accept(APItems.METEORIC_IRON.get());
            }).build());
}
