package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.init.APWoodTypes;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class APBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerBlocks();
        TektiteBlocks.registerBlocks();
        LightningGlassBlocks.registerBlocks();
        CapsuleBlocks.registerBlocks();
    }

    public static final DeferredBlock<Block> KAMACITE = BLOCKS.register("kamacite", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> TAENITE = BLOCKS.register("taenite", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> TETRATAENITE = BLOCKS.register("tetrataenite", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> GOLDEN_MATRIX = BLOCKS.register("golden_matrix", () -> new DropExperienceBlock(UniformInt.of(1, 4), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> QUARTZ_MATRIX = BLOCKS.register("quartz_matrix", () -> new DropExperienceBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> CHARGED_QUARTZ_MATRIX = BLOCKS.register("charged_quartz_matrix", () -> new ChargedQuartzMatrix(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final DeferredBlock<Block> DEBRIS_MATRIX = BLOCKS.register("debris_matrix", () -> new DropExperienceBlock(UniformInt.of(10, 20), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> LONSDALEITE_MATRIX = BLOCKS.register("lonsdaleite_matrix", () -> new DropExperienceBlock(UniformInt.of(5, 15), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> LONSDALEITE_BLOCK = BLOCKS.register("lonsdaleite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> MOISSANITE_BLOCK = BLOCKS.register("moissanite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> METEORIC_IRON_BLOCK = BLOCKS.register("meteoric_iron_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final DeferredBlock<Block> METEORIC_ICE = BLOCKS.register("meteoric_ice", () -> new HalfTransparentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(2.8F).friction(1.0F).sound(SoundType.GLASS)));

    public static final DeferredBlock<RotatedPillarBlock> SOIL_FULGURITE = BLOCKS.register("soil_fulgurite", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));
    public static final DeferredBlock<RotatedPillarBlock> STONE_FULGURITE = BLOCKS.register("stone_fulgurite", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));

    public static final DeferredBlock<RotatedPillarBlock> BURNING_LOG = BLOCKS.register("burning_log", () -> burningLog(MapColor.STONE, MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> BURNING_WOOD = BLOCKS.register("burning_wood", () -> new APBurningLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    public static final DeferredBlock<RotatedPillarBlock> SMOULDERING_LOG = BLOCKS.register("smouldering_log", () -> smoulderingLog(MapColor.STONE, MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> SMOULDERING_WOOD = BLOCKS.register("smouldering_wood", () -> new APSmoulderingLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    private static final String baseName = "charred";
    public static final DeferredBlock<RotatedPillarBlock> CHARRED_LOG = BLOCKS.register(baseName + "_log", () -> charredLog(MapColor.STONE, MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHARRED_LOG = BLOCKS.register("stripped_" + baseName + "_log", () -> charredLog(MapColor.STONE, MapColor.STONE));
    public static final DeferredBlock<RotatedPillarBlock> CHARRED_WOOD = BLOCKS.register(baseName + "_wood", () -> new APCharredLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = BLOCKS.register("stripped_" + baseName + "_wood", () -> new APCharredLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> CHARRED_PLANKS = BLOCKS.register(baseName+"_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<APWoodenSlabBlock> CHARRED_SLAB = BLOCKS.register(baseName+"_slab", () -> new APWoodenSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<APWoodenStairBlock> CHARRED_STAIRS = BLOCKS.register(baseName+"_stairs", () -> new APWoodenStairBlock(CHARRED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CHARRED_PLANKS.get())));
    public static final DeferredBlock<APWoodenFenceBlock> CHARRED_FENCE = BLOCKS.register(baseName+"_fence", () -> new APWoodenFenceBlock(BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<APWoodenFenceGate> CHARRED_FENCE_GATE = BLOCKS.register(baseName+"_fence_gate", () -> new APWoodenFenceGate(BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), APWoodTypes.CHARRED));
    public static final DeferredBlock<DoorBlock> CHARRED_DOOR = BLOCKS.register(baseName+"_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<TrapDoorBlock> CHARRED_TRAPDOOR = BLOCKS.register(baseName+"_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava()));
    public static final DeferredBlock<PressurePlateBlock> CHARRED_PRESSURE_PLATE = BLOCKS.register(baseName+"_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<ButtonBlock> CHARRED_BUTTON = BLOCKS.register(baseName+"_button", () -> woodenButton(BlockSetType.OAK));
    public static final DeferredBlock<APSignBlock> CHARRED_SIGN = BLOCKS.register(baseName+"_sign", () -> new APSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), APWoodTypes.CHARRED));
    public static final DeferredBlock<APWallSignBlock> CHARRED_WALL_SIGN = BLOCKS.register(baseName+"_wall_sign", () -> new APWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), APWoodTypes.CHARRED));
    public static final DeferredBlock<APCeilingHangingSignBlock> CHARRED_HANGING_SIGN = BLOCKS.register(baseName+"_hanging_sign", () -> new APCeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), APWoodTypes.CHARRED));
    public static final DeferredBlock<APWallHangingSignBlock> CHARRED_WALL_HANGING_SIGN = BLOCKS.register(baseName+"_wall_hanging_sign", () -> new APWallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), APWoodTypes.CHARRED));
    public static final DeferredBlock<APBookshelvesBlock> CHARRED_BOOKSHELF = BLOCKS.register(baseName+"_bookshelf", () -> new APBookshelvesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

    private static RotatedPillarBlock charredLog(MapColor p_285370_, MapColor p_285126_) {
        return new APCharredLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava());
    }
    private static RotatedPillarBlock smoulderingLog(MapColor p_285370_, MapColor p_285126_) {
        return new APSmoulderingLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava());
    }
    private static RotatedPillarBlock burningLog(MapColor p_285370_, MapColor p_285126_) {
        return new APBurningLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava());
    }

    private static ButtonBlock woodenButton(BlockSetType p_278239_, FeatureFlag... p_278229_) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (p_278229_.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(p_278229_);
        }

        return new ButtonBlock(p_278239_, 30, blockbehaviour$properties);
    }
}
