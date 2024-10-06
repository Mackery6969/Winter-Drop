
package net.mackery.paleforest.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class PalePressurePlateBlock extends PressurePlateBlock {
	public PalePressurePlateBlock() {
		super(Sensitivity.EVERYTHING,
				BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(0.5f, 10f).noOcclusion().randomTicks().isRedstoneConductor((bs, br, bp) -> false).dynamicShape().forceSolidOn(),
				BlockSetType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
