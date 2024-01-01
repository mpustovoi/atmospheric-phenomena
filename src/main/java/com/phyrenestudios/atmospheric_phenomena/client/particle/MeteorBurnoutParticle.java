package com.phyrenestudios.atmospheric_phenomena.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeteorBurnoutParticle extends BaseAshSmokeParticle {
    protected MeteorBurnoutParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet p_107051_) {
        super(level, x, y, z, 0.1F, 0.1F, 0.1F, xSpeed, ySpeed, zSpeed, 4.0F, p_107051_, 0.3F, 32, -0.1F, true);
    }

    @OnlyIn(Dist.CLIENT)
    public record Factory(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType p_107518_, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new MeteorBurnoutParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
        }
    }

}
