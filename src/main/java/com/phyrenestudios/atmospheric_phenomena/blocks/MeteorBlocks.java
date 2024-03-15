package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

public enum MeteorBlocks implements StringRepresentable {

    CHONDRITE("chondrite", MapColor.TERRACOTTA_CYAN),
    ENSTATITE_CHONDRITE("enstatite_chondrite", MapColor.TERRACOTTA_BROWN),
    CARBONACEOUS_CHONDRITE("carbonaceous_chondrite", MapColor.TERRACOTTA_BLACK),
    ANGRITE("angrite", MapColor.TERRACOTTA_MAGENTA),
    UREILITE("ureilite", MapColor.TERRACOTTA_BLUE),
    PALLASITE("pallasite", MapColor.TERRACOTTA_WHITE),
    MESOSIDERITE("mesosiderite", MapColor.TERRACOTTA_YELLOW);

    RegistryObject<Block> block;
    RegistryObject<Block> bricks;
    RegistryObject<SlabBlock> slab;
    RegistryObject<StairBlock> stairs;
    RegistryObject<WallBlock> wall;
    RegistryObject<ChiseledMeteoriteBlock> chiseled;

    private final String name;
    private final MapColor mapColor;

    MeteorBlocks(String name, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
    }

    public String toString() {
        return this.name;
    }
    public String getSerializedName() {
        return this.name;
    }
    public Block getMeteorBlock() {return this.block.get();}
    public Block getBricks() {return this.bricks.get();}
    public SlabBlock getBricksSlab() {return this.slab.get();}
    public StairBlock getBricksStairs() {return this.stairs.get();}
    public WallBlock getBricksWall() {return this.wall.get();}
    public ChiseledMeteoriteBlock getChiseled() {return this.chiseled.get();}

    public static void registerBlocks() {
        for (MeteorBlocks baseBlock : values()) {
            baseBlock.block =  APBlocks.BLOCKS.register(baseBlock.getSerializedName(), () -> new Block(BlockBehaviour.Properties.of().mapColor(baseBlock.mapColor).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 9.0F)));
            baseBlock.bricks =  APBlocks.BLOCKS.register(baseBlock.getSerializedName()+"_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(baseBlock.mapColor).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
            baseBlock.slab =  APBlocks.BLOCKS.register(baseBlock.getSerializedName()+"_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock.bricks.get())));
            baseBlock.stairs =  APBlocks.BLOCKS.register(baseBlock.getSerializedName()+"_bricks_stairs", () -> new StairBlock(baseBlock.bricks.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(baseBlock.bricks.get())));
            baseBlock.wall =  APBlocks.BLOCKS.register(baseBlock.getSerializedName()+"_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock.bricks.get())));
            baseBlock.chiseled =  APBlocks.BLOCKS.register("chiseled_"+baseBlock.getSerializedName(), () -> new ChiseledMeteoriteBlock(BlockBehaviour.Properties.of().mapColor(baseBlock.mapColor).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(4.0F, 12.0F)));
        }
    }

    public static void registerItems() {
        for (MeteorBlocks baseBlock : values()) {
            APItems.ITEMS.register(baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.block.get(), new Item.Properties().stacksTo(64)));
            APItems.ITEMS.register(baseBlock.getSerializedName()+"_bricks", () -> new BlockItem(baseBlock.bricks.get(), new Item.Properties().stacksTo(64)));
            APItems.ITEMS.register(baseBlock.getSerializedName()+"_bricks_slab", () -> new BlockItem(baseBlock.slab.get(), new Item.Properties().stacksTo(64)));
            APItems.ITEMS.register(baseBlock.getSerializedName()+"_bricks_stairs", () -> new BlockItem(baseBlock.stairs.get(), new Item.Properties().stacksTo(64)));
            APItems.ITEMS.register(baseBlock.getSerializedName()+"_bricks_wall", () -> new BlockItem(baseBlock.wall.get(), new Item.Properties().stacksTo(64)));
            APItems.ITEMS.register("chiseled_"+baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.chiseled.get(), new Item.Properties().stacksTo(64)));
        }
    }

}