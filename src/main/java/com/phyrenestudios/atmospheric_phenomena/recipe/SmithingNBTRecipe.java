package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class SmithingNBTRecipe implements SmithingRecipe {
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final String modifier;

    public SmithingNBTRecipe(Ingredient p_266750_, Ingredient p_266787_, Ingredient p_267292_, ItemStack resultIn, String modifierIn) {
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
            itemstack.setHoverName(Component.translatable("tooltip.modifier." + modifier).append(" ").append(itemstack.getHoverName().copy()));
        }
        return itemstack;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267209_) {
        return this.result;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack p_267113_) {
        return this.template.test(p_267113_);
    }

    @Override
    public boolean isBaseIngredient(ItemStack p_267276_) {
        return this.base.test(p_267276_);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack p_267260_) {
        return this.addition.test(p_267260_);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return APRecipeSerializers.SMITHING_NBT_RECIPE_SERIALIZER.get();
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(net.neoforged.neoforge.common.CommonHooks::hasNoElements);
    }

    public static class Serializer implements RecipeSerializer<SmithingNBTRecipe> {
        private static final Codec<SmithingNBTRecipe> CODEC = RecordCodecBuilder.create(
                p_311739_ -> p_311739_.group(
                                Ingredient.CODEC.fieldOf("template").forGetter(p_301310_ -> p_301310_.template),
                                Ingredient.CODEC.fieldOf("base").forGetter(p_300938_ -> p_300938_.base),
                                Ingredient.CODEC.fieldOf("addition").forGetter(p_301153_ -> p_301153_.addition),
                                ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(p_300935_ -> p_300935_.result),
                                ExtraCodecs.strictOptionalField(Codec.STRING, "modifier", "").forGetter(p_300947_ -> p_300947_.modifier)
                        )
                        .apply(p_311739_, SmithingNBTRecipe::new)
        );

        @Override
        public Codec<SmithingNBTRecipe> codec() {
            return CODEC;
        }

        public SmithingNBTRecipe fromNetwork(FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
            Ingredient ingredient2 = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            String modifier = buffer.readUtf();
            return new SmithingNBTRecipe(ingredient, ingredient1, ingredient2, itemstack, modifier);
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

