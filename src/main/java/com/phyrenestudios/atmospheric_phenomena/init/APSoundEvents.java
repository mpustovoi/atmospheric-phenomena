package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, AtmosphericPhenomena.MODID);

    public static final Supplier<SoundEvent> ATMOSPHERIC_ENTRY = SOUNDS.register("atmospheric_entry",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AtmosphericPhenomena.MODID,"atmospheric_entry")));
}
