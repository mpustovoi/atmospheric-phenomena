package com.phyrenestudios.atmospheric_phenomena.data.lang;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
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
            add(base.getMeteorBlock(), parseLangName(name(base.getMeteorBlock())));
        }
        add("itemGroup.atmoshperic_phenomena.atmospheric_phenomena_tab", "Atmospheric Phenomena");
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
