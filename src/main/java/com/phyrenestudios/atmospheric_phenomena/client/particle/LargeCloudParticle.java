package com.phyrenestudios.atmospheric_phenomena.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LargeCloudParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    public LargeCloudParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz, SpriteSet p_107490_) {
        super(level, x, y, z, vx, vy, vz);
        this.friction = 0.96F;
        this.sprites = p_107490_;
        float f = 2.5F;
        this.xd *= (double)0.1F;
        this.yd *= (double)0.1F;
        this.zd *= (double)0.1F;
        this.xd += vx;
        this.yd += vy;
        this.zd += vz;
        float f1 = (float)Math.random() * 0.1F;
        this.rCol = 1.0F - f1 * 4.5F;
        this.gCol = 1.0F - f1;
        this.bCol = 1.0F;
        this.quadSize *= 4F;
        int i = (int)(8.0D / (Math.random() * 0.8D + 0.3D));
        this.lifetime = (int)Math.max((float)i * 2.5F, 2.0F);
        this.hasPhysics = false;
        this.setSpriteFromAge(p_107490_);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public float getQuadSize(float p_107504_) {
        return this.quadSize * Mth.clamp(((float)this.age + p_107504_) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSpriteFromAge(this.sprites);
            Player player = this.level.getNearestPlayer(this.x, this.y, this.z, 2.0D, false);
            if (player != null) {
                double d0 = player.getY();
                if (this.y > d0) {
                    this.y += (d0 - this.y) * 0.2D;
                    this.yd += (player.getDeltaMovement().y - this.yd) * 0.2D;
                    this.setPos(this.x, this.y, this.z);
                }
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType p_107518_, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new LargeCloudParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }

}
