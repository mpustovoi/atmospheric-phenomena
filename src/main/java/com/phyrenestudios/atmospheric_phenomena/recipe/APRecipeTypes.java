package com.phyrenestudios.atmospheric_phenomena.recipe;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, AtmosphericPhenomena.MODID);

    public static Supplier<RecipeType<SmithingNBTRecipe>> SMITHING_NBT = RECIPE_TYPES.register("smithing_nbt",() -> new RecipeType<>() {
        public String toString() {
            return "smithing_nbt";
        }
    });
}
