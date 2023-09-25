package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public enum LightningGlassBlocks implements StringRepresentable {

    //LIGHTNING_GLASS("lightning_glass", DyeColor.WHITE),
    WHITE_LIGHTNING_GLASS("white_lightning_glass", DyeColor.WHITE),
    LIGHT_GRAY_LIGHTNING_GLASS("light_gray_lightning_glass", DyeColor.LIGHT_GRAY),
    GRAY_LIGHTNING_GLASS("gray_lightning_glass", DyeColor.GRAY),
    BLACK_LIGHTNING_GLASS("black_lightning_glass", DyeColor.BLACK),
    BROWN_LIGHTNING_GLASS("brown_lightning_glass", DyeColor.BROWN),
    RED_LIGHTNING_GLASS("red_lightning_glass", DyeColor.RED),
    ORANGE_LIGHTNING_GLASS("orange_lightning_glass", DyeColor.ORANGE),
    YELLOW_LIGHTNING_GLASS("yellow_lightning_glass", DyeColor.YELLOW),
    LIME_LIGHTNING_GLASS("lime_lightning_glass", DyeColor.LIME),
    GREEN_LIGHTNING_GLASS("green_lightning_glass", DyeColor.GREEN),
    CYAN_LIGHTNING_GLASS("cyan_lightning_glass", DyeColor.CYAN),
    LIGHT_BLUE_LIGHTNING_GLASS("light_blue_lightning_glass", DyeColor.LIGHT_BLUE),
    BLUE_LIGHTNING_GLASS("blue_lightning_glass", DyeColor.BLUE),
    PURPLE_LIGHTNING_GLASS("purple_lightning_glass", DyeColor.PURPLE),
    MAGENTA_LIGHTNING_GLASS("magenta_lightning_glass", DyeColor.MAGENTA),
    PINK_LIGHTNING_GLASS("pink_lightning_glass", DyeColor.PINK);



    RegistryObject<Block> block;

    private final String name;
    private final DyeColor dyeColor;

    LightningGlassBlocks(String name, DyeColor dyeIn) {
        this.name = name;
        this.dyeColor = dyeIn;
    }

    public String toString() {
        return this.name;
    }
    public String getSerializedName() {
        return this.name;
    }
    public Block getGlass() {return this.block.get();}
    public DyeColor getDyeColor() {return this.dyeColor;}

    public static void registerBlocks() {
        for (LightningGlassBlocks baseBlock : values()) {
            baseBlock.block =  APBlocks.BLOCKS.register(baseBlock.getSerializedName(),() -> new LightningGlassBlock(BlockBehaviour.Properties.of().strength(5.0F, 30.0F).sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops()));
        }
    }

    public static void registerItems() {
        for (LightningGlassBlocks baseBlock : values()) {
            APItems.ITEMS.register(baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.block.get(), new Item.Properties().stacksTo(64)));
        }
    }

}