
package net.mackery.paleforest.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class PaleButtonBlock extends ButtonBlock {
	public PaleButtonBlock() {
		super(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.WOOD).strength(0.5f, 1f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape(), BlockSetType.STONE, 20, false);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
