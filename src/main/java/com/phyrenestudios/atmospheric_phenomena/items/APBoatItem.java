package com.phyrenestudios.atmospheric_phenomena.items;

import com.phyrenestudios.atmospheric_phenomena.entities.APBoat;
import com.phyrenestudios.atmospheric_phenomena.entities.APChestBoat;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class APBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final APBoat.Type type;
    private final boolean chest;

    public APBoatItem(boolean chest, APBoat.Type type, Item.Properties properties) {
        super(properties);
        this.chest = chest;
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult result = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        }
        else {
            Vec3 vector3d = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vector3d1 = player.getEyePosition(1.0F);

                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(vector3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (result.getType() == HitResult.Type.BLOCK) {
                APBoat boat = this.getBoat(level, result);
                boat.setAPBoatType(this.type);
                boat.setYRot(player.getYRot());
                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                }
                else {
                    if (!level.isClientSide()) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, result.getLocation());
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
                }
            }
            else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    private APBoat getBoat(Level level, HitResult result) {
        return this.chest ? new APChestBoat(level, result.getLocation().x(), result.getLocation().y(), result.getLocation().z()) : new APBoat(level, result.getLocation().x(), result.getLocation().y(), result.getLocation().z());
    }
}
