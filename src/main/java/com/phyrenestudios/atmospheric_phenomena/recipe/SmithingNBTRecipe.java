package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class SmithingNBTRecipe implements SmithingRecipe {
    private final ResourceLocation id;
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final String modifier;

    public SmithingNBTRecipe(ResourceLocation p_267143_, Ingredient p_266750_, Ingredient p_266787_, Ingredient p_267292_, ItemStack resultIn, String modifierIn) {
        this.id = p_267143_;
        this.template = p_266750_;
        this.base = p_266787_;
        this.addition = p_267292_;
        this.result = resultIn;
        this.modifier = modifierIn;
    }

    public boolean matches(Container container, Level levelIn) {
        return this.template.test(container.getItem(0)) && this.base.test(container.getItem(1)) && this.addition.test(container.getItem(2)) && !this.modifier.equals(container.getItem(1).getTag().getString("modifier"));
    }

    public ItemStack assemble(Container p_267036_, RegistryAccess p_266699_) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = p_267036_.getItem(1).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }
        if (!itemstack.getTag().contains("modifier")) {
            itemstack.getOrCreateTag().putString("modifier", modifier);
        }
        return itemstack;
    }

    public ItemStack getResultItem(RegistryAccess p_267209_) {
        return this.result;
    }

    public boolean isTemplateIngredient(ItemStack p_267113_) {
        return this.template.test(p_267113_);
    }

    public boolean isBaseIngredient(ItemStack p_267276_) {
        return this.base.test(p_267276_);
    }

    public boolean isAdditionIngredient(ItemStack p_267260_) {
        return this.addition.test(p_267260_);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return APRecipeSerializers.SMITHING_NBT_RECIPE_SERIALIZER.get();
    }

    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(net.minecraftforge.common.ForgeHooks::hasNoElements);
    }

    public static class Serializer implements RecipeSerializer<SmithingNBTRecipe> {
        public SmithingNBTRecipe fromJson(ResourceLocation p_266953_, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "template"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "addition"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            String modifier = GsonHelper.getAsString(jsonObject, "modifier");
            return new SmithingNBTRecipe(p_266953_, ingredient, ingredient1, ingredient2, itemstack, modifier);
        }

        public SmithingNBTRecipe fromNetwork(ResourceLocation p_267117_, FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
            Ingredient ingredient2 = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            String modifier = buffer.readUtf();
            return new SmithingNBTRecipe(p_267117_, ingredient, ingredient1, ingredient2, itemstack, modifier);
        }

        public void toNetwork(FriendlyByteBuf buffer, SmithingNBTRecipe recipeIn) {
            recipeIn.template.toNetwork(buffer);
            recipeIn.base.toNetwork(buffer);
            recipeIn.addition.toNetwork(buffer);
            buffer.writeItem(recipeIn.result);
            buffer.writeUtf(recipeIn.modifier);
        }
    }
}

