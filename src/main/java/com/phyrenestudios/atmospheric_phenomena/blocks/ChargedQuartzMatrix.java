package com.phyrenestudios.atmospheric_phenomena.blocks;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class ChargedQuartzMatrix extends DropExperienceBlock {
    public ChargedQuartzMatrix(Properties p_221083_, IntProvider p_221084_) {
        super(p_221084_, p_221083_);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level levelIn, BlockPos posIn, Player player, boolean willHarvest, FluidState fluid) {
        if (!player.isCreative()) player.hurt(player.damageSources().lightningBolt(), 5.0f);
        if (!EnchantmentHelper.hasSilkTouch(player.getMainHandItem())) {
            levelIn.playSound(null, posIn, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5f, 1.0f);
            if (levelIn instanceof ServerLevel serverLevel) {
                for (int i = 0; i < 40; ++i) {
                    serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, posIn.getX() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*2.0, posIn.getY() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*2.0, posIn.getZ() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*2.0, 1, 0D,0D,0D, 1.0D);
                }
            }
        }
        return super.onDestroyedByPlayer(state, levelIn, posIn, player, willHarvest, fluid);
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState stateIn, Entity entityIn) {
        if (!(entityIn instanceof LivingEntity)) return;
        if (levelIn instanceof ServerLevel) entityIn.thunderHit((ServerLevel) levelIn, EntityType.LIGHTNING_BOLT.create(levelIn));
        if (entityIn instanceof ServerPlayer playerEnt && !playerEnt.isAlive()) {
            playerEnt.getAdvancements().award(((ServerLevel)levelIn).getServer().getAdvancements().get(new ResourceLocation(AtmosphericPhenomena.MODID,"killed_by_discharge")), "killed_by_discharge");
        } else if (entityIn instanceof Pig || entityIn instanceof Villager || entityIn instanceof Creeper) {
            Player playerEnt = levelIn.getNearestPlayer(entityIn, 10);
            if (playerEnt instanceof ServerPlayer) ((ServerPlayer) playerEnt).getAdvancements().award(((ServerLevel)levelIn).getServer().getAdvancements().get(new ResourceLocation(AtmosphericPhenomena.MODID,"discharge_entity")), "discharge_entity");
        }
        levelIn.setBlockAndUpdate(posIn, APBlocks.QUARTZ_MATRIX.get().defaultBlockState());
        if (levelIn instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 40; ++i) {
                serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK, posIn.getX() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*3.0, posIn.getY() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*3.0, posIn.getZ() + 0.5D + (levelIn.getRandom().nextDouble()-0.5)*3.0, 1, 0D,0D,0D, 1.0D);
            }
        }
        levelIn.playSound(null, posIn, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5f, 1.0f);
        super.stepOn(levelIn, posIn, stateIn, entityIn);
    }

}
