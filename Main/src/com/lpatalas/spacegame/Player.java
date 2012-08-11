package com.lpatalas.spacegame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * User: Lukasz
 * Date: 11.08.12
 */
class Player {
	private final Sprite sprite;
	private Vector2 targetPosition;
	private final Vector2 velocity = new Vector2(0, 0);

	public Player(Texture texture) {
		this.sprite = new Sprite(texture);
	}

	public Rectangle getBoundingRectangle() {
		return sprite.getBoundingRectangle();
	}

	public void moveTo(Vector2 position) {
		targetPosition = new Vector2(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
	}

	public void render(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}

	public void update(float dt) {
		if (targetPosition != null)
			moveTowardsTarget(dt);
	}

	private void moveTowardsTarget(float dt) {
		final float mass = 1.0f;
		final float k = 200.0f;
		final float b = 200.0f;

		Vector2 toTarget = getVectorToTarget();
		Vector2 damping = velocity.cpy().mul(b);
		Vector2 force = toTarget.mul(k).sub(damping);

		Vector2 acc = force.mul(dt * mass);
		velocity.add(acc.mul(dt));

		float x = sprite.getX() + velocity.x * dt;
		float y = sprite.getY() + velocity.y * dt;

		sprite.setPosition(x, y);
	}

	private Vector2 getPosition() {
		return new Vector2(sprite.getX(), sprite.getY());
	}

	private Vector2 getVectorToTarget() {
		Vector2 toTarget = targetPosition.cpy();
		toTarget.sub(getPosition());
		return toTarget;
	}
}
