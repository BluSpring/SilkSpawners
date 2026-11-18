package xyz.bluspring.silkspawners;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class SilkSpawners implements ModInitializer {
	@Override
	public void onInitialize() {
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if (blockEntity instanceof SpawnerBlockEntity spawner && !player.isCreative()) {
				var stack = player.getItemInHand(InteractionHand.MAIN_HAND);
				if (stack.isCorrectToolForDrops(state) && EnchantmentHelper.hasSilkTouch(stack) && world.destroyBlock(pos, false)) {
					var spawnerStack = new ItemStack(Items.SPAWNER);
					BlockItem.setBlockEntityData(spawnerStack, spawner.getType(), spawner.saveWithoutMetadata());
					Block.popResource(world, pos, spawnerStack);
					return false;
				}
			}

			return true;
		});
	}
}
