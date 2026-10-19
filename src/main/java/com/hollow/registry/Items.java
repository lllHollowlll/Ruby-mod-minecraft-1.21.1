package com.hollow.registry;

import com.hollow.RubyMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Items {

    public static final Item RUBY = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby"),
            new Item(new Item.Settings())
    );

    public static final Item RUBY_ORE_ITEM = Registry.register (
            Registries.ITEM,
            RubyMod.id("ruby_ore"),
            new BlockItem(Blocks.RUBY_ORE, new Item.Settings())
    );

    public static final Item DEEPSLATE_RUBY_ORE_ITEM = Registry.register(
            Registries.ITEM,
            RubyMod.id("deepslate_ruby_ore"),
            new BlockItem(Blocks.DEEPSLATE_RUBY_ORE, new Item.Settings())
    );

    public static final Item RUBY_BLOCK = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_block"),
            new BlockItem(Blocks.RUBY_BLOCK, new Item.Settings())
    );

    public static void initialize() {
        RubyMod.LOGGER.info("Ruby registered.");
    }

}
