package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class APItemModelProvider extends ItemModelProvider {

    public APItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, AtmosphericPhenomena.MODID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return AtmosphericPhenomena.MODID + " - Item Models";
    }

    protected void registerModels() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            withExistingParent(base.getMeteorBlock());
            withExistingParent(base.getBricks());
            slabParent(base.getBricksSlab());
            stairsParent(base.getBricksStairs());
            wallParent(base.getBricksWall());
            withExistingParent(name(base.getChiseled()), modLoc("block/" + name(base.getChiseled())+5));
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            withExistingParent(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            withExistingParent(base.getGlass());
        }
        for (CapsuleBlocks base : CapsuleBlocks.values()) {
            withExistingParent(base.getCapsule());
        }
        withExistingParent(APBlocks.KAMACITE.get());
        withExistingParent(APBlocks.TAENITE.get());
        withExistingParent(APBlocks.TETRATAENITE.get());
        withExistingParent(APBlocks.GOLDEN_MATRIX.get());
        withExistingParent(APBlocks.QUARTZ_MATRIX.get());
        withExistingParent(APBlocks.CHARGED_QUARTZ_MATRIX.get());
        withExistingParent(APBlocks.DEBRIS_MATRIX.get());
        withExistingParent(APBlocks.LONSDALEITE_MATRIX.get());
        withExistingParent(APBlocks.LONSDALEITE_BLOCK.get());
        withExistingParent(APBlocks.METEORIC_IRON_BLOCK.get());
        withExistingParent(APBlocks.METEORIC_ICE.get());
        withExistingParent(APBlocks.SOIL_FULGURITE.get());
        withExistingParent(APBlocks.STONE_FULGURITE.get());

        basicItem(APItems.LONSDALEITE.get());
        basicItem(APItems.METEORIC_IRON.get());
        basicItem(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get());

        withExistingParent(APBlocks.BURNING_LOG.get());
        withExistingParent(APBlocks.BURNING_WOOD.get());
        withExistingParent(APBlocks.SMOULDERING_LOG.get());
        withExistingParent(APBlocks.SMOULDERING_WOOD.get());
        withExistingParent(APBlocks.CHARRED_LOG.get());
        withExistingParent(APBlocks.STRIPPED_CHARRED_LOG.get());
        withExistingParent(APBlocks.CHARRED_WOOD.get());
        withExistingParent(APBlocks.STRIPPED_CHARRED_WOOD.get());
        withExistingParent(APBlocks.CHARRED_PLANKS.get());
        slabParent(APBlocks.CHARRED_SLAB.get());
        stairsParent(APBlocks.CHARRED_STAIRS.get());
        withExistingParent(name(APBlocks.CHARRED_FENCE.get()), getBlockRSL(name(APBlocks.CHARRED_FENCE.get(),"_inventory")));
        withExistingParent(APBlocks.CHARRED_FENCE_GATE.get());
        basicItem(APBlocks.CHARRED_DOOR.get().asItem());
        withExistingParent(name(APBlocks.CHARRED_TRAPDOOR.get()), getBlockRSL(name(APBlocks.CHARRED_TRAPDOOR.get(),"_bottom")));
        withExistingParent(APBlocks.CHARRED_PRESSURE_PLATE.get());
        withExistingParent(name(APBlocks.CHARRED_BUTTON.get()), getBlockRSL(name(APBlocks.CHARRED_BUTTON.get(),"_inventory")));
        basicItem(APItems.CHARRED_SIGN.get());
        basicItem(APItems.CHARRED_HANGING_SIGN.get());
        withExistingParent(APBlocks.CHARRED_BOOKSHELF.get());
        basicItem(APItems.CHARRED_BOAT.get());
        basicItem(APItems.CHARRED_CHEST_BOAT.get());



    }

    private ItemModelBuilder wallParent(Block BLK) {
        return withExistingParent(name(BLK), getBlockRSL(name(BLK,"_inventory")));
    }

    private ItemModelBuilder withExistingParent(Block blk) {
        return withExistingParent(name(blk), modLoc("block/" + name(blk)));
    }
    private ItemModelBuilder slabParent(Block BLK) {
        return withExistingParent(name(BLK), getBlockRSL(name(BLK)));
    }
    private ItemModelBuilder stairsParent(Block BLK) {
        return withExistingParent(name(BLK), getBlockRSL(name(BLK)));
    }

    private static ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private static ResourceLocation key(Item item) {
        return ForgeRegistries.ITEMS.getKey(item);
    }
    private static String name(Block itemLike) {
        return key(itemLike).getPath();
    }
    private static String name(Block itemLike, String suffix) {
        return key(itemLike).getPath() + suffix;
    }
    private static String name(String prefix, Block itemLike) {
        return prefix + key(itemLike).getPath();
    }
    private static String name(String prefix, Block itemLike, String suffix) {
        return prefix + key(itemLike).getPath() + suffix;
    }
    private static String name(Item itemLike) {
        return key(itemLike).getPath();
    }
    private static String name(Item itemLike, String suffix) {
        return key(itemLike).getPath() + suffix;
    }
    private static String name(String prefix, Item itemLike) {
        return prefix + key(itemLike).getPath();
    }
    private static String name(String prefix, Item itemLike, String suffix) {
        return prefix + key(itemLike).getPath() + suffix;
    }
    private ResourceLocation getBlockRSL(Block itemLike) {
        return getBlockRSL(name(itemLike));
    }
    private ResourceLocation getBlockRSL(String textureName) {
        return modLoc("block/"+textureName);
    }
    private ResourceLocation getBlockRSL(String namespace, String textureName) {
        return new ResourceLocation(namespace,"block/"+textureName);
    }
}
