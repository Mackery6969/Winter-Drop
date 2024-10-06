package net.mackery.paleforest.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import net.mackery.paleforest.init.PaleForestModBlocks;

@Mod.EventBusSubscriber
public class BoneMealCureProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(),
				event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z,
			Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getItem() == Items.BONE_MEAL) {
			// Cast x, y, z to int
			BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
			BlockState currentState = world.getBlockState(pos);

			// Replace pale blocks with their cured version
			if (currentState.is(PaleForestModBlocks.PALE_LOGS.get())) {
				world.setBlock(pos, Blocks.OAK_LOG.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_PLANKS.get())) {
				world.setBlock(pos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_GRASS.get())) {
				world.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_DIRT.get())) {
				world.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_MOSS_BLOCK.get())) {
				world.setBlock(pos, Blocks.MOSS_BLOCK.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_STAIRS.get())) {
				world.setBlock(pos, Blocks.OAK_STAIRS.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_SLABS.get())) {
				world.setBlock(pos, Blocks.OAK_SLAB.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_FENCE.get())) {
				world.setBlock(pos, Blocks.OAK_FENCE.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_FENCE_GATE.get())) {
				world.setBlock(pos, Blocks.OAK_FENCE_GATE.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_DOOR.get())) {
				world.setBlock(pos, Blocks.OAK_DOOR.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_TRAPDOOR.get())) {
				world.setBlock(pos, Blocks.OAK_TRAPDOOR.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_PRESSURE_PLATE.get())) {
				world.setBlock(pos, Blocks.OAK_PRESSURE_PLATE.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_BUTTON.get())) {
				world.setBlock(pos, Blocks.OAK_BUTTON.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_COARSE_DIRT.get())) {
				world.setBlock(pos, Blocks.COARSE_DIRT.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_SAND.get())) {
				world.setBlock(pos, Blocks.SAND.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_SANDSTONE.get())) {
				world.setBlock(pos, Blocks.SANDSTONE.defaultBlockState(), 3);
			} else if (currentState.is(PaleForestModBlocks.PALE_LEAVES.get())) {
				world.setBlock(pos, Blocks.OAK_LEAVES.defaultBlockState(), 3);
			}

			// Skip saplings intentionally, as specified
		}
	}
}
