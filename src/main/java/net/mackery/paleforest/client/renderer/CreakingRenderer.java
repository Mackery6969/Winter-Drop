
package net.mackery.paleforest.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mackery.paleforest.entity.CreakingEntity;
import net.mackery.paleforest.client.model.Modelcreaking;

public class CreakingRenderer extends MobRenderer<CreakingEntity, Modelcreaking<CreakingEntity>> {
	public CreakingRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelcreaking(context.bakeLayer(Modelcreaking.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(CreakingEntity entity) {
		return new ResourceLocation("pale_forest:textures/entities/creaking.png");
	}
}
