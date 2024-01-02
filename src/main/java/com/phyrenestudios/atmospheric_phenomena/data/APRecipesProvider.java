package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import com.phyrenestudios.atmospheric_phenomena.recipe.SmithingNBTRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class APRecipesProvider extends RecipeProvider {

    public APRecipesProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, APItems.LONSDALEITE.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.LONSDALEITE_BLOCK.get());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, APItems.MOISSANITE.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.MOISSANITE_BLOCK.get());
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, APItems.METEORIC_IRON.get(), RecipeCategory.BUILDING_BLOCKS, APBlocks.METEORIC_IRON_BLOCK.get());
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(APItems.METEORIC_IRON.get()), RecipeCategory.MISC, Items.IRON_INGOT, 0.8F, 200).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(recipeOutput, AtmosphericPhenomena.MODID+":iron_from_meteoric_iron_smelting");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.PLATED_SHEET.get(), 1).pattern("GGG").pattern("IPI").define('G', APItems.METEORIC_IRON.get()).define('P', Items.IRON_INGOT).define('I', Items.IRON_INGOT).unlockedBy("has_ingredient", has(APItems.METEORIC_IRON.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.STUDDED_SHEET.get(), 1).pattern("GGG").pattern("IPI").define('G', APItems.LONSDALEITE.get()).define('P', APItems.PLATED_SHEET.get()).define('I', Items.OBSIDIAN).unlockedBy("has_ingredient", has(APItems.LONSDALEITE.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.EMBOSSED_SHEET.get(), 1).pattern("GGG").pattern("IPI").define('G', APItems.MOISSANITE.get()).define('P', APItems.STUDDED_SHEET.get()).define('I', Items.NETHERITE_SCRAP).unlockedBy("has_ingredient", has(APItems.MOISSANITE.get())).save(recipeOutput);


        for (MeteorBlocks base : MeteorBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getBricks(), 4).pattern("BB").pattern("BB").define('B', base.getMeteorBlock()).unlockedBy("has_ingredient", has(base.getMeteorBlock())).save(recipeOutput);
            slabRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks());
            stairsRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            wallRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), 1).pattern("B").pattern("B").define('B', base.getBricksSlab()).unlockedBy("has_ingredient", has(base.getBricksSlab())).save(recipeOutput);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricks(), base.getMeteorBlock());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getMeteorBlock(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getMeteorBlock());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getMeteorBlock());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getMeteorBlock());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksSlab(), base.getBricks(), 2);
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksStairs(), base.getBricks());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getBricksWall(), base.getBricks());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, base.getChiseled(), base.getBricks());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, base.getGlass(), 8).pattern("GGG").pattern("GDG").pattern("GGG").define('G', APTags.Items.LIGHTNING_GLASS).define('D', base.getDyeColor().getTag()).unlockedBy("has_ingredient", has(APTags.Items.LIGHTNING_GLASS)).group(AtmosphericPhenomena.MODID+":lightning_glasses").save(recipeOutput);
        }


        generateForEnabledAPBlockFamilies(recipeOutput, FeatureFlagSet.of(FeatureFlags.VANILLA));
        planksFromLogs(recipeOutput, APBlocks.CHARRED_PLANKS.get(), APTags.Items.CHARRED_LOGS, 4);
        woodFromLogs(recipeOutput, APBlocks.CHARRED_WOOD.get(), APBlocks.CHARRED_LOG.get());
        woodFromLogs(recipeOutput, APBlocks.STRIPPED_CHARRED_WOOD.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
        woodenBoat(recipeOutput, APItems.CHARRED_BOAT.get(), APBlocks.CHARRED_PLANKS.get());
        chestBoat(recipeOutput, APItems.CHARRED_CHEST_BOAT.get(), APItems.CHARRED_BOAT.get());
        hangingSign(recipeOutput, APItems.CHARRED_HANGING_SIGN.get(), APBlocks.STRIPPED_CHARRED_LOG.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, APBlocks.CHARRED_BOOKSHELF.get()).pattern("PPP").pattern("BBB").pattern("PPP").define('P', APItems.CHARRED_PLANKS.get()).define('B',Items.BOOK).unlockedBy("has_ingredient", has(APItems.CHARRED_PLANKS.get())).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get(), 2).define('#', Tags.Items.GEMS_DIAMOND).define('T', APTags.Items.TEKTITES).define('C', APTags.Items.METEORITE_BLOCKS).define('S', APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).pattern("TST").pattern("TCT").pattern("###").unlockedBy(getHasName(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()), has(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get())).save(recipeOutput);

        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_HELMET, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_HELMET, "embossed_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_CHESTPLATE, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_CHESTPLATE, "embossed_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_LEGGINGS, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_LEGGINGS, "embossed_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_BOOTS, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_BOOTS, "embossed_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_PICKAXE, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_PICKAXE, "embossed_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_SHOVEL, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_SHOVEL, "embossed_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_AXE, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_AXE, "embossed_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_HOE, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_HOE, "embossed_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.NETHERITE_SWORD, APItems.EMBOSSED_SHEET.get(), Items.NETHERITE_SWORD, "embossed_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_HELMET, APItems.STUDDED_SHEET.get(), Items.DIAMOND_HELMET, "studded_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_CHESTPLATE, APItems.STUDDED_SHEET.get(), Items.DIAMOND_CHESTPLATE, "studded_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_LEGGINGS, APItems.STUDDED_SHEET.get(), Items.DIAMOND_LEGGINGS, "studded_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_BOOTS, APItems.STUDDED_SHEET.get(), Items.DIAMOND_BOOTS, "studded_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_PICKAXE, APItems.STUDDED_SHEET.get(), Items.DIAMOND_PICKAXE, "studded_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_SHOVEL, APItems.STUDDED_SHEET.get(), Items.DIAMOND_SHOVEL, "studded_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_AXE, APItems.STUDDED_SHEET.get(), Items.DIAMOND_AXE, "studded_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_HOE, APItems.STUDDED_SHEET.get(), Items.DIAMOND_HOE, "studded_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.DIAMOND_SWORD, APItems.STUDDED_SHEET.get(), Items.DIAMOND_SWORD, "studded_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_HELMET, APItems.PLATED_SHEET.get(), Items.IRON_HELMET, "plated_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_CHESTPLATE, APItems.PLATED_SHEET.get(), Items.IRON_CHESTPLATE, "plated_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_LEGGINGS, APItems.PLATED_SHEET.get(), Items.IRON_LEGGINGS, "plated_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_BOOTS, APItems.PLATED_SHEET.get(), Items.IRON_BOOTS, "plated_armor");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_PICKAXE, APItems.PLATED_SHEET.get(), Items.IRON_PICKAXE, "plated_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_SHOVEL, APItems.PLATED_SHEET.get(), Items.IRON_SHOVEL, "plated_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_AXE, APItems.PLATED_SHEET.get(), Items.IRON_AXE, "plated_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_HOE, APItems.PLATED_SHEET.get(), Items.IRON_HOE, "plated_tool");
        nbtSmithing(recipeOutput, RecipeCategory.COMBAT,Items.IRON_SWORD, APItems.PLATED_SHEET.get(), Items.IRON_SWORD, "plated_tool");
    }

    protected static void stonecutterResultFromBase(RecipeOutput p_251589_, RecipeCategory p_248911_, ItemLike p_251265_, ItemLike p_250033_) {
        stonecutterResultFromBase(p_251589_, p_248911_, p_251265_, p_250033_, 1);
    }

    protected static void stonecutterResultFromBase(RecipeOutput p_249145_, RecipeCategory p_250609_, ItemLike p_251254_, ItemLike p_249666_, int p_251462_) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(p_249666_), p_250609_, p_251254_, p_251462_).unlockedBy(getHasName(p_249666_), has(p_249666_)).save(p_249145_, AtmosphericPhenomena.MODID + ":"+getConversionRecipeName(p_251254_, p_249666_) + "_stonecutting");
    }

    private static void slabRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").unlockedBy(getHasName(input), has(input)).save(recipeOutput);
    }

    private static void stairsRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 4).define('#', input).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getHasName(input), has(input)).save(recipeOutput);
    }

    private static void wallRecipe(RecipeOutput recipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(category, output, 6).define('#', input).pattern("###").pattern("###").unlockedBy(getHasName(input), has(input)).save(recipeOutput);
    }


    protected static void nineBlockStorageRecipes(RecipeOutput p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
    }
    protected static void nineBlockStorageRecipes(RecipeOutput p_250423_, RecipeCategory p_250083_, ItemLike p_250042_, RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @Nullable String p_248641_, String p_252237_, @Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).requires(p_251911_).group(p_250414_).unlockedBy(getHasName(p_251911_), has(p_251911_)).save(p_250423_, new ResourceLocation(AtmosphericPhenomena.MODID,p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_).define('#', p_250042_).pattern("###").pattern("###").pattern("###").group(p_248641_).unlockedBy(getHasName(p_250042_), has(p_250042_)).save(p_250423_, new ResourceLocation(AtmosphericPhenomena.MODID,p_250475_));
    }

    protected void generateForEnabledAPBlockFamilies(RecipeOutput p_301146_, FeatureFlagSet p_251836_) {
        APBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(p_313461_ -> generateRecipes(p_301146_, p_313461_, p_251836_));
    }

    protected static void nbtSmithing(RecipeOutput recipeOutput, RecipeCategory p_248986_, Item base, Item material, Item result, String modifierIn) {
        SmithingNBTRecipeBuilder.smithing(Ingredient.of(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(base), Ingredient.of(material), p_248986_, result, modifierIn).unlocks("has_material", has(material)).save(recipeOutput, new ResourceLocation(AtmosphericPhenomena.MODID,getItemName(result) + "_upgrade_smithing"));
    }
}
