
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mackery.paleforest.PaleForestMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PaleForestModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PaleForestMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(PaleForestModBlocks.PALE_SANDSTONE.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_MOSS_BLOCK.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_LOGS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_WOOD.get().asItem());
			tabData.accept(PaleForestModBlocks.STRIPPED_PALE_LOGS.get().asItem());
			tabData.accept(PaleForestModBlocks.STRIPPED_PALE_WOOD.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_PLANKS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_STAIRS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_SLABS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_FENCE.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_FENCE_GATE.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_DOOR.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_TRAPDOOR.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_BUTTON.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_PRESSURE_PLATE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(PaleForestModBlocks.CREAKING_HEART.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			tabData.accept(PaleForestModBlocks.PALE_DOOR.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_BUTTON.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_PRESSURE_PLATE.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_TRAPDOOR.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_FENCE_GATE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(PaleForestModItems.CREAKING_SPAWN_EGG.get());
			tabData.accept(PaleForestModBlocks.CREAKING_HEART.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(PaleForestModBlocks.PALE_GRASS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_MOSS_BLOCK.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_DIRT.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_COARSE_DIRT.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_LOGS.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_LEAVES.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_SAPLING.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_VINE.get().asItem());
			tabData.accept(PaleForestModBlocks.CREAKING_HEART.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_SAND.get().asItem());
			tabData.accept(PaleForestModBlocks.PALE_SANDSTONE.get().asItem());
		}
	}
}
