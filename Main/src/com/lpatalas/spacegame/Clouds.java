package com.lpatalas.spacegame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

class Clouds {
	private final List<CloudLayer> layers = new ArrayList<CloudLayer>();

	public Clouds() {
		layers.add(new CloudLayer("images/cloud1.png", 0.34f, new Color(1, 0.5f, 0, 0.4f)));
		layers.add(new CloudLayer("images/cloud2.png", 0.1f, new Color(0, 0.0f, 0.5f, 0.4f)));
	}

	public void update(float deltaTime) {
		for (CloudLayer layer : layers) {
			layer.move(deltaTime);
		}
	}

	public void render(SpriteBatch spriteBatch) {
		setupBlending(spriteBatch);

		for (CloudLayer layer : layers) {
			layer.render(spriteBatch);
		}

		spriteBatch.setColor(1, 1, 1, 1);
	}

	private void setupBlending(SpriteBatch spriteBatch) {
		spriteBatch.enableBlending();
		spriteBatch.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}
}