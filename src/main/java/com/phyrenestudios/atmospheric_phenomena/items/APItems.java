package com.phyrenestudios.atmospheric_phenomena.items;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerItems();
        TektiteBlocks.registerItems();
        LightningGlassBlocks.registerItems();

    }
    public static final RegistryObject<Item> KAMACITE = ITEMS.register("kamacite", () -> new BlockItem(APBlocks.KAMACITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TAENITE = ITEMS.register("taenite", () -> new BlockItem(APBlocks.TAENITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TETRATAENITE = ITEMS.register("tetrataenite", () -> new BlockItem(APBlocks.TETRATAENITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> QUARTZ_MATRIX = ITEMS.register("quartz_matrix", () -> new BlockItem(APBlocks.QUARTZ_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARGED_QUARTZ_MATRIX = ITEMS.register("charged_quartz_matrix", () -> new BlockItem(APBlocks.CHARGED_QUARTZ_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GOLDEN_MATRIX = ITEMS.register("golden_matrix", () -> new BlockItem(APBlocks.GOLDEN_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DEBRIS_MATRIX = ITEMS.register("debris_matrix", () -> new BlockItem(APBlocks.DEBRIS_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE_MATRIX = ITEMS.register("lonsdaleite_matrix", () -> new BlockItem(APBlocks.LONSDALEITE_MATRIX.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE = ITEMS.register("lonsdaleite", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONSDALEITE_BLOCK = ITEMS.register("lonsdaleite_block", () -> new BlockItem(APBlocks.LONSDALEITE_BLOCK.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEORIC_ICE = ITEMS.register("meteoric_ice", () -> new BlockItem(APBlocks.METEORIC_ICE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEORIC_IRON = ITEMS.register("meteoric_iron", () -> new Item(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SOIL_FULGURITE = ITEMS.register("soil_fulgurite", () -> new BlockItem(APBlocks.SOIL_FULGURITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> STONE_FULGURITE = ITEMS.register("stone_fulgurite", () -> new BlockItem(APBlocks.SSTONE_FULGURITE.get(), new Item.Properties().stacksTo(64)));

    private static final String baseName = "charred";
    public static final RegistryObject<Item> CHARRED_LOG = ITEMS.register(baseName + "_log", () -> new BlockItem(APBlocks.CHARRED_LOG.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> STRIPPED_CHARRED_LOG = ITEMS.register("stripped_" + baseName + "_log", () -> new BlockItem(APBlocks.STRIPPED_CHARRED_LOG.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_WOOD = ITEMS.register(baseName + "_wood", () -> new BlockItem(APBlocks.CHARRED_WOOD.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> STRIPPED_CHARRED_WOOD = ITEMS.register("stripped_" + baseName + "_wood", () -> new BlockItem(APBlocks.STRIPPED_CHARRED_WOOD.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_PLANKS = ITEMS.register(baseName+"_planks", () -> new BlockItem(APBlocks.CHARRED_PLANKS.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_SLAB = ITEMS.register(baseName+"_slab", () -> new BlockItem(APBlocks.CHARRED_SLAB.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_STAIRS = ITEMS.register(baseName+"_stairs", () -> new BlockItem(APBlocks.CHARRED_STAIRS.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_FENCE = ITEMS.register(baseName+"_fence", () -> new BlockItem(APBlocks.CHARRED_FENCE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_FENCE_GATE = ITEMS.register(baseName+"_fence_gate", () -> new BlockItem(APBlocks.CHARRED_FENCE_GATE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_DOOR = ITEMS.register(baseName+"_door", () -> new BlockItem(APBlocks.CHARRED_DOOR.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_TRAPDOOR = ITEMS.register(baseName+"_trapdoor", () -> new BlockItem(APBlocks.CHARRED_TRAPDOOR.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_PRESSURE_PLATE = ITEMS.register(baseName+"_pressure_plate", () -> new BlockItem(APBlocks.CHARRED_PRESSURE_PLATE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_BUTTON = ITEMS.register(baseName+"_button", () -> new BlockItem(APBlocks.CHARRED_BUTTON.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_SIGN = ITEMS.register(baseName+"_sign", () -> new SignItem(new Item.Properties().stacksTo(16), APBlocks.CHARRED_SIGN.get(), APBlocks.CHARRED_WALL_SIGN.get()));
    public static final RegistryObject<Item> CHARRED_BOOKSHELF = ITEMS.register(baseName+"_bookshelf", () -> new BlockItem(APBlocks.CHARRED_BOOKSHELF.get(), new Item.Properties().stacksTo(64)));
    //public static final RegistryObject<Item> CHARRED_ = ITEMS.register(baseName+"_boat", () -> new RankineBoatItem(RankineBoatEntity.Type.getTypeFromString(baseName), new Item.Properties().stacksTo(1)));




    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    //public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    //public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
    //        .alwaysEat().nutrition(1).saturationMod(2f).build())));

}
