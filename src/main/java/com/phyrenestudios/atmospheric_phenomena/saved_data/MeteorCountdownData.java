package com.phyrenestudios.atmospheric_phenomena.saved_data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.raid.Raids;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.scores.ScoreboardSaveData;

import java.util.function.Function;
import java.util.function.Supplier;

public class MeteorCountdownData extends SavedData {
    private int meteorCountdown = 0;


    public static SavedData.Factory<MeteorCountdownData> factory() {
        return new SavedData.Factory<>(() -> {
            return new MeteorCountdownData();
        }, (p_296865_) -> {
            return load(p_296865_);
        }, DataFixTypes.SAVED_DATA_RAIDS);
    }

    public static MeteorCountdownData create() {
        return new MeteorCountdownData();
    }

    public static MeteorCountdownData load(CompoundTag tag) {
        MeteorCountdownData data = create();
        int testInt = tag.getInt("meteor_countdown");
        data.meteorCountdown = testInt;
        return data;
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("meteor_countdown", meteorCountdown);
        return tag;
    }

    public static MeteorCountdownData getDataManager(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(factory(), "meteor_countdown");
    }

    public int getMeteorCountdown() {
        return this.meteorCountdown;
    }
    public void setMeteorCountdown(int meteorCountdown) {
        this.meteorCountdown = meteorCountdown;
        this.setDirty();
    }
}
