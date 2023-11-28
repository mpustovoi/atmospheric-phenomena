package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class APWoodTypes {

    public static final WoodType CHARRED = WoodType.register(new WoodType(AtmosphericPhenomena.MODID+":charred", BlockSetType.OAK));
}
