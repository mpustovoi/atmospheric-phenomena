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
        if (modifierName.equals("embossed_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.EMBOSSED_ARMOR, "embossed_armor", 2, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("embossed_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.EMBOSSED_TOOL, "embossed_tool", 2, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("studded_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.STUDDED_ARMOR, "studded_armor", 1.5, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("studded_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.STUDDED_TOOL, "studded_tool", 1.5, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("plated_armor") && event.getItemStack().getItem() instanceof ArmorItem && event.getSlotType() == ((ArmorItem) event.getItemStack().getItem()).getEquipmentSlot()) {
            event.addModifier(Attributes.ARMOR, new AttributeModifier(APAttributes.PLATED_ARMOR, "plated_armor", 1, AttributeModifier.Operation.ADDITION));
        } else if (modifierName.equals("plated_tool") && (event.getSlotType() == EquipmentSlot.MAINHAND || event.getSlotType() == EquipmentSlot.OFFHAND)) {
            event.addModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(APAttributes.PLATED_TOOL, "plated_tool", 1, AttributeModifier.Operation.ADDITION));
        }
    }
}
