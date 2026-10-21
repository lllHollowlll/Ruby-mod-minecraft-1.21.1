package com.hollow.registry;

import com.hollow.RubyMod;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Tools {

    public static final Item RUBY_SWORD = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_sword"),
            new SwordItem(
                    ToolMaterials.RUBY,
                    new Item.Settings().attributeModifiers(
                            SwordItem.createAttributeModifiers(ToolMaterials.RUBY, 5, -2.0F)
                    )
            )
    );

    public static final Item RUBY_PICKAXE = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_pickaxe"),
            new PickaxeItem(
                    ToolMaterials.RUBY_FAST,
                    new Item.Settings().attributeModifiers(
                            PickaxeItem.createAttributeModifiers(ToolMaterials.RUBY_FAST, 4, -2.4F)
                    )
            )
    );

    public static final Item RUBY_AXE = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_axe"),
            new AxeItem(
                    ToolMaterials.RUBY_FAST,
                    new Item.Settings().attributeModifiers(
                            AxeItem.createAttributeModifiers(ToolMaterials.RUBY_FAST, 7, -2.2F)
                    )
            )
    );

    public static final Item RUBY_HOE = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_hoe"),
            new HoeItem(
                    ToolMaterials.RUBY,
                    new Item.Settings().attributeModifiers(
                            HoeItem.createAttributeModifiers(ToolMaterials.RUBY, 2, -2.0F)
                    )
            )
    );

    public static final Item RUBY_SHOVEL = Registry.register(
            Registries.ITEM,
            RubyMod.id("ruby_shovel"),
            new ShovelItem(
                    ToolMaterials.RUBY,
                    new Item.Settings().attributeModifiers(
                            ShovelItem.createAttributeModifiers(ToolMaterials.RUBY, 3, -2.4F)
                    )
            )
    );

    public static void initialize() {
        RubyMod.LOGGER.info("Ruby tools registered.");
    }
}