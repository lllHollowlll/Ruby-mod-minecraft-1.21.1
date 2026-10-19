package com.hollow.worldgen;

import com.hollow.RubyMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration {

    public static void initialize() {
            BiomeModifications.addFeature(
                    BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    RegistryKey.of(
                            RegistryKeys.PLACED_FEATURE,
                            RubyMod.id("ruby_ore")
                    )
            );

            BiomeModifications.addFeature(
                    BiomeSelectors.foundInOverworld(),
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    RegistryKey.of(
                            RegistryKeys.PLACED_FEATURE,
                            RubyMod.id("deepslate_ruby_ore")
                   )
           );

            RubyMod.LOGGER.info("Ore generation registered.");

    }
}

