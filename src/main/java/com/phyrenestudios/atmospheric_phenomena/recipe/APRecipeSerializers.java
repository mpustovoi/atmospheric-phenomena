package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryName(), AtmosphericPhenomena.MODID);

    public static RegistryObject<RecipeSerializer<SmithingNBTRecipe>> SMITHING_NBT_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("smithing_nbt", SmithingNBTRecipe.Serializer::new);
}
