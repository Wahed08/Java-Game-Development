package com.mygdx.game.com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Collision;

import static com.mygdx.game.com.mygdx.game.bullet.Bullet.Y;

public class baloon {

    public static final int speed=100;
    public static final int Width=30;
    public static final int Height=40;

    private static Texture texture;
    float x,y;
    public boolean flag=false;
    Collision col;
    public baloon(float x){
        this.x=x;
        this.y=y;
        this.col=new Collision(x,y,Width,Height);

        if(texture==null){
            texture =new Texture("baloon.png");
        }
    }

    public void update(float deltaTime){
        y+=speed*deltaTime;
        if(y < -Height){
            flag=true;
        }
       col.move(x,y);
    }

    public void render(SpriteBatch batch) {

        batch.draw(texture,x,y);
    }
    public Collision getCollision(){

        return  col;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

}
