package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.com.mygdx.game.bullet.stone.Height;

public class DemoScreen implements Screen {

    MyGdxGame game;
    Texture texture;
    BitmapFont font;
    public  DemoScreen(MyGdxGame game){
        this.game=game;
        texture=new Texture("background.png");
        font=new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture,0,0,MyGdxGame.Width,MyGdxGame.Height);
        GlyphLayout layout = new GlyphLayout(font, "This is a Libgdx \n\n 2D Game Created By\n\n Mohammad Farhad\n\nand Sifat Ibne Kamal", Color.BLACK, 0, Align.center, false);

        GlyphLayout tryAgainLayout = new GlyphLayout(font, "Retry",Color.RED,0,Align.center,false);
        GlyphLayout mainMenuLayout = new GlyphLayout(font, "MainMenu",Color.RED,0,Align.center,false);

        float tryAgainX = MyGdxGame.Width / 2 - tryAgainLayout.width / 2;
        float tryAgainY = MyGdxGame.Height / 2 - tryAgainLayout.height / 2;
        float mainMenuX = MyGdxGame.Width / 2 - mainMenuLayout.width / 2;
        float mainMenuY = MyGdxGame.Height / 2 - mainMenuLayout.height / 2 - tryAgainLayout.height - 60;

        float touchX = game.camera.getInputInGameWorld().x;
        float touchY = MyGdxGame.Height - game.camera.getInputInGameWorld().y;

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
        font.draw(game.batch, layout,   MyGdxGame.Width /2 , MyGdxGame.Height/2+200);
        //font.draw(game.batch,tryAgainLayout, tryAgainX,tryAgainY);
        font.draw(game.batch, mainMenuLayout,mainMenuX,mainMenuY);
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
