package com.lpatalas.spacegame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * User: Lukasz
 * Date: 11.08.12
 */
class Assets {
	private static final String ASTEROID_TEXTURE_NAME = "images/asteroid.png";
	private static final String GAME_OVER_TEXTURE_NAME = "images/gameOver.png";
	private static final String PLAYER_TEXTURE_NAME = "images/ship.png";
	private static final String STAR_TEXTURE_NAME = "images/dot.png";

	private final AssetManager assetManager = new AssetManager();

	public void load() {
		assetManager.load(ASTEROID_TEXTURE_NAME, Texture.class);
		assetManager.load(GAME_OVER_TEXTURE_NAME, Texture.class);
		assetManager.load(PLAYER_TEXTURE_NAME, Texture.class);
		assetManager.load(STAR_TEXTURE_NAME, Texture.class);
		assetManager.finishLoading();
	}

	public Texture getAsteroidTexture() {
		return assetManager.get(ASTEROID_TEXTURE_NAME, Texture.class);
	}

	public Texture getGameOverTexture() {
		return assetManager.get(GAME_OVER_TEXTURE_NAME, Texture.class);
	}

	public Texture getPlayerTexture() {
		return assetManager.get(PLAYER_TEXTURE_NAME, Texture.class);
	}

	public Texture getStarTexture() {
		return assetManager.get(STAR_TEXTURE_NAME, Texture.class);
	}

	public void dispose() {
		assetManager.clear();
	}
}
