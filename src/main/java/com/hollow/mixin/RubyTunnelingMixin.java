package com.hollow.mixin;

import com.hollow.registry.Tools;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class RubyTunnelingMixin {

    @Shadow @Final protected ServerPlayerEntity player;

    @Unique
    private static final ThreadLocal<Boolean> RUBY_TUNNELING =
            ThreadLocal.withInitial(() -> false);

    @Unique
    private static final Identifier TUNNELING_ID =
            Identifier.of("ruby-mod", "tunneling");

    @Unique
    private static final net.minecraft.registry.tag.TagKey<net.minecraft.block.Block>
            TUNNELING_BLOCKS = net.minecraft.registry.tag.TagKey.of(
            RegistryKeys.BLOCK,
            Identifier.of("ruby-mod", "tunneling_blocks")
    );

    @Inject(method = "tryBreakBlock", at = @At("HEAD"))
    private void rubyTunneling(
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (RUBY_TUNNELING.get()) return;

        var stack = player.getMainHandStack();

        if (!stack.isOf(Tools.RUBY_PICKAXE)) return;

        World world = player.getWorld();
        BlockState centerState = world.getBlockState(pos);

        if (!centerState.isIn(TUNNELING_BLOCKS)) return;

        var enchantmentRegistry =
                world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);

        RegistryEntry<Enchantment> tunneling =
                enchantmentRegistry.getEntry(TUNNELING_ID).orElse(null);

        if (tunneling == null) return;
        if (EnchantmentHelper.getLevel(tunneling, stack) <= 0) return;

        Direction facing = player.getHorizontalFacing();
        float pitch = player.getPitch();

        List<BlockPos> extraBlocks = new ArrayList<>();

        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                if (a == 0 && b == 0) continue;

                BlockPos target;

                if (pitch <= -45.0F) {
                    // Mirando hacia arriba: plano horizontal X-Z.
                    target = pos.add(a, 0, b);
                } else if (pitch >= 45.0F) {
                    // Mirando hacia abajo: plano horizontal X-Z.
                    target = pos.add(a, 0, b);
                } else if (facing.getOffsetX() != 0) {
                    // Este u oeste: plano vertical Z-Y.
                    target = pos.add(0, a, b);
                } else {
                    // Norte o sur: plano vertical X-Y.
                    target = pos.add(b, a, 0);
                }

                if (world.getBlockState(target).isIn(TUNNELING_BLOCKS)) {
                    extraBlocks.add(target.toImmutable());
                }
            }
        }

        RUBY_TUNNELING.set(true);

        try {
            ServerPlayerInteractionManager manager =
                    (ServerPlayerInteractionManager) (Object) this;

            for (BlockPos target : extraBlocks) {
                if (!player.isAlive()) break;
                if (player.getMainHandStack().isEmpty()) break;

                manager.tryBreakBlock(target);
            }
        } finally {
            RUBY_TUNNELING.set(false);
        }
    }
}