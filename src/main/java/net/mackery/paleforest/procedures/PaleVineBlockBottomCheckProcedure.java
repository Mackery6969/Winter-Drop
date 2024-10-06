package net.mackery.paleforest.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModBlocks;

import java.util.Map;

public class PaleVineBlockBottomCheckProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE.get()) && !((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE_BOTTOM.get())) {
			if (!world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude() && !(world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves")))) {
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			}
		}
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE.get() || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == PaleForestModBlocks.PALE_VINE_BOTTOM.get()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PaleForestModBlocks.PALE_VINE_BOTTOM.get()) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = PaleForestModBlocks.PALE_VINE.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		} else {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == PaleForestModBlocks.PALE_VINE.get()) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = PaleForestModBlocks.PALE_VINE_BOTTOM.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
						if (_property != null && _bs.getValue(_property) != null)
							try {
								_bs = _bs.setValue(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
	}
}
