package com.waterdiscoveries.game.stages;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 25.01.17.
 */

public class TestBlock extends Actor {
    public static final String TAG = TestBlock.class.getSimpleName();

//    private Rectangle _rect;

    private Texture _texture;

    private TextureRegion _region;

    private World _world;

    private Body _body;

    private IExternalLogger _logger;

    Vector2 vertex[];

    private float _width, _height;

    public void RotateByPoint(float angle, Vector2 point){
        _body.setTransform(point.x , point.y , angle);

//        _body.getTransform().setPosition(new Vector2(point.x + _width / 2, point.y + _height / 2));
//        _body.getTransform().setPosition(10, 10);
//        _body.setAngularVelocity(angle);
    }

    public TestBlock(int x, int y, int width, int height, World world){
        _world = world;

        _width = width;
        _height = height;

        _logger = WaterDiscoveries.getInstance().getExternal().GetLogger();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.KinematicBody;

        bodyDef.bullet = true;

        _body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

//        shape.setAsBox(width, height);

        vertex = new Vector2[4];

        vertex[0] = new Vector2(- width / 2, - height / 2);

        vertex[1] = new Vector2(-width / 2, height / 2);

        vertex[2] = new Vector2(width / 2, height / 2);

        vertex[3] = new Vector2(width / 2, -height / 2);

        shape.set(vertex);

        Fixture fixture = _body.createFixture(shape, 1);

//        _rect = new Rectangle(0, 0, width, height);

//        _texture = new Texture(MakePixmap(vertex));

//        setX(x);

//        setY(y);


        float angle = 45 * (float)Math.PI / 180;

        _body.setTransform(x, y, angle);

        _body.getPosition().set(x, y);

        _body.getTransform().setPosition(new Vector2(x + width / 2, y + height / 2));

        _body.getLocalCenter().set(width / 2, height / 2);

        MassData massa = new MassData();

        massa.center.set(x, y);

        massa.mass = 1000;

        _body.setMassData(massa);


        setX(x - width / 2);
        setY(y - height / 2);

//        t.setOrientation(x + width / 2, y + height / 2);

//        _body.getPosition().set(x, y);



//        _region = new TextureRegion(_texture, 0, 0, width, height);


//        this.setOrigin(width / 2, height / 2);

//        this.setRotation(-10);

//        this.setRotation(-10);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){

//        String msg = "X: " + _body.getTransform().getPosition().x + "; Y:"+  _body.getTransform().getPosition().y;

//        _logger.ShowMessage(TAG, msg);

//        draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
//        float scaleX, float scaleY, float rotation)


        Transform t = _body.getTransform();




        float angle = 180 * t.getRotation() / (float)Math.PI;


        TextureRegion region = MakePixmap(vertex);

        batch.draw(region, _body.getPosition().x - getWidth() / 2, _body.getPosition().y - getHeight() / 2,
                           _body.getPosition().x - getX(), _body.getPosition().y - getY(), getWidth(), getHeight(), 1, 1, angle);

//        batch.draw(GetTextureRectangle(vertex), _body.getPosition().x,t.getPosition().y);

        batch.draw(RotatePoint(), _body.getPosition().x - 5, _body.getPosition().y - 5);

//        batch.draw(_texture, getX() - getWidth() / 2 , getY() - getHeight() / 2);
    }

    private TextureRegion GetTextureRectangle(Vector2[] vertex){
        int maxX = -1000;
        int maxY = -1000;
        int minX = 100000;
        int minY = 100000;
        for(int i = 0; i < vertex.length; i++){
            if(maxX < (int)vertex[i].x){
                maxX = (int)vertex[i].x;
            }
            if(minX > (int)vertex[i].x){
                minX = (int)vertex[i].x;
            }
            if(maxY < (int)vertex[i].y){
                maxY = (int)vertex[i].y;
            }
            if(minY > (int)vertex[i].y){
                minY = (int)vertex[i].y;
            }
        }

        Pixmap pixmap = new Pixmap(maxX - minX, maxY - minY, Pixmap.Format.RGBA8888);

        pixmap.setColor(0, 1, 0, 1);

        pixmap.drawRectangle(0, 0, maxX - minX, maxY - minY);

        Texture texture = new Texture(pixmap);

        return new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    private Texture RotatePoint(){
        Pixmap pixmap = new Pixmap(11, 11, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 1);

        pixmap.drawCircle(5, 5, 5);

        Texture texture = new Texture(pixmap);

        return  texture;
    }


    private TextureRegion MakePixmap(Vector2[] vertex){
        int maxX = -1000;
        int maxY = -1000;
        int minX = 100000;
        int minY = 100000;
        for(int i = 0; i < vertex.length; i++){
            if(maxX < (int)vertex[i].x){
                maxX = (int)vertex[i].x;
            }
            if(minX > (int)vertex[i].x){
                minX = (int)vertex[i].x;
            }
            if(maxY < (int)vertex[i].y){
                maxY = (int)vertex[i].y;
            }
            if(minY > (int)vertex[i].y){
                minY = (int)vertex[i].y;
            }
        }


        setWidth(maxX - minX);

        setHeight(maxY - minY);

        Pixmap pixmap = new Pixmap(maxX - minX, maxY - minY, Pixmap.Format.RGBA8888);

        pixmap.setColor(0, 0, 0, 1);


//        pixmap.fillTriangle((int)vertex[0].x,(int)vertex[0].y,(int)vertex[1].x,(int)vertex[1].y,(int)vertex[2].x,(int)vertex[2].y);
//        pixmap.fillTriangle((int)vertex[2].x,(int)vertex[2].y,(int)vertex[3].x,(int)vertex[3].y,(int)vertex[0].x,(int)vertex[0].y);
//
//        return pixmap;

        for(int i = 1; i < vertex.length - 1; i ++){

            int x1 = (int)vertex[0].x - minX;

            int y1 = (int)vertex[0].y - minY;

            int x2 = (int)vertex[i].x - minX;

            int y2 = (int)vertex[i].y - minY;

            int x3 = (int)vertex[i + 1].x - minX;

            int y3 = (int)vertex[i + 1].y - minY;

//            int y2 = i == vertex.length - 1 ? (int)vertex[0].y:
//                                i == vertex.length ? (int)vertex[1].y:
//                                        (int)vertex[i].y;
//
//            int x3 = i == vertex.length - 2 ? (int)vertex[0].x:
//                                i == vertex.length - 1 ? (int)vertex[1].x:
//                                        i == vertex.length ? (int)vertex[2].x:
//                                                (int)vertex[i].x;
//
//            int y3 = i == vertex.length - 2? (int)vertex[0].y:
//                            i==vertex.length - 1? (int)vertex[1].y:
//                                    i == vertex.length ? (int)vertex[2].y:
//                                            (int)vertex[i].y;

            pixmap.fillTriangle(x1, y1, x2, y2, x3, y3);
        }

        Texture texture = new Texture(pixmap);

        return new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }
}
