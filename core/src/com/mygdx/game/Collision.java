package com.mygdx.game;

public class Collision {

    float x,y;
    int Width,Height;

    public Collision(float x,float y,int Width,int Height){
            this.x=x;
            this.y=y;
            this.Width=Width;
            this.Height=Height;
    }
    public void move(float x,float y){
        this.x=x;
        this.y=y;
    }
    public boolean collides(Collision col){
        return x<col.x+col.Width && y<col.y+col.Height && x+Width>col.x && y+Height>col.y;
    }
}


