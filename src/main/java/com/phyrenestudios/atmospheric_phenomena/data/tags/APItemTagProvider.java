package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
        for (MeteorBlocks base : MeteorBlocks.values()) {
            tag(APTags.Items.METEORITE_BLOCKS).add(base.getMeteorBlock().asItem());
        }
        tag(ItemTags.TRIM_MATERIALS).add(APItems.LONSDALEITE.get(), APItems.METEORIC_IRON.get());
        tag(Tags.Items.GEMS_DIAMOND).add(APItems.LONSDALEITE.get());
        tag(Tags.Items.STORAGE_BLOCKS_DIAMOND).add(APItems.LONSDALEITE_BLOCK.get());
        copy(APTags.Blocks.CHARRED_LOGS, APTags.Items.CHARRED_LOGS);
        copy(APTags.Blocks.SMOULDERING_LOGS, APTags.Items.SMOULDERING_LOGS);
        copy(APTags.Blocks.BURNING_LOGS, APTags.Items.BURNING_LOGS);
        copy(APTags.Blocks.CAPSULES, APTags.Items.CAPSULES);
        tag(ItemTags.SIGNS).add(APItems.CHARRED_SIGN.get());
        tag(ItemTags.HANGING_SIGNS).add(APItems.CHARRED_HANGING_SIGN.get());
        tag(ItemTags.BOATS).add(APItems.CHARRED_BOAT.get());
        tag(ItemTags.CHEST_BOATS).add(APItems.CHARRED_CHEST_BOAT.get());
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(APTags.Blocks.LIGHTNING_GLASS, APTags.Items.LIGHTNING_GLASS);
        copy(APTags.Blocks.TEKTITES, APTags.Items.TEKTITES);
        copy(Tags.Blocks.GLASS, Tags.Items.GLASS);

    }
}
