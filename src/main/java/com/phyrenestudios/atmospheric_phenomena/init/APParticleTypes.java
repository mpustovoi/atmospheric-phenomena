package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.client.particle.EntryFlameParticle;
import com.phyrenestudios.atmospheric_phenomena.client.particle.LargeCloudParticle;
import com.phyrenestudios.atmospheric_phenomena.client.particle.LargeSnowflakeParticle;
import com.phyrenestudios.atmospheric_phenomena.client.particle.MeteorBurnoutParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class APParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, AtmosphericPhenomena.MODID);

    public static final Supplier<SimpleParticleType> ENTRY_FLAME = PARTICLE_TYPES.register("entry_flame",  () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> METEOR_BURNOUT = PARTICLE_TYPES.register("meteor_burnout",  () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> LARGE_CLOUD = PARTICLE_TYPES.register("large_cloud",  () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> LARGE_SNOWFLAKE = PARTICLE_TYPES.register("large_snowflake",  () -> new SimpleParticleType(false));


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(APParticleTypes.ENTRY_FLAME.get(), EntryFlameParticle.Factory::new);
        event.registerSpriteSet(APParticleTypes.METEOR_BURNOUT.get(), MeteorBurnoutParticle.Factory::new);
        event.registerSpriteSet(APParticleTypes.LARGE_CLOUD.get(), LargeCloudParticle.Factory::new);
        event.registerSpriteSet(APParticleTypes.LARGE_SNOWFLAKE.get(), LargeSnowflakeParticle.Factory::new);
    }

}
