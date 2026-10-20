package com.hollow.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class ToolMaterials {

    public static final ToolMaterial RUBY = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 750;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 8.0F;
        }

        @Override
        public float getAttackDamage() {
            return 0.0F;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        @Override
        public int getEnchantability() {
            return 18;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(
                    com.hollow.registry.Items.RUBY
            );
        }
    };

    public static final ToolMaterial RUBY_FAST = new ToolMaterial() {
        @Override
        public int getDurability() {
            return RUBY.getDurability();
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 9.6F;
        }

        @Override
        public float getAttackDamage() {
            return RUBY.getAttackDamage();
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return RUBY.getInverseTag();
        }

        @Override
        public int getEnchantability() {
            return RUBY.getEnchantability();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return RUBY.getRepairIngredient();
        }
    };

}