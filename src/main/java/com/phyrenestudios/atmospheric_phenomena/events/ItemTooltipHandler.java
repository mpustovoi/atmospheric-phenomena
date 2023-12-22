package com.phyrenestudios.atmospheric_phenomena.events;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipHandler {

    public static void smithingModifiers(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getTag() == null || !itemStack.getTag().contains("modifier")) return;
        String modifierName = itemStack.getTag().getString("modifier");
        if (modifierName.contains("lonsdaleite")) {
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.LIGHT_PURPLE));
        } else if (modifierName.contains("meteoric_iron")) {
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.GREEN));
        } else if (modifierName.contains("moissanite")) {
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.AQUA));
        }
        if (modifierName.contains("_tool")) {
            event.getToolTip().add(Component.translatable("tooltip.modifier.mining_speed").withStyle(ChatFormatting.BLUE));
        }

    }
}
