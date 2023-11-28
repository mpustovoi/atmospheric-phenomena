package com.phyrenestudios.atmospheric_phenomena.data;

import com.google.common.collect.Maps;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class APBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final BlockFamily CHARRED_PLANKS = familyBuilder(APBlocks.CHARRED_PLANKS.get()).button(APBlocks.CHARRED_BUTTON.get()).fence(APBlocks.CHARRED_FENCE.get()).fenceGate(APBlocks.CHARRED_FENCE_GATE.get()).pressurePlate(APBlocks.CHARRED_PRESSURE_PLATE.get()).sign(APBlocks.CHARRED_SIGN.get(), APBlocks.CHARRED_WALL_SIGN.get()).slab(APBlocks.CHARRED_SLAB.get()).stairs(APBlocks.CHARRED_STAIRS.get()).door(APBlocks.CHARRED_DOOR.get()).trapdoor(APBlocks.CHARRED_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

    private static BlockFamily.Builder familyBuilder(Block p_175936_) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(p_175936_);
        BlockFamily blockfamily = MAP.put(p_175936_, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(p_175936_));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
