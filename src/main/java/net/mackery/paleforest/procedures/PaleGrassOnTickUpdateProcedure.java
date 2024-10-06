package net.mackery.paleforest.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModBlocks;

public class PaleGrassOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) && ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get() || (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get() || (world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get() || (world.getBlockState(BlockPos.containing(x + 1, y - 1, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x - 1, y - 1, z))).getBlock() == PaleForestModBlocks.PALE_DIRT.get() || (world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get() || (world.getBlockState(BlockPos.containing(x, y - 1, z + 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get()
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z - 1))).getBlock() == PaleForestModBlocks.PALE_DIRT.get())) {
			world.setBlock(BlockPos.containing(x, y, z), PaleForestModBlocks.PALE_GRASS.get().defaultBlockState(), 3);
		}
		if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()) {
			world.setBlock(BlockPos.containing(x, y, z), PaleForestModBlocks.PALE_DIRT.get().defaultBlockState(), 3);
		}
	}
}
