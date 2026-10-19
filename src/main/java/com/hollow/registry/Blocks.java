package com.hollow.registry;

import com.hollow.RubyMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class Blocks {

    public static final Block RUBY_ORE = Registry.register(
            Registries.BLOCK,
            RubyMod.id("ruby_ore"),
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.copy(net.minecraft.block.Blocks.DIAMOND_ORE)
                            .requiresTool()
            )
    );

    public static final Block DEEPSLATE_RUBY_ORE = Registry.register(
            Registries.BLOCK,
            RubyMod.id("deepslate_ruby_ore"),
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.copy(net.minecraft.block.Blocks.DEEPSLATE_DIAMOND_ORE)
                            .requiresTool()
            )
    );

    public static final Block RUBY_BLOCK = Registry.register(
            Registries.BLOCK,
            RubyMod.id("ruby_block"),
            new Block(
                    AbstractBlock.Settings.copy(net.minecraft.block.Blocks.DIAMOND_BLOCK)
                            .requiresTool()
            )
    );

    public static void initialize() {
        RubyMod.LOGGER.info("Bloques de Ruby Mod registrados.");
    }
}
