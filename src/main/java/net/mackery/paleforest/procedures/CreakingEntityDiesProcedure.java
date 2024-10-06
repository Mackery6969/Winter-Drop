package net.mackery.paleforest.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mackery.paleforest.init.PaleForestModBlocks;

import java.util.List;
import java.util.Comparator;

public class CreakingEntityDiesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Mth.nextInt(RandomSource.create(), 1, 150) == 1) {
			world.setBlock(BlockPos.containing(x, y, z), PaleForestModBlocks.PALE_SAPLING.get().defaultBlockState(), 3);
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator instanceof ServerPlayer _plr2 && _plr2.level() instanceof ServerLevel
						&& _plr2.getAdvancements().getOrStartProgress(_plr2.server.getAdvancements().getAdvancement(new ResourceLocation("pale_forest:creaking_kill"))).isDone()) && entityiterator instanceof Player) {
					if (entityiterator instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("pale_forest:creaking_kill"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
				if (!(entityiterator instanceof ServerPlayer _plr5 && _plr5.level() instanceof ServerLevel
						&& _plr5.getAdvancements().getOrStartProgress(_plr5.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:adventure/kill_a_mob"))).isDone()) && entityiterator instanceof Player) {
					if (entityiterator instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("minecraft:adventure/kill_a_mob"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
			}
		}
	}
}
