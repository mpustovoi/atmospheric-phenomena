package com.phyrenestudios.atmospheric_phenomena.data.loot;

import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.stream.Collectors;

public class APBlockLootSubProvider extends BlockLootSubProvider {
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

    public APBlockLootSubProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return APBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            dropSelf(base.getMeteorBlock());
            dropSelf(base.getBricks());
            addSlab(base.getBricksSlab());
            dropSelf(base.getBricksStairs());
            dropSelf(base.getBricksWall());
            dropSelf(base.getChiseled());
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            dropSelf(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            dropSelf(base.getGlass());
        }
        for (CapsuleBlocks base : CapsuleBlocks.values()) {
            dropSelf(base.getCapsule());
        }

        addOre(APBlocks.KAMACITE.get(), APItems.METEORIC_IRON.get());
        addOre(APBlocks.TAENITE.get(), APItems.METEORIC_IRON.get());
        addOre(APBlocks.TETRATAENITE.get(), APItems.METEORIC_IRON.get());
        addOre(APBlocks.QUARTZ_MATRIX.get(), Items.QUARTZ);
        addOre(APBlocks.CHARGED_QUARTZ_MATRIX.get(), Items.QUARTZ);
        addOre(APBlocks.GOLDEN_MATRIX.get(), Items.RAW_GOLD);
        addOre(APBlocks.DEBRIS_MATRIX.get(), Items.NETHERITE_SCRAP);
        addOre(APBlocks.LONSDALEITE_MATRIX.get(), APItems.LONSDALEITE.get());
        dropSelf(APBlocks.LONSDALEITE_BLOCK.get());
        dropSelf(APBlocks.METEORIC_IRON_BLOCK.get());
        dropSelf(APBlocks.METEORIC_ICE.get());
        dropSelf(APBlocks.SOIL_FULGURITE.get());
        dropSelf(APBlocks.STONE_FULGURITE.get());


        dropOther(APBlocks.BURNING_LOG.get(), APBlocks.CHARRED_LOG.get());
        dropOther(APBlocks.BURNING_WOOD.get(), APBlocks.CHARRED_WOOD.get());
        dropOther(APBlocks.SMOULDERING_LOG.get(), APBlocks.CHARRED_LOG.get());
        dropOther(APBlocks.SMOULDERING_WOOD.get(), APBlocks.CHARRED_WOOD.get());
        dropSelf(APBlocks.CHARRED_LOG.get());
        dropSelf(APBlocks.STRIPPED_CHARRED_LOG.get());
        dropSelf(APBlocks.CHARRED_WOOD.get());
        dropSelf(APBlocks.STRIPPED_CHARRED_WOOD.get());
        dropSelf(APBlocks.CHARRED_PLANKS.get());
        addSlab(APBlocks.CHARRED_SLAB.get());
        dropSelf(APBlocks.CHARRED_STAIRS.get());
        dropSelf(APBlocks.CHARRED_FENCE.get());
        dropSelf(APBlocks.CHARRED_FENCE_GATE.get());
        addDoor(APBlocks.CHARRED_DOOR.get());
        dropSelf(APBlocks.CHARRED_TRAPDOOR.get());
        dropSelf(APBlocks.CHARRED_PRESSURE_PLATE.get());
        dropSelf(APBlocks.CHARRED_BUTTON.get());
        dropSelf(APBlocks.CHARRED_SIGN.get());
        dropOther(APBlocks.CHARRED_WALL_SIGN.get(), APBlocks.CHARRED_SIGN.get());
        dropSelf(APBlocks.CHARRED_HANGING_SIGN.get());
        dropOther(APBlocks.CHARRED_WALL_HANGING_SIGN.get(), APBlocks.CHARRED_HANGING_SIGN.get());
        addSilkTouchSingleItem(APBlocks.CHARRED_BOOKSHELF.get(), Items.BOOK, ConstantValue.exactly(3.0F));
    }
    private void addSlab(Block blk) {
        add(blk, createSlabItemTable(blk));
    }
    private void addDoor(Block blk) {
        add(blk, createDoorTable(blk));
    }
    private void addOre(Block blk, ItemLike itemLike) {
        add(blk, createSilkTouchDispatchTable(blk, this.applyExplosionDecay(blk, LootItem.lootTableItem(itemLike).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }
    private void addSilkTouchSingleItem(Block blk, ItemLike itemLike) {
        add(blk, createSingleItemTableWithSilkTouch(blk, itemLike));
    }
    private void addSilkTouchSingleItem(Block blk, ItemLike itemLike, NumberProvider p_250047_) {
        add(blk, createSingleItemTableWithSilkTouch(blk, itemLike, p_250047_));
    }
}
