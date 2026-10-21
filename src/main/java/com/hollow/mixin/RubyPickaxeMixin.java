package com.hollow.mixin;

import com.hollow.registry.Tools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class RubyPickaxeMixin {

    @Shadow @Final protected ServerPlayerEntity player;

    @Unique
    private static final ThreadLocal<Boolean> RUBY_VEIN_MINING =
            ThreadLocal.withInitial(() -> false);

    @Unique
    private static final int MAX_VEIN_SIZE = 64;

    @Unique
    private static final TagKey<Block> VEIN_MINER_ORES =
            TagKey.of(
                    RegistryKeys.BLOCK,
                    Identifier.of("ruby-mod", "vein_miner_ores")
            );

    @Inject(method = "tryBreakBlock", at = @At("HEAD"))
    private void rubyVeinMiner(
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (RUBY_VEIN_MINING.get()) return;

        if (!player.getMainHandStack().isOf(Tools.RUBY_PICKAXE)) return;

        World world = player.getWorld();
        BlockState initialState = world.getBlockState(pos);

        if (!initialState.isIn(VEIN_MINER_ORES)) return;

        RUBY_VEIN_MINING.set(true);

        try {
            Set<BlockPos> visited = new HashSet<>();
            List<BlockPos> vein = new ArrayList<>();
            ArrayDeque<BlockPos> queue = new ArrayDeque<>();

            BlockPos start = pos.toImmutable();
            visited.add(start);
            queue.add(start);

            while (!queue.isEmpty() && vein.size() < MAX_VEIN_SIZE) {
                BlockPos current = queue.removeFirst();

                BlockPos[] neighbors = {
                        current.up(),
                        current.down(),
                        current.north(),
                        current.south(),
                        current.east(),
                        current.west()
                };

                for (BlockPos neighbor : neighbors) {
                    BlockPos next = neighbor.toImmutable();

                    if (visited.contains(next)) continue;

                    BlockState nextState = world.getBlockState(next);

                    if (!nextState.isIn(VEIN_MINER_ORES)) continue;
                    if (!sameOreFamily(initialState, nextState)) continue;

                    visited.add(next);
                    queue.addLast(next);
                    vein.add(next);

                    if (vein.size() >= MAX_VEIN_SIZE) break;
                }
            }

            ServerPlayerInteractionManager manager =
                    (ServerPlayerInteractionManager) (Object) this;

            for (BlockPos orePos : vein) {
                if (!player.isAlive()) break;
                if (player.getMainHandStack().isEmpty()) break;

                manager.tryBreakBlock(orePos);
            }
        } finally {
            RUBY_VEIN_MINING.set(false);
        }
    }

    @Unique
    private static boolean sameOreFamily(
            BlockState first,
            BlockState second
    ) {
        if (first.isOf(second.getBlock())) return true;

        if (first.isIn(BlockTags.COAL_ORES))
            return second.isIn(BlockTags.COAL_ORES);

        if (first.isIn(BlockTags.IRON_ORES))
            return second.isIn(BlockTags.IRON_ORES);

        if (first.isIn(BlockTags.COPPER_ORES))
            return second.isIn(BlockTags.COPPER_ORES);

        if (isGoldOre(first))
            return isGoldOre(second);

        if (first.isIn(BlockTags.DIAMOND_ORES))
            return second.isIn(BlockTags.DIAMOND_ORES);

        if (first.isIn(BlockTags.EMERALD_ORES))
            return second.isIn(BlockTags.EMERALD_ORES);

        if (first.isIn(BlockTags.LAPIS_ORES))
            return second.isIn(BlockTags.LAPIS_ORES);

        if (first.isIn(BlockTags.REDSTONE_ORES))
            return second.isIn(BlockTags.REDSTONE_ORES);

        Identifier firstId = Registries.BLOCK.getId(first.getBlock());
        Identifier secondId = Registries.BLOCK.getId(second.getBlock());

        if (firstId.getNamespace().equals("ruby-mod")
                && secondId.getNamespace().equals("ruby-mod")) {
            return firstId.getPath().endsWith("ruby_ore")
                    && secondId.getPath().endsWith("ruby_ore");
        }

        return false;
    }

    @Unique
    private static boolean isGoldOre(BlockState state) {
        return state.isIn(BlockTags.GOLD_ORES)
                || state.isOf(net.minecraft.block.Blocks.NETHER_GOLD_ORE);
    }
}