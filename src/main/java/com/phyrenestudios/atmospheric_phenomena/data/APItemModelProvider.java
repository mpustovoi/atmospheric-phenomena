package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class APItemModelProvider extends ItemModelProvider {

    public APItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, AtmosphericPhenomena.MODID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return AtmosphericPhenomena.MODID + " - Item Models";
    }

    protected void registerModels() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            withExistingParent(base.getMeteorBlock());
        }
    }

    private ItemModelBuilder withExistingParent(Block blk) {
        return withExistingParent(name(blk), modLoc("block/" + name(blk)));
    }

    private static ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private static ResourceLocation key(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }
    private static String name(Block itemLike) {
        return key(itemLike).getPath();
    }
    private static String name(Block itemLike, String suffix) {
        return key(itemLike).getPath() + suffix;
    }
    private static String name(String prefix, Block itemLike) {
        return prefix + key(itemLike).getPath();
    }
    private static String name(String prefix, Block itemLike, String suffix) {
        return prefix + key(itemLike).getPath() + suffix;
    }
    private static String name(Item itemLike) {
        return key(itemLike).getPath();
    }
    private static String name(Item itemLike, String suffix) {
        return key(itemLike).getPath() + suffix;
    }
    private static String name(String prefix, Item itemLike) {
        return prefix + key(itemLike).getPath();
    }
    private static String name(String prefix, Item itemLike, String suffix) {
        return prefix + key(itemLike).getPath() + suffix;
    }
}
