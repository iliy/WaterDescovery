package com.waterdiscoveries.game.interfaces;

/**
 * Created by r2d2 on 25.01.17.
 */

public interface IInputListener {

    boolean touchDown(int screenX, int screenY, int pointer, int button);

    boolean touchUp(int screenX, int screenY, int pointer, int button);

    boolean touchDragged(int screenX, int screenY, int pointer);


//    boolean keyDown(int keycode);
//
//    boolean keyUp(int keycode);
//
//    boolean keyTyped(char character);
//
//
//    boolean mouseMoved(int screenX, int screenY)
//
//    boolean scrolled(int amount);
}
