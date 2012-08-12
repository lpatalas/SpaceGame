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
	private final Vector2 position = new Vector2();
	private Vector2 targetPosition;
	private final Texture texture;
	private final Vector2 velocity = new Vector2(0, 0);

	public Player(Texture texture) {
		this.texture = texture;
	}

	public Rectangle getBoundingRectangle() {
		float width = texture.getWidth();
		float height = texture.getHeight() / 2;

		return new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
	}

	public Vector2 getPosition() {
		return position.cpy();
	}

	public void moveTo(Vector2 position) {
		targetPosition = position.cpy();
	}

	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, position.x - texture.getWidth() / 2, position.y - texture.getHeight() / 2);
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

		float x = position.x + velocity.x * dt;
		float y = position.y + velocity.y * dt;

		position.set(x, y);
	}

	private Vector2 getVectorToTarget() {
		Vector2 toTarget = targetPosition.cpy();
		toTarget.sub(getPosition());
		return toTarget;
	}
}
