package com.phyrenestudios.atmospheric_phenomena.data.lang;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class APEnUsLangProvider extends LanguageProvider {
    private final String locale;
    public APEnUsLangProvider(PackOutput packOutput, String locale) {
        super(packOutput, AtmosphericPhenomena.MODID, locale);
        this.locale = locale;
    }

    @Override
    public String getName() {
        return AtmosphericPhenomena.MODID + ": " + locale;
    }

    @Override
    protected void addTranslations() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            add(base.getMeteorBlock());
            add(base.getBricks());
            add(base.getBricksSlab());
            add(base.getBricksStairs());
            add(base.getBricksWall());
            add(base.getChiseled());
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            add(base.getTektite());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            add(base.getGlass());
        }
        for (CapsuleBlocks base : CapsuleBlocks.values()) {
            add(base.getCapsule());
        }
        add(APBlocks.KAMACITE.get());
        add(APBlocks.TAENITE.get());
        add(APBlocks.TETRATAENITE.get());
        add(APBlocks.GOLDEN_MATRIX.get());
        add(APBlocks.QUARTZ_MATRIX.get());
        add(APBlocks.CHARGED_QUARTZ_MATRIX.get());
        add(APBlocks.DEBRIS_MATRIX.get());
        add(APBlocks.LONSDALEITE_MATRIX.get());
        add(APBlocks.LONSDALEITE_BLOCK.get(), "Block of Lonsdaleite");
        add(APBlocks.MOISSANITE_BLOCK.get(), "Block of Moissanite");
        add(APBlocks.METEORIC_IRON_BLOCK.get(), "Block of Meteoric Iron");
        add(APBlocks.METEORIC_ICE.get());
        add(APBlocks.SOIL_FULGURITE.get());
        add(APBlocks.STONE_FULGURITE.get());
        add(APItems.LONSDALEITE.get());
        add(APItems.MOISSANITE.get());
        add(APItems.METEORIC_IRON.get());
        add(APItems.PLATED_SHEET.get());
        add(APItems.STUDDED_SHEET.get());
        add(APItems.EMBOSSED_SHEET.get());

        add(APBlocks.BURNING_LOG.get());
        add(APBlocks.BURNING_WOOD.get());
        add(APBlocks.SMOULDERING_LOG.get());
        add(APBlocks.SMOULDERING_WOOD.get());
        add(APBlocks.CHARRED_LOG.get());
        add(APBlocks.STRIPPED_CHARRED_LOG.get());
        add(APBlocks.CHARRED_WOOD.get());
        add(APBlocks.STRIPPED_CHARRED_WOOD.get());
        add(APBlocks.CHARRED_PLANKS.get());
        add(APBlocks.CHARRED_SLAB.get());
        add(APBlocks.CHARRED_STAIRS.get());
        add(APBlocks.CHARRED_FENCE.get());
        add(APBlocks.CHARRED_FENCE_GATE.get());
        add(APBlocks.CHARRED_DOOR.get());
        add(APBlocks.CHARRED_TRAPDOOR.get());
        add(APBlocks.CHARRED_PRESSURE_PLATE.get());
        add(APBlocks.CHARRED_BUTTON.get());
        add(APBlocks.CHARRED_BOOKSHELF.get());
        add(APItems.CHARRED_SIGN.get());
        add(APItems.CHARRED_HANGING_SIGN.get());
        add(APItems.CHARRED_BOAT.get());
        add(APItems.CHARRED_CHEST_BOAT.get());
        add(APItems.OTHERWORLDLY_UPGRADE_SMITHING_TEMPLATE.get());

        add("itemGroup.atmospheric_phenomena.main", "Atmospheric Phenomena");
        add("entity.atmospheric_phenomena.meteor", "Meteor");
        add("entity.atmospheric_phenomena.comet", "Comet");
        add("entity.atmospheric_phenomena.ap_boat", "Boat");
        add("entity.atmospheric_phenomena.ap_chest_boat", "Chest Boat");
        add("atmospheric_phenomena.subtitle.atmospheric_entry", "Atmospheric Entry");
        add("gamerule.createImpactCraters", "Meteoroids create impact craters on land");
        add("death.attack.meteoroid", "%1$s was pummeled by a meteoroid");
        add("death.attack.meteoroid.player", "%1$s was pummeled by a meteoroid whilst trying to escape %2$s");
        add("trim_material.atmospheric_phenomena.lonsdaleite", "Lonsdaleite Material");
        add("trim_material.atmospheric_phenomena.meteoric_iron", "Meteoric Iron Material");
        add("container.capsule.filled", "Filled");
        add("container.capsule.name", "Capsule");

        //Smithing Modifiers
        add("tooltip.modifier.mining_speed", "+25% Mining Speed");
        add("tooltip.modifier.embossed_armor", "Embossed");
        add("tooltip.modifier.embossed_tool", "Embossed");
        add("tooltip.modifier.studded_armor", "Studded");
        add("tooltip.modifier.studded_tool", "Studded");
        add("tooltip.modifier.plated_armor", "Plated");
        add("tooltip.modifier.plated_tool", "Plated");

        add("item.atmospheric_phenomena.smithing_template.otherworldly_upgrade.additions_slot_description", "Add upgrade sheet");
        add("item.atmospheric_phenomena.smithing_template.otherworldly_upgrade.applies_to", "Equipment");
        add("item.atmospheric_phenomena.smithing_template.otherworldly_upgrade.base_slot_description", "Add armor, weapon, or tool");
        add("item.atmospheric_phenomena.smithing_template.otherworldly_upgrade.ingredients", "Sheets");
        add("upgrade.atmospheric_phenomena.otherworldly_upgrade", "Otherworldly Upgrade");

        //Advancements
        add("advancements.atmospheric_phenomena.root.title", "Atmospheric Phenomena");
        add("advancements.atmospheric_phenomena.root.description", "Seeing is believing");
        add("advancements.atmospheric_phenomena.obtain_meteoric_iron.title", "Not Quite Rusty");
        add("advancements.atmospheric_phenomena.obtain_meteoric_iron.description", "Obtain a chunk of meteoric iron from a fallen meteorite");
        add("advancements.atmospheric_phenomena.obtain_lonsdaleite.title", "Pink Diamonds!");
        add("advancements.atmospheric_phenomena.obtain_lonsdaleite.description", "Obtain a lonsdaleite gem (a diamond polymorph) from a fallen meteorite");
        add("advancements.atmospheric_phenomena.obtain_moissanite.title", "These Gotta Be Diamonds..");
        add("advancements.atmospheric_phenomena.obtain_moissanite.description", "Obtain a moissanite gem (silicon carbide) from a fallen meteorite");
        add("advancements.atmospheric_phenomena.obtain_capsule.title", "Signed and Sealed");
        add("advancements.atmospheric_phenomena.obtain_capsule.description", "Retrieve a capsule, and get those items out by using a hopper");
        add("advancements.atmospheric_phenomena.spyglass_at_meteoroid.title", "The Sky Is Falling");
        add("advancements.atmospheric_phenomena.spyglass_at_meteoroid.description", "Look at a meteor through a Spyglass");
        add("advancements.atmospheric_phenomena.killed_by_meteoroid.title", "The Way of the Dinosaurs");
        add("advancements.atmospheric_phenomena.killed_by_meteoroid.description", "Face the full impact of a falling meteoroid");
        add("advancements.atmospheric_phenomena.obtain_otherworldly_upgrade_template.title", "Smithing for Skill");
        add("advancements.atmospheric_phenomena.obtain_otherworldly_upgrade_template.description", "Find a new smithing template inside meteorite capsules to upgrade equipment");
        add("advancements.atmospheric_phenomena.plated_equipment.title", "Spaceware");
        add("advancements.atmospheric_phenomena.plated_equipment.description", "Use a plated sheet to upgrade iron equipment");
        add("advancements.atmospheric_phenomena.studded_equipment.title", "You Stud");
        add("advancements.atmospheric_phenomena.studded_equipment.description", "Use a studded sheet to upgrade diamond equipment");
        add("advancements.atmospheric_phenomena.embossed_equipment.title", "Like a Boss");
        add("advancements.atmospheric_phenomena.embossed_equipment.description", "Use a embossed sheet to upgrade netherite equipment");
        add("advancements.atmospheric_phenomena.emboss_netherite.title", "Fine Suit Indeed");
        add("advancements.atmospheric_phenomena.emboss_netherite.description", "Emboss a full suit of netherite armor for maximum protection");
        add("advancements.atmospheric_phenomena.obtain_vitrified_blocks.title", "Glassy");
        add("advancements.atmospheric_phenomena.obtain_vitrified_blocks.description", "Find the remnants of a lightning strike seared into the ground");
        add("advancements.atmospheric_phenomena.conductive_line.title", "Like a Long Rod");
        add("advancements.atmospheric_phenomena.conductive_line.description", "Direct the energy of a lightning strike using conductive blocks");
        add("advancements.atmospheric_phenomena.killed_by_discharge.title", "Discharged");
        add("advancements.atmospheric_phenomena.killed_by_discharge.description", "Unleash the stored power of a lightning strike... on yourself");
        add("advancements.atmospheric_phenomena.discharge_entity.title", "Thunderstruck");
        add("advancements.atmospheric_phenomena.discharge_entity.description", "Convert a mob using stored lightning energy");
    }

    private void add(Block blockIn) {
        add(blockIn, parseLangName(BuiltInRegistries.BLOCK.getKey(blockIn).getPath()));
    }
    private void add(Item itemIn) {
        add(itemIn, parseLangName(BuiltInRegistries.ITEM.getKey(itemIn).getPath()));
    }

    private String parseLangName(String registryName) {
        String LangName = "";
        for (String s : registryName.split("_")) {
            if (LangName.equals("")) {
                LangName = s.substring(0,1).toUpperCase() + s.substring(1);
            } else {
                LangName = LangName + " " + s.substring(0,1).toUpperCase() + s.substring(1);
            }
        }
        return LangName;
    }

}
