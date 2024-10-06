package net.mackery.paleforest.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import java.util.UUID;
import java.util.List;

import net.mackery.paleforest.init.PaleForestModEntities;
import net.mackery.paleforest.init.PaleForestModBlocks;
import net.mackery.paleforest.entity.CreakingEntity;

public class CreakingHeartCheckProcedure {
	public static final int COOLDOWN_TICKS = 1800; // 1.5 minutes in ticks (90 seconds * 20 ticks)

	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos blockPos = BlockPos.containing(x, y, z);
		BlockEntity blockEntity = world.getBlockEntity(blockPos);
		BlockState currentState = world.getBlockState(blockPos);
		boolean isActive = currentState.getBlock() == PaleForestModBlocks.CREAKING_HEART_ACTIVE.get();

		// Carry over NBT data when switching between active and inactive states
		CompoundTag tag = (blockEntity != null) ? blockEntity.getPersistentData() : new CompoundTag();
		final UUID spawnedCreakingUUID = tag.hasUUID("spawnedCreaking") ? tag.getUUID("spawnedCreaking") : null;
		int cooldown = tag.getInt("spawnCooldown");

		// Decrease cooldown
		if (cooldown > 0) {
			cooldown--;
			tag.putInt("spawnCooldown", cooldown);
		}

		// Check for valid logs and update block state
		boolean hasValidLogs = ((world.getBlockState(BlockPos.containing(x + 1, y, z))
				.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks"))) &&
				world.getBlockState(BlockPos.containing(x - 1, y, z))
						.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks"))))
				||
				(world.getBlockState(BlockPos.containing(x, y + 1, z))
						.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks"))) &&
						world.getBlockState(BlockPos.containing(x, y - 1, z))
								.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks"))))
				||
				(world.getBlockState(BlockPos.containing(x, y, z + 1))
						.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks"))) &&
						world.getBlockState(BlockPos.containing(x, y, z - 1))
								.is(BlockTags.create(new ResourceLocation("pale_forest:heart_enabling_blocks")))));

		if (hasValidLogs && !isActive) {
			// Activate the block (switch from inactive to active)
			world.setBlock(blockPos, PaleForestModBlocks.CREAKING_HEART_ACTIVE.get().defaultBlockState(), 3);
			playActivationSound(world, blockPos);
			isActive = true; // Mark block as active after changing state
		} else if (!hasValidLogs && isActive) {
			// Deactivate the block (switch from active to inactive)
			world.setBlock(blockPos, PaleForestModBlocks.CREAKING_HEART.get().defaultBlockState(), 3);
			playDeactivationSound(world, blockPos);
			isActive = false; // Mark block as inactive after changing state
		}

		// If the block is active, handle spawning logic (only at night, with cooldown,
		// and player check)
		if (isActive && world instanceof Level _level && !_level.isDay() && cooldown <= 0) {
			// Check for players within an 8x8 area
			List<Player> playersInRange = world.getEntitiesOfClass(Player.class,
					new AABB(new Vec3(x, y, z), new Vec3(x, y, z)).inflate(4));

			if (!playersInRange.isEmpty()) { // Only proceed if there are players in range
				// Check for Creakings within 32-block radius
				List<Entity> creakingsIn32Range = world.getEntitiesOfClass(Entity.class,
						new AABB(new Vec3(x, y, z), new Vec3(x, y, z)).inflate(16), e -> e instanceof CreakingEntity);

				// Ensure only one Creaking per heart in a 32x32 area
				if (creakingsIn32Range.isEmpty() || spawnedCreakingUUID == null) {
					// Check if the currently tracked Creaking is still alive
					boolean trackedCreakingIsAlive = false;
					if (spawnedCreakingUUID != null) {
						List<Entity> entities = world.getEntitiesOfClass(Entity.class,
								new AABB(new Vec3(x, y, z), new Vec3(x, y, z)).inflate(32),
								e -> e.getUUID().equals(spawnedCreakingUUID));

						if (!entities.isEmpty()) {
							trackedCreakingIsAlive = true;
						}
					}

					// If there is no tracked Creaking or it is no longer alive, the heart can spawn
					// a new one
					if (!trackedCreakingIsAlive) {
						RandomSource randomSource = RandomSource.create();
						double randomx = Mth.nextInt(randomSource, -8, 8);
						double randomz = Mth.nextInt(randomSource, -8, 8);

						BlockPos spawnPos = findValidSpawnY(world, BlockPos.containing(x + randomx, y, z + randomz));

						if (spawnPos != null && canSpawnHere(world, spawnPos)) {
							if (world instanceof ServerLevel _serverLevel) {
								Entity entityToSpawn = PaleForestModEntities.CREAKING.get().spawn(_serverLevel,
										spawnPos,
										MobSpawnType.MOB_SUMMONED);
								if (entityToSpawn != null) {
									entityToSpawn.setDeltaMovement(0, 0, 0);
									// Store the UUID of the spawned Creaking in the block's NBT
									tag.putUUID("spawnedCreaking", entityToSpawn.getUUID());
									// Set the cooldown to 1.5 minutes
									tag.putInt("spawnCooldown", COOLDOWN_TICKS);
								}
							}
						}
					}
				}
			}
		}

		// Update the NBT data
		if (blockEntity != null) {
			blockEntity.getPersistentData().merge(tag);
		}
	}

	// Helper function to find the first valid Y-level for spawning (searching above
	// and below)
	private static BlockPos findValidSpawnY(LevelAccessor world, BlockPos pos) {
		// Search for valid Y levels above and below the current position
		for (int yOffset = -5; yOffset <= 5; yOffset++) {
			BlockPos checkPos = pos.offset(0, yOffset, 0);
			if (canSpawnHere(world, checkPos)) {
				return checkPos;
			}
		}
		return null; // Return null if no valid Y level was found
	}

	// Helper function to check if a spawn location is valid (i.e., air blocks above
	// and solid block below)
	private static boolean canSpawnHere(LevelAccessor world, BlockPos pos) {
		BlockPos blockBelowSpawn = pos.below();
		return (!world.isEmptyBlock(blockBelowSpawn) &&
				world.isEmptyBlock(pos) &&
				world.isEmptyBlock(pos.above()) &&
				world.isEmptyBlock(pos.above(2)));
	}

	// Helper function to play the activation sound
	private static void playActivationSound(LevelAccessor world, BlockPos blockPos) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, blockPos,
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_activate")),
						SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_activate")),
						SoundSource.BLOCKS, 1, 1, false);
			}
		}
	}

	// Helper function to play the deactivation sound
	private static void playDeactivationSound(LevelAccessor world, BlockPos blockPos) {
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, blockPos,
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_deactivate")),
						SoundSource.BLOCKS, 1, 1);
			} else {
				_level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_deactivate")),
						SoundSource.BLOCKS, 1, 1, false);
			}
		}
	}
}
