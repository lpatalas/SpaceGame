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
	private static final float MOVE_SPEED = 1000.0f;

	private final Assets assets = new Assets();
	private Asteroids asteroids;
	private Clouds clouds;
	private boolean isGameOver = false;
	private final Particles particles = new Particles();
	private Player player;
	private SpriteBatch spriteBatch;
	private Stars stars;

	public boolean isRunning() {
		return !isGameOver;
	}

	@Override
    public void create() {
		spriteBatch = new SpriteBatch();

		assets.load();
		particles.load();
		createObjects();
		resetGame();
    }

	private void createObjects() {
		createPlayer();
		asteroids = new Asteroids(assets.getAsteroidTexture());
		clouds = new Clouds();
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

		float deltaTime = Gdx.graphics.getDeltaTime();

		update(deltaTime);

		spriteBatch.begin();
		renderObjects(deltaTime);
		spriteBatch.end();
    }

	private void update(float deltaTime) {
		player.update(deltaTime);
		stars.update(deltaTime, MOVE_SPEED);
		asteroids.update(deltaTime);
		particles.update();
		clouds.update(deltaTime);

		if (!isGameOver && asteroids.collideWith(player.getBoundingRectangle())) {
			isGameOver = true;
			particles.addExplosion(player.getPosition());
		}
	}

	private void renderObjects(float deltaTime) {
		clouds.render(spriteBatch);
		stars.render(spriteBatch);
		asteroids.render(spriteBatch);

		if (!isGameOver)
			player.render(spriteBatch);

		particles.render(spriteBatch, deltaTime);

		if (isGameOver) {
			renderGameOver(spriteBatch);
		}
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
