package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.client.particle.EntryFlameParticle;
import com.phyrenestudios.atmospheric_phenomena.client.particle.LargeCloudParticle;
import com.phyrenestudios.atmospheric_phenomena.client.particle.MeteorBurnoutParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class APParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AtmosphericPhenomena.MODID);

    public static final RegistryObject<SimpleParticleType> ENTRY_FLAME = PARTICLE_TYPES.register("entry_flame",  () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> METEOR_BURNOUT = PARTICLE_TYPES.register("meteor_burnout",  () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LARGE_CLOUD = PARTICLE_TYPES.register("large_cloud",  () -> new SimpleParticleType(false));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(APParticleTypes.ENTRY_FLAME.get(), EntryFlameParticle.Factory::new);
        event.registerSpriteSet(APParticleTypes.METEOR_BURNOUT.get(), MeteorBurnoutParticle.Factory::new);
        event.registerSpriteSet(APParticleTypes.LARGE_CLOUD.get(), LargeCloudParticle.Factory::new);
    }

}
