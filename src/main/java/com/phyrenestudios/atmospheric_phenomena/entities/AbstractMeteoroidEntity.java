package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.init.APGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
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

abstract class AbstractMeteoroidEntity extends Entity {
    protected static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(AbstractMeteoroidEntity.class, EntityDataSerializers.INT);

    public AbstractMeteoroidEntity(EntityType<?> entityType, Level levelIn) {
        super(entityType, levelIn);
    }
    public AbstractMeteoroidEntity(EntityType<?> entityType, Level levelIn, BlockPos posIn) {
        this(entityType, levelIn);
        this.setPos(new Vec3(posIn.getY(), posIn.getY(), posIn.getZ()));
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
        this.setSize(getSize()-burnoutModifier());
        if (this.getSize() <= burnoutModifier() && this.level().isClientSide) {
            for (int i = 0; i < 60; ++i) {
                this.level().addAlwaysVisibleParticle(burnoutParticle(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getY() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 2, (random.nextDouble()-0.5D)*1, (random.nextDouble()-0.5D)*1, (random.nextDouble()-0.5D)*1);
            }
        }
        if (this.getSize() <= 0) {
            this.burnOut();
            return;
        }
        if (!this.isNoGravity() && this.getDeltaMovement().length() == 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().add(getRandomMotion(random)));
        }
        if (this.level().isClientSide) {
            for (int i = 0; i < 4; ++i) {
                this.level().addAlwaysVisibleParticle(trailParticle1(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
                this.level().addAlwaysVisibleParticle(trailParticle2(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
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
            impactFeature().ifPresent(configuredFeatureHolder -> configuredFeatureHolder.value().place((WorldGenLevel) this.level(), ((ServerLevel)this.level()).getChunkSource().getGenerator(), this.level().getRandom(), this.blockPosition()));
        }
    }

    public void burnOut() {
        this.level().explode(null, this.getX(), this.getY(), this.getZ(), 1.0f, Level.ExplosionInteraction.NONE);
        this.discard();
    }

    public abstract Vec3 getRandomMotion(RandomSource rand);
    protected abstract int burnoutModifier();
    protected abstract ParticleOptions trailParticle1();
    protected abstract ParticleOptions trailParticle2();
    protected abstract ParticleOptions burnoutParticle();
    protected abstract Optional<? extends Holder<ConfiguredFeature<?, ?>>> impactFeature();
}
