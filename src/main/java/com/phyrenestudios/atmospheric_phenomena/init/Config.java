package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue LIGHTNING_MAX_BLOCKS = BUILDER
            .comment("The maximum number of blocks that can be converted by a lightning strike")
            .defineInRange("lightningMaxBlocks", 80, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue BURNING_LOG_SPAWN_FIRE_CHANCE = BUILDER
            .comment("The chance for a burning log to spawn fire around it.")
            .defineInRange("burningLogSpawnFire", 0.05D, 0.0D, 1.0D);

    private static final ForgeConfigSpec.IntValue COMET_CHANCE = BUILDER
            .comment("The chance for a comet to spawn. Set to 0 to disable.")
            .defineInRange("cometChance", 1000000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue METEOR_CHANCE = BUILDER
            .comment("The chance for a meteor to spawn. Set to 0 to disable.")
            .defineInRange("meteorChance", 1000000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.BooleanValue METEORITE_DESTROY_ALL = BUILDER
            .comment("Defines if meteorite features destroy all blocks. If false, the feature only replaces blocks from #atmospheric_phenomena:valid_meteorite_spawn.")
            .define("meteoriteDestroyAll", true);
    private static final ForgeConfigSpec.IntValue METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with blocks from #atmospheric_phenomena:meteor_blocks.")
            .defineInRange("meteoriteChance", 10, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue RARE_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with blocks from #atmospheric_phenomena:rare_meteor_blocks.")
            .defineInRange("rareMeteoriteChance", 3, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue ULTRA_RARE_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with blocks from #atmospheric_phenomena:ultra_rare_meteor_blocks.")
            .defineInRange("ultraRareMeteoriteChance", 1, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue SOLID_CORE_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with no core blocks.")
            .defineInRange("solidCoreMeteoriteChance", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue BURRIED_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with a filled in crater.")
            .defineInRange("burriedMeteoriteChance", 0.3D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue MAGMA_FREQUENCY = BUILDER
            .comment("The chance to determine the frequency of magma blocks in the crater wall.")
            .defineInRange("magmaBlockFrequency", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue TEKTITE_FREQUENCY = BUILDER
            .comment("The chance to determine the frequency of blocks in #atmospheric_phenomena:strewn_blocks to generate in the crater wall.")
            .defineInRange("tektiteBlockFrequency", 0.2D, 0.0D, 1.0D);


    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int lightningMaxBlocks;
    public static double burningLogSpawnFire;
    public static boolean meteoriteDestroyAll;

    public static int cometChance;
    public static int meteorChance;
    public static int meteoriteChance;
    public static int rareMeteoriteChance;
    public static int ultraRareMeteoriteChance;
    public static double solidCoreMeteoriteChance;
    public static double magmaBlockFrequency;
    public static double tektiteBlockFrequency;
    public static double burriedMeteoriteChance;


    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        lightningMaxBlocks = LIGHTNING_MAX_BLOCKS.get();
        burningLogSpawnFire = BURNING_LOG_SPAWN_FIRE_CHANCE.get();
        meteoriteDestroyAll = METEORITE_DESTROY_ALL.get();

        cometChance = COMET_CHANCE.get();
        meteorChance = METEOR_CHANCE.get();
        meteoriteChance = METEORITE_CHANCE.get();
        rareMeteoriteChance = RARE_METEORITE_CHANCE.get();
        ultraRareMeteoriteChance = ULTRA_RARE_METEORITE_CHANCE.get();
        solidCoreMeteoriteChance = SOLID_CORE_METEORITE_CHANCE.get();
        magmaBlockFrequency = MAGMA_FREQUENCY.get();
        tektiteBlockFrequency = TEKTITE_FREQUENCY.get();
        burriedMeteoriteChance = BURRIED_METEORITE_CHANCE.get();

    }
}
