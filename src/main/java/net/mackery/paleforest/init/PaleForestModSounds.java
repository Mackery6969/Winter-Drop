
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mackery.paleforest.PaleForestMod;

public class PaleForestModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PaleForestMod.MODID);
	public static final RegistryObject<SoundEvent> CREAKING_IDLE = REGISTRY.register("creaking_idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_idle")));
	public static final RegistryObject<SoundEvent> CREAKING_STEP = REGISTRY.register("creaking_step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_step")));
	public static final RegistryObject<SoundEvent> CREAKING_ATTACK = REGISTRY.register("creaking_attack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_attack")));
	public static final RegistryObject<SoundEvent> CREAKING_ACTIVATE = REGISTRY.register("creaking_activate", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_activate")));
	public static final RegistryObject<SoundEvent> CREAKING_DEATH = REGISTRY.register("creaking_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_death")));
	public static final RegistryObject<SoundEvent> CREAKING_HURT = REGISTRY.register("creaking_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_hurt")));
	public static final RegistryObject<SoundEvent> CREAKING_FREEZE = REGISTRY.register("creaking_freeze", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_freeze")));
	public static final RegistryObject<SoundEvent> CREAKING_UNFREEZE = REGISTRY.register("creaking_unfreeze", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_unfreeze")));
	public static final RegistryObject<SoundEvent> CREAKING_SPAWN = REGISTRY.register("creaking_spawn", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_spawn")));
	public static final RegistryObject<SoundEvent> CREAKING_DEACTIVATE = REGISTRY.register("creaking_deactivate", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("pale_forest", "creaking_deactivate")));
}
