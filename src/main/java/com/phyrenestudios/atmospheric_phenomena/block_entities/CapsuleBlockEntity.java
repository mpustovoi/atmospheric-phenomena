package com.phyrenestudios.atmospheric_phenomena.block_entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CapsuleBlockEntity extends BlockEntity implements Container, Nameable {
    @Nullable
    private Component name;
    private final int CONTAINER_SIZE = 16;
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    public static final String LOOT_TABLE_TAG = "LootTable";
    public static final String LOOT_TABLE_SEED_TAG = "LootTableSeed";
    @Nullable
    protected ResourceLocation lootTable;
    protected long lootTableSeed;

    public CapsuleBlockEntity(BlockPos posIn, BlockState stateIn) {
        super(APBlockEntities.CAPSULE.get(), posIn, stateIn);
    }

    public void load(CompoundTag p_155678_) {
        super.load(p_155678_);
        this.loadFromTag(p_155678_);
        if (p_155678_.contains("CustomName", 8)) {
            this.name = Component.Serializer.fromJson(p_155678_.getString("CustomName"));
        }
    }

    protected void saveAdditional(CompoundTag p_187513_) {
        super.saveAdditional(p_187513_);
        if (!this.trySaveLootTable(p_187513_)) {
            ContainerHelper.saveAllItems(p_187513_, this.itemStacks, false);
        }
        if (this.name != null) {
            p_187513_.putString("CustomName", Component.Serializer.toJson(this.name));
        }

    }

    public void loadFromTag(CompoundTag p_59694_) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_59694_) && p_59694_.contains("Items", 9)) {
            ContainerHelper.loadAllItems(p_59694_, this.itemStacks);
        }

    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }
    @Override
    public boolean isEmpty() {
        this.unpackLootTable((Player)null);
        return this.getItemStacks().stream().allMatch(ItemStack::isEmpty);
    }
    @Override
    public ItemStack getItem(int index) {
        this.unpackLootTable((Player)null);
        return this.getItemStacks().get(index);
    }
    @Override
    public ItemStack removeItem(int index, int count) {
        this.unpackLootTable((Player)null);
        ItemStack itemstack = ContainerHelper.removeItem(this.getItemStacks(), index, count);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }

        return itemstack;
    }
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        this.unpackLootTable((Player)null);
        return ContainerHelper.takeItem(this.getItemStacks(), index);
    }
    @Override
    public void setItem(int index, ItemStack p_59617_) {
        this.unpackLootTable((Player)null);
        this.getItemStacks().set(index, p_59617_);
        if (p_59617_.getCount() > this.getMaxStackSize()) {
            p_59617_.setCount(this.getMaxStackSize());
        }

        this.setChanged();
    }
    @Override
    public boolean stillValid(Player p_59619_) {
        return Container.stillValidBlockEntity(this, p_59619_);
    }
    @Override
    public void clearContent() {
        this.getItemStacks().clear();
    }

    protected NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }

    protected void setItemStacks(NonNullList<ItemStack> p_58610_) {
        this.itemStacks = p_58610_;
    }


    public static void setLootTable(BlockGetter p_222767_, RandomSource p_222768_, BlockPos p_222769_, ResourceLocation p_222770_) {
        BlockEntity blockentity = p_222767_.getBlockEntity(p_222769_);
        if (blockentity instanceof CapsuleBlockEntity capsuleBlockEntity) {
            capsuleBlockEntity.setLootTable(p_222770_, p_222768_.nextLong());
        }

    }

    public void setLootTable(ResourceLocation p_59627_, long p_59628_) {
        this.lootTable = p_59627_;
        this.lootTableSeed = p_59628_;
    }

    protected boolean tryLoadLootTable(CompoundTag p_59632_) {
        if (p_59632_.contains("LootTable", 8)) {
            this.lootTable = new ResourceLocation(p_59632_.getString("LootTable"));
            this.lootTableSeed = p_59632_.getLong("LootTableSeed");
            return true;
        } else {
            return false;
        }
    }

    protected boolean trySaveLootTable(CompoundTag p_59635_) {
        if (this.lootTable == null) {
            return false;
        } else {
            p_59635_.putString("LootTable", this.lootTable.toString());
            if (this.lootTableSeed != 0L) {
                p_59635_.putLong("LootTableSeed", this.lootTableSeed);
            }

            return true;
        }
    }

    public void unpackLootTable(@Nullable Player p_59641_) {
        if (this.lootTable != null && this.level.getServer() != null) {
            LootTable loottable = this.level.getServer().getLootData().getLootTable(this.lootTable);
            if (p_59641_ instanceof ServerPlayer) {
                CriteriaTriggers.GENERATE_LOOT.trigger((ServerPlayer)p_59641_, this.lootTable);
            }

            this.lootTable = null;
            LootParams.Builder lootparams$builder = (new LootParams.Builder((ServerLevel)this.level)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(this.worldPosition));
            if (p_59641_ != null) {
                lootparams$builder.withLuck(p_59641_.getLuck()).withParameter(LootContextParams.THIS_ENTITY, p_59641_);
            }

            loottable.fill(this, lootparams$builder.create(LootContextParamSets.CHEST), this.lootTableSeed);
        }

    }

    public void setCustomName(Component p_58639_) {
        this.name = p_58639_;
    }

    public Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    public Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    public Component getCustomName() {
        return this.name;
    }

    protected Component getDefaultName() {
        return Component.translatable("container.capsule.name");
    }
}
