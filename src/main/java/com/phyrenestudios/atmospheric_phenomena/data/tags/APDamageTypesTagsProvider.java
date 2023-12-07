package com.phyrenestudios.atmospheric_phenomena.data.tags;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.init.APDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class APDamageTypesTagsProvider extends DamageTypeTagsProvider {
    public APDamageTypesTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(output, registries, AtmosphericPhenomena.MODID, fileHelper);
    }

    public String getName() {
        return AtmosphericPhenomena.MODID + " - Damage Type Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(APTags.DamageTypes.IS_METEOROID_DAMAGE).add(APDamageTypes.METEOROID);
    }
}
