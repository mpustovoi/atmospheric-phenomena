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
        if (modifierName.contains("studded")) {
            event.getToolTip().add(Component.empty());
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC));
        } else if (modifierName.contains("plated")) {
            event.getToolTip().add(Component.empty());
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.ITALIC));
        } else if (modifierName.contains("embossed")) {
            event.getToolTip().add(Component.empty());
            event.getToolTip().add(Component.translatable("tooltip.modifier." + modifierName).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC));
        }
        if (modifierName.contains("_tool")) {
            event.getToolTip().add(Component.translatable("tooltip.modifier.mining_speed").withStyle(ChatFormatting.BLUE));
        }

    }
}
