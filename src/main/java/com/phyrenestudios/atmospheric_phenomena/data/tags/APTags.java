package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class APTags {

    public static final class Blocks {
        public static final TagKey<Block> VALID_METEORITE_SPAWN = modBlock("valid_meteorite_spawn");
        public static final TagKey<Block> METEOROID_DESTROY = modBlock("meteoroid_destroy");
        public static final TagKey<Block> COMMON_METEOR_BLOCKS = modBlock("common_meteor_blocks");
        public static final TagKey<Block> UNCOMMON_METEOR_BLOCKS = modBlock("uncommon_meteor_blocks");
        public static final TagKey<Block> RARE_METEOR_BLOCKS = modBlock("rare_meteor_blocks");
        public static final TagKey<Block> VERY_RARE_METEOR_BLOCKS = modBlock("very_rare_meteor_blocks");
        public static final TagKey<Block> COMMON_METEOR_CORE_BLOCKS = modBlock("common_meteor_core_blocks");
        public static final TagKey<Block> UNCOMMON_METEOR_CORE_BLOCKS = modBlock("uncommon_meteor_core_blocks");
        public static final TagKey<Block> RARE_METEOR_CORE_BLOCKS = modBlock("rare_meteor_core_blocks");
        public static final TagKey<Block> VERY_RARE_METEOR_CORE_BLOCKS = modBlock("very_rare_meteor_core_blocks");
        public static final TagKey<Block> METEORITE_STREWN_BLOCKS = modBlock("meteorite_strewn_blocks");
        public static final TagKey<Block> VITRIFIES_TO_SOIL_FULGURITE = modBlock("vitrifies_to_soil_fulgurite");
        public static final TagKey<Block> VITRIFIES_TO_STONE_FULGURITE = modBlock("vitrifies_to_stone_fulgurite");
        public static final TagKey<Block> VITRIFIES_TO_GLASS = modBlock("vitrifies_to_glass");
        public static final TagKey<Block> TEKTITES = modBlock("tektites");
        public static final TagKey<Block> LIGHTNING_GLASS = modBlock("lightning_glass");
        public static final TagKey<Block> LIGHTNING_CONDUCTIVE = modBlock("lightning_conductive");
        public static final TagKey<Block> EXPLODES_FROM_CONDUCTIVITY = modBlock("explodes_from_conductivity");
        public static final TagKey<Block> CHARRED_LOGS = modBlock("charred_logs");
        public static final TagKey<Block> SMOULDERING_LOGS = modBlock("smouldering_logs");
        public static final TagKey<Block> BURNING_LOGS = modBlock("burning_logs");
        public static final TagKey<Block> CAPSULES = modBlock("capsules");

    }

    public static final class Items {
        public static final TagKey<Item> LIGHTNING_GLASS = modItem("lightning_glass");
        public static final TagKey<Item> CHARRED_LOGS = modItem("charred_logs");
        public static final TagKey<Item> SMOULDERING_LOGS = modItem("smouldering_logs");
        public static final TagKey<Item> BURNING_LOGS = modItem("burning_logs");
        public static final TagKey<Item> METEORITE_BLOCKS = modItem("meteorite_blocks");
        public static final TagKey<Item> TEKTITES = modItem("tektites");
        public static final TagKey<Item> CAPSULES = modItem("capsules");

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
    private static TagKey<Biome> createBiomeTag(String path) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(AtmosphericPhenomena.MODID, path));
    }
    private static TagKey<DamageType> createDamageTypeTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(AtmosphericPhenomena.MODID, path));
    }


}
