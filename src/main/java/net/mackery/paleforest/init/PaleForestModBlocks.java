
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mackery.paleforest.block.StrippedPaleWoodBlock;
import net.mackery.paleforest.block.StrippedPaleLogsBlock;
import net.mackery.paleforest.block.PaleWoodBlock;
import net.mackery.paleforest.block.PaleVineBottomBlock;
import net.mackery.paleforest.block.PaleVineBlock;
import net.mackery.paleforest.block.PaleTrapdoorBlock;
import net.mackery.paleforest.block.PaleSlabsBlock;
import net.mackery.paleforest.block.PaleSaplingBlock;
import net.mackery.paleforest.block.PaleSandstoneBlock;
import net.mackery.paleforest.block.PaleSandBlock;
import net.mackery.paleforest.block.PalePressurePlateBlock;
import net.mackery.paleforest.block.PalePlanksBlock;
import net.mackery.paleforest.block.PalePlankStairsBlock;
import net.mackery.paleforest.block.PaleMossBlockBlock;
import net.mackery.paleforest.block.PaleLogsBlock;
import net.mackery.paleforest.block.PaleLeavesBlock;
import net.mackery.paleforest.block.PaleGrassBlock;
import net.mackery.paleforest.block.PaleFenceGateBlock;
import net.mackery.paleforest.block.PaleFenceBlock;
import net.mackery.paleforest.block.PaleDoorBlock;
import net.mackery.paleforest.block.PaleDirtBlock;
import net.mackery.paleforest.block.PaleCourseDirtBlock;
import net.mackery.paleforest.block.PaleButtonBlock;
import net.mackery.paleforest.block.CreakingHeartBlock;
import net.mackery.paleforest.block.CreakingHeartActiveBlock;
import net.mackery.paleforest.PaleForestMod;

public class PaleForestModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, PaleForestMod.MODID);
	public static final RegistryObject<Block> PALE_LOGS = REGISTRY.register("pale_logs", () -> new PaleLogsBlock());
	public static final RegistryObject<Block> PALE_DIRT = REGISTRY.register("pale_dirt", () -> new PaleDirtBlock());
	public static final RegistryObject<Block> PALE_PLANKS = REGISTRY.register("pale_planks", () -> new PalePlanksBlock());
	public static final RegistryObject<Block> PALE_GRASS = REGISTRY.register("pale_grass", () -> new PaleGrassBlock());
	public static final RegistryObject<Block> PALE_FENCE = REGISTRY.register("pale_fence", () -> new PaleFenceBlock());
	public static final RegistryObject<Block> CREAKING_HEART = REGISTRY.register("creaking_heart", () -> new CreakingHeartBlock());
	public static final RegistryObject<Block> PALE_SAPLING = REGISTRY.register("pale_sapling", () -> new PaleSaplingBlock());
	public static final RegistryObject<Block> CREAKING_HEART_ACTIVE = REGISTRY.register("creaking_heart_active", () -> new CreakingHeartActiveBlock());
	public static final RegistryObject<Block> PALE_STAIRS = REGISTRY.register("pale_stairs", () -> new PalePlankStairsBlock());
	public static final RegistryObject<Block> PALE_FENCE_GATE = REGISTRY.register("pale_fence_gate", () -> new PaleFenceGateBlock());
	public static final RegistryObject<Block> PALE_SLABS = REGISTRY.register("pale_slabs", () -> new PaleSlabsBlock());
	public static final RegistryObject<Block> PALE_BUTTON = REGISTRY.register("pale_button", () -> new PaleButtonBlock());
	public static final RegistryObject<Block> PALE_PRESSURE_PLATE = REGISTRY.register("pale_pressure_plate", () -> new PalePressurePlateBlock());
	public static final RegistryObject<Block> PALE_MOSS_BLOCK = REGISTRY.register("pale_moss_block", () -> new PaleMossBlockBlock());
	public static final RegistryObject<Block> PALE_TRAPDOOR = REGISTRY.register("pale_trapdoor", () -> new PaleTrapdoorBlock());
	public static final RegistryObject<Block> PALE_LEAVES = REGISTRY.register("pale_leaves", () -> new PaleLeavesBlock());
	public static final RegistryObject<Block> PALE_VINE = REGISTRY.register("pale_vine", () -> new PaleVineBlock());
	public static final RegistryObject<Block> PALE_VINE_BOTTOM = REGISTRY.register("pale_vine_bottom", () -> new PaleVineBottomBlock());
	public static final RegistryObject<Block> PALE_DOOR = REGISTRY.register("pale_door", () -> new PaleDoorBlock());
	public static final RegistryObject<Block> STRIPPED_PALE_WOOD = REGISTRY.register("stripped_pale_wood", () -> new StrippedPaleWoodBlock());
	public static final RegistryObject<Block> PALE_WOOD = REGISTRY.register("pale_wood", () -> new PaleWoodBlock());
	public static final RegistryObject<Block> STRIPPED_PALE_LOGS = REGISTRY.register("stripped_pale_logs", () -> new StrippedPaleLogsBlock());
	public static final RegistryObject<Block> PALE_COARSE_DIRT = REGISTRY.register("pale_coarse_dirt", () -> new PaleCourseDirtBlock());
	public static final RegistryObject<Block> PALE_SAND = REGISTRY.register("pale_sand", () -> new PaleSandBlock());
	public static final RegistryObject<Block> PALE_SANDSTONE = REGISTRY.register("pale_sandstone", () -> new PaleSandstoneBlock());

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			PaleGrassBlock.blockColorLoad(event);
			PaleLeavesBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			PaleGrassBlock.itemColorLoad(event);
		}
	}
}
