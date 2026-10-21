package com.hollow.registry;

import com.hollow.RubyMod;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class Trades {

    public static void initialize() {
        TradeOfferHelper.registerVillagerOffers(
                VillagerProfession.TOOLSMITH,
                5,
                factories -> factories.add((entity, random) -> {
                    var enchantments = entity.getWorld()
                            .getRegistryManager()
                            .get(RegistryKeys.ENCHANTMENT);

                    RegistryKey<Enchantment> tunnelingKey =
                            RegistryKey.of(
                                    RegistryKeys.ENCHANTMENT,
                                    RubyMod.id("tunneling")
                            );

                    var tunneling = enchantments.getEntry(tunnelingKey);

                    if (tunneling.isEmpty()) {
                        return null;
                    }

                    ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

                    ItemEnchantmentsComponent.Builder builder =
                            new ItemEnchantmentsComponent.Builder(
                                    ItemEnchantmentsComponent.DEFAULT
                            );

                    builder.set(tunneling.get(), 1);

                    book.set(
                            DataComponentTypes.STORED_ENCHANTMENTS,
                            builder.build()
                    );

                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, 15),
                            java.util.Optional.of(
                                    new TradedItem(Items.BOOK, 1)
                            ),
                            book,
                            1,
                            30,
                            0.2F
                    );
                })
        );
    }
}