package com.phyrenestudios.atmospheric_phenomena.util;

import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureUtils {

    public static WeightedCollection<Block> meteorBlockCollection;
    public static WeightedCollection<Block> meteorCoreBlockCollection;
    public static WeightedCollection<Block> meteorStrewnBlockCollection;

    public static void populateBlockCollections() {
        meteorBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.meteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.rareMeteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.ultraRareMeteoriteChance, blk);
        }

        meteorCoreBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.meteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.rareMeteoriteChance, blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.ultraRareMeteoriteChance, blk);
        }

        meteorStrewnBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEORITE_STREWN_BLOCKS)) {
            meteorStrewnBlockCollection.add(1, blk);
        }

    }



}
