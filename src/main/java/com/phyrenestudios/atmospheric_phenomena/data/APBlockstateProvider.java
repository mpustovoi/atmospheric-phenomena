package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.MeteorBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class APBlockstateProvider extends BlockStateProvider {

    public APBlockstateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, AtmosphericPhenomena.MODID, existingFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return AtmosphericPhenomena.MODID + " - BlockStates/Models";
    }

    @Override
    protected void registerStatesAndModels() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            cubeAll(base.getMeteorBlock());
        }
        cubeAll(APBlocks.RAW_LONSDALEITE.get());
        cubeAll(APBlocks.LONSDALEITE_BLOCK.get());
        cubeAll(APBlocks.METEORIC_ICE.get());
    }

}
