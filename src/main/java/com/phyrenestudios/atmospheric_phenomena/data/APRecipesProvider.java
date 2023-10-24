package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class APRecipesProvider extends RecipeProvider {

    public APRecipesProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, APItems.LONSDALEITE.get(), 9).requires(APItems.LONSDALEITE_BLOCK.get()).unlockedBy("has_ingredient", has(APItems.LONSDALEITE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.LONSDALEITE_BLOCK.get(), 1).pattern("GGG").pattern("GGG").pattern("GGG").define('G', APItems.LONSDALEITE.get()).unlockedBy("has_ingredient", has(APItems.LONSDALEITE.get())).save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(APItems.METEORIC_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.8F, 200).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(consumer, AtmosphericPhenomena.MODID+":iron_from_meteoric_iron_smelting");

        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, base.getGlass(), 8).pattern("GGG").pattern("GDG").pattern("GGG").define('G', APTags.Items.LIGHTNING_GLASS).define('D', base.getDyeColor().getTag()).unlockedBy("has_ingredient", has(APTags.Items.LIGHTNING_GLASS)).group(AtmosphericPhenomena.MODID+":lightning_glasses").save(consumer);
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

}
