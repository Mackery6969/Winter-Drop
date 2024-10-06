package net.mackery.paleforest.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModBlocks;

public class PaleVineBlockValidPlacementConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE.get()
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE_BOTTOM.get()
				|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
			return true;
		}
		return false;
	}
}
