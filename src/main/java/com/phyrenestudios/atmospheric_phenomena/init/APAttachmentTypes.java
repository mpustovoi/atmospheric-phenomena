package com.phyrenestudios.atmospheric_phenomena.init;

import com.mojang.serialization.Codec;
import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class APAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, AtmosphericPhenomena.MODID);

    public static final Supplier<AttachmentType<Integer>> METEOR_COUNTDOWN = ATTACHMENT_TYPES.register("meteor_countdown", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());
    public static final Supplier<AttachmentType<Integer>> COMET_COUNTDOWN = ATTACHMENT_TYPES.register("comet_countdown", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());
}
