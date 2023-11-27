package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
/*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Wood.getWood(), 3).pattern("##").pattern("##").define('#', Wood.getLog()).unlockedBy("has_ingredient", has(Wood.getLog())).group("rankine:wood").save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Wood.getStrippedWood(), 3).pattern("##").pattern("##").define('#', Wood.getStrippedLog()).unlockedBy("has_ingredient", has(Wood.getStrippedLog())).group("stripped_wood").save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Wood.getPlanks(), 4).requires(ItemTags.create(new ResourceLocation("rankine", Wood.getBaseName() + "_logs"))).group("planks").unlockedBy("has_ingredient", has(ItemTags.create(new ResourceLocation("rankine", Wood.getBaseName() + "_logs")))).save(consumer);

        Block PLANK = APBlocks.CHARRED_PLANKS.get();
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, APBlocks.CHARRED_PRESSURE_PLATE.get(), PLANK);
        stairBuilder(APBlocks.CHARRED_PRESSURE_PLATE.get(), PLANK);
        pressurePlate(consumer, APBlocks.CHARRED_PRESSURE_PLATE.get(), PLANK);
        doorBuilder(APBlocks.CHARRED_DOOR.get(), PLANK);
        trapdoor(consumer, Wood.getTrapdoor(), PLANK, "wooden_trapdoor",  "has_plank", PLANK);
        fence(consumer, Wood.getFence(), PLANK, "wooden_fence",  "has_plank", PLANK);
        fenceGate(consumer, Wood.getFenceGate(), PLANK, "wooden_fence_gate",  "has_plank", PLANK);
        bookshelf(consumer, Wood.getBookshelf(), PLANK, "wooden_bookshelves",  "has_plank", PLANK);
        boat(consumer, Wood.getBoat(), PLANK, "boat",  "has_plank", PLANK);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Wood.getSignItem(), 3).pattern("###").pattern("###").pattern(" S ").define('#', Wood.getPlanks()).define('S', Tags.Items.RODS_WOODEN).group("sign").unlockedBy("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(Wood.getPlanks())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Wood.getButton()).requires(PLANK).group("wooden_button").unlockedBy("has_ingredient", has(PLANK)).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PLANK).requires(Wood.getSlab()).requires(Wood.getSlab()).group("block_from_vslab").unlockedBy("has_ingredient", has(PLANK)).save(consumer,"rankine:"+Wood.getBaseName()+"_from_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PLANK,3).requires(Wood.getStairs()).requires(Wood.getStairs()).requires(Wood.getStairs()).requires(Wood.getStairs()).group("block_from_stairs").unlockedBy("has_ingredient", has(PLANK)).save(consumer,"rankine:"+Wood.getBaseName()+"_from_stairs");


 */
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

}
