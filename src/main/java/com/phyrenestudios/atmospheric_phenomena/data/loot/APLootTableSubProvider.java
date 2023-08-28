package com.phyrenestudios.atmospheric_phenomena.data.loot;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;

public class APLootTableSubProvider extends LootTableProvider {
    public APLootTableSubProvider(PackOutput packOutput) {
        super(packOutput, Collections.emptySet(), VanillaLootTableProvider.create(packOutput).getTables());
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return ImmutableList.of(
                new SubProviderEntry(APBlockLotSubProvider::new, LootContextParamSets.BLOCK)
                //other loot sub providers (fishing, entities)
        );
    }

}
