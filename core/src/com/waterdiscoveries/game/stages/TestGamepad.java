package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by r2d2 on 26.01.17.
 */

public class TestGamepad extends Actor {
    public static final String TAG = TestGamepad.class.getSimpleName();

    private Texture _elipce;
    private Texture _jostik;
    private int _width;
    private int _height;

    public TestGamepad(int width, int height){
        _width = width;
        _height = height;
        _elipce = new Texture(MakeElipce());
    }

    @Override
    public void draw(Batch bacth, float delta){
        bacth.draw(_elipce, _width  + 40, _height / 2);
    }

    private Pixmap MakeElipce(){
        Pixmap pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);

        pixmap.setColor(0, 0, 1, 1);

        pixmap.drawCircle(0, 0, _height);

        return pixmap;
    }
}
