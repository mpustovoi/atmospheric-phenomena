package com.phyrenestudios.atmospheric_phenomena.block_entities;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
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

public class MeteorCrateBlockEntity extends BlockEntity implements Container {
    public static final int CONTAINER_SIZE = 16;
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    public static final String LOOT_TABLE_TAG = "LootTable";
    public static final String LOOT_TABLE_SEED_TAG = "LootTableSeed";
    @Nullable
    protected ResourceLocation lootTable;
    protected long lootTableSeed;

    public MeteorCrateBlockEntity(BlockPos posIn, BlockState stateIn) {
        super(APBlockEntities.METEOR_CRATE.get(), posIn, stateIn);
    }

    protected void saveAdditional(CompoundTag p_187459_) {
        super.saveAdditional(p_187459_);
        if (!this.trySaveLootTable(p_187459_)) {
            ContainerHelper.saveAllItems(p_187459_, this.items);
        }

    }
    public void load(CompoundTag p_155055_) {
        super.load(p_155055_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_155055_)) {
            ContainerHelper.loadAllItems(p_155055_, this.items);
        }

    }
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }
    @Override
    public boolean isEmpty() {
        this.unpackLootTable((Player)null);
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }
    @Override
    public ItemStack getItem(int index) {
        this.unpackLootTable((Player)null);
        return this.getItems().get(index);
    }
    @Override
    public ItemStack removeItem(int index, int count) {
        this.unpackLootTable((Player)null);
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), index, count);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }

        return itemstack;
    }
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        this.unpackLootTable((Player)null);
        return ContainerHelper.takeItem(this.getItems(), index);
    }
    @Override
    public void setItem(int index, ItemStack p_59617_) {
        this.unpackLootTable((Player)null);
        this.getItems().set(index, p_59617_);
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
        this.getItems().clear();
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> p_58610_) {
        this.items = p_58610_;
    }


    public static void setLootTable(BlockGetter p_222767_, RandomSource p_222768_, BlockPos p_222769_, ResourceLocation p_222770_) {
        BlockEntity blockentity = p_222767_.getBlockEntity(p_222769_);
        if (blockentity instanceof MeteorCrateBlockEntity) {
            ((MeteorCrateBlockEntity)blockentity).setLootTable(p_222770_, p_222768_.nextLong());
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
}
