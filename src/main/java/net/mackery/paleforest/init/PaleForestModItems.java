
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.mackery.paleforest.PaleForestMod;

public class PaleForestModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, PaleForestMod.MODID);
	public static final RegistryObject<Item> PALE_LOGS = block(PaleForestModBlocks.PALE_LOGS);
	public static final RegistryObject<Item> PALE_DIRT = block(PaleForestModBlocks.PALE_DIRT);
	public static final RegistryObject<Item> PALE_PLANKS = block(PaleForestModBlocks.PALE_PLANKS);
	public static final RegistryObject<Item> PALE_GRASS = block(PaleForestModBlocks.PALE_GRASS);
	public static final RegistryObject<Item> PALE_FENCE = block(PaleForestModBlocks.PALE_FENCE);
	public static final RegistryObject<Item> CREAKING_HEART = block(PaleForestModBlocks.CREAKING_HEART);
	public static final RegistryObject<Item> PALE_SAPLING = block(PaleForestModBlocks.PALE_SAPLING);
	public static final RegistryObject<Item> CREAKING_HEART_ACTIVE = block(PaleForestModBlocks.CREAKING_HEART_ACTIVE);
	public static final RegistryObject<Item> CREAKING_SPAWN_EGG = REGISTRY.register("creaking_spawn_egg", () -> new ForgeSpawnEggItem(PaleForestModEntities.CREAKING, -12105395, -5550592, new Item.Properties()));
	public static final RegistryObject<Item> PALE_STAIRS = block(PaleForestModBlocks.PALE_STAIRS);
	public static final RegistryObject<Item> PALE_FENCE_GATE = block(PaleForestModBlocks.PALE_FENCE_GATE);
	public static final RegistryObject<Item> PALE_SLABS = block(PaleForestModBlocks.PALE_SLABS);
	public static final RegistryObject<Item> PALE_BUTTON = block(PaleForestModBlocks.PALE_BUTTON);
	public static final RegistryObject<Item> PALE_PRESSURE_PLATE = block(PaleForestModBlocks.PALE_PRESSURE_PLATE);
	public static final RegistryObject<Item> PALE_MOSS_BLOCK = block(PaleForestModBlocks.PALE_MOSS_BLOCK);
	public static final RegistryObject<Item> PALE_TRAPDOOR = block(PaleForestModBlocks.PALE_TRAPDOOR);
	public static final RegistryObject<Item> PALE_LEAVES = block(PaleForestModBlocks.PALE_LEAVES);
	public static final RegistryObject<Item> PALE_VINE = block(PaleForestModBlocks.PALE_VINE);
	public static final RegistryObject<Item> PALE_VINE_BOTTOM = block(PaleForestModBlocks.PALE_VINE_BOTTOM);
	public static final RegistryObject<Item> PALE_DOOR = doubleBlock(PaleForestModBlocks.PALE_DOOR);
	public static final RegistryObject<Item> STRIPPED_PALE_WOOD = block(PaleForestModBlocks.STRIPPED_PALE_WOOD);
	public static final RegistryObject<Item> PALE_WOOD = block(PaleForestModBlocks.PALE_WOOD);
	public static final RegistryObject<Item> STRIPPED_PALE_LOGS = block(PaleForestModBlocks.STRIPPED_PALE_LOGS);
	public static final RegistryObject<Item> PALE_COARSE_DIRT = block(PaleForestModBlocks.PALE_COARSE_DIRT);
	public static final RegistryObject<Item> PALE_SAND = block(PaleForestModBlocks.PALE_SAND);
	public static final RegistryObject<Item> PALE_SANDSTONE = block(PaleForestModBlocks.PALE_SANDSTONE);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
