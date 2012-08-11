package com.lpatalas.spacegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Lukasz
 * Date: 11.08.12
 */
class Asteroids {
	private static final int INITIAL_ASTEROID_COUNT = 5;
	private static final float NEW_ASTEROID_INTERVAL = 5.0f;

	private final List<Asteroid> asteroids = new ArrayList<Asteroid>();
	private final Texture texture;
	private float timeToNextAsteroid = NEW_ASTEROID_INTERVAL;

	public Asteroids(Texture texture) {
		this.texture = texture;
	}

	public void reset() {
		asteroids.clear();

		for (int i = 0; i < INITIAL_ASTEROID_COUNT; i++) {
			addNewAsteroid();
		}

		timeToNextAsteroid = NEW_ASTEROID_INTERVAL;
	}

	public boolean collideWith(Rectangle testedBounds) {
		for (Asteroid asteroid : asteroids) {
			if (asteroid.collidesWith(testedBounds))
				return true;
		}

		return false;
	}

	public void update(float deltaTime) {
		updateAsteroidCount(deltaTime);

		for (Asteroid asteroid : asteroids) {
			asteroid.update(deltaTime);
		}
	}

	private void updateAsteroidCount(float deltaTime) {
		timeToNextAsteroid -= deltaTime;
		if (timeToNextAsteroid <= 0) {
			timeToNextAsteroid = Asteroids.NEW_ASTEROID_INTERVAL;
			addNewAsteroid();
		}
	}

	private void addNewAsteroid() {
		Asteroid asteroid = new Asteroid(texture);
		asteroids.add(asteroid);
	}

	public void render(SpriteBatch spriteBatch) {
		for (Asteroid asteroid : asteroids) {
			asteroid.render(spriteBatch);
		}
	}
}
