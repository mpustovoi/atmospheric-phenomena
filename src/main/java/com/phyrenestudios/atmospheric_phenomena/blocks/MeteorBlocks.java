package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

public enum MeteorBlocks implements StringRepresentable {

    CHONDRITE("chondrite"),
    ENSTATITE_CHONDRITE("enstatite_chondrite"),
    CARBONACEOUS_CHONDRITE("carbonaceous_chondrite"),
    ANGRITE("angrite"),
    UREILITE("ureilite"),
    PALLASITE("pallasite"),
    MESOSIDERITE("mesosiderite"),
    KAMACITE("kamacite"),
    TAENITE("taenite"),
    TETRATAENITE("tetrataenite");

    RegistryObject<Block> block;

    private final String name;

    MeteorBlocks(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
    public String getSerializedName() {
        return this.name;
    }
    public Block getMeteorBlock() {return this.block.get();}

    public static void registerBlocks() {
        for (MeteorBlocks baseBlock : values()) {
            baseBlock.block =  APBlocks.BLOCKS.register(baseBlock.getSerializedName(), () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
        }
    }

    public static void registerItems() {
        Item.Properties DEF_BUILDING = new Item.Properties().stacksTo(64);
        for (MeteorBlocks baseBlock : values()) {
            APItems.ITEMS.register(baseBlock.getSerializedName(), () -> new BlockItem(baseBlock.block.get(), DEF_BUILDING));
        }
    }

}