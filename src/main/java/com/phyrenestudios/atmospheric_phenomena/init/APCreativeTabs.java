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
                    output.accept(base.getBricks());
                    output.accept(base.getBricksSlab());
                    output.accept(base.getBricksStairs());
                    output.accept(base.getBricksWall());
                    output.accept(base.getChiseled());
                }
                output.accept(APItems.KAMACITE.get());
                output.accept(APItems.TAENITE.get());
                output.accept(APItems.TETRATAENITE.get());
                for (TektiteBlocks base : TektiteBlocks.values()) {
                    output.accept(base.getTektite());
                }
                output.accept(APItems.METEORIC_ICE.get());
                output.accept(APItems.QUARTZ_MATRIX.get());
                output.accept(APItems.CHARGED_QUARTZ_MATRIX.get());
                output.accept(APItems.GOLDEN_MATRIX.get());
                output.accept(APItems.LONSDALEITE_MATRIX.get());
                output.accept(APItems.DEBRIS_MATRIX.get());
                output.accept(APItems.STONE_FULGURITE.get());
                output.accept(APItems.SOIL_FULGURITE.get());

                for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
                    output.accept(base.getGlass());
                }
                output.accept(APItems.METEORIC_IRON.get());
                output.accept(APItems.METEORIC_IRON_BLOCK.get());
                output.accept(APItems.LONSDALEITE.get());
                output.accept(APItems.LONSDALEITE_BLOCK.get());

                output.accept(APItems.BURNING_LOG.get());
                output.accept(APItems.BURNING_WOOD.get());
                output.accept(APItems.SMOULDERING_LOG.get());
                output.accept(APItems.SMOULDERING_WOOD.get());
                output.accept(APItems.CHARRED_LOG.get());
                output.accept(APItems.STRIPPED_CHARRED_LOG.get());
                output.accept(APItems.CHARRED_WOOD.get());
                output.accept(APItems.STRIPPED_CHARRED_WOOD.get());
                output.accept(APItems.CHARRED_PLANKS.get());
                output.accept(APItems.CHARRED_STAIRS.get());
                output.accept(APItems.CHARRED_SLAB.get());
                output.accept(APItems.CHARRED_FENCE.get());
                output.accept(APItems.CHARRED_FENCE_GATE.get());
                output.accept(APItems.CHARRED_DOOR.get());
                output.accept(APItems.CHARRED_TRAPDOOR.get());
                output.accept(APItems.CHARRED_PRESSURE_PLATE.get());
                output.accept(APItems.CHARRED_BUTTON.get());
                output.accept(APItems.CHARRED_SIGN.get());
                output.accept(APItems.CHARRED_HANGING_SIGN.get());
                output.accept(APItems.CHARRED_BOOKSHELF.get());
                output.accept(APItems.CHARRED_BOAT.get());
                output.accept(APItems.CHARRED_CHEST_BOAT.get());
                output.accept(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get());


            }).build());
}
