package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.waterdiscoveries.game.Configs;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.interfaces.IInputListener;
import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 24.01.17.
 */

public class TestStage extends Stage implements IInputListener {
    public static final String TAG = TestStage.class.getSimpleName();

    private Box2DDebugRenderer _renderer;

    private int WIDTH  = 800;
    private int HEIGHT = 480;

    private float _relativeX;
    private float _relativeY;

    private OrthographicCamera _cam;
    private Rectangle _rect;
    private Rectangle _viewPort;

    private World _world;

    Actor _firstActor;
    Actor _block;

    IExternalLogger _logger;
    private Texture _centerPoint;

    public TestStage(){
        super();

        _logger = WaterDiscoveries.getInstance().getExternal().GetLogger();

        _centerPoint = CenterPoint();

        _renderer = new Box2DDebugRenderer();

//        _renderer.setDrawBodies(true);

//        _renderer.setDrawAABBs(true);

//        _renderer.setDrawBodies(true);

//        _renderer.setDrawContacts(true);

        _renderer.setDrawVelocities(true);

//        _renderer.setDrawJoints(true);

        _world = new World(new Vector2(150 * (float)Math.cos(_angle), 150 * (float)Math.sin(_angle)), true);


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

        _firstActor = new TestActor(_world);
//Configs.HEIGHT / 2
        _block = new TestBlock(300, 200, Configs.WIDTH / 2, 50, _world);

        ((TestBlock)_block).RotateByPoint(_step * 10, new Vector2(300, 200));

//        _block.setOrigin(Configs.WIDTH / 2, 25);

//        _block.setRotation(-10);

//        RotateToAction action = new RotateToAction();

//        action.setRotation(-10);

//        _block.addAction(action);

        _viewPort = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        logger.ShowMessage(TAG, "Camera x:" + _cam.position.X + "; y:" + _cam.position.Y);
    }

    private int _oldY = -1;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        super.touchUp(screenX, screenY, pointer, button);
//        _oldY = -1;
        _logger.ShowMessage(TAG, "" + pointer);
        return  true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer){
        super.touchDragged(x, y, pointer);

        if(_oldY == -1){
            _oldY = y;
            return true;
        }

        float dy = (float)(y - _oldY) / 51000f;

        _angle -= dy;

        _logger.ShowMessage(TAG, "" + pointer);
//        _angle -= (float)Math.PI * 3 / 2;

//        _logger.ShowMessage(TAG, "Angle: " + _angle );

//        _firstActor.setX((float)x / _relativeX);
//
//        _firstActor.setY((float)(Gdx.graphics.getHeight() - y) / _relativeY);
        _cam.rotate(_angle - (float)Math.PI * 3 / 2);

        Vector2 gravity = new Vector2((float)(Math.cos(_angle) * 150f), (float)(Math.sin(_angle) * 150f));
//
        _world.setGravity(gravity);


//        ((TestBlock)_block).RotateByPoint(_angle, new Vector2(_firstActor.getX(), _firstActor.getY()));
//        _cam.position.set(_firstActor.getX(), _firstActor.getY(), 0);

        return  true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        super.touchDown(x, y, pointer, button);
        _logger.ShowMessage(TAG, "" + pointer);
        return  true;
    }

    private Texture CenterPoint(){
        Pixmap pixmap = new Pixmap(11, 11, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 1);

        pixmap.drawCircle(5, 5, 5);

        Texture texture = new Texture(pixmap);

        return  texture;
    }

    private float _angle = 0;//(float)Math.PI * 3 / 2;//
    float _step = (float)Math.PI / 50;

    @Override
    public void draw(){

        getBatch().begin();

        _cam.position.set(_firstActor.getX(), _firstActor.getY(), 0);

        _cam.update();

        _block.act(Gdx.graphics.getDeltaTime());

        GL20 gl = Gdx.graphics.getGL20();

        gl.glViewport((int)_viewPort.x,
                      (int)_viewPort.y,
                      (int)_viewPort.getWidth(),
                      (int)_viewPort.getHeight());


//        _renderer.render(_world, _cam.combined);

        getBatch().setProjectionMatrix(_cam.combined);

        _firstActor.act(Gdx.graphics.getDeltaTime());

        _firstActor.draw(getBatch(), Gdx.graphics.getDeltaTime());

        _block.act(Gdx.graphics.getDeltaTime());

        _block.draw(getBatch(), Gdx.graphics.getDeltaTime());


        getBatch().draw(_centerPoint, -5 , -5);

        getBatch().end();

        _renderer.render(_world, _cam.combined);

        _world.step(Gdx.graphics.getDeltaTime(), 4, 4);
    }
}
