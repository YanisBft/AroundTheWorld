package com.yanisbft.aroundtheworld.block;

import com.yanisbft.aroundtheworld.block.entity.TravellerBlockEntity;
import com.yanisbft.aroundtheworld.item.BiomeEmblemItem;
import com.yanisbft.aroundtheworld.item.TravellerKeyItem;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class TravellerBlock extends BlockWithEntity {

    public TravellerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TravellerBlockEntity travellerBlockEntity) {
            ItemStack handStack = player.getStackInHand(hand);

            if (handStack.getItem() instanceof TravellerKeyItem) {
                if (world instanceof ServerWorld serverWorld && player instanceof ServerPlayerEntity serverPlayer) {
                    Biome biome = this.getBiome(serverWorld, travellerBlockEntity);

                    if (biome != null) {
                        this.teleportToBiome(serverWorld, serverPlayer, biome, travellerBlockEntity.getPos());
                    } else {
                        //TODO error message: no biome selected
                    }
                }
            } else {
                player.openHandledScreen(travellerBlockEntity);
            }
        }

        return ActionResult.CONSUME;
    }

    private Biome getBiome(ServerWorld world, TravellerBlockEntity blockEntity) {
        ItemStack stack = blockEntity.getStack(0);

        if (!stack.isEmpty() && stack.getItem() instanceof BiomeEmblemItem biomeEmblemItem) {
            RegistryKey<Biome> biomeKey = biomeEmblemItem.getBiomeKey();
            return world.getRegistryManager().get(Registry.BIOME_KEY).get(biomeKey);
        }

        return null;
    }

    private void teleportToBiome(ServerWorld world, ServerPlayerEntity player, Biome biome, BlockPos pos) {
        BlockPos biomePos = world.locateBiome(biome, pos, 6400, 8);

        if (biomePos != null) {
            player.teleport(world, biomePos.getX(), biomePos.getY(), biomePos.getZ(), player.getYaw(), player.getPitch());
        } else {
            //TODO error message: no biome found
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TravellerBlockEntity(pos, state);
    }
}
