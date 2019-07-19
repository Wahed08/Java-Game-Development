package com.mygdx.game.com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Collision;
import com.mygdx.game.MyGdxGame;

public class Bullet {

    public static final int speed=800;
    public static final int Y=80;
    public static final int Height=26;
    public static final int Width=10;

    private static Texture texture;
    float x,y;
    public boolean flag=false;
    Collision col;

    public Bullet(float x){
        this.x=x;
        this.y=Y;
        this.col=new Collision(x,y,Width,Height);

        if(texture==null){
            texture =new Texture("bullet.png");
        }
    }

    public void update(float deltaTime){
        y+=speed*deltaTime;
        if(y > MyGdxGame.Height){
            flag=true;
        }
        col.move(x,y);
    }

    public void render(SpriteBatch batch){

        batch.draw(texture,x,y);
    }
    public  Collision getCollision(){
       return col;
    }

}
