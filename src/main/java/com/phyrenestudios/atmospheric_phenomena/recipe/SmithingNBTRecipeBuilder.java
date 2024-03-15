package com.phyrenestudios.atmospheric_phenomena.recipe;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.LinkedHashMap;
import java.util.Map;

public class SmithingNBTRecipeBuilder {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final RecipeCategory category;
    private final Item result;
    private final String modifier;
    private final RecipeSerializer<?> type;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SmithingNBTRecipeBuilder(RecipeSerializer<?> p_266683_, Ingredient p_266973_, Ingredient p_267047_, Ingredient p_267009_, RecipeCategory p_266694_, Item p_267183_, String modifierIn) {
        this.category = p_266694_;
        this.type = p_266683_;
        this.template = p_266973_;
        this.base = p_267047_;
        this.addition = p_267009_;
        this.result = p_267183_;
        this.modifier = modifierIn;
    }

    public static SmithingNBTRecipeBuilder smithing(Ingredient p_267071_, Ingredient p_266959_, Ingredient p_266803_, RecipeCategory p_266757_, Item p_267256_, String modifierIn) {
        return new SmithingNBTRecipeBuilder(APRecipeSerializers.SMITHING_NBT_RECIPE_SERIALIZER.get(), p_267071_, p_266959_, p_266803_, p_266757_, p_267256_, modifierIn);
    }

    public SmithingNBTRecipeBuilder unlocks(String p_266919_, Criterion<?> p_300923_) {
        this.criteria.put(p_266919_, p_300923_);
        return this;
    }

    public void save(RecipeOutput p_301163_, String p_300906_) {
        this.save(p_301163_, new ResourceLocation(p_300906_));
    }

    public void save(RecipeOutput p_301291_, ResourceLocation p_300873_) {
        this.ensureValid(p_300873_);
        Advancement.Builder advancement$builder = p_301291_.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_300873_))
                .rewards(AdvancementRewards.Builder.recipe(p_300873_))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        SmithingNBTRecipe smithingNBTRecipe = new SmithingNBTRecipe(this.template, this.base, this.addition, new ItemStack(this.result), this.modifier);
        p_301291_.accept(p_300873_, smithingNBTRecipe, advancement$builder.build(p_300873_.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation p_267259_) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_267259_);
        }
    }
}

