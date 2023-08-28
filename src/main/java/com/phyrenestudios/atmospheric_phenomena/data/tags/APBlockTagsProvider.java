package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
    }
}
