package com.phyrenestudios.atmospheric_phenomena.entities;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class APEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AtmosphericPhenomena.MODID);

    public static final RegistryObject<EntityType<MeteorEntity>> METEOR = ENTITY_TYPES.register("meteor", () -> EntityType.Builder.<MeteorEntity>of(MeteorEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "meteor").toString()));
    public static final RegistryObject<EntityType<CometEntity>> COMET = ENTITY_TYPES.register("comet", () -> EntityType.Builder.<CometEntity>of(CometEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "comet").toString()));
    public static final RegistryObject<EntityType<APBoat>> AP_BOAT = ENTITY_TYPES.register("ap_boat", () -> EntityType.Builder.<APBoat>of(APBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "ap_boat").toString()));
    public static final RegistryObject<EntityType<APChestBoat>> AP_CHEST_BOAT = ENTITY_TYPES.register("ap_chest_boat", () -> EntityType.Builder.<APChestBoat>of(APChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(AtmosphericPhenomena.MODID, "ap_chest_boat").toString()));

}
