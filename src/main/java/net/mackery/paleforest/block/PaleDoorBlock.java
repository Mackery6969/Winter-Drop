
package net.mackery.paleforest.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class PaleDoorBlock extends DoorBlock {
	public PaleDoorBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(4f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape(), BlockSetType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
