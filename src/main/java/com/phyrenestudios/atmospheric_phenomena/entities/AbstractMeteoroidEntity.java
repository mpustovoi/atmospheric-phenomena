package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.init.APDamageTypes;
import com.phyrenestudios.atmospheric_phenomena.init.APGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;

import java.util.Optional;
import java.util.function.Predicate;

abstract class AbstractMeteoroidEntity extends Entity {
    protected static final EntityDataAccessor<Integer> ID_SIZE = SynchedEntityData.defineId(AbstractMeteoroidEntity.class, EntityDataSerializers.INT);

    public AbstractMeteoroidEntity(EntityType<?> entityType, Level levelIn) {
        super(entityType, levelIn);
    }
    public AbstractMeteoroidEntity(EntityType<?> entityType, Level levelIn, BlockPos posIn) {
        this(entityType, levelIn);
        this.setPos(new Vec3(posIn.getX(), posIn.getY(), posIn.getZ()));
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
        int i = Mth.clamp(size, 0, 100000);
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
        if (this.onGround()) {
            this.crash();
        }
        if (!this.level().isClientSide) {
            if (!this.isNoGravity() && this.getDeltaMovement().length() == 0.0D) {
                this.setDeltaMovement(this.getDeltaMovement().add(getRandomMotion(random)));
            }
        }
        this.move(MoverType.SELF, this.getDeltaMovement());

        if (this.level().isClientSide) {
            for (int i = 0; i < 4; ++i) {
                this.level().addAlwaysVisibleParticle(trailParticle1(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
                this.level().addAlwaysVisibleParticle(trailParticle2(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 4.0, this.getY() + random.nextDouble(), this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 4.0, 0D, 0.5D, 0D);
            }
            if (this.getSize() <= burnoutModifier()) {
                this.burnOut();
            }
        } else {
            if (this.getSize() <= 0) {
                this.destroy();
                return;
            }
            this.setSize(getSize()-burnoutModifier());
        }

    }

    private void crash() {
        if (this.level().isClientSide) {
            this.burnOut();
        } else {
            if (this.level().getGameRules().getBoolean(APGameRules.RULE_CREATE_IMPACT_CRATERS)) {
                impactFeature().ifPresent(configuredFeatureHolder -> configuredFeatureHolder.value().place((WorldGenLevel) this.level(), ((ServerLevel)this.level()).getChunkSource().getGenerator(), this.level().getRandom(), this.blockPosition()));
            }
            this.destroy();
        }
    }

    private void burnOut() {
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.3F, true);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), burnoutSound(), SoundSource.BLOCKS, 4.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.3F, true);
        for (int i = 0; i < 80; ++i) {
            this.level().addAlwaysVisibleParticle(burnoutParticle(), true, this.getX() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getY() + 0.5D + (random.nextDouble() - 0.5) * 2, this.getZ() + 0.5D + (random.nextDouble() - 0.5) * 2, (random.nextDouble() - 0.5D) * 2, (random.nextDouble() - 0.5D) * 2, (random.nextDouble() - 0.5D) * 2);
        }
    }

    private void destroy() {
        this.damageEntities();
        this.discard();
        this.level().gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }

    private void damageEntities() {
        Predicate<Entity> predicate = EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE);
        DamageSource damagesource = this.damageSources().source(APDamageTypes.METEOROID, this);
        this.level().getEntities(this, this.getBoundingBox().inflate(20.0D, 10.0D, 20.0D), predicate).forEach((ent) -> {
            float f = Math.max(1.0f, 30f - (float) this.position().distanceTo(ent.position()));
            ent.hurt(damagesource, f);
            if (ent instanceof ServerPlayer playerEnt) {
                if (playerEnt.isAlive()) return;
                playerEnt.getAdvancements().award(((ServerLevel)this.level()).getServer().getAdvancements().getAdvancement(new ResourceLocation(AtmosphericPhenomena.MODID+"/killed_by_meteoroid")), "killed_by_meteoroid");
            }
        });
    }


    public abstract Vec3 getRandomMotion(RandomSource rand);
    protected abstract int burnoutModifier();
    protected abstract ParticleOptions trailParticle1();
    protected abstract ParticleOptions trailParticle2();
    protected abstract ParticleOptions burnoutParticle();
    protected abstract SoundEvent burnoutSound();
    protected abstract Optional<? extends Holder<ConfiguredFeature<?, ?>>> impactFeature();
}
