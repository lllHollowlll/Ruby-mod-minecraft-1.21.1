package com.hollow.registry;

import com.hollow.RubyMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ItemGroups {

    public static final ItemGroup RUBY_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            RubyMod.id("ruby_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(Items.RUBY))
                    .displayName(Text.translatable("itemGroup.ruby-mod.ruby_group"))
                    .entries((context, entries) -> {
                        entries.add(Items.RUBY_BLOCK);
                        entries.add(Items.RUBY_ORE_ITEM);
                        entries.add(Items.DEEPSLATE_RUBY_ORE_ITEM);
                        entries.add(Tools.RUBY_SHOVEL);
                        entries.add(Tools.RUBY_PICKAXE);
                        entries.add(Tools.RUBY_AXE);
                        entries.add(Tools.RUBY_HOE);
                        entries.add(Tools.RUBY_SWORD);
                        entries.add(Items.RUBY);
                    })
            .build()
    );

    public static void initialize() {
        RubyMod.LOGGER.info("All items registered.");
    }

}
