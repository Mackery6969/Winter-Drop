// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelcreaking<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "creaking"), "main");
	private final ModelPart Head;
	private final ModelPart Horns;
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public Modelcreaking(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Horns = this.Head.getChild("Horns");
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-7.0F, -14.0F, -3.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(28, 31)
						.addBox(-7.0F, -17.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.0F, -1.0F, 0.0F));

		PartDefinition Horns = Head.addOrReplaceChild("Horns",
				CubeListBuilder.create().texOffs(39, 14)
						.addBox(-11.0F, -16.0F, 0.0F, 4.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(12, 44)
						.addBox(-1.0F, -13.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(24, 0)
						.addBox(-6.0F, -5.0F, -2.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(0.0F, -4.0F, -2.0F, 6.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(22, 13)
						.addBox(-3.0F, -2.0F, -2.0F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(46, 0)
						.addBox(-3.0F, 19.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-6.0F, -3.0F, 1.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(52, 13)
						.addBox(0.0F, -4.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(30, 40)
						.addBox(0.0F, -1.0F, -2.0F, 3.0F, 19.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 19)
						.addBox(0.0F, 15.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(45, 45)
						.addBox(-11.0F, 27.0F, -5.0F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(6.0F, -3.0F, 1.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(12, 35)
						.addBox(-3.0F, -2.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 34)
						.addBox(-3.0F, 0.0F, -2.0F, 3.0F, 19.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(45, 55)
						.addBox(1.0F, 19.0F, -5.0F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 5.0F, 1.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(22, 13)
				.addBox(-3.0F, -6.0F, -2.0F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 9.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.Horns.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Horns.xRot = headPitch / (180F / (float) Math.PI);
	}
}