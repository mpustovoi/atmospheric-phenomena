package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.items.APItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class APTrimMaterials {
    public static final ResourceKey<TrimMaterial> LONSDALEITE = registryKey("lonsdaleite");
    public static final ResourceKey<TrimMaterial> METEORIC_IRON = registryKey("meteoric_iron");

    private static ResourceKey<TrimMaterial> registryKey(String p_266965_) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(AtmosphericPhenomena.MODID, p_266965_));
    }

    public static void bootstrap(BootstapContext<TrimMaterial> p_267033_) {
        register(p_267033_, LONSDALEITE, APItems.LONSDALEITE.get(), Style.EMPTY.withColor(11238262), 0.1F);
        register(p_267033_, METEORIC_IRON, APItems.METEORIC_IRON.get(), Style.EMPTY.withColor(8619629), 0.1F);
    }

    private static void register(BootstapContext<TrimMaterial> p_268176_, ResourceKey<TrimMaterial> p_268293_, Item p_268156_, Style p_268174_, float p_268274_) {
        register(p_268176_, p_268293_, p_268156_, p_268174_, p_268274_, Map.of());
    }

    private static void register(BootstapContext<TrimMaterial> p_268244_, ResourceKey<TrimMaterial> p_268139_, Item p_268311_, Style p_268232_, float p_268197_, Map<ArmorMaterials, String> p_268352_) {
        TrimMaterial trimmaterial = TrimMaterial.create(p_268139_.location().getPath(), p_268311_, p_268197_, Component.translatable(Util.makeDescriptionId("trim_material", p_268139_.location())).withStyle(p_268232_), p_268352_);
        p_268244_.register(p_268139_, trimmaterial);
    }
}
