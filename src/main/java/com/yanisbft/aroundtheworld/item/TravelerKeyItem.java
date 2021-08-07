package com.yanisbft.aroundtheworld.item;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class TravelerKeyItem extends Item {

    public TravelerKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("Owner")) {
            GameProfile gameProfile = NbtHelper.toGameProfile(nbt.getCompound("Owner"));
            if (gameProfile != null) {
                tooltip.add(new TranslatableText("item.aroundtheworld.traveler_key.owner", gameProfile.getName()).formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    public GameProfile getOwner(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null && nbt.contains("Owner")) {
            return NbtHelper.toGameProfile(nbt.getCompound("Owner"));
        }
        return null;
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.put("Owner", NbtHelper.writeGameProfile(new NbtCompound(), player.getGameProfile()));
        super.onCraft(stack, world, player);
    }
}
