package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class APItemTagProvider extends ItemTagsProvider {
    public APItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blocks, ExistingFileHelper fileHelper) {
        super(output, registries, blocks, AtmosphericPhenomena.MODID, fileHelper);
    }

    public String getName() {
        return AtmosphericPhenomena.MODID + " - Item Tags ";
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.GEMS_DIAMOND).add(APItems.LONSDALEITE.get());
        tag(Tags.Items.STORAGE_BLOCKS_DIAMOND).add(APItems.LONSDALEITE_BLOCK.get());

        for (TektiteBlocks base : TektiteBlocks.values()) {
            tag(Tags.Items.GLASS).add(base.getTektite().asItem());
        }
    }
}
