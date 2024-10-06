
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mackery.paleforest.client.model.Modelcreaking;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class PaleForestModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelcreaking.LAYER_LOCATION, Modelcreaking::createBodyLayer);
	}
}
