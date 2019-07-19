package com.mygdx.game.com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.Collision;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.com.mygdx.game.bullet.Bullet;
import com.mygdx.game.com.mygdx.game.bullet.baloon;
import com.mygdx.game.com.mygdx.game.bullet.explosion;
import com.mygdx.game.com.mygdx.game.bullet.stone;
import com.sun.javafx.scene.KeyboardShortcutsHandler;

import java.util.ArrayList;
import java.util.Random;

public class PlayScreen implements Screen {
    public static final int Speed=300;
    public static final int Man_width=41;
    public static final int Man_height=94;
    public static final float low_time=0.3f;
    public static final float high_time=0.6f;
    public static final float shoot_time=0.2f;
    Texture img,play_background;
    Texture background,line,line2;
    float x;
    float y;
    float stone_times;
    float baloon_times;
    float shootTime=0;
    Random random;
    Random randomB;
    int score;
    private Sound bulletSound;
    private Sound explosionSound;
    private Sound painSound;
    private Sound diesound;

    boolean paused;

    MyGdxGame game;
    ArrayList<Bullet>bullets;
    ArrayList<stone>stones;
    ArrayList<baloon>baloons;
    ArrayList<explosion>explosions;

    BitmapFont scorefont;
    float HealthBar=1;
    float cnt=1;
    Collision player;

    public PlayScreen(MyGdxGame game){
        this.game=game;
        y=10;
        score=0;
        x=MyGdxGame.Width/2 - Man_width/2;
        bullets=new ArrayList<Bullet>();
        stones=new ArrayList<stone>();
        baloons=new ArrayList<baloon>();
        explosions=new ArrayList<explosion>();
        player=new Collision(0,0,Man_width,Man_height);
        random= new Random();
        randomB=new Random();
        stone_times=random.nextFloat()*(high_time - low_time)+low_time;
        baloon_times=randomB.nextFloat();
        scorefont=new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        line=new Texture("blank1.png");
       // line2=new Texture("blank1.png");
        bulletSound=Gdx.audio.newSound(Gdx.files.internal("audio/bulletsound.ogg"));
        explosionSound=Gdx.audio.newSound(Gdx.files.internal("audio/ExplosionSound.ogg"));
        painSound=Gdx.audio.newSound(Gdx.files.internal("audio/pain.ogg"));

    }

    @Override
    public void show() {
        img=new Texture("rifleman.png");
        play_background=new Texture("play_background.png");

    }

