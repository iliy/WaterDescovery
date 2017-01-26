package com.waterdiscoveries.game.infrastructure;

import com.badlogic.gdx.InputProcessor;
import com.waterdiscoveries.game.interfaces.IInputListener;

/**
 * Created by r2d2 on 25.01.17.
 */

public class CustomInputProcessor implements InputProcessor {
    public static final String TAG = CustomInputProcessor.class.getSimpleName();

    private IInputListener _inputListener;

    private static CustomInputProcessor _instance;

    public static CustomInputProcessor getInstance()
    {
        if(_instance == null) _instance = new CustomInputProcessor();
        return  _instance;
    }

    private CustomInputProcessor(){}

    public void setInputListener(IInputListener listener){
        _inputListener = listener;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(_inputListener != null) return _inputListener.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(_inputListener != null) return _inputListener.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(_inputListener != null) return _inputListener.touchDragged(screenX, screenY, pointer);
        return false;
    }


    /*** Прочие методы интерфейса ***/

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
