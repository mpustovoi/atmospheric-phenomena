package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
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
        for (TektiteBlocks base : TektiteBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getTektite());
            tag(BlockTags.NEEDS_IRON_TOOL).add(base.getTektite());
            tag(Tags.Blocks.GLASS).add(base.getTektite());
            tag(APTags.Blocks.METEORITE_STREWN_BLOCKS).add(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(base.getGlass());
            tag(APTags.Blocks.LIGHTNING_GLASS).add(base.getGlass());
            tag(Tags.Blocks.GLASS).add(base.getGlass());
        }
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), APBlocks.GOLDEN_MATRIX.get(), APBlocks.LONSDALEITE_MATRIX.get(), APBlocks.LONSDALEITE_BLOCK.get(), APBlocks.METEORIC_ICE.get(), APBlocks.SOIL_FULGURITE.get(), APBlocks.SSTONE_FULGURITE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), APBlocks.GOLDEN_MATRIX.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(APBlocks.LONSDALEITE_MATRIX.get());
        tag(APTags.Blocks.RARE_METEOR_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get(), Blocks.CRYING_OBSIDIAN).addTag(Tags.Blocks.OBSIDIAN);
        tag(APTags.Blocks.METEOR_CORE_BLOCKS).add(APBlocks.KAMACITE.get(), APBlocks.TAENITE.get(), APBlocks.TETRATAENITE.get());
        tag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS).add(APBlocks.GOLDEN_MATRIX.get(), APBlocks.LONSDALEITE_MATRIX.get(), APBlocks.METEORIC_ICE.get());
        tag(Tags.Blocks.STORAGE_BLOCKS_DIAMOND).add(APBlocks.LONSDALEITE_BLOCK.get());
        tag(BlockTags.ICE).add(APBlocks.METEORIC_ICE.get());
        tag(APTags.Blocks.VALID_METEORITE_SPAWN).addTags(Tags.Blocks.STONE,BlockTags.DIRT,BlockTags.SNOW,BlockTags.SAND,Tags.Blocks.SANDSTONE,Tags.Blocks.OBSIDIAN,Tags.Blocks.GRAVEL,Tags.Blocks.COBBLESTONE,Tags.Blocks.ORES,BlockTags.TERRACOTTA).add(Blocks.DIRT_PATH, Blocks.CLAY);
        tag(APTags.Blocks.VITRIFIES_TO_STONE_FULGURITE).addTags(Tags.Blocks.STONE,Tags.Blocks.SANDSTONE,Tags.Blocks.GRAVEL,Tags.Blocks.COBBLESTONE,BlockTags.STONE_BRICKS);
        tag(APTags.Blocks.VITRIFIES_TO_SOIL_FULGURITE).addTags(BlockTags.DIRT).add(Blocks.CLAY);
        tag(APTags.Blocks.VITRIFIES_TO_GLASS).addTags(BlockTags.SAND);
        tag(APTags.Blocks.LIGHTNING_CONDUCTIVE).addTags(Tags.Blocks.STORAGE_BLOCKS_COPPER, Tags.Blocks.STORAGE_BLOCKS_IRON, Tags.Blocks.STORAGE_BLOCKS_GOLD, Tags.Blocks.STORAGE_BLOCKS_REDSTONE).add(Blocks.CUT_COPPER, Blocks.EXPOSED_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CUT_COPPER, Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_CUT_COPPER, Blocks.WAXED_EXPOSED_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.WAXED_WEATHERED_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WAXED_OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER);

    }
}
