package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class APTags {


    public static final class Blocks {
        public static final TagKey<Block> METEOR_BLOCKS = modBlock("meteor_blocks");
        public static final TagKey<Block> RARE_METEOR_BLOCKS = modBlock("rare_meteor_blocks");
        public static final TagKey<Block> METEOR_CORE_BLOCKS = modBlock("meteor_core_blocks");
        public static final TagKey<Block> RARE_METEOR_CORE_BLOCKS = modBlock("rare_meteor_core_blocks");

    }

    private static TagKey<Block> forgeBlock(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }
    private static TagKey<Block> modBlock(String path) {
        return BlockTags.create(new ResourceLocation(AtmosphericPhenomena.MODID, path));
    }
    private static TagKey<Item> forgeItem(String path) {
        return ItemTags.create(new ResourceLocation("forge", path));
    }
    private static TagKey<Item> modItem(String path) {
        return ItemTags.create(new ResourceLocation(AtmosphericPhenomena.MODID, path));
    }
}
