package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.waterdiscoveries.game.Configs;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 24.01.17.
 */

public class TestActor extends Actor {

    public static final String TAG = TestActor.class.getSimpleName();

    Texture texture;

    private Pixmap _pixmap;

    private World _world;

    private Body _body;

    private Fixture _fixture;

    private IExternalLogger _logger;

    public TestActor(World world){
        _world = world;

        _logger = WaterDiscoveries.getInstance().getExternal().GetLogger();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.bullet = true;

        _body = _world.createBody(bodyDef);

        CircleShape shape = new CircleShape();

        setX(Configs.WIDTH / 2);

        setY(450);

        shape.setPosition(new Vector2(0, 0));

        shape.setRadius(15);

        makePixmap();

        _fixture = _body.createFixture(shape, 10);

        _fixture.setFriction(0.1f);

        _body.setFixedRotation(true);

        _body.setBullet(true);

        MassData mass = new MassData();

        mass.mass = 1.0f;

        mass.center.set(15, 15);

        _body.setMassData(mass);

        _body.getPosition().x = getX() + 15;
        _body.getPosition().y = getY() + 15;

        _body.setTransform(getX(), getY(), 0);

        texture = new Texture(_pixmap);

        PolygonShape ss = new PolygonShape();

    }


    public Body getBody(){
        return _body;
    }


    @Override
    public void draw(Batch batch, float parentAlpha){
//        String msg = "X: " + getY() + "; Y:"+  getX();
//
//        _logger.ShowMessage(TAG, msg);

        setPosition(_body.getPosition().x, _body.getPosition().y);

        batch.draw(texture, getX() - texture.getWidth() / 2, (getY()) - texture.getHeight() / 2);
    }

    private void makePixmap(){
        _pixmap = new Pixmap(31, 31, Pixmap.Format.RGBA8888);
        _pixmap.setColor(0.3f, 0.1f, 0.9f, 1);
        _pixmap.fillCircle(15, 15, 15);
    }
}
