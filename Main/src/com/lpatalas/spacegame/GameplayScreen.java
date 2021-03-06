package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * User: Lukasz
 * Date: 14.08.12
 */
class GameplayScreen implements Screen {
	private static final float GAME_OVER_TIMEOUT = 3.0f;
	private static final float MOVE_SPEED = 1000.0f;

	private final Assets assets = new Assets();
	private Asteroids asteroids;
	private Clouds clouds;
	private BitmapFont font;
	private final SpaceGame game;
	private boolean isGameOver = false;
	private final Particles particles = new Particles();
	private Player player;
	private SpriteBatch spriteBatch;
	private Stars stars;
	private long startTime;
	private float timeLeftToMainMenu;
	private long totalGameTime;

	public GameplayScreen(SpaceGame game) {
		this.game = game;
	}

	public boolean isRunning() {
		return !isGameOver;
	}

	@Override
	public void show() {
		font = new BitmapFont(Gdx.files.internal("fonts/dodger.fnt"), false);
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
		Vector2 initialPos = new Vector2(playerTexture.getWidth(), Gdx.graphics.getHeight() / 2);
		player = new Player(playerTexture, initialPos);
		PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor(this, player);
		Gdx.input.setInputProcessor(playerInputProcessor);
	}

	public void resetGame() {
		asteroids.reset();
		player.reset();
		stars.initialize();
		isGameOver = false;
		startTime = System.currentTimeMillis();
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		update(deltaTime);

		spriteBatch.begin();
		renderObjects(deltaTime);
		renderTimer(spriteBatch);
		spriteBatch.end();

		if (isGameOver && timeLeftToMainMenu <= 0) {
			game.setScreen(new MainMenuScreen(game));
		}

	}

	private void update(float deltaTime) {
		if (!isGameOver)
			updateTimer();
		else
			timeLeftToMainMenu -= deltaTime;

		player.update(deltaTime);
		stars.update(deltaTime, MOVE_SPEED);
		asteroids.update(deltaTime);
		particles.update();
		clouds.update(deltaTime);

		if (!isGameOver && asteroids.collideWith(player.getBoundingRectangle())) {
			isGameOver = true;
			timeLeftToMainMenu = GAME_OVER_TIMEOUT;
			particles.addExplosion(player.getPosition());
		}
	}

	private void updateTimer() {
		long currentTime = System.currentTimeMillis();
		totalGameTime = currentTime - startTime;
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

	private void renderTimer(SpriteBatch spriteBatch) {
		font.setColor(0.2f, 1, 0.2f, 1);
		double timeInSeconds = roundToSingleDecimalPlace(totalGameTime / 1000.0);
		font.draw(spriteBatch, "TIME: " + timeInSeconds, 10, Gdx.graphics.getHeight() - 20);
	}

	private static double roundToSingleDecimalPlace(double value) {
		return Math.round(value * 10.0) / 10.0;
	}

	private void renderGameOver(SpriteBatch spriteBatch) {
		Texture gameOverTexture = assets.getGameOverTexture();

		float x = (Gdx.graphics.getWidth() - gameOverTexture.getWidth()) / 2;
		float y = (Gdx.graphics.getHeight() - gameOverTexture.getHeight()) / 2;

		spriteBatch.draw(gameOverTexture, x, y);
	}

	@Override
	public void dispose() {
		hide();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
		assets.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
