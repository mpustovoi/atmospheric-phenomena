package com.phyrenestudios.atmospheric_phenomena.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeteorBurnoutParticle extends TextureSheetParticle {
    private final float flameScale;
    public MeteorBurnoutParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
        super(level, x, y, z, vx, vy, vz);
        this.xd = this.xd * 0.01D + vx;
        this.yd = this.yd * 0.01D + vy;
        this.zd = this.zd * 0.01D + vz;
        this.quadSize *= 5.0D;
        this.flameScale = this.quadSize;
        this.rCol = this.gCol = this.bCol = 1.0F;
        this.lifetime = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.hasPhysics = false;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double p_106817_, double p_106818_, double p_106819_) {
        this.setBoundingBox(this.getBoundingBox().move(p_106817_, p_106818_, p_106819_));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float partialTicks) {
        float relativeAge = (this.age + partialTicks) / this.lifetime;
        return this.flameScale * (1.0F - relativeAge * relativeAge * 0.5F);
    }

    public int getLightColor(float p_106821_) {
        float f = ((float)this.age + p_106821_) / (float)this.lifetime;
        f = Mth.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(p_106821_);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }


    @OnlyIn(Dist.CLIENT)
    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MeteorBurnoutParticle particle = new MeteorBurnoutParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }

}
