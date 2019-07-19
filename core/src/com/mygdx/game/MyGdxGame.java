package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.com.mygdx.game.GameCamera;
import com.mygdx.game.com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.com.mygdx.game.screens.PlayScreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public static final int Height=480;
	public static  final int Width=720;
	public static boolean is_Mobile=false;

	private Music music;
	public GameCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		music=Gdx.audio.newMusic(Gdx.files.internal("audio/backgroundMusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.7f);
		music.play();
		camera=new GameCamera(Width,Height);

		if(Gdx.app.getType()== Application.ApplicationType.Android || Gdx.app.getType()== Application.ApplicationType.iOS){
			is_Mobile=true;
		}
		is_Mobile=true;
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined());
		super.render();

	}
	public void resize(){
		camera.update(Width,Height);
		super.resize(Width,Height);
	}
	public void dispose(){
		super.dispose();
		music.dispose();
	}
}
