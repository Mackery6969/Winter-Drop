package net.mackery.paleforest.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModGameRules;
import net.mackery.paleforest.init.PaleForestModBlocks;
import net.mackery.paleforest.entity.CreakingEntity;

import java.util.List;
import java.util.Comparator;

public class CreakingOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof Player && world.getLevelData().getGameRules().getBoolean(PaleForestModGameRules.CREAKING_DARKNESS)) {
					if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1, false, false));
				}
			}
		}
		sx = -16;
		found = false;
		if (entity instanceof CreakingEntity _datEntSetL)
			_datEntSetL.getEntityData().set(CreakingEntity.DATA_ProtectedByHeart, false);
		for (int index0 = 0; index0 < 32; index0++) {
			sy = -16;
			for (int index1 = 0; index1 < 32; index1++) {
				sz = -16;
				for (int index2 = 0; index2 < 32; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get()) {
						if (entity instanceof CreakingEntity _datEntSetL)
							_datEntSetL.getEntityData().set(CreakingEntity.DATA_ProtectedByHeart, true);
						found = true;
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		if (world instanceof Level _lvl8 && _lvl8.isDay()) {
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}
