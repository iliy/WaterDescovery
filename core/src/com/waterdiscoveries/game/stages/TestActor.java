package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by r2d2 on 24.01.17.
 */

public class TestActor extends Actor {

    Texture texture;

    private Pixmap _pixmap;

    public TestActor(){
        makePixmap();

        setX(100);
        setY(300);

        texture = new Texture(_pixmap);
    }



    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, getX() - texture.getWidth() / 2, (getY()) - texture.getHeight() / 2);
    }

    private void makePixmap(){
        _pixmap = new Pixmap(60, 60, Pixmap.Format.RGBA8888);
        _pixmap.setColor(0f, 0, 0, 1);
        _pixmap.fillCircle(30, 30, 30);
    }
}
