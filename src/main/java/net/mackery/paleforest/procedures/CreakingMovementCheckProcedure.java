package net.mackery.paleforest.procedures;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items; // <-- Add this import

import net.mackery.paleforest.init.PaleForestModSounds;

import java.util.List;
import java.util.Comparator;
import java.util.HashMap;

public class CreakingMovementCheckProcedure {

    private static final HashMap<Entity, Boolean> entityWatchedMap = new HashMap<>();

    public static boolean execute(LevelAccessor world, Entity entity) {
        if (entity == null)
            return false;

        boolean isPlayerInFOV = false;
        double detectionRadius = 30.0;
        double fovAngle = 90.0;
        double cosThreshold = Math.cos(Math.toRadians(fovAngle / 2));

        List<? extends Entity> players = world.getEntitiesOfClass(Player.class, new AABB(
                entity.getX() - detectionRadius, entity.getY() - detectionRadius, entity.getZ() - detectionRadius,
                entity.getX() + detectionRadius, entity.getY() + detectionRadius, entity.getZ() + detectionRadius));

        for (Entity playerEntity : players) {
            if (playerEntity instanceof Player player) {
                // Ignore players in Creative or Spectator mode or wearing a pumpkin
                if (player.isCreative() || player.isSpectator()
                        || player.getInventory().getArmor(3).getItem().equals(Items.CARVED_PUMPKIN)) {
                    continue;
                }

                Vec3 lookVector = player.getLookAngle();
                Vec3 toEntityVector = new Vec3(
                        entity.getX() - player.getX(),
                        entity.getEyeY() - player.getEyeY(),
                        entity.getZ() - player.getZ()).normalize();

                double dotProduct = lookVector.dot(toEntityVector);

                if (dotProduct > cosThreshold && hasLineOfSight(world, player, entity)) {
                    isPlayerInFOV = true;
                    grantAdvancementToNearbyPlayers(world, entity); // Grant advancement
                    break;
                }
            }
        }

        boolean wasWatched = entityWatchedMap.getOrDefault(entity, false);

        if (isPlayerInFOV && !wasWatched) {
            playSound(world, entity.blockPosition(), "creaking_freeze");
        } else if (!isPlayerInFOV && wasWatched) {
            playSound(world, entity.blockPosition(), "creaking_unfreeze");
        }

        entityWatchedMap.put(entity, isPlayerInFOV);

        return !isPlayerInFOV;
    }

    private static boolean hasLineOfSight(LevelAccessor world, Player player, Entity entity) {
        Vec3 startVec = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 endVec = new Vec3(entity.getX(), entity.getY() + (entity.getBbHeight() / 2.0), entity.getZ());

        BlockHitResult hitResult = world.clip(new net.minecraft.world.level.ClipContext(
                startVec, endVec, net.minecraft.world.level.ClipContext.Block.COLLIDER,
                net.minecraft.world.level.ClipContext.Fluid.NONE, player));

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = hitResult.getBlockPos();
            BlockState blockState = world.getBlockState(hitPos);

            return !blockState.canOcclude();
        }

        return true;
    }

    private static void grantAdvancementToNearbyPlayers(LevelAccessor world, Entity entity) {
        if (!(world instanceof ServerLevel serverWorld))
            return;

        final Vec3 entityPos = entity.position();
        List<Player> nearbyPlayers = world.getEntitiesOfClass(Player.class, new AABB(
                entityPos.x - 16, entityPos.y - 16, entityPos.z - 16,
                entityPos.x + 16, entityPos.y + 16, entityPos.z + 16));

        for (Entity nearbyEntity : nearbyPlayers) {
            if (nearbyEntity instanceof ServerPlayer player) {
                Advancement advancement = player.server.getAdvancements()
                        .getAdvancement(new ResourceLocation("pale_forest:encounter_creaking"));
                if (advancement != null) {
                    AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) {
                        for (String criteria : progress.getRemainingCriteria()) {
                            player.getAdvancements().award(advancement, criteria);
                        }
                    }
                }
            }
        }
    }

    private static void playSound(LevelAccessor world, BlockPos pos, String sound) {
        if (world instanceof ServerLevel serverWorld) {
            if ("creaking_freeze".equals(sound)) {
                serverWorld.playSound(null, pos, PaleForestModSounds.CREAKING_FREEZE.get(), SoundSource.BLOCKS, 1.0f,
                        1.0f);
            } else if ("creaking_unfreeze".equals(sound)) {
                serverWorld.playSound(null, pos, PaleForestModSounds.CREAKING_UNFREEZE.get(), SoundSource.BLOCKS, 1.0f,
                        1.0f);
            }
        }
    }
}
