package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Lukasz
 * Date: 12.08.12
 */
class Particles {
	private static final String EXPLOSION_EFFECT_NAME = "explosion";
	private static final String PARTICLES_DIRECTORY = "particles/";

	private final List<ParticleEffect> activeEffects = new ArrayList<ParticleEffect>();
	private ParticleEffect explosionEffect;
	private FileHandle particlesImageDir;

	public Particles() {
	}

	public void addExplosion(Vector2 position) {
		ParticleEffect explosion = new ParticleEffect(explosionEffect);
		explosion.setPosition(position.x, position.y);
		explosion.start();
		activeEffects.add(explosion);
	}

	public void update() {
		removeCompletedEffects();
	}

	private void removeCompletedEffects() {
		for (int i = activeEffects.size() - 1; i >= 0; i--) {
			if (activeEffects.get(i).isComplete()) {
				activeEffects.remove(i);
			}
		}
	}

	public void render(SpriteBatch spriteBatch, float dt) {
		for (ParticleEffect effect : activeEffects) {
			effect.draw(spriteBatch, dt);
		}
	}

	public void load() {
		particlesImageDir = Gdx.files.internal(PARTICLES_DIRECTORY);
		explosionEffect = loadParticleEffect(EXPLOSION_EFFECT_NAME);
	}

	private ParticleEffect loadParticleEffect(String effectName) {
		ParticleEffect effect = new ParticleEffect();

		FileHandle effectFile = getEffectFileHandle(effectName);
		effect.load(effectFile, particlesImageDir);

		return effect;
	}

	private FileHandle getEffectFileHandle(String effectName) {
		String fullPath = PARTICLES_DIRECTORY + effectName + ".effect";
		return Gdx.files.internal(fullPath);
	}
}
