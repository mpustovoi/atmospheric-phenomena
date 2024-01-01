package com.phyrenestudios.atmospheric_phenomena.util;

import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;

import java.util.stream.Collectors;

public class FeatureUtils {

    public static WeightedCollection<Block> meteorBlockCollection;
    public static WeightedCollection<Block> meteorCoreBlockCollection;
    public static WeightedCollection<Block> meteorStrewnBlockCollection;

    public static void populateBlockCollections() {
        meteorBlockCollection = new WeightedCollection<>();
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.COMMON_METEOR_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(0), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.UNCOMMON_METEOR_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(1), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.RARE_METEOR_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(2), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.VERY_RARE_METEOR_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorBlockCollection.add(Config.meteoriteBlocksRarity.get(3), blk.value());
        }

        meteorCoreBlockCollection = new WeightedCollection<>();
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.COMMON_METEOR_CORE_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(0), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.UNCOMMON_METEOR_CORE_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(1), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.RARE_METEOR_CORE_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(2), blk.value());
        }
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.VERY_RARE_METEOR_CORE_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorCoreBlockCollection.add(Config.meteoriteCoreBlocksRarity.get(3), blk.value());
        }

        meteorStrewnBlockCollection = new WeightedCollection<>();
        for (Holder<Block> blk : BuiltInRegistries.BLOCK.getTag(APTags.Blocks.METEORITE_STREWN_BLOCKS).get().stream().collect(Collectors.toList())) {
            meteorStrewnBlockCollection.add(1, blk.value());
        }

    }

    public static double randomDoubleBetween(RandomSource rand, double min, double max) {
        if (min >= max) return min;
        return rand.nextDouble()*(max-min) + min;
    }


}
