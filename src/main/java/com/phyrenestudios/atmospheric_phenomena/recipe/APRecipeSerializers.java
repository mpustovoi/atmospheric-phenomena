package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, AtmosphericPhenomena.MODID);

    public static Supplier<RecipeSerializer<SmithingNBTRecipe>> SMITHING_NBT_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("smithing_nbt", SmithingNBTRecipe.Serializer::new);
}
