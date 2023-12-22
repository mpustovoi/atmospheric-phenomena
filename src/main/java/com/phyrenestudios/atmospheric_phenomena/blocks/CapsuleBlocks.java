package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

public enum CapsuleBlocks implements StringRepresentable {

    PLATED_CAPSULE("plated_capsule", MapColor.RAW_IRON),
    CRYSTALLINE_CAPSULE("crystalline_capsule", MapColor.QUARTZ),
    GILDED_CAPSULE("gilded_capsule", MapColor.GOLD),
    STUDDED_CAPSULE("studded_capsule", MapColor.TERRACOTTA_PINK),
    ANCIENT_CAPSULE("ancient_capsule", MapColor.NETHER),
    FROZEN_CAPSULE("frozen_capsule", MapColor.ICE);

    RegistryObject<CapsuleBlock> block;
    //RegistryObject<Block> coreBlock;

    private final String name;
    private final MapColor mapColor;

    CapsuleBlocks(String name, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        //this.coreBlock = coreBlockIn;
    }

    public String toString() {
        return this.name;
    }
    public String getSerializedName() {
        return this.name;
    }
    public CapsuleBlock getCapsule() {return this.block.get();}
    //public Block getCoreBlock() {return this.coreBlock.get();}

    public static void registerBlocks() {
        for (CapsuleBlocks baseBlock : values()) {
            baseBlock.block =  APBlocks.BLOCKS.register(baseBlock.getSerializedName(), () -> new CapsuleBlock(BlockBehaviour.Properties.of().mapColor(baseBlock.mapColor).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(50.0F, 2000.0F)));
        }
    }

    public static void registerItems() {
        for (CapsuleBlocks baseBlock : values()) {
            APItems.ITEMS.register(baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.block.get(), new Item.Properties().stacksTo(64)));
        }
    }

}