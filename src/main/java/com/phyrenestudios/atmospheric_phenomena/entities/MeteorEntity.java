package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.init.APParticleTypes;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class MeteorEntity extends AbstractMeteoroidEntity {
    protected static final ParticleOptions trailParticle1 = ParticleTypes.LARGE_SMOKE;
    protected static final ParticleOptions trailParticle2 = APParticleTypes.ENTRY_FLAME.get();
    protected static final ParticleOptions burnoutParticle = APParticleTypes.METEOR_BURNOUT.get();

    public MeteorEntity(EntityType<?> entityType, Level levelIn) {
        super(entityType, levelIn);
    }
    public MeteorEntity(Level levelIn, BlockPos posIn) {
        super(APEntityTypes.METEOR.get(), levelIn, posIn);
    }

    @Override
    public Vec3 getRandomMotion(RandomSource rand) {
        double Xrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(0), Config.overworldMeteorVelocity.get(3));
        double Yrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(1), Config.overworldMeteorVelocity.get(4));
        double Zrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(2), Config.overworldMeteorVelocity.get(5));
        return new Vec3(Xrand, Yrand, Zrand);
    }

    @Override
    public ParticleOptions trailParticle1() {
        return trailParticle1;
    }
    @Override
    public ParticleOptions trailParticle2() {
        return trailParticle2;
    }
    @Override
    public ParticleOptions burnoutParticle() {
        return burnoutParticle;
    }
    @Override
    public SoundEvent burnoutSound() {
        return SoundEvents.STONE_BREAK;
    }
    @Override
    public int burnoutModifier() {
        return Config.overworldMeteorSpawnSettings.get(4);
    }
    @Override
    protected Optional<? extends Holder<ConfiguredFeature<?, ?>>> impactFeature() {
        return this.level().registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(APFeatures.CONFIGURED_FRESH_METEORITE);
    }
}
