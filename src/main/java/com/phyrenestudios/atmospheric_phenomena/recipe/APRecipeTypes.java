package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES.getRegistryName(), AtmosphericPhenomena.MODID);

    public static RegistryObject<RecipeType<SmithingNBTRecipe>> SMITHING_NBT = RECIPE_TYPES.register("smithing_nbt",() -> new RecipeType<>() {
        public String toString() {
            return "smithing_nbt";
        }
    });
}
