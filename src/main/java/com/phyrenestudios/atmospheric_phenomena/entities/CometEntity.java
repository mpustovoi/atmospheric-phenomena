package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTables;
import com.phyrenestudios.atmospheric_phenomena.init.APParticleTypes;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class CometEntity extends AbstractMeteoroidEntity {
    protected static final ParticleOptions trailParticle1 = ParticleTypes.SNOWFLAKE;
    protected static final ParticleOptions trailParticle2 = APParticleTypes.LARGE_CLOUD.get();
    protected static final ParticleOptions burnoutParticle = APParticleTypes.LARGE_SNOWFLAKE.get();

    public CometEntity(EntityType<?> entityType, Level levelIn) {
        super(entityType, levelIn);
    }
    public CometEntity(Level levelIn, BlockPos posIn) {
        super(APEntityTypes.COMET.get(), levelIn, posIn);
    }

    @Override
    public Vec3 getRandomMotion(RandomSource rand) {
        double Xrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldCometVelocity.get(0), Config.overworldCometVelocity.get(3));
        double Yrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldCometVelocity.get(1), Config.overworldCometVelocity.get(4));
        double Zrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldCometVelocity.get(2), Config.overworldCometVelocity.get(5));
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
        return SoundEvents.GLASS_BREAK;
    }
    @Override
    public int burnoutModifier() {
        return Config.overworldCometSpawnSettings.get(5);
    }
    @Override
    protected Optional<? extends Holder<ConfiguredFeature<?, ?>>> impactFeature() {
        return this.level().registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(APFeatures.CONFIGURED_COMET);
    }
    @Override
    protected ResourceLocation getLoottable() {
        return APLootTables.OVERWORLD_COMET;
    }

}
