package com.lpatalas.spacegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * User: Lukasz
 * Date: 05.08.12
 */
class SpaceGame extends Game {
	private static final float MOVE_SPEED = 300.0f;

	private final Assets assets = new Assets();
	private Asteroids asteroids;
	private boolean isGameOver = false;
	private SpriteBatch spriteBatch;
	private Player player;
	private Stars stars;

	public boolean isRunning() {
		return !isGameOver;
	}

	@Override
    public void create() {
		spriteBatch = new SpriteBatch();

		assets.load();
		createObjects();
		resetGame();
    }

	private void createObjects() {
		createPlayer();
		asteroids = new Asteroids(assets.getAsteroidTexture());
		stars = new Stars(assets.getStarTexture());
	}

	private void createPlayer() {
		Texture playerTexture = assets.getPlayerTexture();
		player = new Player(playerTexture);
		PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor(this, player);
		Gdx.input.setInputProcessor(playerInputProcessor);
	}

	public void resetGame() {
		asteroids.reset();
		stars.initialize();
		isGameOver = false;
	}

	@Override
    public void render() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (!isGameOver)
			update(Gdx.graphics.getDeltaTime());

		spriteBatch.begin();
		renderObjects();
		spriteBatch.end();
    }

	private void update(float deltaTime) {
		player.update(deltaTime);
		stars.update(deltaTime, MOVE_SPEED);
		asteroids.update(deltaTime);

		if (asteroids.collideWith(player.getBoundingRectangle())) {
			isGameOver = true;
		}
	}

	private void renderObjects() {
		stars.render(spriteBatch);
		asteroids.render(spriteBatch);
		player.render(spriteBatch);

		if (isGameOver)
			renderGameOver(spriteBatch);
	}

	private void renderGameOver(SpriteBatch spriteBatch) {
		Texture gameOverTexture = assets.getGameOverTexture();

		float x = (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2;
		float y = (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2;

		spriteBatch.draw(gameOverTexture, x, y);
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}
}
