package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.interfaces.IInputListener;
import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 24.01.17.
 */

public class TestStage extends Stage implements IInputListener {
    public static final String TAG = TestStage.class.getSimpleName();

    private int WIDTH  = 800;
    private int HEIGHT = 480;

    private float _relativeX;
    private float _relativeY;

    private OrthographicCamera _cam;
    private Rectangle _rect;
    private Rectangle _viewPort;

    Actor _firstActor;
    Actor _block;

    public TestStage(){
        super();

        float d = (float)Gdx.graphics.getWidth() / (float)Gdx.graphics.getHeight();

        WIDTH = (int)(d * (float)HEIGHT);

        IExternalLogger logger = WaterDiscoveries.getInstance().getExternal().GetLogger();

        _cam = new OrthographicCamera(WIDTH, HEIGHT);

        _cam.position.set(WIDTH / 2,HEIGHT / 2, 0);//WIDTH / 2, HEIGHT / 2, 0);

        logger.ShowMessage(TAG, "Camera x:" + _cam.position.X + "; y:" + _cam.position.Y+ "; z:" + _cam.position.Z);

//        logger.ShowMessage(TAG, "Graphics w:" + Gdx.graphics.getWidth() + "; h:" + Gdx.graphics.getHeight());

        _relativeX = (float)Gdx.graphics.getWidth() / _cam.viewportWidth ;
        _relativeY = (float)Gdx.graphics.getHeight() / _cam.viewportHeight;

//        logger.ShowMessage(TAG, "X:" + _relativeX + "; Y:" + _relativeY);

        _firstActor = new TestActor();

        _block = new TestBlock(-50, -50, 50, 50);

        _viewPort = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        logger.ShowMessage(TAG, "Camera x:" + _cam.position.X + "; y:" + _cam.position.Y);
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer){
        super.touchDragged(x, y, pointer);
        _firstActor.setX((float)x / _relativeX);

        _firstActor.setY((float)(Gdx.graphics.getHeight() - y) / _relativeY);

        _cam.position.set(_firstActor.getX(), _firstActor.getY(), 0);

        return  true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        super.touchDown(x, y, pointer, button);
        return  true;
    }

    @Override
    public void draw(){

        getBatch().begin();

        GL20 gl = Gdx.graphics.getGL20();

        gl.glViewport((int)_viewPort.x,
                      (int)_viewPort.y,
                      (int)_viewPort.getWidth(),
                      (int)_viewPort.getHeight());


        _cam.update();

        getBatch().setProjectionMatrix(_cam.combined);

        _firstActor.act(Gdx.graphics.getDeltaTime());

        _firstActor.draw(getBatch(), Gdx.graphics.getDeltaTime());

        _block.act(Gdx.graphics.getDeltaTime());

        _block.draw(getBatch(), Gdx.graphics.getDeltaTime());

        getBatch().end();
    }
}
