package net.mackery.paleforest.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModGameRules;
import net.mackery.paleforest.entity.CreakingEntity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CreakedAttackingProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		boolean foundSpot = false;
		double checkX = 0;
		double checkY = 0;
		double checkZ = 0;
		double xDist = 0;
		double yDist = 0;
		double zDist = 0;
		if (sourceentity instanceof CreakingEntity) {
			if (world.getLevelData().getGameRules().getBoolean(PaleForestModGameRules.CREAKING_ENDERMAN)) {
				checkX = Mth.nextInt(RandomSource.create(), -10, 10);
				checkY = Mth.nextInt(RandomSource.create(), -10, 10);
				checkZ = Mth.nextInt(RandomSource.create(), -10, 10);
				while (!foundSpot) {
					if (!world.getBlockState(BlockPos.containing(x + checkX, y + checkY, z + checkZ)).canOcclude() && !world.getBlockState(BlockPos.containing(x + checkX, y + checkY + 1, z + checkZ)).canOcclude()) {
						foundSpot = true;
						{
							Entity _ent = entity;
							_ent.teleportTo((x + checkX), (y + checkY), (z + checkZ));
							if (_ent instanceof ServerPlayer _serverPlayer)
								_serverPlayer.connection.teleport((x + checkX), (y + checkY), (z + checkZ), _ent.getYRot(), _ent.getXRot());
						}
					} else {
						checkX = Mth.nextInt(RandomSource.create(), -10, 10);
						checkY = Mth.nextInt(RandomSource.create(), -10, 10);
						checkZ = Mth.nextInt(RandomSource.create(), -10, 10);
					}
				}
			}
			if (world.getLevelData().getGameRules().getBoolean(PaleForestModGameRules.CREAKING_DARKNESS)) {
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0, false, false));
			}
		}
	}
}
