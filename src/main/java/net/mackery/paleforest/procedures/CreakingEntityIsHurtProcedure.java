package net.mackery.paleforest.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.mackery.paleforest.init.PaleForestModBlocks;
import net.mackery.paleforest.init.PaleForestModParticleTypes;

import java.util.Random;

public class CreakingEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = -16;
		double sy, sz;
		double foundx = 0, foundy = 0, foundz = 0;
		Random random = new Random();

		for (int index0 = 0; index0 < 32; index0++) {
			sy = -16;
			for (int index1 = 0; index1 < 32; index1++) {
				sz = -16;
				for (int index2 = 0; index2 < 32; index2++) {
					BlockPos currentPos = BlockPos.containing(x + sx, y + sy, z + sz);
					if ((world.getBlockState(currentPos)).getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get()
							&& !found) {
						found = true;
						foundx = x + sx;
						foundy = y + sy;
						foundz = z + sz;

						Vec3 start = new Vec3(x, y + 1, z); // Adjust to spawn at Creaking's center
						Vec3 target = new Vec3(foundx, foundy, foundz);
						Vec3 direction = target.subtract(start).normalize();
						double distance = start.distanceTo(target);

						// Spawn particles along the trail
						for (double step = 0; step < distance; step += 0.1) { // More particles, denser trail
							Vec3 particlePos = start.add(direction.scale(step));

							// Randomize velocity for a shooting-out effect
							double vx = (random.nextDouble() - 0.5) * 0.2;
							double vy = (random.nextDouble() - 0.5) * 0.2;
							double vz = (random.nextDouble() - 0.5) * 0.2;

							if (world instanceof ServerLevel _level) {
								_level.sendParticles(PaleForestModParticleTypes.TRAIL.get(),
										particlePos.x, particlePos.y, particlePos.z, 5, // Increase particle count
										vx, vy, vz, 0.02); // Spread particles with velocity
							}
						}
					}
					sz++;
				}
				sy++;
			}
			sx++;
		}

		if (found) {
			if (world instanceof ServerLevel _level) {
				// Spawn dense particles at the Creaking Heart's location
				for (int i = 0; i < 10; i++) { // Multiple bursts for a more dramatic effect
					double vx = (random.nextDouble() - 0.5) * 0.5;
					double vy = (random.nextDouble() - 0.5) * 0.5;
					double vz = (random.nextDouble() - 0.5) * 0.5;

					_level.sendParticles(PaleForestModParticleTypes.TRAIL.get(),
							foundx, foundy + 1.5, foundz, 20, // Increase particle count and adjust to spawn at center
							vx, vy, vz, 0.1); // Randomized velocity for a burst effect
				}
			}
		}
	}
}
