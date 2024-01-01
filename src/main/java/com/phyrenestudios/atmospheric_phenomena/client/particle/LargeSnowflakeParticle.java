package com.phyrenestudios.atmospheric_phenomena.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LargeSnowflakeParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected LargeSnowflakeParticle(ClientLevel p_172292_, double p_172293_, double p_172294_, double p_172295_, double p_172296_, double p_172297_, double p_172298_, SpriteSet p_172299_) {
        super(p_172292_, p_172293_, p_172294_, p_172295_);
        this.gravity = 0.225F;
        this.friction = 1.0F;
        this.sprites = p_172299_;
        this.xd = p_172296_ + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.yd = p_172297_ + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.zd = p_172298_ + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.quadSize = 0.75F * (this.random.nextFloat() * this.random.nextFloat() * 1.0F + 1.0F);
        this.lifetime = (int)((double)32 / ((double)p_172292_.random.nextFloat() * 0.8D + 0.2D) * 0.3D);
        this.lifetime = Math.max(this.lifetime, 1);
        this.setSpriteFromAge(p_172299_);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.xd *= (double)0.95F;
        this.yd *= (double)0.9F;
        this.zd *= (double)0.95F;
    }

    @OnlyIn(Dist.CLIENT)

    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType p_172315_, ClientLevel p_172316_, double p_172317_, double p_172318_, double p_172319_, double p_172320_, double p_172321_, double p_172322_) {
            LargeSnowflakeParticle snowflakeparticle = new LargeSnowflakeParticle(p_172316_, p_172317_, p_172318_, p_172319_, p_172320_, p_172321_, p_172322_, this.sprite);
            snowflakeparticle.setColor(0.923F, 0.964F, 0.999F);
            return snowflakeparticle;
        }
    }

}
