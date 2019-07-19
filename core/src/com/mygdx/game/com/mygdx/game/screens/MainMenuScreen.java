package com.mygdx.game.com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen {
    private static final int play_button_width=63;
    private static final int play_button_height=144;
    private static final int exit_button_width=63;
    private static final int exit_button_height=144;
    private static final int logo_Width=144;
    private static final int logo_Height=173;
    private static final int menu_button_width=144;
    private static final int menu_button_height=61;
    private static final int option_button_width=144;
    private static final int option_button_height=55;
    private static final int play_button_Y=310;
    private static final int exit_button_Y=90;
    private static final int menu_button_Y=230;
    private static final int option_button_y=150;

    MyGdxGame game;
    Texture Manlogo;
    Texture background;
    Texture PlayButton;
    Texture MenuButton;
    Texture OptionButton;
    Texture ExitButton;
    Texture NameLogo;
    Texture menu;
    public MainMenuScreen(MyGdxGame game){
        this.game=game;
        background=new Texture("bg.png");
        PlayButton=new Texture("play_Butt.png");
        MenuButton=new Texture("menu_Butt.png");
        OptionButton=new Texture("option_Butt.png");
        ExitButton= new Texture("Exit_Butt.png");
        Manlogo=new Texture("ManLogo.png");
        NameLogo=new Texture("balloonshooter.png");
        menu=new Texture("Menu_background.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        int x = MyGdxGame.Width / 2 - play_button_height / 2;
        game.batch.draw(background, 0, 0, MyGdxGame.Width, MyGdxGame.Height);
        game.batch.draw(NameLogo,0,310,x+30,MyGdxGame.Height/2-play_button_height/2);
        if (game.camera.getInputInGameWorld().x < x + play_button_width && game.camera.getInputInGameWorld().x > x && MyGdxGame.Height - game.camera.getInputInGameWorld().y < play_button_Y + play_button_height && MyGdxGame.Height - game.camera.getInputInGameWorld().y> play_button_Y) {
            game.batch.draw(PlayButton, x, play_button_Y, play_button_height, play_button_width);
            if(Gdx.input.isTouched()){
                this.dispose();
               game.setScreen(new PlayScreen(game));
            }

        } else
            game.batch.draw(PlayButton, x, play_button_Y, play_button_height, play_button_width);
         x = MyGdxGame.Width / 2 - menu_button_height / 2;
         if(game.camera.getInputInGameWorld().x < x + menu_button_width && game.camera.getInputInGameWorld().x > x && MyGdxGame.Height - game.camera.getInputInGameWorld().y< menu_button_Y + menu_button_height && MyGdxGame.Height - game.camera.getInputInGameWorld().y > menu_button_Y)
         {
             game.batch.draw(MenuButton, (MyGdxGame.Width / 2) - MenuButton.getWidth() / 2, MenuButton.getHeight() * 3.85f);
             if(Gdx.input.isTouched()){
                 this.dispose();
                   game.setScreen(new DemoScreen(game));
             }
         }
         else
             game.batch.draw(MenuButton, (MyGdxGame.Width / 2) - MenuButton.getWidth() / 2, MenuButton.getHeight() * 3.85f);
        //

        x = MyGdxGame.Width / 2 - option_button_height / 2;
        if(game.camera.getInputInGameWorld().x < x + option_button_width && game.camera.getInputInGameWorld().x > x && MyGdxGame.Height - game.camera.getInputInGameWorld().y< option_button_y + option_button_height && MyGdxGame.Height - game.camera.getInputInGameWorld().y > option_button_y)
        {
            game.batch.draw(OptionButton, (MyGdxGame.Width / 2) - OptionButton.getWidth() / 2, OptionButton.getHeight() * 3);
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new DemoScreen2(game));
            }
        }
        else
            game.batch.draw(OptionButton, (MyGdxGame.Width / 2) - OptionButton.getWidth() / 2, OptionButton.getHeight() * 3);


        x = MyGdxGame.Width / 2 - exit_button_height / 2;
        if (game.camera.getInputInGameWorld().x < x + exit_button_width && game.camera.getInputInGameWorld().x > x && MyGdxGame.Height -game.camera.getInputInGameWorld().y < exit_button_Y + exit_button_height && MyGdxGame.Height - game.camera.getInputInGameWorld().y > exit_button_Y){
            game.batch.draw(ExitButton, x, exit_button_Y, exit_button_height, exit_button_width);
            if (Gdx.input.isTouched())
                Gdx.app.exit();
    }
        else
            game.batch.draw(ExitButton, x, exit_button_Y, exit_button_height, exit_button_width);
        game.batch.draw(Manlogo,logo_Width/2,logo_Height/2,logo_Width+logo_Width/2,logo_Height+logo_Height/2);
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
