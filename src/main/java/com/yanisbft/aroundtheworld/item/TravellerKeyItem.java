package com.yanisbft.aroundtheworld.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class TravellerKeyItem extends Item {

    public TravellerKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getTag();
        if (nbt != null && nbt.contains("Owner")) {
            PlayerEntity player = world.getPlayerByUuid(nbt.getUuid("Owner"));
            if (player != null) {
                tooltip.add(new TranslatableText("item.aroundtheworld.traveller_key.owner", player.getEntityName()).formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateTag();
        nbt.putUuid("Owner", player.getUuid());
        super.onCraft(stack, world, player);
    }
}
