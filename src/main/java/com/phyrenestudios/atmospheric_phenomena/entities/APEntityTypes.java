package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class APEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, AtmosphericPhenomena.MODID);

    public static final Supplier<EntityType<MeteorEntity>> METEOR = ENTITY_TYPES.register("meteor", () -> EntityType.Builder.<MeteorEntity>of(MeteorEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "meteor").toString()));
    public static final Supplier<EntityType<CometEntity>> COMET = ENTITY_TYPES.register("comet", () -> EntityType.Builder.<CometEntity>of(CometEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "comet").toString()));
    public static final Supplier<EntityType<APBoat>> AP_BOAT = ENTITY_TYPES.register("ap_boat", () -> EntityType.Builder.<APBoat>of(APBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "ap_boat").toString()));
    public static final Supplier<EntityType<APChestBoat>> AP_CHEST_BOAT = ENTITY_TYPES.register("ap_chest_boat", () -> EntityType.Builder.<APChestBoat>of(APChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "ap_chest_boat").toString()));

}
