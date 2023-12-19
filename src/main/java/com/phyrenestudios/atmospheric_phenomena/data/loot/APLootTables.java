package com.phyrenestudios.atmospheric_phenomena.data.loot;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;

import com.google.common.collect.Sets;
import java.util.Set;

public class APLootTables {
    private static final Set<ResourceLocation> AP_LOOT_TABLES = Sets.newHashSet();

    public static final ResourceLocation OVERWORLD_METEOR = register("chests/overworld_meteor");

    private static ResourceLocation register(String path) {
        return register(new ResourceLocation(AtmosphericPhenomena.MODID, path));
    }

    private static ResourceLocation register(ResourceLocation p_78770_) {
        if (AP_LOOT_TABLES.add(p_78770_)) {
            return p_78770_;
        } else {
            throw new IllegalArgumentException(p_78770_ + " is already a registered built-in loot table");
        }
    }
}
