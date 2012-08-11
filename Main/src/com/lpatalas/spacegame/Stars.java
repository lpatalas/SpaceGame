package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

class Stars {
	private static final int MAX_STARS = 20;
	private static final int STAR_LENGTH = 10;

	private final Random random = new Random();
	private final Vector2[] stars = new Vector2[MAX_STARS];
	private final Texture texture;

	public Stars(Texture texture) {
		this.texture = texture;
	}

	public void initialize() {

		for (int i = 0; i < stars.length; i++) {
			float x = random.nextFloat() * Gdx.graphics.getWidth();
			float y = random.nextFloat() * Gdx.graphics.getHeight();

			stars[i] = new Vector2(x, y);
		}
	}

	public void render(SpriteBatch spriteBatch) {
		for (Vector2 star : stars) {
			spriteBatch.draw(texture, star.x, star.y, STAR_LENGTH, 1);
		}
	}

	public void update(float deltaTime, float moveSpeed) {
		for (Vector2 star : stars) {
			star.x -= deltaTime * moveSpeed;
			if (star.x < 0) {
				resetStar(star);
			}
		}
	}

	private void resetStar(Vector2 star) {
		star.x = Gdx.graphics.getWidth();
		star.y = random.nextFloat() * Gdx.graphics.getHeight();
	}
}