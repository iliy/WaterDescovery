package com.waterdiscoveries.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.waterdiscoveries.game.stages.TestStage;

/**
 * Created by r2d2 on 24.01.17.
 */

public class TestScreen implements Screen {


    SpriteBatch batch;
    Texture img;
    Stage _stage;
    Viewport _viewPort;

    public TestScreen(){
//        OrthographicCamera camera = new OrthographicCamera(320, 180);
//
//        batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");


//        _stage = new Stage(new ScreenViewport(camera), batch);
        _stage = new TestStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _stage.draw();
//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
//        _stage.act(delta);
//        _stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        _stage.dispose();
    }
}
