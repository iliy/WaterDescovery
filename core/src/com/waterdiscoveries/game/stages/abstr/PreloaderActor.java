package com.waterdiscoveries.game.stages.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.waterdiscoveries.game.Configs;

/**
 * Created by nobos on 27.01.2017.
 */

public class PreloaderActor extends Actor {
    public static final String TAG = PreloaderActor.class.getSimpleName();

    private Sprite _preloader;


    public PreloaderActor(){
        Texture preloadTexture = new Texture("images/preloader.png");

        _preloader = new Sprite(preloadTexture);

        _preloader.setPosition(Configs.WIDTH / 2 - _preloader.getWidth() / 2, Configs.HEIGHT / 2 - _preloader.getHeight() / 2);
    }

    @Override
    public void act(float alpha){
        _preloader.rotate(-1.5f);
    }

    @Override
    public void draw(Batch batch, float alpha){
        _preloader.draw(batch);
    }
}
