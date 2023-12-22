package com.phyrenestudios.atmospheric_phenomena.events;

import com.phyrenestudios.atmospheric_phenomena.init.APAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class ItemAttributeModifierHandler {

    public static void smithingModifiers(ItemAttributeModifierEvent event) {
        ItemStack itemStack = event.getItemStack();
        CompoundTag tag = itemStack.getTag();
        if (tag == null) return;
        if (tag.contains("modifier")) {
            String modifierName = tag.getString("modifier");
            addModifiers(event, modifierName);
        }

    }

    private static void addModifiers(ItemAttributeModifierEvent event, String modifierName) {
        if (modifierName.equals("moissanite_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.MOISSANITE_ARMOR, "moissanite_armor", 2, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("moissanite_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.MOISSANITE_TOOL, "moissanite_tool", 2, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("lonsdaleite_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.LONSDALEITE_ARMOR, "lonsdaleite_armor", 1.5, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("lonsdaleite_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.LONSDALEITE_TOOL, "lonsdaleite_tool", 1.5, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("meteoric_iron_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.METEORIC_IRON_ARMOR, "meteoric_iron_armor", 1, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("meteoric_iron_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.METEORIC_IRON_TOOL, "meteoric_iron_tool", 1, AttributeModifier.Operation.ADDITION));
        }
    }
}
