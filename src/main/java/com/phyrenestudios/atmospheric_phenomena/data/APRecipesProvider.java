package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class APRecipesProvider extends RecipeProvider {

    public APRecipesProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, APItems.LONSDALEITE.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.LONSDALEITE_BLOCK.get());
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, APItems.METEORIC_IRON.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.METEORIC_IRON_BLOCK.get());
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(APItems.METEORIC_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.8F, 200).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(consumer, AtmosphericPhenomena.MODID+":iron_from_meteoric_iron_smelting");
        //SimpleCookingRecipeBuilder.smelting(Ingredient.of(APItems.METEORIC_IRON_BLOCK.get()), RecipeCategory.MISC, Items.IRON_BLOCK, 0.8F, 200).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(consumer, AtmosphericPhenomena.MODID+":iron_block_from_meteoric_iron_block_smelting");

        for (MeteorBlocks base : MeteorBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getBricks(), 4).pattern("BB").pattern("BB").define('B', base.getMeteorBlock()).unlockedBy("has_ingredient", has(base.getMeteorBlock())).save(consumer);
            slabRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks());
            stairsRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            wallRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), 1).pattern("B").pattern("B").define('B', base.getBricksSlab()).unlockedBy("has_ingredient", has(base.getBricksSlab())).save(consumer);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricks(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getMeteorBlock(), 2);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks(), 2);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getBricks());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getGlass(), 8).pattern("GGG").pattern("GDG").pattern("GGG").define('G', APTags.Items.LIGHTNING_GLASS).define('D', base.getDyeColor().getTag()).unlockedBy("has_ingredient", has(APTags.Items.LIGHTNING_GLASS)).group(AtmosphericPhenomena.MODID+":lightning_glasses").save(consumer);
        }


        generateForEnabledAPBlockFamilies(consumer, FeatureFlagSet.of(FeatureFlags.VANILLA));
        planksFromLogs(consumer, APBlocks.CHARRED_PLANKS.get(), APTags.Items.CHARRED_LOGS, 4);
        woodFromLogs(consumer, APBlocks.CHARRED_WOOD.get(), APBlocks.CHARRED_LOG.get());
        woodFromLogs(consumer, APBlocks.STRIPPED_CHARRED_WOOD.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
        //woodenBoat(consumer, APItems.CHARRED_BOAT.get(), APBlocks.CHARRED_PLANKS);
        //chestBoat(p_250804_, Items.ACACIA_CHEST_BOAT, Items.ACACIA_BOAT);
    }

    private static void slabRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }

    private static void stairsRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 4).define('#', input).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }

    private static void wallRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }

    protected void generateForEnabledAPBlockFamilies(Consumer<FinishedRecipe> p_249188_, FeatureFlagSet p_251836_) {
        APBlockFamilies.getAllFamilies().filter((p_248034_) -> {
            return p_248034_.shouldGenerateRecipe(p_251836_);
        }).forEach((p_176624_) -> {
            generateRecipes(p_249188_, p_176624_);
        });
    }
}
