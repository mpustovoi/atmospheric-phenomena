package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class APBlockTagsProvider extends BlockTagsProvider {
    public APBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, AtmosphericPhenomena.MODID, fileHelper);
    }

    public String getName() {
        return AtmosphericPhenomena.MODID + " - Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getMeteorBlock());
            tag(BlockTags.NEEDS_IRON_TOOL).add(base.getMeteorBlock());
            tag(APTags.Blocks.METEOR_BLOCKS).add(base.getMeteorBlock());
        }
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), APBlocks.RAW_LONSDALEITE.get(), APBlocks.LONSDALEITE_BLOCK.get(), APBlocks.METEORIC_ICE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(APBlocks.RAW_LONSDALEITE.get());
        tag(APTags.Blocks.RARE_METEOR_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get()).addTag(Tags.Blocks.OBSIDIAN);
        tag(APTags.Blocks.METEOR_CORE_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get());
        tag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS).add(APBlocks.RAW_LONSDALEITE.get(), APBlocks.METEORIC_ICE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND).add(APBlocks.LONSDALEITE_BLOCK.get());
        tag(BlockTags.ICE).add(APBlocks.METEORIC_ICE.get());
        tag(APTags.Blocks.VALID_METEORITE_SPAWN).addTags(Tags.Blocks.STONE,BlockTags.DIRT,BlockTags.SNOW,BlockTags.SAND,Tags.Blocks.SANDSTONE,Tags.Blocks.OBSIDIAN,Tags.Blocks.GRAVEL,Tags.Blocks.COBBLESTONE,Tags.Blocks.ORES,BlockTags.TERRACOTTA).add(Blocks.DIRT_PATH, Blocks.CLAY);

    }
}
