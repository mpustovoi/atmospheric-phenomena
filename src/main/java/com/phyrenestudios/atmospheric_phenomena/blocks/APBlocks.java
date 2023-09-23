package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AtmosphericPhenomena.MODID);

    static {
        MeteorBlocks.registerBlocks();
        TektiteBlocks.registerBlocks();
    }

    public static final RegistryObject<Block> KAMACITE = BLOCKS.register("kamacite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> TAENITE = BLOCKS.register("taenite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> TETRATAENITE = BLOCKS.register("tetrataenite", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> GOLDEN_MATRIX = BLOCKS.register("golden_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> LONSDALEITE_MATRIX = BLOCKS.register("lonsdaleite_matrix", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> LONSDALEITE_BLOCK = BLOCKS.register("lonsdaleite_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final RegistryObject<Block> METEORIC_ICE = BLOCKS.register("meteoric_ice", () -> new HalfTransparentBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).strength(2.8F).friction(1.0F).sound(SoundType.GLASS)));

    public static final RegistryObject<Block> SOIL_FULGURITE = BLOCKS.register("soil_fulgurite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));
    public static final RegistryObject<Block> SSTONE_FULGURITE = BLOCKS.register("stone_fulgurite", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3F, 3F)));


    //Charred wood
}
