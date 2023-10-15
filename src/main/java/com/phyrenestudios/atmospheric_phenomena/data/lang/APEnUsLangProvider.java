package com.phyrenestudios.atmospheric_phenomena.data.lang;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class APEnUsLangProvider extends LanguageProvider {
    private final String locale;
    public APEnUsLangProvider(PackOutput packOutput, String locale) {
        super(packOutput, AtmosphericPhenomena.MODID, locale);
        this.locale = locale;
    }

    @Override
    public String getName() {
        return AtmosphericPhenomena.MODID + ": " + locale;
    }

    @Override
    protected void addTranslations() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            add(base.getMeteorBlock());
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            add(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            add(base.getGlass());
        }
        add(APBlocks.KAMACITE.get());
        add(APBlocks.TAENITE.get());
        add(APBlocks.TETRATAENITE.get());
        add(APBlocks.GOLDEN_MATRIX.get());
        add(APBlocks.QUARTZ_MATRIX.get());
        add(APBlocks.CHARGED_QUARTZ_MATRIX.get());
        add(APBlocks.DEBRIS_MATRIX.get());
        add(APBlocks.LONSDALEITE_MATRIX.get());
        add(APBlocks.LONSDALEITE_BLOCK.get(), "Block of Lonsdaleite");
        add(APBlocks.METEORIC_ICE.get());
        add(APBlocks.SOIL_FULGURITE.get());
        add(APBlocks.SSTONE_FULGURITE.get());
        add(APItems.LONSDALEITE.get());
        add(APItems.METEORIC_IRON.get());
        add("itemGroup.atmospheric_phenomena.main", "Atmospheric Phenomena");
    }

    private void add(Block blockIn) {
        add(blockIn, parseLangName(ForgeRegistries.BLOCKS.getResourceKey(blockIn).get().location().getPath()));
    }
    private void add(Item itemIn) {
        add(itemIn, parseLangName(ForgeRegistries.ITEMS.getResourceKey(itemIn).get().location().getPath()));
    }

    private String parseLangName(String registryName) {
        String LangName = "";
        for (String s : registryName.split("_")) {
            if (LangName.equals("")) {
                LangName = s.substring(0,1).toUpperCase() + s.substring(1);
            } else {
                LangName = LangName + " " + s.substring(0,1).toUpperCase() + s.substring(1);
            }
        }
        return LangName;
    }

    private static String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
    private static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }
}
