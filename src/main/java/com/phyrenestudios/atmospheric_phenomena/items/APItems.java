package com.phyrenestudios.atmospheric_phenomena.items;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
import com.phyrenestudios.atmospheric_phenomena.entities.APBoat;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> METEORIC_IRON_BLOCK = ITEMS.register("meteoric_iron_block", () -> new BlockItem(APBlocks.METEORIC_IRON_BLOCK.get(), new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> SOIL_FULGURITE = ITEMS.register("soil_fulgurite", () -> new BlockItem(APBlocks.SOIL_FULGURITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> STONE_FULGURITE = ITEMS.register("stone_fulgurite", () -> new BlockItem(APBlocks.STONE_FULGURITE.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> METEOR_CRATE = ITEMS.register("meteor_crate", () -> new BlockItem(APBlocks.METEOR_CRATE.get(), new Item.Properties().stacksTo(64)));

    private static final String baseName = "charred";
    public static final RegistryObject<Item> BURNING_LOG = ITEMS.register("burning_log", () -> new BlockItem(APBlocks.BURNING_LOG.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BURNING_WOOD = ITEMS.register("burning_wood", () -> new BlockItem(APBlocks.BURNING_WOOD.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SMOULDERING_LOG = ITEMS.register("smouldering_log", () -> new BlockItem(APBlocks.SMOULDERING_LOG.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SMOULDERING_WOOD = ITEMS.register("smouldering_wood", () -> new BlockItem(APBlocks.SMOULDERING_WOOD.get(), new Item.Properties().stacksTo(64)));
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
    public static final RegistryObject<Item> CHARRED_HANGING_SIGN = ITEMS.register(baseName+"_hanging_sign", () -> new HangingSignItem(APBlocks.CHARRED_HANGING_SIGN.get(), APBlocks.CHARRED_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> CHARRED_BOOKSHELF = ITEMS.register(baseName+"_bookshelf", () -> new BlockItem(APBlocks.CHARRED_BOOKSHELF.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CHARRED_BOAT = ITEMS.register(baseName+"_boat", () -> new APBoatItem(false, APBoat.Type.CHARRED, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHARRED_CHEST_BOAT = ITEMS.register(baseName+"_chest_boat", () -> new APBoatItem(true, APBoat.Type.CHARRED, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("otherworldly_upgrade_smithing_template", APSmithingTemplateItem::createOtherworldlyUpgradeTemplate);
}
