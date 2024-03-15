package com.phyrenestudios.atmospheric_phenomena.saved_data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;

public class CometCountdownData extends SavedData {
    private int cometCountdown = 0;

    public static SavedData.Factory<CometCountdownData> factory() {
        return new SavedData.Factory<>(() -> {
            return new CometCountdownData();
        }, (p_296865_) -> {
            return load(p_296865_);
        }, DataFixTypes.SAVED_DATA_RAIDS);
    }

    public static CometCountdownData create() {
        return new CometCountdownData();
    }

    public static CometCountdownData load(CompoundTag tag) {
        CometCountdownData data = create();
        int testInt = tag.getInt("comet_countdown");
        data.cometCountdown = testInt;
        return data;
    }

    public CompoundTag save(CompoundTag tag) {
        tag.putInt("comet_countdown", cometCountdown);
        return tag;
    }

    public static CometCountdownData getDataManager(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(factory(), "comet_countdown");
    }

    public int getCometCountdown() {
        return this.cometCountdown;
    }
    public void setCometCountdown(int cometCountdown) {
        this.cometCountdown = cometCountdown;
        this.setDirty();
    }
}
