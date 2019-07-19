package com.mygdx.game.com.mygdx.game.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class explosion {

    public static final float length=0.1f;
    public static final int offset=8;
    public static  final int size=32;

    private static Animation animation=null;
    float x,y;
    float stateTime;
    public boolean flag=false  ;

    public explosion(float x,float y){
        this.x=x-offset;
        this.y=y-offset;
        stateTime=0;

        if(animation==null)
            animation=new Animation(length, TextureRegion.split(new Texture("explosion.png"),size,size)[0]);
    }
    public void update(float delta){
            stateTime+=delta;
            if(animation.isAnimationFinished(stateTime))
                flag=true;
    }
    public void render(SpriteBatch batch){
        batch.draw((TextureRegion) animation.getKeyFrame(stateTime),x,y);
    }
}
