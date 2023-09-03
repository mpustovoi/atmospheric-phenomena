package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
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


    }

}