    @Override
    public void render(float delta) {
        shootTime+=delta;

        if(((isRight() || isLeft()) && (shootTime >= shoot_time)) || (Gdx.input.isKeyPressed(Input.Keys.SPACE) && (shootTime >= shoot_time))){
            shootTime=0;
            bullets.add(new Bullet(x+20));
           // bullets.add(new Bullet(x+Man_width-2));
        }
        //stone code

        stone_times-=delta;
        if(stone_times <= 0){
            stone_times=random.nextFloat()*(high_time - low_time)+low_time;
            stones.add(new stone(random.nextInt(Gdx.graphics.getWidth()-stone.Height)));
        }
        //baloon code
        baloon_times-=delta;
        if(baloon_times<0){
            baloon_times=randomB.nextFloat();
            baloons.add(new baloon(randomB.nextInt(MyGdxGame.Width-baloon.Height)));
        }
        ArrayList<Bullet> bulletToflag=new ArrayList<Bullet>();
        ArrayList<stone> stoneToFlag=new ArrayList<stone>();
        ArrayList<baloon>baloonTOFlag=new ArrayList<baloon>();
        ArrayList<explosion>explosionsToFlag=new ArrayList<explosion>();

        //Update stone

        for(stone Stone: stones){
            Stone.update(delta);
            if(Stone.flag)
                stoneToFlag.add(Stone);
        }
        // Update Bullet

        for(Bullet bullet: bullets){
            bullet.update(delta);
            if(bullet.flag)
                bulletToflag.add(bullet);
        }

        //update baloon
        for(baloon Baloon: baloons){
            Baloon.update(delta);
            if(Baloon.flag)
                baloonTOFlag.add(Baloon);
        }

        //update explosion
        for(explosion Explosion: explosions){
            Explosion.update(delta);
            if(Explosion.flag)
                explosionsToFlag.add(Explosion);
        }
        explosions.removeAll(explosionsToFlag);

        if(paused){
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                paused=false;
                try {
                    Thread.sleep(2000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else
            generalupdate();
        //Movement

        if(isLeft()){
            x-=Speed*Gdx.graphics.getDeltaTime();
            if(x<0){
                x=0;
            }
        }
        if(isRight()){
            x+=Speed*Gdx.graphics.getDeltaTime();
            if(x+Man_width>Gdx.graphics.getWidth()){
                x=MyGdxGame.Width-Man_width;
            }
        }


        player.move(x,y);

        // collision code

        for (Bullet bullet: bullets){
            for(baloon Baloon: baloons){
                if(bullet.getCollision().collides(Baloon.getCollision())){
                    bulletToflag.add(bullet);
                    baloonTOFlag.add(Baloon);
                    explosions.add(new explosion(Baloon.getX(),Baloon.getY()));
                    score+=10;
                    bulletSound.play();
                    explosionSound.play();
                }
            }
        }
        //stone player collision
        for(stone Stone: stones){
            if(Stone.getCollision().collides(player)){
                stoneToFlag.add(Stone);
                painSound.play();
                HealthBar-=0.2;
                cnt-=0.2;
                if(HealthBar<=0){
                    this.dispose();
                    game.setScreen(new GameOverScreen(game,score));
                    return;
                }
            }
        }
       //stone collision code

      /*  for(Bullet bullet: bullets){
            for(stone Stone: stones){
                if(bullet.getCollision().collides(Stone.getCollision())){
                    bulletToflag.add(bullet);
                    stoneToFlag.add(Stone);
                }
            }
        }

       */
        bullets.removeAll(bulletToflag);
        baloons.removeAll(baloonTOFlag);
        stones.removeAll(stoneToFlag);

        Gdx.gl.glClearColor(0.2f,0.5f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        game.batch.draw(play_background,0,0,MyGdxGame.Width,MyGdxGame.Height);
        game.batch.draw(img,x,y);
        GlyphLayout scorelayout=new GlyphLayout(scorefont, "Score " + score);
        scorefont.draw(game.batch,scorelayout,MyGdxGame.Width/2 - scorelayout.width/2,MyGdxGame.Height-scorelayout.height-10);

       for(Bullet bullet:bullets){
           bullet.render(game.batch);
       }
       for(stone Stone :stones){
           Stone.render(game.batch);
       }
       for(baloon Baloon: baloons){
           Baloon.render(game.batch);
       }
       for(explosion Explosion:explosions){
           Explosion.render(game.batch );
       }
        if(HealthBar>0.6f){
            game.batch.setColor(Color.GREEN);
        }
       else if(HealthBar>0.4f){
            game.batch.setColor(Color.ORANGE);
        }
        else {
            game.batch.setColor(Color.RED);
        }

       // game.batch.draw(line2,0,0,Gdx.graphics.getWidth()*cnt,7);
        game.batch.draw(line,0,0,MyGdxGame.Width*HealthBar,10);

        game.batch.setColor(Color.WHITE);
        generalupdate();

        game.batch.end();
    }
    private boolean isRight(){
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched()) && game.camera.getInputInGameWorld().x>=MyGdxGame.Width/2;
    }
    private boolean isLeft(){
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched()) &&game.camera.getInputInGameWorld().x<MyGdxGame.Width/2;
    }

    public void generalupdate(){

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            paused=true;
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

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
        bulletSound.dispose();
        explosionSound.dispose();
        painSound.dispose();
    }
}
