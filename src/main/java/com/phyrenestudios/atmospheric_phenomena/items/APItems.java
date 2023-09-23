package com.phyrenestudios.atmospheric_phenomena.items;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerItems();
        TektiteBlocks.registerItems();
    }
    public static final RegistryObject<Item> KAMACITE = ITEMS.register("kamacite", () -> new BlockItem(APBlocks.KAMACITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TAENITE = ITEMS.register("taenite", () -> new BlockItem(APBlocks.TAENITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TETRATAENITE = ITEMS.register("tetrataenite", () -> new BlockItem(APBlocks.TETRATAENITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GOLDEN_MATRIX = ITEMS.register("golden_matrix", () -> new BlockItem(APBlocks.GOLDEN_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE_MATRIX = ITEMS.register("lonsdaleite_matrix", () -> new BlockItem(APBlocks.LONSDALEITE_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE = ITEMS.register("lonsdaleite", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE_BLOCK = ITEMS.register("lonsdaleite_block", () -> new BlockItem(APBlocks.LONSDALEITE_BLOCK.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEORIC_ICE = ITEMS.register("meteoric_ice", () -> new BlockItem(APBlocks.METEORIC_ICE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEORIC_IRON = ITEMS.register("meteoric_iron", () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SOIL_FULGURITE = ITEMS.register("soil_fulgurite", () -> new BlockItem(APBlocks.SOIL_FULGURITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SSTONE_FULGURITE = ITEMS.register("stone_fulgurite", () -> new BlockItem(APBlocks.SSTONE_FULGURITE.get(), new Item.Properties().stacksTo(64)));


    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    //public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    //public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
    //        .alwaysEat().nutrition(1).saturationMod(2f).build())));

}
