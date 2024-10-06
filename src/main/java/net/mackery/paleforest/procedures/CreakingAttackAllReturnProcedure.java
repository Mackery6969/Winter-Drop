package net.mackery.paleforest.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mackery.paleforest.init.PaleForestModGameRules;
import net.mackery.paleforest.entity.CreakingEntity;

public class CreakingAttackAllReturnProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		if (!(entity instanceof CreakingEntity) && world.getLevelData().getGameRules().getBoolean(PaleForestModGameRules.CREAKING_KILL_ALL_MOBS_EXPERIMENT)) {
			return true;
		}
		return false;
	}
}
