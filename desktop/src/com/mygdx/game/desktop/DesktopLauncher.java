package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=60;
		config.width=MyGdxGame.Width;
		config.height=MyGdxGame.Height;
		config.resizable=true;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
