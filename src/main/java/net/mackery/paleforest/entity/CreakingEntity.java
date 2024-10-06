
package net.mackery.paleforest.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.mackery.paleforest.procedures.CreakingOnInitialEntitySpawnProcedure;
import net.mackery.paleforest.procedures.CreakingOnEntityTickUpdateProcedure;
import net.mackery.paleforest.procedures.CreakingMovementCheckProcedure;
import net.mackery.paleforest.procedures.CreakingEntityIsHurtProcedure;
import net.mackery.paleforest.procedures.CreakingEntityDiesProcedure;
import net.mackery.paleforest.procedures.CreakingAttackAllReturnProcedure;
import net.mackery.paleforest.init.PaleForestModEntities;
import net.mackery.paleforest.init.PaleForestModBlocks;

import javax.annotation.Nullable;

public class CreakingEntity extends Monster {
	public static final EntityDataAccessor<Boolean> DATA_ProtectedByHeart = SynchedEntityData.defineId(CreakingEntity.class, EntityDataSerializers.BOOLEAN);

	public CreakingEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(PaleForestModEntities.CREAKING.get(), world);
	}

	public CreakingEntity(EntityType<CreakingEntity> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ProtectedByHeart, false);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}

			@Override
			public boolean canUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canContinueToUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}

		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.9) {
			@Override
			public boolean canUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canContinueToUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}
		});
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canContinueToUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}
		});
		this.goalSelector.addGoal(5, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canContinueToUse() && CreakingMovementCheckProcedure.execute(world, entity);
			}
		});
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, Player.class, false, false));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, Villager.class, false, false));
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, LivingEntity.class, false, false) {
			@Override
			public boolean canUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canUse() && CreakingAttackAllReturnProcedure.execute(world, entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = CreakingEntity.this.getX();
				double y = CreakingEntity.this.getY();
				double z = CreakingEntity.this.getZ();
				Entity entity = CreakingEntity.this;
				Level world = CreakingEntity.this.level();
				return super.canContinueToUse() && CreakingAttackAllReturnProcedure.execute(world, entity);
			}
		});
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(PaleForestModBlocks.PALE_LOGS.get()));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_idle"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_step")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("pale_forest:creaking_death"));
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();
		Entity immediatesourceentity = damagesource.getDirectEntity();

		CreakingEntityIsHurtProcedure.execute(world, x, y, z);
		if (damagesource.is(DamageTypes.FALL))
			return false;
		if (damagesource.is(DamageTypes.CACTUS))
			return false;
		if (damagesource.is(DamageTypes.DROWN))
			return false;
		return super.hurt(damagesource, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		CreakingEntityDiesProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		CreakingOnInitialEntitySpawnProcedure.execute(world, this.getX(), this.getY(), this.getZ());
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("DataProtectedByHeart", this.entityData.get(DATA_ProtectedByHeart));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("DataProtectedByHeart"))
			this.entityData.set(DATA_ProtectedByHeart, compound.getBoolean("DataProtectedByHeart"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		CreakingOnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
	}

	@Override
	public boolean canBreatheUnderwater() {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		return true;
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 99);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 2);
		return builder;
	}
}
