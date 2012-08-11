package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * User: Lukasz
 * Date: 09.08.12
 */
public class Asteroid {
	private float movementSpeed;
	private final float rotationSpeed;
	private final Sprite sprite;
	private float radius;

	public Asteroid(Texture texture) {
		this.movementSpeed = MathUtils.random(50.0f, 250.0f);
		this.radius = calculateRadius(texture);
		this.rotationSpeed = getRandomRotationSpeed();
		this.sprite = createSprite(texture);

		randomizePosition();
	}

	private float calculateRadius(Texture texture) {
		return Math.max(texture.getWidth(), texture.getHeight());
	}

	private float getRandomRotationSpeed() {
		boolean clockwiseDirection = MathUtils.randomBoolean();
		float speed = MathUtils.random(50.0f, 200.0f);

		if (clockwiseDirection)
			speed = -speed;

		return speed;
	}

	private Sprite createSprite(Texture texture) {
		Sprite sprite = new Sprite(texture);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		return sprite;
	}

	public boolean collidesWith(Rectangle bounds) {
		float x = sprite.getX() + sprite.getOriginX();
		float y = sprite.getY() + sprite.getOriginY();

		return bounds.contains(x, y);
	}

	public void update(float dt) {
		updateRotation(dt);
		updatePosition(dt);
	}

	private void updatePosition(float dt) {
		if (isOutOfScreen())
			randomizePosition();
		else {
			move(dt);
		}
	}

	private boolean isOutOfScreen() {
		return sprite.getX() < -radius;
	}

	private void randomizePosition() {
		float x = Gdx.graphics.getWidth() + MathUtils.random(Gdx.graphics.getWidth());
		float y = MathUtils.random(Gdx.graphics.getHeight());

		sprite.setPosition(x, y);
	}

	private void move(float dt) {
		float x = sprite.getX();
		x -= movementSpeed * dt;
		sprite.setX(x);
	}

	private void updateRotation(float dt) {
		float rotation = sprite.getRotation();
		rotation += rotationSpeed * dt;
		sprite.setRotation(rotation);
	}

	public void render(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}
}
