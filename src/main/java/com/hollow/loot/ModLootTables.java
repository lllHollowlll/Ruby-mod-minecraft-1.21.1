package com.hollow.loot;

import com.hollow.RubyMod;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.loot.function.SetEnchantmentsLootFunction;

public class ModLootTables {

    public static void initialize() {
        LootTableEvents.MODIFY.register(
                (key, tableBuilder, source, registries) -> {
                    if (!key.equals(
                            RegistryKey.of(
                                    RegistryKeys.LOOT_TABLE,
                                    Identifier.ofVanilla(
                                            "chests/abandoned_mineshaft"
                                    )
                            )
                    )) {
                        return;
                    }

                    var enchantments =
                            registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

                    RegistryKey<Enchantment> tunnelingKey =
                            RegistryKey.of(
                                    RegistryKeys.ENCHANTMENT,
                                    RubyMod.id("tunneling")
                            );

                    var tunneling = enchantments.getOrThrow(tunnelingKey);

                    tableBuilder.pool(
                            LootPool.builder()
                                    .rolls(ConstantLootNumberProvider.create(1))
                                    .with(
                                            ItemEntry.builder(Items.BOOK)
                                                    .apply(
                                                            new SetEnchantmentsLootFunction.Builder()
                                                                    .enchantment(
                                                                            tunneling,
                                                                            ConstantLootNumberProvider.create(1)
                                                                    )
                                                    )
                                    )
                    );
                }
        );
    }
}