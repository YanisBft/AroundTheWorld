package com.yanisbft.aroundtheworld.block;

import com.yanisbft.aroundtheworld.block.entity.TravelerBlockEntity;
import com.yanisbft.aroundtheworld.item.BiomeEmblemItem;
import com.yanisbft.aroundtheworld.item.TravelerKeyItem;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.UUID;

public class TravelerBlock extends BlockWithEntity {

    public TravelerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TravelerBlockEntity travelerBlockEntity) {
            ItemStack handStack = player.getStackInHand(hand);
            UUID playerUuid = player.getUuid();

            // if the traveller block does not have an owner yet
            if (travelerBlockEntity.getOwner() == null) {
                travelerBlockEntity.setOwner(playerUuid);
            }

            // if the player is the owner of the traveller block
            if (playerUuid.equals(travelerBlockEntity.getOwner())) {

                // if the player holds a traveller key
                if (handStack.getItem() instanceof TravelerKeyItem travelerKeyItem) {

                    // if the player is the owner of the traveller key
                    if (playerUuid.equals(travelerKeyItem.getOwner(handStack))) {

                        if (world instanceof ServerWorld serverWorld && player instanceof ServerPlayerEntity serverPlayer) {
                            Biome biome = this.getBiome(serverWorld, travelerBlockEntity);

                            if (biome != null) {
                                this.teleportToBiome(serverWorld, serverPlayer, biome, travelerBlockEntity.getPos());
                            } else {
                                player.sendMessage(new TranslatableText("block.aroundtheworld.traveler.no_biome_emblem"), true);
                            }
                        }
                    } else {
                        player.sendMessage(new TranslatableText("block.aroundtheworld.traveler.not_owner.traveler_key"), true);
                    }
                } else {
                    player.openHandledScreen(travelerBlockEntity);
                }
            } else {
                player.sendMessage(new TranslatableText("block.aroundtheworld.traveler.not_owner.traveler"), true);
            }
        }

        return ActionResult.CONSUME;
    }

    private Biome getBiome(ServerWorld world, TravelerBlockEntity blockEntity) {
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
            player.teleport(world, biomePos.getX(), this.getSurfacePos(world, pos).getY(), biomePos.getZ(), player.getYaw(), player.getPitch());
        } else {
            player.sendMessage(new TranslatableText("block.aroundtheworld.traveler.no_biome_found"), true);
        }
    }

    private BlockPos getSurfacePos(ServerWorld world, BlockPos pos) {
        if (world.getBlockState(pos).isAir()) {
            return pos;
        }
        return this.getSurfacePos(world, pos.up());
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TravelerBlockEntity travelerBlockEntity) {
                ItemScatterer.spawn(world, pos, travelerBlockEntity);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TravelerBlockEntity(pos, state);
    }
}
