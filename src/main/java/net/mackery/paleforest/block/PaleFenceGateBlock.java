
package net.mackery.paleforest.block;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class PaleFenceGateBlock extends FenceGateBlock {
	public PaleFenceGateBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(4f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape().forceSolidOn(), WoodType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
