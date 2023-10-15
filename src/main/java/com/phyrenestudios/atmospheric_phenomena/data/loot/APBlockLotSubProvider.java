package com.phyrenestudios.atmospheric_phenomena.data.loot;

import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.TektiteBlocks;
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
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.stream.Collectors;

public class APBlockLotSubProvider extends BlockLootSubProvider {
    protected static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    protected static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    protected static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

    public APBlockLotSubProvider() {
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
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            dropSelf(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            dropSelf(base.getGlass());
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
        dropSelf(APBlocks.METEORIC_ICE.get());
        dropSelf(APBlocks.SOIL_FULGURITE.get());
        dropSelf(APBlocks.SSTONE_FULGURITE.get());
    }

    private void addOre(Block blk, ItemLike itemLike) {
        add(blk, createSilkTouchDispatchTable(blk, this.applyExplosionDecay(blk, LootItem.lootTableItem(itemLike).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }
    private void addSilkTouchSingleItem(Block blk, ItemLike itemLike) {
        add(blk, createSingleItemTableWithSilkTouch(blk, itemLike));
    }
}
