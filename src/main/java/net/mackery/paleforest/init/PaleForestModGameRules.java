
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mackery.paleforest.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PaleForestModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> CREAKING_ENDERMAN = GameRules.register("creakingEnderman", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> SPREADING_PALENESS = GameRules.register("spreadingPaleness", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.BooleanValue> CREAKING_DARKNESS = GameRules.register("creakingDarkness", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> CREAKING_KILL_ALL_MOBS_EXPERIMENT = GameRules.register("creakingKillAllMobsExperiment", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));
}
