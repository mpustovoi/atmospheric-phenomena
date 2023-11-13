package com.phyrenestudios.atmospheric_phenomena.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.phyrenestudios.atmospheric_phenomena.AtmosphericPhenomena;
import com.phyrenestudios.atmospheric_phenomena.client.model.CometModel;
import com.phyrenestudios.atmospheric_phenomena.entities.CometEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CometRenderer extends EntityRenderer<CometEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AtmosphericPhenomena.MODID, "textures/entity/comet.png");
    protected final EntityModel<CometEntity> model;

    public CometRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CometModel<>(context.bakeLayer(CometModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(CometEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(CometEntity entityIn, float p_114486_, float p_114487_, PoseStack stackIn, MultiBufferSource bufferIn, int p_114490_) {
        super.render(entityIn, p_114486_, p_114487_, stackIn, bufferIn, p_114490_);
        stackIn.pushPose();
        float f = Math.max(entityIn.getSize()/100f, 0.5f);
        stackIn.scale(f, f, f);
        VertexConsumer vertexconsumer = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(stackIn, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stackIn.popPose();
    }


}
