package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

class Stars {
	private static final int STAR_COUNT = 20;
	private static final int STAR_LENGTH = 10;

	private final Vector2[] starPositions = new Vector2[STAR_COUNT];
	private final Texture texture;

	public Stars(Texture texture) {
		this.texture = texture;
	}

	public void initialize() {
		for (int i = 0; i < starPositions.length; i++) {
			starPositions[i] = new Vector2();
			randomizeStarPosition(starPositions[i], 0);
		}
	}

	public void render(SpriteBatch spriteBatch) {
		for (Vector2 starPosition : starPositions) {
			spriteBatch.draw(texture, starPosition.x, starPosition.y, STAR_LENGTH, 1);
		}
	}

	public void update(float deltaTime, float moveSpeed) {
		for (Vector2 starPosition : starPositions) {
			starPosition.x -= deltaTime * moveSpeed;
			if (isOutOfScreen(starPosition)) {
				randomizeStarPosition(starPosition, Gdx.graphics.getWidth());
			}
		}
	}

	private boolean isOutOfScreen(Vector2 starPosition) {
		return starPosition.x < 0;
	}

	private void randomizeStarPosition(Vector2 position, float xOffset) {
		position.x = MathUtils.random(xOffset, xOffset + Gdx.graphics.getWidth());
		position.y = MathUtils.random(Gdx.graphics.getHeight());
	}
}