package com.phyrenestudios.atmospheric_phenomena.data;

import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.data.lang.APEnUsLangProvider;
import com.phyrenestudios.atmospheric_phenomena.data.loot.APLootTableSubProvider;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APBlockTagsProvider;
import com.phyrenestudios.atmospheric_phenomena.data.tags.APItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AtmosphericPhenomena.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(true, new APBlockstateProvider(packOutput, helper));
        gen.addProvider(true, new APItemModelProvider(packOutput, helper));
        gen.addProvider(event.includeClient(), new APEnUsLangProvider(packOutput, "en_us"));
        gen.addProvider(event.includeServer(), new APLootTableSubProvider(packOutput));
        APBlockTagsProvider blockTags = new APBlockTagsProvider(packOutput, provider, helper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new APItemTagProvider(packOutput, provider, blockTags.contentsGetter(), helper));
        gen.addProvider(event.includeServer(), new APRecipesProvider(packOutput));
        /*
        gen.addProvider(event.includeServer(), new RankineBiomeTagsProvider(packOutput, provider, helper));



         */

        //gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, CompletableFuture.supplyAsync(DataGenerators::getProvider), Set.of(AtmosphericPhenomena.MODID)));
    }
/*
    private static HolderLookup.Provider getProvider() {
        final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
        registryBuilder.add(Registries.CONFIGURED_FEATURE, context -> {
            RankineTreeFeatures.bootstrap(context);
            RankineUndergroundFeatures.bootstrap(context);
        });
        registryBuilder.add(Registries.PLACED_FEATURE, context -> {
            RankineTreePlacements.bootstrap(context);
            RankineUndergroundPlacements.bootstrap(context);
        });
        registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
            HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);

            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ProjectRankine.MODID, "overworld_meteorite")), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
                    HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RankineUndergroundPlacements.OVERWORLD_METEORITE)),
                    GenerationStep.Decoration.LOCAL_MODIFICATIONS
            ));

        });
        registryBuilder.add(Registries.BIOME, context -> {});
        RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
    }

 */

}
