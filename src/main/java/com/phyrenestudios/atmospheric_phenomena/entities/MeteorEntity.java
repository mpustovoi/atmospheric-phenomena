package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.init.APGameRules;
import com.phyrenestudios.atmospheric_phenomena.init.APParticleTypes;
import com.phyrenestudios.atmospheric_phenomena.init.Config;
import com.phyrenestudios.atmospheric_phenomena.util.FeatureUtils;
import com.phyrenestudios.atmospheric_phenomena.worldgen.APFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;

import java.util.Optional;

public class MeteorEntity extends Entity {
    private static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(MeteorEntity.class, EntityDataSerializers.INT);
    //private int size;
    public MeteorEntity(EntityType<?> p_19870_, Level levelIn) {
        super(p_19870_, levelIn);
    }
    public MeteorEntity(Level levelIn, double p_31557_, double p_31558_, double p_31559_) {
        this(APEntityTypes.METEOR.get(), levelIn);
        this.setPos(p_31557_, p_31558_, p_31559_);
    }
    public MeteorEntity(Level levelIn, BlockPos posIn) {
        this(levelIn, posIn.getX(), posIn.getY(), posIn.getZ());
    }

    @Override
    public void setNoGravity(boolean p_20243_) {
        super.setNoGravity(false);
    }


    @Override
    public boolean isAttackable() {
        return false;
    }
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
    @Override
    public boolean isPushable() {
        return false;
    }
    @Override
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_SIZE, 1);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        this.setSize(p_20052_.getInt("Size"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        p_20139_.putInt("Size", this.getSize());
    }

    public int getSize() {
        return this.entityData.get(ID_SIZE);
    }
    public void setSize(int size) {
        int i = Mth.clamp(size, 0, 10000);
        this.entityData.set(ID_SIZE, i);
    }

    @Override
    public boolean hurt(DamageSource p_31579_, float p_31580_) {
        if (!this.level().isClientSide && !this.isRemoved()) {
            this.discard();
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSize(getSize()-1);
        if (this.getSize() == 1 && this.level().isClientSide) {
            for (int i = 0; i < 60; ++i) {
                this.level().addAlwaysVisibleParticle(APParticleTypes.METEOR_BURNOUT.get(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getY() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 2, (random.nextDouble()-0.5D)*1, (random.nextDouble()-0.5D)*1, (random.nextDouble()-0.5D)*1);
                //this.level().addAlwaysVisibleParticle(ParticleTypes.FALLING_LAVA, true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4, this.getY() + 0.5D + (random.nextDouble() - 0.5) * 4, this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4, 0,0,0);
            }
        }
        if (this.getSize() <= 0) {
            this.burnOut();
            return;
        }


        if (!this.isNoGravity() && this.getDeltaMovement().length() == 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().add((random.nextDouble()-0.5D)*2.0D, random.nextDouble()*-1.0D - 0.5D, (random.nextDouble()-0.5D)*2.0D));
        }

        if (this.level().isClientSide) {
            for (int i = 0; i < 4; ++i) {
                this.level().addAlwaysVisibleParticle(APParticleTypes.ENTRY_FLAME.get(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
                this.level().addAlwaysVisibleParticle(ParticleTypes.LARGE_SMOKE, true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
            }
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        if (!this.level().isClientSide) {
            //if (this.getSize() % 30 == 0) {
            //    this.level().playSound(null, this.getX(), this.getY(), this.getZ(), APSoundEvents.ATMOSPHERIC_ENTRY.get(), SoundSource.WEATHER, 1.0f, 1.0f);
            //}
            BlockHitResult blockhitresult = this.level().clip(new ClipContext(new Vec3(this.xo, this.yo, this.zo), this.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, this));
            if (blockhitresult.getType() != HitResult.Type.MISS || this.onGround()) {
                this.crash();
            }
        }

    }

    public void crash() {
        BlockPos blockpos = this.blockPosition();
        if (blockpos.getY() > this.level().getMinBuildHeight() && blockpos.getY() < this.level().getMaxBuildHeight()) {
            this.discard();
            if (!this.level().getGameRules().getBoolean(APGameRules.RULE_CREATE_IMPACT_CRATERS)) {
                this.level().explode(null, this.getX(), this.getY(), this.getZ(), 1.0f, Level.ExplosionInteraction.NONE);
                return;
            }
            Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = this.level().registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(APFeatures.CONFIGURED_FRESH_METEORITE);
            optional.ifPresent(configuredFeatureHolder -> configuredFeatureHolder.value().place((WorldGenLevel) this.level(), ((ServerLevel)this.level()).getChunkSource().getGenerator(), this.level().getRandom(), this.blockPosition()));
        }
    }

    public void burnOut() {
        this.level().explode(null, this.getX(), this.getY(), this.getZ(), 1.0f, Level.ExplosionInteraction.NONE);
        this.discard();
    }

    public static Vec3 getMeteorMotion(RandomSource rand) {
        double Xrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(0), Config.overworldMeteorVelocity.get(3));
        double Yrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(1), Config.overworldMeteorVelocity.get(4));
        double Zrand = FeatureUtils.randomDoubleBetween(rand, Config.overworldMeteorVelocity.get(2), Config.overworldMeteorVelocity.get(5));
        return new Vec3(Xrand, Yrand, Zrand);
    }
}
