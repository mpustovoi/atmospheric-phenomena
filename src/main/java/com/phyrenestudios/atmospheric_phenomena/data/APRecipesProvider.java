package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import com.phyrenestudios.atmospheric_phenomena.recipe.SmithingNBTRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class APRecipesProvider extends RecipeProvider {

    public APRecipesProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, APItems.LONSDALEITE.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.LONSDALEITE_BLOCK.get());
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, APItems.METEORIC_IRON.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.METEORIC_IRON_BLOCK.get());
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(APItems.METEORIC_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.8F, 200).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(consumer, AtmosphericPhenomena.MODID+":iron_from_meteoric_iron_smelting");



        for (MeteorBlocks base : MeteorBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getBricks(), 4).pattern("BB").pattern("BB").define('B', base.getMeteorBlock()).unlockedBy("has_ingredient", has(base.getMeteorBlock())).save(consumer);
            slabRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks());
            stairsRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            wallRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), 1).pattern("B").pattern("B").define('B', base.getBricksSlab()).unlockedBy("has_ingredient", has(base.getBricksSlab())).save(consumer);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricks(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getMeteorBlock(), 2);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getMeteorBlock());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks(), 2);
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            stonecutterResultFromBase(consumer, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getBricks());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getGlass(), 8).pattern("GGG").pattern("GDG").pattern("GGG").define('G', APTags.Items.LIGHTNING_GLASS).define('D', base.getDyeColor().getTag()).unlockedBy("has_ingredient", has(APTags.Items.LIGHTNING_GLASS)).group(AtmosphericPhenomena.MODID+":lightning_glasses").save(consumer);
        }


        generateForEnabledAPBlockFamilies(consumer, FeatureFlagSet.of(FeatureFlags.VANILLA));
        planksFromLogs(consumer, APBlocks.CHARRED_PLANKS.get(), APTags.Items.CHARRED_LOGS, 4);
        woodFromLogs(consumer, APBlocks.CHARRED_WOOD.get(), APBlocks.CHARRED_LOG.get());
        woodFromLogs(consumer, APBlocks.STRIPPED_CHARRED_WOOD.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
        woodenBoat(consumer, APItems.CHARRED_BOAT.get(), APBlocks.CHARRED_PLANKS.get());
        chestBoat(consumer, APItems.CHARRED_CHEST_BOAT.get(), APItems.CHARRED_BOAT.get());
        hangingSign(consumer, APItems.CHARRED_HANGING_SIGN.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, APBlocks.CHARRED_BOOKSHELF.get()).pattern("PPP").pattern("BBB").pattern("PPP").define('P', APItems.CHARRED_PLANKS.get()).define('B',Items.BOOK).unlockedBy("has_ingredient", has(APItems.CHARRED_PLANKS.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get(), 2).define('#', Tags.Items.GEMS_DIAMOND).define('T', APTags.Items.TEKTITES).define('C', APTags.Items.METEORITE_BLOCKS).define('S', APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).pattern("TST").pattern("TCT").pattern("###").unlockedBy(getHasName(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()), has(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get())).save(consumer);

        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_HELMET, APItems.LONSDALEITE.get(), Items.DIAMOND_HELMET, "lonsdaleite_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_CHESTPLATE, APItems.LONSDALEITE.get(), Items.DIAMOND_CHESTPLATE, "lonsdaleite_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_LEGGINGS, APItems.LONSDALEITE.get(), Items.DIAMOND_LEGGINGS, "lonsdaleite_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_BOOTS, APItems.LONSDALEITE.get(), Items.DIAMOND_BOOTS, "lonsdaleite_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_PICKAXE, APItems.LONSDALEITE.get(), Items.DIAMOND_PICKAXE, "lonsdaleite_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_SHOVEL, APItems.LONSDALEITE.get(), Items.DIAMOND_SHOVEL, "lonsdaleite_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_AXE, APItems.LONSDALEITE.get(), Items.DIAMOND_AXE, "lonsdaleite_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_HOE, APItems.LONSDALEITE.get(), Items.DIAMOND_HOE, "lonsdaleite_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.DIAMOND_SWORD, APItems.LONSDALEITE.get(), Items.DIAMOND_SWORD, "lonsdaleite_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_HELMET, APItems.METEORIC_IRON.get(), Items.IRON_HELMET, "meteoric_iron_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_CHESTPLATE, APItems.METEORIC_IRON.get(), Items.IRON_CHESTPLATE, "meteoric_iron_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_LEGGINGS, APItems.METEORIC_IRON.get(), Items.IRON_LEGGINGS, "meteoric_iron_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_BOOTS, APItems.METEORIC_IRON.get(), Items.IRON_BOOTS, "meteoric_iron_armor");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_PICKAXE, APItems.METEORIC_IRON.get(), Items.IRON_PICKAXE, "meteoric_iron_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_SHOVEL, APItems.METEORIC_IRON.get(), Items.IRON_SHOVEL, "meteoric_iron_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_AXE, APItems.METEORIC_IRON.get(), Items.IRON_AXE, "meteoric_iron_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_HOE, APItems.METEORIC_IRON.get(), Items.IRON_HOE, "meteoric_iron_tool");
        nbtSmithing(consumer, RecipeCategory.COMBAT,Items.IRON_SWORD, APItems.METEORIC_IRON.get(), Items.IRON_SWORD, "meteoric_iron_tool");
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_251589_, RecipeCategory p_248911_, ItemLike p_251265_, ItemLike p_250033_) {
        stonecutterResultFromBase(p_251589_, p_248911_, p_251265_, p_250033_, 1);
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> p_249145_, RecipeCategory p_250609_, ItemLike p_251254_, ItemLike p_249666_, int p_251462_) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(p_249666_), p_250609_, p_251254_, p_251462_).unlockedBy(getHasName(p_249666_), has(p_249666_)).save(p_249145_, AtmosphericPhenomena.MODID + ":"+getConversionRecipeName(p_251254_, p_249666_) + "_stonecutting");
    }

    private static void slabRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }

    private static void stairsRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 4).define('#', input).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }

    private static void wallRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").pattern("###").unlockedBy(getHasName(input), has(input)).save(consumer);
    }


    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
    }
    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_250423_, RecipeCategory p_250083_, ItemLike p_250042_, RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @Nullable String p_248641_, String p_252237_, @Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).requires(p_251911_).group(p_250414_).unlockedBy(getHasName(p_251911_), has(p_251911_)).save(p_250423_, new ResourceLocation(AtmosphericPhenomena.MODID,p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_).define('#', p_250042_).pattern("###").pattern("###").pattern("###").group(p_248641_).unlockedBy(getHasName(p_250042_), has(p_250042_)).save(p_250423_, new ResourceLocation(AtmosphericPhenomena.MODID,p_250475_));
    }

    protected void generateForEnabledAPBlockFamilies(Consumer<FinishedRecipe> p_249188_, FeatureFlagSet p_251836_) {
        APBlockFamilies.getAllFamilies().filter((p_248034_) -> {
            return p_248034_.shouldGenerateRecipe(p_251836_);
        }).forEach((p_176624_) -> {
            generateRecipes(p_249188_, p_176624_);
        });
    }

    protected static void nbtSmithing(Consumer<FinishedRecipe> consumer, RecipeCategory p_248986_, Item base, Item material, Item result, String modifierIn) {
        SmithingNBTRecipeBuilder.smithing(Ingredient.of(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(base), Ingredient.of(material), p_248986_, result, modifierIn).unlocks("has_lonsdaleite", has(APItems.LONSDALEITE.get())).save(consumer, getItemName(result) + "_upgrade_smithing");
    }
}
