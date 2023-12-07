package com.phyrenestudios.atmospheric_phenomena.data.advancements;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APTags;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class APAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement ROOT = addItemList(Advancement.Builder.advancement(), List.of(MeteorBlocks.CHONDRITE.getMeteorBlock(), MeteorBlocks.ENSTATITE_CHONDRITE.getMeteorBlock(), MeteorBlocks.CARBONACEOUS_CHONDRITE.getMeteorBlock(), MeteorBlocks.ANGRITE.getMeteorBlock(), MeteorBlocks.UREILITE.getMeteorBlock(), MeteorBlocks.PALLASITE.getMeteorBlock(), MeteorBlocks.MESOSIDERITE.getMeteorBlock())).requirements(RequirementsStrategy.OR).display(MeteorBlocks.CHONDRITE.getMeteorBlock(), Component.translatable("advancements.atmospheric_phenomena.root.title"), Component.translatable("advancements.atmospheric_phenomena.root.description"), new ResourceLocation(AtmosphericPhenomena.MODID,"textures/gui/advancements/chondrite.png"), FrameType.TASK, true, false, false).save(saver, AtmosphericPhenomena.MODID+"/main/root");
        Advancement METEORIC_IRON = Advancement.Builder.advancement().parent(ROOT).display(APItems.METEORIC_IRON.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_meteoric_iron.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_meteoric_iron.description"), null, FrameType.TASK, true, true, false).addCriterion("meteoric_iron", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.METEORIC_IRON.get())).save(saver, AtmosphericPhenomena.MODID+"/main/obtain_meteoric_iron");
        Advancement LONSDALEITE = Advancement.Builder.advancement().parent(METEORIC_IRON).display(APItems.LONSDALEITE.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_lonsdaleite.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_lonsdaleite.description"), null, FrameType.TASK, true, true, false).addCriterion("lonsdaleite", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.LONSDALEITE.get())).save(saver, AtmosphericPhenomena.MODID+"/main/obtain_lonsdaleite");
        Advancement DEBRIS = Advancement.Builder.advancement().parent(LONSDALEITE).display(APItems.DEBRIS_MATRIX.get(), Component.translatable("advancements.atmospheric_phenomena.obtain_debris_matrix.title"), Component.translatable("advancements.atmospheric_phenomena.obtain_debris_matrix.description"), null, FrameType.TASK, true, true, false).addCriterion("debris_matrix", InventoryChangeTrigger.TriggerInstance.hasItems(APItems.DEBRIS_MATRIX.get())).save(saver, AtmosphericPhenomena.MODID+"/main/obtain_debris_matrix");
        Advancement KILLED_BY_METEOROID = Advancement.Builder.advancement().parent(ROOT).display(MeteorBlocks.CHONDRITE.getMeteorBlock(), Component.translatable("advancements.atmospheric_phenomena.killed_by_meteoroid.title"), Component.translatable("advancements.atmospheric_phenomena.killed_by_meteoroid.description"), null, FrameType.TASK, true, true, false).addCriterion("killed_by_meteoroid_damage", KilledTrigger.TriggerInstance.entityKilledPlayer(EntityPredicate.ANY, DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(APTags.DamageTypes.IS_METEOROID_DAMAGE)))).save(saver, AtmosphericPhenomena.MODID+"/main/killed_by_meteoroid");
//, DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(APTags.DamageTypes.IS_METEOROID_DAMAGE)).build()
    }


    private static Advancement.Builder addItemList(Advancement.Builder builder, List<ItemLike> itemList) {
        for (ItemLike itemLike : itemList) {
            builder.addCriterion(ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(itemLike));
        }
        return builder;
    }

}
