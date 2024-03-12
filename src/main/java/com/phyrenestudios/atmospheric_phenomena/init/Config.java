package com.phyrenestudios.atmospheric_phenomena.init;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> OVERWORLD_METEOR_SPAWN_SETTINGS = BUILDER
            .comment("Settings for meteors spawning in the Overworld.",
                    "The first integer represents the time in minutes between meteor spawns. A full minecraft day cycle is 20 minutes. Set to 0 to disable meteor spawning. Default: 70",
                    "The second integer represents the time variability in minutes of comet spawns. Default: 20",
                    "The third integer represents the Y-level that meteors spawn at in the Overworld.. Default: 350",
                    "The fourth integer represents the minimum spawn size of a meteor. Default: 500",
                    "The fifth integer represents the maximum spawn size of a meteor. Default: 1200",
                    "The sixth integer represents the rate at which a meteor decreases in size per tick. Default: 1")
            .defineList("overworldMeteorSpawnSettings", Arrays.asList(70, 20, 350, 600, 1200, 1), (i) -> i instanceof Integer);
    private static final ForgeConfigSpec.ConfigValue<List<? extends Double>> OVERWORLD_METEOR_VELOCITY = BUILDER
            .comment("Meteors spawn in with a random velocity. Set the lower and upper bounds to configure the direction and speed of new meteors.",
                    "The first double represents the minimum bound in the X axis . Default: -1.0",
                    "The second double represents the minimum bound in the Y axis . Default: -1.5",
                    "The third double represents the minimum bound in the Z axis . Default: -1.0",
                    "The fourth double represents the maximum bound in the X axis . Default: 1.0",
                    "The fifth double represents the maximum bound in the Y axis . Default: -0.5",
                    "The sixth double represents the maximum bound in the Z axis . Default: 1.0")
            .defineList("overworldMeteorVelocity", Arrays.asList(-0.5D, -1.5D, -0.5D, 0.5D, -0.5D, 0.5D), (d) -> d instanceof Double);
    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> OVERWORLD_COMET_SPAWN_SETTINGS = BUILDER
            .comment("Settings for comets spawning in the Overworld.",
                    "The first integer represents the time in minutes between comet spawns. A full minecraft day cycle is 20 minutes. Set to 0 to disable comet spawning. Default: 70",
                    "The second integer represents the time variability in minutes of comet spawns. Default: 20",
                    "The third integer represents the Y-level that comets spawn at in the Overworld.. Default: 350",
                    "The fourth integer represents the minimum spawn size of a meteor. Default: 500",
                    "The fifth integer represents the maximum spawn size of a meteor. Default: 1200",
                    "The sixth integer represents the rate at which a comet decreases in size per tick. Default: 2")
            .defineList("overworldCometSpawnSettings", Arrays.asList(70, 20, 350, 600, 1200, 2), (i) -> i instanceof Integer);
    private static final ForgeConfigSpec.ConfigValue<List<? extends Double>> OVERWORLD_COMET_VELOCITY = BUILDER
            .comment("Comets spawn in with a random velocity. Set the lower and upper bounds to configure the direction and speed of new comets.",
                    "The first double represents the minimum bound in the X axis . Default: -1.0",
                    "The second double represents the minimum bound in the Y axis . Default: -0.3",
                    "The third double represents the minimum bound in the Z axis . Default: -1.0",
                    "The fourth double represents the maximum bound in the X axis . Default: 1.0",
                    "The fifth double represents the maximum bound in the Y axis . Default: -0.0",
                    "The sixth double represents the maximum bound in the Z axis . Default: 1.0")
            .defineList("overworldCometVelocity", Arrays.asList(-0.5D, -0.8D, -0.5D, 0.5D, -0.3D, 0.5D), (d) -> d instanceof Double);

    private static final ForgeConfigSpec.BooleanValue METEORITE_DESTROY_ALL = BUILDER
            .comment("Defines if meteorite features destroy all blocks. If false, the feature only replaces blocks from #atmospheric_phenomena:valid_meteorite_spawn.")
            .define("meteoriteDestroyAll", true);
    private static final ForgeConfigSpec.DoubleValue METEORITE_CAPSULE_SPAWN_CHANCE = BUILDER
            .comment("The chance for a meteor to spawn with a capsule. Affects generation of capsules when gamerule createImpactCraters is false.")
            .defineInRange("meteoriteCapsuleSpawnChance", 0.5D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue SOLID_CORE_METEORITE_CHANCE = BUILDER
            .comment("The chance for a meteorite to generate with no core blocks.")
            .defineInRange("solidCoreMeteoriteChance", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue MAGMA_FREQUENCY = BUILDER
            .comment("The chance to determine the frequency of magma blocks in the crater wall of an impact crater.")
            .defineInRange("magmaBlockFrequency", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.DoubleValue STREWN_BLOCK_FREQUENCY = BUILDER
            .comment("The chance to determine the frequency of blocks in #atmospheric_phenomena:strewn_blocks to generate in the crater wall.")
            .defineInRange("strewnBlockFrequency", 0.2D, 0.0D, 1.0D);
    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> METEOR_BLOCKS_RARITY = BUILDER
            .comment("Determines the rarity of meteorite blocks selected for meteorite generation. Blocks from all of the meteorite tags are added to a collection.",
                    "The first integer represents the weight of each item in #atmospheric_phenomena:common_meteorite_blocks. Default: 30",
                    "The second integer represents the weight of each item in #atmospheric_phenomena:uncommon_meteorite_blocks. Default: 10",
                    "The third integer represents the weight of each item in #atmospheric_phenomena:rare_meteorite_blocks. Default: 3",
                    "The third integer represents the weight of each item in #atmospheric_phenomena:very_rare_meteorite_blocks. Default: 1")
            .defineList("meteorBlocksRarity", Arrays.asList(30,10,3,1), (i) -> i instanceof Integer);
    private static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> METEOR_CORE_BLOCKS_RARITY = BUILDER
            .comment("Determines the rarity of meteorite core blocks selected for meteorite generation. Blocks from all of the meteorite tags are added to a collection.",
                    "The first integer represents the weight of each item in #atmospheric_phenomena:common_meteorite_core_blocks. Default: 30",
                    "The second integer represents the weight of each item in #atmospheric_phenomena:uncommon_meteorite_core_blocks. Default: 10",
                    "The third integer represents the weight of each item in #atmospheric_phenomena:rare_meteorite_core_blocks. Default: 3",
                    "The third integer represents the weight of each item in #atmospheric_phenomena:very_rare_meteorite_core_blocks. Default: 1")
            .defineList("meteorCoreBlocksRarity", Arrays.asList(30,10,3,1), (i) -> i instanceof Integer);

    private static final ForgeConfigSpec.IntValue LIGHTNING_MAX_BLOCKS_CONVERTED = BUILDER
            .comment("The maximum number of blocks that can be converted by a lightning strike")
            .defineInRange("lightningMaxBlocksConverted", 80, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue BURNING_LOG_SPAWN_FIRE_CHANCE = BUILDER
            .comment("The chance for a burning log to spawn fire around it.")
            .defineInRange("burningLogSpawnFire", 0.05D, 0.0D, 1.0D);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
    public static List<? extends Integer> overworldMeteorSpawnSettings;
    public static List<? extends Double> overworldMeteorVelocity;
    public static List<? extends Integer> overworldCometSpawnSettings;
    public static List<? extends Double> overworldCometVelocity;
    public static double meteoriteCapsuleSpawnChance;
    public static double solidCoreMeteoriteChance;
    public static double magmaBlockFrequency;
    public static double strewnBlockFrequency;
    public static List<? extends Integer> meteoriteBlocksRarity;
    public static List<? extends Integer> meteoriteCoreBlocksRarity;
    public static int lightningMaxBlocksConverted;
    public static double burningLogSpawnFire;
    public static boolean meteoriteDestroyAll;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        overworldMeteorSpawnSettings = OVERWORLD_METEOR_SPAWN_SETTINGS.get();
        overworldMeteorVelocity = OVERWORLD_METEOR_VELOCITY.get();
        overworldCometSpawnSettings = OVERWORLD_COMET_SPAWN_SETTINGS.get();
        overworldCometVelocity = OVERWORLD_COMET_VELOCITY.get();

        meteoriteDestroyAll = METEORITE_DESTROY_ALL.get();
        meteoriteCapsuleSpawnChance = METEORITE_CAPSULE_SPAWN_CHANCE.get();
        solidCoreMeteoriteChance = SOLID_CORE_METEORITE_CHANCE.get();
        magmaBlockFrequency = MAGMA_FREQUENCY.get();
        strewnBlockFrequency = STREWN_BLOCK_FREQUENCY.get();
        meteoriteBlocksRarity = METEOR_BLOCKS_RARITY.get();
        meteoriteCoreBlocksRarity = METEOR_CORE_BLOCKS_RARITY.get();

        lightningMaxBlocksConverted = LIGHTNING_MAX_BLOCKS_CONVERTED.get();
        burningLogSpawnFire = BURNING_LOG_SPAWN_FIRE_CHANCE.get();

    }
}
