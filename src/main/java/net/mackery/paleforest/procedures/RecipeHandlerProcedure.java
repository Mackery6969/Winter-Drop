package net.mackery.paleforest.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;

import net.mackery.paleforest.init.PaleForestModBlocks;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RecipeHandlerProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PaleForestModBlocks.PALE_LOGS.get())) : false) {
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_planks")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_wood")});
		} else if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PaleForestModBlocks.STRIPPED_PALE_LOGS.get())) : false) {
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_planks")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:stripped_pale_wood")});
		} else if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PaleForestModBlocks.PALE_PLANKS.get())) : false) {
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("minecraft:crafting_table")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("minecraft:stick")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_stairs")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_fence")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_fence_gate")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_door")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_trapdoor")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_slab")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_pressure_plate")});
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_button")});
		}
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(PaleForestModBlocks.PALE_SAND.get())) : false) {
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("pale_forest:pale_sandstone")});
		}
	}
}
