package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class APBlockstateProvider extends BlockStateProvider {

    public APBlockstateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, AtmosphericPhenomena.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (MeteorBlocks base : MeteorBlocks.values()) {
            simpleBlock(base.getMeteorBlock());
            simpleBlock(base.getBricks());
            slabBlock(base.getBricksSlab(), getBlockRSL(base.getBricks()), getBlockRSL(base.getBricks()));
            stairsBlock(base.getBricksStairs(), getBlockRSL(base.getBricks()));
            wallBlock(base.getBricksWall(), getBlockRSL(base.getBricks()));
            phaseBlock(base.getChiseled());
        }
        for (LightningGlassBlocks base : LightningGlassBlocks.values()) {
            axisBlockWithRenderType(base.getGlass(), "translucent");
        }
        for (TektiteBlocks base : TektiteBlocks.values()) {
            simpleBlock(base.getTektite(), models().cubeAll(base.getSerializedName(), blockTexture(base.getTektite())).renderType("translucent"));
        }
        for (CapsuleBlocks base : CapsuleBlocks.values()) {
            capsuleBlock(base.getCapsule());
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
        simpleBlock(APBlocks.MOISSANITE_BLOCK.get());
        simpleBlock(APBlocks.METEORIC_IRON_BLOCK.get());
        simpleBlock(APBlocks.METEORIC_ICE.get());
        axisBlock(APBlocks.SOIL_FULGURITE.get());
        axisBlock(APBlocks.STONE_FULGURITE.get());


        axisBlock(APBlocks.BURNING_LOG.get(), getBlockRSL(APBlocks.BURNING_LOG.get()), getBlockRSL(name(APBlocks.BURNING_LOG.get(),"_top")));
        axisBlock(APBlocks.BURNING_WOOD.get(), getBlockRSL(APBlocks.BURNING_LOG.get()), getBlockRSL(APBlocks.BURNING_LOG.get()));

        axisBlock(APBlocks.SMOULDERING_LOG.get(), getBlockRSL(APBlocks.SMOULDERING_LOG.get()), getBlockRSL(name(APBlocks.SMOULDERING_LOG.get(),"_top")));
        axisBlock(APBlocks.SMOULDERING_WOOD.get(), getBlockRSL(APBlocks.SMOULDERING_LOG.get()), getBlockRSL(APBlocks.SMOULDERING_LOG.get()));

        axisBlock(APBlocks.CHARRED_LOG.get(), getBlockRSL(APBlocks.CHARRED_LOG.get()), getBlockRSL(name(APBlocks.CHARRED_LOG.get(),"_top")));
        axisBlock(APBlocks.STRIPPED_CHARRED_LOG.get(), getBlockRSL(APBlocks.STRIPPED_CHARRED_LOG.get()), getBlockRSL(name(APBlocks.STRIPPED_CHARRED_LOG.get(),"_top")));
        axisBlock(APBlocks.CHARRED_WOOD.get(), getBlockRSL(APBlocks.CHARRED_LOG.get()), getBlockRSL(APBlocks.CHARRED_LOG.get()));
        axisBlock(APBlocks.STRIPPED_CHARRED_WOOD.get(), getBlockRSL(APBlocks.STRIPPED_CHARRED_LOG.get()), getBlockRSL(APBlocks.STRIPPED_CHARRED_LOG.get()));
        simpleBlock(APBlocks.CHARRED_PLANKS.get());
        slabBlock(APBlocks.CHARRED_SLAB.get(), getBlockRSL(name(APBlocks.CHARRED_PLANKS.get())), getBlockRSL(name(APBlocks.CHARRED_PLANKS.get())));
        stairsBlock(APBlocks.CHARRED_STAIRS.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));
        fenceBlock(APBlocks.CHARRED_FENCE.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));
        fenceGateBlock(APBlocks.CHARRED_FENCE_GATE.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));
        doorBlock(APBlocks.CHARRED_DOOR.get(), getBlockRSL(name(APBlocks.CHARRED_DOOR.get(),"_bottom")), getBlockRSL(name(APBlocks.CHARRED_DOOR.get(),"_top")));
        trapdoorBlock(APBlocks.CHARRED_TRAPDOOR.get(), getBlockRSL(APBlocks.CHARRED_TRAPDOOR.get()), true);
        pressurePlateBlock(APBlocks.CHARRED_PRESSURE_PLATE.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));
        buttonBlock(APBlocks.CHARRED_BUTTON.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));
        signBlock(APBlocks.CHARRED_SIGN.get(), APBlocks.CHARRED_WALL_SIGN.get(), getBlockRSL(APBlocks.CHARRED_PLANKS.get()));

        simpleBlock(APBlocks.CHARRED_BOOKSHELF.get(), models().withExistingParent("charred_bookshelf", mcLoc("block/cube_column")).texture("end", getBlockRSL("charred_planks")).texture("side", getBlockRSL("charred_bookshelf")));

    }

    @Override
    public void fenceBlock(FenceBlock blk, ResourceLocation texture) {
        super.fenceBlock(blk, texture);
        models().withExistingParent(name(blk) + "_inventory", mcLoc("block/fence_inventory"))
                .texture("texture", texture);
    }
    @Override
    public void wallBlock(WallBlock blk, ResourceLocation texture) {
        super.wallBlock(blk, texture);
        models().withExistingParent(name(blk) + "_inventory", mcLoc("block/wall_inventory"))
                .texture("wall", texture);
    }

    public void buttonBlock(ButtonBlock blk, ResourceLocation texture) {
        buttonInventory(name(blk), texture);
        buttonBlock(blk, models().withExistingParent(name(blk), mcLoc("block/button")).texture("texture", texture), models().withExistingParent(name(blk,"_pressed"), mcLoc("block/button_pressed")).texture("texture", texture));
    }
    public ModelBuilder<BlockModelBuilder> buttonInventory(String name, ResourceLocation texture) {
        return models().withExistingParent(name+"_inventory", mcLoc("block/button_inventory")).texture("texture", texture);
    }
    public void buttonBlock(ButtonBlock block, ModelFile button, ModelFile pressed) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            AttachFace face = state.getValue(ButtonBlock.FACE);
            Direction facing = state.getValue(ButtonBlock.FACING);
            Boolean powered = state.getValue(ButtonBlock.POWERED);
            int yRot = (int) facing.toYRot();
            return ConfiguredModel.builder()
                    .modelFile(powered ? pressed : button)
                    .rotationY(yRot)
                    .rotationX(face == AttachFace.CEILING ? 180 : face == AttachFace.FLOOR ? 0 : 270)
                    .uvLock(true)
                    .build();
        });
    }
    public void phaseBlock(ChiseledMeteoriteBlock blk) {
        getVariantBuilder(blk).forAllStatesExcept(state -> {
            int i = state.getValue(ChiseledMeteoriteBlock.PHASE);
            return ConfiguredModel.builder()
                    .modelFile(models().cubeAll(name(blk)+i, getBlockRSL(name(blk)+i)))
                    //.modelFile(models().withExistingParent(name(blk)+i,mcLoc("block/block"))
                    //        .texture("all", getBlockRSL(blk))
                    //        .element().from(0,0,0).to(16,16,16)
                    //        .allFaces((dir, face) -> face.uvs(0,16*(i-1), 16, 16*(i)).texture("#all").cullface(dir))
                    //        .end())
                    .build();
        });
    }

    public void capsuleBlock(CapsuleBlock block) {
        ModelFile vertical = models().cubeColumn(name(block), blockTexture(block),  extend(blockTexture(block), "_top"));
        ModelFile horizontal = models().cubeColumnHorizontal(name(block) + "_horizontal", blockTexture(block),  extend(blockTexture(block), "_top"));
        getVariantBuilder(block)
                .partialState().with(CapsuleBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(CapsuleBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(CapsuleBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
    }

    public void axisBlockWithRenderType(Block block, String renderType) {
        axisBlockWithRenderType(block, blockTexture(block), renderType);
    }
    public void axisBlockWithRenderType(Block block, ResourceLocation baseName, String renderType) {
        axisBlockWithRenderType(block, extend(baseName, "_side"), extend(baseName, "_end"), renderType);
    }
    public void axisBlockWithRenderType(Block block, ResourceLocation side, ResourceLocation end, String renderType) {
        axisBlock(block,
                models().cubeColumn(name(block), side, end).renderType(renderType),
                models().cubeColumnHorizontal(name(block) + "_horizontal", side, end).renderType(renderType));
    }
    public void axisBlock(Block block, ModelFile vertical, ModelFile horizontal) {
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
    }

    public ResourceLocation getBlockRSL(Block blk) {
        return getBlockRSL(name(blk));
    }
    public ResourceLocation getBlockRSL(String textureName) {
        return modLoc("block/"+textureName);
    }
    public ResourceLocation getBlockRSL(String namespace, String textureName) {
        return new ResourceLocation(namespace,"block/"+textureName);
    }
    private static ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private static String name(Block blk) {
        return key(blk).getPath();
    }
    private static String name(Block blk, String suffix) {
        return key(blk).getPath() + suffix;
    }
    private static String name(String prefix, Block blk) {
        return prefix + key(blk).getPath();
    }
    private static String name(String prefix, Block blk, String suffix) {
        return prefix + key(blk).getPath() + suffix;
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }
}

