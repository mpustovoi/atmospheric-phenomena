package com.phyrenestudios.atmospheric_phenomena.block_entities;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.blocks.APBlocks;
import com.phyrenestudios.atmospheric_phenomena.blocks.CapsuleBlocks;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AtmosphericPhenomena.MODID);

    public static final Supplier<BlockEntityType<CapsuleBlockEntity>> CAPSULE = BLOCK_ENTITY_TYPES.register("capsule",
            () -> BlockEntityType.Builder.of(CapsuleBlockEntity::new, CapsuleBlocks.FROZEN_CAPSULE.getCapsule(), CapsuleBlocks.ANCIENT_CAPSULE.getCapsule(), CapsuleBlocks.CRYSTALLINE_CAPSULE.getCapsule(), CapsuleBlocks.GILDED_CAPSULE.getCapsule(), CapsuleBlocks.PLATED_CAPSULE.getCapsule(), CapsuleBlocks.STUDDED_CAPSULE.getCapsule())
                    .build(null));

    public static final Supplier<BlockEntityType<APSignBlockEntity>> AP_SIGN = BLOCK_ENTITY_TYPES.register("ap_sign",
            () -> BlockEntityType.Builder.of(APSignBlockEntity::new,
                    APBlocks.CHARRED_SIGN.get(), APBlocks.CHARRED_WALL_SIGN.get()
            ).build(null));

    public static final Supplier<BlockEntityType<APSignBlockEntity>> AP_HANGING_SIGN = BLOCK_ENTITY_TYPES.register("ap_hanging_sign",
            () -> BlockEntityType.Builder.of(APSignBlockEntity::new,
                    APBlocks.CHARRED_HANGING_SIGN.get(), APBlocks.CHARRED_WALL_HANGING_SIGN.get()
            ).build(null));


    @OnlyIn(Dist.CLIENT)
    public static void registerBlockEntityRenders() {
        BlockEntityRenderers.register(AP_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(AP_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
