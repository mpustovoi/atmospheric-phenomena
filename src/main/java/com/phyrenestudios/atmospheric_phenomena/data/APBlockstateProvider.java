package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
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
            simpleBlock(base.getMeteorBlock());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            getVariantBuilder(base.getGlass())
                    .partialState().with(LightningGlassBlock.GLOWING, false).modelForState().modelFile(models().cubeAll(base.getSerializedName(), blockTexture(base.getGlass())).renderType("translucent")).addModel()
                    .partialState().with(LightningGlassBlock.GLOWING, true).modelForState().modelFile(models().cubeAll(base.getSerializedName()+"_glowing", blockTexture(base.getGlass())).renderType("translucent")).addModel();
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            simpleBlock(base.getTektite(), models().cubeAll(base.getSerializedName(), blockTexture(base.getTektite())).renderType("translucent"));
        }
        simpleBlock(APBlocks.KAMACITE.get());
        simpleBlock(APBlocks.TAENITE.get());
        simpleBlock(APBlocks.TETRATAENITE.get());
        simpleBlock(APBlocks.GOLDEN_MATRIX.get());
        simpleBlock(APBlocks.QUARTZ_MATRIX.get());
        simpleBlock(APBlocks.CHARGED_QUARTZ_MATRIX.get());
        simpleBlock(APBlocks.DEBRIS_MATRIX.get());
        simpleBlock(APBlocks.LONSDALEITE_MATRIX.get());
        simpleBlock(APBlocks.LONSDALEITE_BLOCK.get());
        simpleBlock(APBlocks.METEORIC_ICE.get());
        simpleBlock(APBlocks.SOIL_FULGURITE.get());
        simpleBlock(APBlocks.SSTONE_FULGURITE.get());
    }

    private void lightningGlassBlock(Block blockIn, ModelFile modelIn) {
        getVariantBuilder(blockIn)
                .partialState().with(LightningGlassBlock.GLOWING, false).modelForState().modelFile(modelIn).addModel()
                .partialState().with(LightningGlassBlock.GLOWING, true).modelForState().modelFile(modelIn).addModel();
    }




}

