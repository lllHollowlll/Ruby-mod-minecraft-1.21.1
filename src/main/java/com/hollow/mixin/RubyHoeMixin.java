package com.hollow.mixin;

import com.hollow.registry.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class RubyHoeMixin {

	@Inject(
			method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
			at = @At("RETURN"),
			cancellable = true
	)
	private static void rubyHoeDuplicateDrops(
			BlockState state,
			ServerWorld world,
			BlockPos pos,
			net.minecraft.block.entity.BlockEntity blockEntity,
			net.minecraft.entity.Entity entity,
			ItemStack tool,
			CallbackInfoReturnable<List<ItemStack>> cir
	) {
		if (!(entity instanceof PlayerEntity)) return;
		if (!tool.isOf(Tools.RUBY_HOE)) return;
		if (!(state.getBlock() instanceof CropBlock)) return;
		if (state.get(CropBlock.AGE) < CropBlock.MAX_AGE) return;

		if (world.getRandom().nextFloat() >= 0.10F) return;

		List<ItemStack> originalDrops = cir.getReturnValue();
		List<ItemStack> result = new ArrayList<>(originalDrops.size());

		for (ItemStack drop : originalDrops) {
			ItemStack copy = drop.copy();

			if (drop.isOf(Items.POTATO)
					|| drop.isOf(Items.CARROT)
					|| drop.isOf(Items.WHEAT)
					|| drop.isOf(Items.BEETROOT)
					|| drop.isOf(Items.NETHER_WART)
					|| drop.isOf(Items.MELON)
					|| drop.isOf(Items.PUMPKIN)) {
				copy.setCount(copy.getCount() * 2);
			}

			result.add(copy);
		}

		cir.setReturnValue(result);
	}
}