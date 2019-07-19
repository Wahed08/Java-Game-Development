package com.mygdx.game.com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Collision;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.com.mygdx.game.bullet.Bullet.Y;

public class stone {

    public static final int speed=400;
    public static final int Width=36;
    public static final int Height=36;

    private static Texture texture;
    float x,y;
    public boolean flag=false;
    Collision col;

    public stone(float x){
        this.x=x;
        this.y= MyGdxGame.Height;
        this.col=new Collision(x,y,Width,Height);

        if(texture==null){
            texture =new Texture("Stone.png");
        }
    }

    public void update(float deltaTime){
        y-=speed*deltaTime;
        if(y < -Height){
            flag=true;
        }
        col.move(x,y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y);
    }
    public Collision getCollision(){
        return col;
    }

}
