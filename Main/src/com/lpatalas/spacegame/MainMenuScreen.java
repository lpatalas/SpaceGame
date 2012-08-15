package com.lpatalas.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.Scaling;

/**
 * User: Lukasz
 * Date: 15.08.12
 */
public class MainMenuScreen implements Screen {
	private Texture backgroundTexture;
	private Texture buttonTexture;
	private BitmapFont font;
	private Stage stage;

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		backgroundTexture = new Texture(Gdx.files.internal("images/title_scene.png"));
		buttonTexture = new Texture(Gdx.files.internal("images/button2.png"));
		font = new BitmapFont(Gdx.files.internal("fonts/dodger60.fnt"), false);
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);



		Stack stack = new Stack();
		stack.setFillParent(true);
		stage.addActor(stack);

		Image background = new Image(backgroundTexture, Scaling.stretch);
		stack.addActor(background);

		Table buttonsTable = new Table();
		buttonsTable.setFillParent(true);
		stack.addActor(buttonsTable);

		Color color = new Color(0, 1, 0, 1);
		NinePatch patch = new NinePatch(buttonTexture, 1, 1, 1, 1);
		patch.setColor(new Color(1, 1, 1, 0.5f));
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(patch, patch, patch, 0, 0, 0, 0, font, color, color, color);
		TextButton startButton = new TextButton("START", buttonStyle);
		TextButton exitButton = new TextButton("EXIT", buttonStyle);

		buttonsTable.defaults().center().width(350).height(100).pad(10);
		buttonsTable.add(startButton);
		buttonsTable.row();
		buttonsTable.add(exitButton);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		backgroundTexture.dispose();
		buttonTexture.dispose();
		font.dispose();
	}
}
