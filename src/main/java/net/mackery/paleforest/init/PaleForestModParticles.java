
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mackery.paleforest.client.particle.TrailParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PaleForestModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(PaleForestModParticleTypes.TRAIL.get(), TrailParticle::provider);
	}
}
