package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_COLOR_BURNPeer;

public class GameOverScreen implements Screen {
    private static final int Width=144;
    private static final int Height=63;

    MyGdxGame game;
    int score,highscore;
    Texture gameover,gamedeath;
    BitmapFont scorefont;

    public GameOverScreen(MyGdxGame game,int score) {
        this.game=game;
        this.score=score;

        Preferences pref= Gdx.app.getPreferences("MyGdxGame");
        this.highscore=pref.getInteger("highscore",0);

        if(score>highscore)
        {
            pref.putInteger("highscore",score);
            pref.flush();
        }
        gameover=new Texture("game_over.png");
        gamedeath=new Texture("gamebackground.png");
        scorefont=new BitmapFont(Gdx.files.internal("fonts/score.fnt"));

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.5f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(gamedeath, 0, 0, MyGdxGame.Width, MyGdxGame.Height);
        game.batch.draw(gameover, MyGdxGame.Width / 2 - Width / 2, MyGdxGame.Height - Height - 20, Width, Height);
        GlyphLayout scorelayout = new GlyphLayout(scorefont, "Score \n" + score, Color.BLACK, 0, Align.center, false);
        GlyphLayout highscorelayout = new GlyphLayout(scorefont, "Highscore \n" + highscore, Color.BLACK, 0, Align.center, false);

        GlyphLayout tryAgainLayout = new GlyphLayout(scorefont, "Retry",Color.RED,0,Align.center,false);
        GlyphLayout mainMenuLayout = new GlyphLayout(scorefont, "MainMenu",Color.RED,0,Align.center,false);

        float tryAgainX = MyGdxGame.Width / 2 - tryAgainLayout.width / 2;
        float tryAgainY = MyGdxGame.Height/ 2 - tryAgainLayout.height / 2;
        float mainMenuX = MyGdxGame.Width / 2 - mainMenuLayout.width / 2;
        float mainMenuY = MyGdxGame.Height / 2 - mainMenuLayout.height / 2 - tryAgainLayout.height - 20;

        float touchX = game.camera.getInputInGameWorld().x;
        float touchY =MyGdxGame.Height - game.camera.getInputInGameWorld().y;

        if (Gdx.input.isTouched()) {
            if (touchX > tryAgainX && touchX < tryAgainX + tryAgainLayout.width && touchY > tryAgainY - tryAgainLayout.height && touchY < tryAgainY) {
                this.dispose();
                game.batch.end();
                game.setScreen(new PlayScreen(game));
                return;
            }

            if (touchX > mainMenuX && touchX < mainMenuX + mainMenuLayout.width && touchY > mainMenuY - mainMenuLayout.height && touchY < mainMenuY) {
                this.dispose();
                game.batch.end();
                game.setScreen(new MainMenuScreen(game));
                return;
            }

        }

        scorefont.draw(game.batch, scorelayout,   MyGdxGame.Width / 2 - scorelayout.width / 2, MyGdxGame.Height - Height - 20);
        scorefont.draw(game.batch, highscorelayout, MyGdxGame.Width / 2 - highscorelayout.width / 4, MyGdxGame.Height - Height - 20*5 );

        scorefont.draw(game.batch,tryAgainLayout, tryAgainX,tryAgainY);
        scorefont.draw(game.batch, mainMenuLayout,mainMenuX,mainMenuY);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
