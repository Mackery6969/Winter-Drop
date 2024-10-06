package net.mackery.paleforest.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.init.PaleForestModBlocks;
import net.mackery.paleforest.entity.CreakingEntity;

import java.util.List;
import java.util.Comparator;

public class CreakingNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double foundx = 0;
		double foundy = 0;
		double foundz = 0;
		double heartint = 0;
		double creakingint = 0;
		sx = -16;
		foundx = x;
		foundy = y;
		foundz = z;
		found = false;
		for (int index0 = 0; index0 < 32; index0++) {
			sy = -16;
			for (int index1 = 0; index1 < 32; index1++) {
				sz = -16;
				for (int index2 = 0; index2 < 32; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get()) {
						if (true) {
							foundx = x + sx;
							foundy = y + sy;
							foundz = z + sy;
						}
						found = true;
						heartint = heartint + 1;
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		{
			final Vec3 _center = new Vec3(foundx, foundy, foundz);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof CreakingEntity) {
					creakingint = creakingint + 1;
				}
			}
		}
		if (found == true && !(world instanceof Level _lvl4 && _lvl4.isDay()) && heartint > creakingint && heartint <= 1) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_spawn")), SoundSource.HOSTILE, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_spawn")), SoundSource.HOSTILE, 1, 1, false);
				}
			}
			return true;
		}
		return false;
	}
}
