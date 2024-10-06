
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mackery.paleforest.block.entity.PaleSaplingBlockEntity;
import net.mackery.paleforest.block.entity.CreakingHeartBlockEntity;
import net.mackery.paleforest.block.entity.CreakingHeartActiveBlockEntity;
import net.mackery.paleforest.PaleForestMod;

public class PaleForestModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PaleForestMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> CREAKING_HEART = register("creaking_heart", PaleForestModBlocks.CREAKING_HEART, CreakingHeartBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> PALE_SAPLING = register("pale_sapling", PaleForestModBlocks.PALE_SAPLING, PaleSaplingBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> CREAKING_HEART_ACTIVE = register("creaking_heart_active", PaleForestModBlocks.CREAKING_HEART_ACTIVE, CreakingHeartActiveBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
