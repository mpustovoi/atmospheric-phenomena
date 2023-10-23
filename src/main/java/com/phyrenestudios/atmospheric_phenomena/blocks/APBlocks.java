package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerBlocks();
        TektiteBlocks.registerBlocks();
        LightningGlassBlocks.registerBlocks();
    }

    public static final RegistryObject<Block> KAMACITE = BLOCKS.register("kamacite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> TAENITE = BLOCKS.register("taenite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> TETRATAENITE = BLOCKS.register("tetrataenite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> GOLDEN_MATRIX = BLOCKS.register("golden_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> QUARTZ_MATRIX = BLOCKS.register("quartz_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> CHARGED_QUARTZ_MATRIX = BLOCKS.register("charged_quartz_matrix", () -> new ChargedQuartzMatrix(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> DEBRIS_MATRIX = BLOCKS.register("debris_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(10, 20)));
    public static final RegistryObject<Block> LONSDALEITE_MATRIX = BLOCKS.register("lonsdaleite_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(7, 15)));
    public static final RegistryObject<Block> LONSDALEITE_BLOCK = BLOCKS.register("lonsdaleite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final RegistryObject<Block> METEORIC_ICE = BLOCKS.register("meteoric_ice", () -> new HalfTransparentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(2.8F).friction(1.0F).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> SOIL_FULGURITE = BLOCKS.register("soil_fulgurite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));
    public static final RegistryObject<Block> SSTONE_FULGURITE = BLOCKS.register("stone_fulgurite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));

    //Charred wood

    private static final String baseName = "charred";
    public static final WoodType CHARRED_WOODTYPE = WoodType.register(new WoodType("rankine:"+baseName, BlockSetType.OAK));
    public static final RegistryObject<RotatedPillarBlock> CHARRED_LOG = BLOCKS.register(baseName + "_log", () -> log(MapColor.STONE, MapColor.STONE));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CHARRED_LOG = BLOCKS.register("stripped_" + baseName + "_log", () -> log(MapColor.STONE, MapColor.STONE));
    public static final RegistryObject<RotatedPillarBlock> CHARRED_WOOD = BLOCKS.register(baseName + "_wood", () -> new APLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = BLOCKS.register("stripped_" + baseName + "_wood", () -> new APLogBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> CHARRED_PLANKS = BLOCKS.register(baseName+"_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<APWoodenSlabBlock> CHARRED_SLAB = BLOCKS.register(baseName+"_slab", () -> new APWoodenSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<APWoodenStairBlock> CHARRED_STAIRS = BLOCKS.register(baseName+"_stairs", () -> new APWoodenStairBlock(CHARRED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CHARRED_PLANKS.get())));
    public static final RegistryObject<APWoodenFenceBlock> CHARRED_FENCE = BLOCKS.register(baseName+"_fence", () -> new APWoodenFenceBlock(BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<APWoodenFenceGate> CHARRED_FENCE_GATE = BLOCKS.register(baseName+"_fence_gate", () -> new APWoodenFenceGate(BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), CHARRED_WOODTYPE));
    public static final RegistryObject<DoorBlock> CHARRED_DOOR = BLOCKS.register(baseName+"_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK));
    public static final RegistryObject<TrapDoorBlock> CHARRED_TRAPDOOR = BLOCKS.register(baseName+"_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava(), BlockSetType.OAK));
    public static final RegistryObject<PressurePlateBlock> CHARRED_PRESSURE_PLATE = BLOCKS.register(baseName+"_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(CHARRED_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK));
    public static final RegistryObject<ButtonBlock> CHARRED_BUTTON = BLOCKS.register(baseName+"_button", () -> woodenButton(BlockSetType.OAK));
    public static final RegistryObject<APSignBlock> CHARRED_SIGN = BLOCKS.register(baseName+"_sign", () -> new APSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), CHARRED_WOODTYPE));
    public static final RegistryObject<APWallSignBlock> CHARRED_WALL_SIGN = BLOCKS.register(baseName+"_wall_sign", () -> new APWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(CHARRED_SIGN.get()).ignitedByLava(),CHARRED_WOODTYPE));
    //public static final RegistryObject<Block> CHARRED_WALL_SIGN = BLOCKS.register(baseName+"_wall_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(OAK_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), WoodType.OAK));
    //public static final RegistryObject<Block> CHARRED_WALL_SIGN = BLOCKS.register(baseName+"_wall_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(OAK_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(OAK_HANGING_SIGN), WoodType.OAK));
    public static final RegistryObject<APBookshelvesBlock> CHARRED_BOOKSHELF = BLOCKS.register(baseName+"_bookshelf", () -> new APBookshelvesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD).ignitedByLava()));

    private static RotatedPillarBlock log(MapColor p_285370_, MapColor p_285126_) {
        return new APLogBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }

    private static RotatedPillarBlock log(MapColor p_285425_, MapColor p_285292_, SoundType p_285418_) {
        return new APLogBlock(BlockBehaviour.Properties.of().mapColor((p_258972_) -> {
            return p_258972_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285425_ : p_285292_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(p_285418_).ignitedByLava());
    }

    private static ButtonBlock woodenButton(BlockSetType p_278239_, FeatureFlag... p_278229_) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (p_278229_.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(p_278229_);
        }

        return new ButtonBlock(blockbehaviour$properties, p_278239_, 30, true);
    }
}
