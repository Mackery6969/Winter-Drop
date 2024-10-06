package net.mackery.paleforest.block.entity;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModBlockEntities;

public class PaleSaplingBlockEntity extends BlockEntity {
	public PaleSaplingBlockEntity(BlockPos pos, BlockState state) {
		super(PaleForestModBlockEntities.PALE_SAPLING.get(), pos, state);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithFullMetadata();
	}
}
