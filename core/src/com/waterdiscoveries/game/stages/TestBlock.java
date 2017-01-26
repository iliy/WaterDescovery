package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by r2d2 on 25.01.17.
 */

public class TestBlock extends Actor {
    public static final String TAG = TestBlock.class.getSimpleName();

    private Rectangle _rect;

    private Texture _texture;

    public TestBlock(int x, int y, int width, int height){
        _rect = new Rectangle(0, 0, width, height);
        _texture = new Texture(MakePixmap());
        setX(x);
        setY(y);

    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(_texture, getX() - _texture.getWidth() / 2, (getY()) - _texture.getHeight() / 2);
    }

    private Pixmap MakePixmap(){
        Pixmap pixmap = new Pixmap((int)_rect.getWidth(), (int)_rect.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fillRectangle(0, 0, (int)_rect.getWidth(), (int)_rect.getHeight());
        return pixmap;
    }
}
