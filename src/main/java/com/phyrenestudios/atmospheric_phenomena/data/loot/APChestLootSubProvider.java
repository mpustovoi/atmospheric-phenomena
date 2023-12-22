package com.phyrenestudios.atmospheric_phenomena.data.loot;

import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class APChestLootSubProvider implements LootTableSubProvider {
    ///give @p chest{BlockEntityTag:{LootTable:"atmospheric_phenomena:chests/overworld_meteor"}} 1
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> register) {
        register.accept(APLootTables.OVERWORLD_METEOR,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(2))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(75))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(75))
                                .add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))).setWeight(75))
                                .add(LootItem.lootTableItem(APItems.CHARRED_LOG.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))).setWeight(75))
                                .add(EmptyLootItem.emptyItem().setWeight(25)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(2))
                                .add(LootItem.lootTableItem(Items.END_STONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(APItems.LONSDALEITE.get()))
                                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2))
                                .add(LootItem.lootTableItem(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2))
                                .add(EmptyLootItem.emptyItem().setWeight(2))));

        register.accept(APLootTables.OVERWORLD_COMET,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(2))
                                .add(LootItem.lootTableItem(Items.ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 16))).setWeight(20))
                                .add(LootItem.lootTableItem(Items.PACKED_ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))).setWeight(20))
                                .add(EmptyLootItem.emptyItem().setWeight(10)))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.BLUE_ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                                .add(LootItem.lootTableItem(APItems.METEORIC_ICE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
                                .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 12)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2))
                                .add(LootItem.lootTableItem(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2))
                                .add(EmptyLootItem.emptyItem().setWeight(2))));

    }


}
