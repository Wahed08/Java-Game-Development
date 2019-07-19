package com.mygdx.game.com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameCamera {

    private OrthographicCamera camera;
    private StretchViewport viewport;

    public GameCamera(int width,int height){
        camera=new OrthographicCamera();
        viewport=new StretchViewport(width,height,camera);
        viewport.apply();
        camera.position.set(width/2,height/2,0);
        camera.update();
    }
    public Matrix4 combined(){
        return camera.combined;
    }
    public void update(int width,int height){
        viewport.update(width,height);
    }
    public Vector2 getInputInGameWorld(){
        Vector3 InputScreen=new Vector3(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY(),0);
        Vector3 unprojected=camera.unproject(InputScreen);
        return new Vector2(unprojected.x,unprojected.y);
    }
}
