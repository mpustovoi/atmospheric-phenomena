package com.phyrenestudios.atmospheric_phenomena.util;

import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureUtils {

    public static WeightedCollection<Block> meteorBlockCollection;
    public static WeightedCollection<Block> meteorCoreBlockCollection;
    public static WeightedCollection<Block> meteorStrewnBlockCollection;

    public static void populateBlockCollections() {
        meteorBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(0), blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(1), blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_BLOCKS)) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(2), blk);
        }

        meteorCoreBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(0), blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(1), blk);
        }
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.ULTRA_RARE_METEOR_CORE_BLOCKS)) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(2), blk);
        }

        meteorStrewnBlockCollection = new WeightedCollection<>();
        for (Block blk : ForgeRegistries.BLOCKS.tags().getTag(APTags.Blocks.METEORITE_STREWN_BLOCKS)) {
            meteorStrewnBlockCollection.add(1, blk);
        }

    }

    public static double randomDoubleBetween(RandomSource rand, double min, double max) {
        if (min >= max) return min;
        return rand.nextDouble()*(max-min) + min;
    }


}
