package net.mackery.paleforest.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import net.mackery.paleforest.init.PaleForestModGameRules;
import net.mackery.paleforest.init.PaleForestModBlocks;

public class SpreadingProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		// Only proceed if the gamerule is enabled
		if (world.getLevelData().getGameRules().getBoolean(PaleForestModGameRules.SPREADING_PALENESS)) {
			// Set a 10% chance for spreading
			if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
				BlockPos sourcePos = BlockPos.containing(x, y, z);

				// Iterate over a 3x3x3 area centered on (x, y, z)
				for (int sx = -1; sx <= 1; sx++) {
					for (int sy = -1; sy <= 1; sy++) {
						for (int sz = -1; sz <= 1; sz++) {
							BlockPos targetPos = sourcePos.offset(sx, sy, sz);

							// Skip the center block (current block)
							if (sx == 0 && sy == 0 && sz == 0)
								continue;

							// Ensure there's enough air around the target block before spreading
							if (hasAirAround(world, targetPos)) {
								// Check if the block is not in the pale_blocks tag
								if (!world.getBlockState(targetPos)
										.is(BlockTags.create(new ResourceLocation("pale_garden:pale_blocks")))) {
									// Randomly select this block for replacement
									if (Mth.nextInt(RandomSource.create(), 1, 10) == 1) {
										// Replace the block with the corresponding pale block
										replaceWithPaleBlock(world, targetPos);
										return; // Exit after one block spread
									}
								}
							}
						}
					}
				}
			}

			// If no block was spread, call the block-specific procedures
			checkAndCallProcedures(world, x, y, z);
		}
	}

	private static boolean hasAirAround(LevelAccessor world, BlockPos pos) {
		// Check the 3x3x3 area around the target position for air blocks
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				for (int dz = -1; dz <= 1; dz++) {
					BlockPos checkPos = pos.offset(dx, dy, dz);
					if (world.getBlockState(checkPos).isAir()) {
						return true; // Found air around the block
					}
				}
			}
		}
		return false; // No air around the block
	}

	private static void replaceWithPaleBlock(LevelAccessor world, BlockPos targetPos) {
		BlockState targetBlockState = world.getBlockState(targetPos);
		BlockState newBlockState = null;

		// Replace block based on its type
		// first check if the block is already a pale block or if it's a Creaking Heart
		// block
		if (targetBlockState.is(BlockTags.create(new ResourceLocation("pale_garden:pale_blocks"))) ||
				targetBlockState.getBlock() == PaleForestModBlocks.CREAKING_HEART.get() ||
				targetBlockState.getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get()) {
			return; // Ignore if it's already a pale block or a Creaking Heart block
		}

		if (targetBlockState.is(BlockTags.LOGS)) {
			newBlockState = PaleForestModBlocks.PALE_LOGS.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.PLANKS)) {
			newBlockState = PaleForestModBlocks.PALE_PLANKS.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.SAPLINGS)) {
			newBlockState = PaleForestModBlocks.PALE_SAPLING.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.GRASS_BLOCK)) {
			newBlockState = PaleForestModBlocks.PALE_GRASS.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.DIRT)) {
			newBlockState = PaleForestModBlocks.PALE_DIRT.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.MOSS_BLOCK)) {
			newBlockState = PaleForestModBlocks.PALE_MOSS_BLOCK.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_STAIRS)) {
			newBlockState = PaleForestModBlocks.PALE_STAIRS.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_SLABS)) {
			newBlockState = PaleForestModBlocks.PALE_SLABS.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_FENCES)) {
			newBlockState = PaleForestModBlocks.PALE_FENCE.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.FENCE_GATES)) {
			newBlockState = PaleForestModBlocks.PALE_FENCE_GATE.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_DOORS)) {
			newBlockState = PaleForestModBlocks.PALE_DOOR.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_TRAPDOORS)) {
			newBlockState = PaleForestModBlocks.PALE_TRAPDOOR.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_PRESSURE_PLATES)) {
			newBlockState = PaleForestModBlocks.PALE_PRESSURE_PLATE.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.WOODEN_BUTTONS)) {
			newBlockState = PaleForestModBlocks.PALE_BUTTON.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.SAND)) {
			newBlockState = PaleForestModBlocks.PALE_SAND.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.SANDSTONE)) {
			newBlockState = PaleForestModBlocks.PALE_SANDSTONE.get().defaultBlockState();
		} else if (targetBlockState.is(Blocks.COARSE_DIRT)) {
			newBlockState = PaleForestModBlocks.PALE_COARSE_DIRT.get().defaultBlockState();
		} else if (targetBlockState.is(BlockTags.LEAVES)) {
			newBlockState = PaleForestModBlocks.PALE_LEAVES.get().defaultBlockState();
		}

		// Replace the block with its pale version if a match was found
		if (newBlockState != null) {
			world.setBlock(targetPos, newBlockState, 3);
		}
	}

	private static void checkAndCallProcedures(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = BlockPos.containing(x, y, z);

		// Check if it's a Creaking Heart and execute the corresponding procedure
		if (world.getBlockState(pos).getBlock() == PaleForestModBlocks.CREAKING_HEART.get() ||
				world.getBlockState(pos).getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get()) {
			CreakingHeartCheckProcedure.execute(world, x, y, z);
		}
		// Check for Pale Sapling
		else if (world.getBlockState(pos).getBlock() == PaleForestModBlocks.PALE_SAPLING.get()) {
			PaleSaplingOnTickUpdateProcedure.execute(world, x, y, z);
		}
		// Check for Pale Vine
		else if (world.getBlockState(pos).getBlock() == PaleForestModBlocks.PALE_VINE.get() ||
				world.getBlockState(pos).getBlock() == PaleForestModBlocks.PALE_VINE_BOTTOM.get()) {
			PaleVineBlockBottomCheckProcedure.execute(world, x, y, z);
		}
		// Check for Pale Grass
		else if (world.getBlockState(pos).getBlock() == PaleForestModBlocks.PALE_GRASS.get()) {
			PaleGrassOnTickUpdateProcedure.execute(world, x, y, z);
		}
	}
}
