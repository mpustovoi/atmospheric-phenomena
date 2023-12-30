package com.phyrenestudios.atmospheric_phenomena.data.advancements;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.CapsuleBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.LightningGlassBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.entities.APEntityTypes;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class APAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement ROOT = Advancement.Builder.advancement().display(MeteorBlocks.CHONDRITE.getMeteorBlock(), Component.translatable("advancements.atmospheric_phenomena.root.title"), Component.translatable("advancements.atmospheric_phenomena.root.description"), new ResourceLocation(AtmosphericPhenomena.MODID,"textures/gui/advancements/chondrite.png"), FrameType.TASK, true, false, false).addCriterion("obtain_meteorite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(APTags.Items.METEORITE_BLOCKS).build())).save(saver, AtmosphericPhenomena.MODID+":root");

        Advancement METEORIC_IRON = Advancement.Builder.advancement().parent(ROOT).display(APItems.METEORIC_IRON.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_meteoric_iron.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_meteoric_iron.description"), null, FrameType.TASK, true, true, false).addCriterion("meteoric_iron", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.METEORIC_IRON.get())).save(saver, AtmosphericPhenomena.MODID+":obtain_meteoric_iron");
        Advancement CAPSULES = Advancement.Builder.advancement().parent(METEORIC_IRON).display(CapsuleBlocks.PLATED_CAPSULE.getCapsule(), Component.translatable("advancements.atmospheric_phenomena.obtain_capsule.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_capsule.description"), null, FrameType.TASK, true, true, false).addCriterion("obtain_capsule", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(APTags.Items.CAPSULES).build())).save(saver, AtmosphericPhenomena.MODID+":obtain_capsule");
        Advancement LONSDALEITE = Advancement.Builder.advancement().parent(METEORIC_IRON).display(APItems.LONSDALEITE.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_lonsdaleite.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_lonsdaleite.description"), null, FrameType.TASK, true, true, false).addCriterion("lonsdaleite", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.LONSDALEITE.get())).save(saver, AtmosphericPhenomena.MODID+":obtain_lonsdaleite");
        Advancement MOISSANITE = Advancement.Builder.advancement().parent(LONSDALEITE).display(APItems.MOISSANITE.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_moissanite.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_moissanite.description"), null, FrameType.TASK, true, true, false).addCriterion("moissanite", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.MOISSANITE.get())).save(saver, AtmosphericPhenomena.MODID+":obtain_moissanite");
        Advancement KILLED_BY_METEOROID = Advancement.Builder.advancement().parent(CAPSULES).display(MeteorBlocks.CHONDRITE.getMeteorBlock(), Component.translatable("advancements.atmospheric_phenomena.killed_by_meteoroid.title"), Component.translatable("advancements.atmospheric_phenomena.killed_by_meteoroid.description"), null, FrameType.TASK, true, true, false).addCriterion("killed_by_meteoroid", KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.Builder.entity().of(APEntityTypes.METEOR.get()))).save(saver, AtmosphericPhenomena.MODID+":killed_by_meteoroid");

        CompoundTag embossedArmorModifier = new CompoundTag();
        embossedArmorModifier.putString("modifier", "embossed_armor");
        CompoundTag embossedToolModifier = new CompoundTag();
        embossedToolModifier.putString("modifier", "embossed_tool");
        CompoundTag studdedArmorModifier = new CompoundTag();
        studdedArmorModifier.putString("modifier", "studded_armor");
        CompoundTag studdedToolModifier = new CompoundTag();
        studdedToolModifier.putString("modifier", "studded_tool");
        CompoundTag platedArmorModifier = new CompoundTag();
        platedArmorModifier.putString("modifier", "plated_armor");
        CompoundTag platedToolModifier = new CompoundTag();
        platedToolModifier.putString("modifier", "plated_tool");
        Advancement OTHERWORLDLY_UPGRADE = Advancement.Builder.advancement().parent(ROOT).display(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_otherworldly_upgrade_template.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_otherworldly_upgrade_template.description"), null, FrameType.TASK, true, true, false).addCriterion("otherworldly_upgrade_template", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get())).save(saver, AtmosphericPhenomena.MODID+":obtain_otherworldly_upgrade_template");
        Advancement PLATED_EQUIPMENT = Advancement.Builder.advancement().parent(OTHERWORLDLY_UPGRADE).display(APItems.PLATED_SHEET.get(), Component.translatable("advancements.atmospheric_phenomena.plated_equipment.title"), Component.translatable("advancements.atmospheric_phenomena.plated_equipment.description"), null, FrameType.TASK, true, true, false).addCriterion("plated_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(platedArmorModifier).build())).addCriterion("plated_tool", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(platedToolModifier).build())).requirements(RequirementsStrategy.OR).save(saver, AtmosphericPhenomena.MODID+":plated_equipment");
        Advancement STUDDED_EQUIPMENT = Advancement.Builder.advancement().parent(PLATED_EQUIPMENT).display(APItems.STUDDED_SHEET.get(), Component.translatable("advancements.atmospheric_phenomena.studded_equipment.title"), Component.translatable("advancements.atmospheric_phenomena.studded_equipment.description"), null, FrameType.TASK, true, true, false).addCriterion("studded_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(studdedArmorModifier).build())).addCriterion("studded_tool", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(studdedToolModifier).build())).requirements(RequirementsStrategy.OR).save(saver, AtmosphericPhenomena.MODID+":studded_equipment");
        Advancement EMBOSSED_EQUIPMENT = Advancement.Builder.advancement().parent(STUDDED_EQUIPMENT).display(APItems.EMBOSSED_SHEET.get(), Component.translatable("advancements.atmospheric_phenomena.embossed_equipment.title"), Component.translatable("advancements.atmospheric_phenomena.embossed_equipment.description"), null, FrameType.TASK, true, true, false).addCriterion("embossed_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(embossedArmorModifier).build())).addCriterion("embossed_tool", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasNbt(embossedToolModifier).build())).requirements(RequirementsStrategy.OR).save(saver, AtmosphericPhenomena.MODID+":embossed_equipment");
        Advancement UPGRADED_NETHERITE = Advancement.Builder.advancement().parent(EMBOSSED_EQUIPMENT).display(Items.NETHERITE_CHESTPLATE, Component.translatable("advancements.atmospheric_phenomena.emboss_netherite.title"), Component.translatable("advancements.atmospheric_phenomena.emboss_netherite.description"), null, FrameType.CHALLENGE, true, true, false).rewards(AdvancementRewards.Builder.experience(100)).addCriterion("embossed_netherite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.NETHERITE_HELMET).hasNbt(embossedArmorModifier).build(), ItemPredicate.Builder.item().of(Items.NETHERITE_CHESTPLATE).hasNbt(embossedArmorModifier).build(), ItemPredicate.Builder.item().of(Items.NETHERITE_LEGGINGS).hasNbt(embossedArmorModifier).build(), ItemPredicate.Builder.item().of(Items.NETHERITE_BOOTS).hasNbt(embossedArmorModifier).build())).save(saver, AtmosphericPhenomena.MODID+":emboss_netherite");

        Advancement VITRIFIED_BLOCKS = Advancement.Builder.advancement().parent(ROOT).display(LightningGlassBlocks.ORANGE_LIGHTNING_GLASS.getGlass(), Component.translatable("advancements.atmospheric_phenomena.obtain_vitrified_blocks.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_vitrified_blocks.description"), null, FrameType.TASK, true, true, false).addCriterion("ligntning_glass", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(APTags.Items.LIGHTNING_GLASS).build())).addCriterion("soil_fulgurite", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.SOIL_FULGURITE.get())).addCriterion("stone_fulgurite", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.STONE_FULGURITE.get())).requirements(RequirementsStrategy.OR).save(saver, AtmosphericPhenomena.MODID+":obtain_vitrified_blocks");
        Advancement CONDUCTIVE = Advancement.Builder.advancement().parent(VITRIFIED_BLOCKS).display(Items.COPPER_BLOCK, Component.translatable("advancements.atmospheric_phenomena.conductive_line.title"), Component.translatable("advancements.atmospheric_phenomena.conductive_line.description"), null, FrameType.TASK, true, true, false).addCriterion("conductive_line", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.LIGHTNING_BOLT))).save(saver, AtmosphericPhenomena.MODID+":conductive_line");
        Advancement DISCHARGED = Advancement.Builder.advancement().parent(CONDUCTIVE).display(APItems.CHARGED_QUARTZ_MATRIX.get(), Component.translatable("advancements.atmospheric_phenomena.killed_by_discharge.title"), Component.translatable("advancements.atmospheric_phenomena.killed_by_discharge.description"), null, FrameType.TASK, true, true, false).addCriterion("killed_by_discharge", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.LIGHTNING_BOLT))).save(saver, AtmosphericPhenomena.MODID+":killed_by_discharge");
        Advancement THUNDERSTRUCK = Advancement.Builder.advancement().parent(DISCHARGED).display(APItems.CHARGED_QUARTZ_MATRIX.get(), Component.translatable("advancements.atmospheric_phenomena.discharge_entity.title"), Component.translatable("advancements.atmospheric_phenomena.discharge_entity.description"), null, FrameType.CHALLENGE, true, true, false).rewards(AdvancementRewards.Builder.experience(100)).addCriterion("discharge_entity", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.LIGHTNING_BOLT))).save(saver, AtmosphericPhenomena.MODID+":discharge_entity");

    }


    private static Advancement.Builder addItemList(Advancement.Builder builder, List<ItemLike> itemList) {
        for (ItemLike itemLike : itemList) {
            builder.addCriterion(ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(itemLike));
        }
        return builder;
    }

}
