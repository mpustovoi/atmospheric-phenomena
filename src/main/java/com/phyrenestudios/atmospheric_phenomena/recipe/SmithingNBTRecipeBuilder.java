package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.google.gson.JsonObject;
import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SmithingNBTRecipeBuilder {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final RecipeCategory category;
    private final Item result;
    private final String modifier;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    private final RecipeSerializer<?> type;

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

    public SmithingNBTRecipeBuilder unlocks(String p_266919_, CriterionTriggerInstance p_267277_) {
        this.advancement.addCriterion(p_266919_, p_267277_);
        return this;
    }

    public void save(Consumer<FinishedRecipe> p_267068_, String p_267035_) {
        this.save(p_267068_, new ResourceLocation(AtmosphericPhenomena.MODID, p_267035_));
    }

    public void save(Consumer<FinishedRecipe> p_267089_, ResourceLocation p_267287_) {
        this.ensureValid(p_267287_);
        this.advancement.parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(p_267287_)).rewards(AdvancementRewards.Builder.recipe(p_267287_)).requirements(RequirementsStrategy.OR);
        p_267089_.accept(new SmithingNBTRecipeBuilder.Result(p_267287_, this.type, this.template, this.base, this.addition, this.result, this.modifier, this.advancement, p_267287_.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation p_267259_) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + p_267259_);
        }
    }

    public static record Result(ResourceLocation id, RecipeSerializer<?> type, Ingredient template, Ingredient base, Ingredient addition, Item result, String modifier, Advancement.Builder advancement, ResourceLocation advancementId) implements FinishedRecipe {
        public void serializeRecipeData(JsonObject p_266713_) {
            p_266713_.add("template", this.template.toJson());
            p_266713_.add("base", this.base.toJson());
            p_266713_.add("addition", this.addition.toJson());
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result).toString());
            p_266713_.add("result", jsonobject);
            p_266713_.addProperty("modifier", this.modifier);
        }

        public ResourceLocation getId() {
            return this.id;
        }

        public RecipeSerializer<?> getType() {
            return this.type;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}

