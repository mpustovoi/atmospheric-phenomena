package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AtmosphericPhenomena.MODID);

    public static final RegistryObject<SoundEvent> ATMOSPHERIC_ENTRY = SOUNDS.register("atmospheric_entry",() -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AtmosphericPhenomena.MODID,"atmospheric_entry")));
}
