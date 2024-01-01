package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public enum TektiteBlocks implements StringRepresentable {

    BLACK_TEKTITE("black_tektite"),
    GRAY_TEKTITE("gray_tektite"),
    GREEN_TEKTITE("green_tektite"),
    YELLOW_TEKTITE("yellow_tektite"),
    RED_TEKTITE("red_tektite"),
    BROWN_TEKTITE("brown_tektite");

    DeferredBlock<Block> block;

    private final String name;

    TektiteBlocks(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
    public String getSerializedName() {
        return this.name;
    }
    public Block getTektite() {return this.block.get();}

    public static void registerBlocks() {
        for (TektiteBlocks baseBlock : values()) {
            baseBlock.block =  APBlocks.BLOCKS.register(baseBlock.getSerializedName(),() -> new TektiteBlock(BlockBehaviour.Properties.of().strength(5.0F, 30.0F).sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops()));
        }
    }

    public static void registerItems() {
        for (TektiteBlocks baseBlock : values()) {
            APItems.ITEMS.register(baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.block.get(), new Item.Properties().stacksTo(64)));
        }
    }

}