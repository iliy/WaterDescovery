package com.waterdiscoveries.game.stages.abstr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.waterdiscoveries.game.Configs;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.interfaces.ITaskListener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by nobos on 27.01.2017.
 */

public abstract class ALoadStage<T, V> extends Stage implements Callable<T>{

    protected V _data;

    private Texture _background;

    private PreloaderActor _preloaderActor;

    private ITaskListener _listener;

    private Future<T> _future;

    private OrthographicCamera _cam;

    private Rectangle _glViewPort;

    private ExecutorService _executer;

    public ALoadStage(ITaskListener listener, V data){
        _data = data;

        _listener = listener;

        _background = new Texture(makeBackground());

        _preloaderActor = new PreloaderActor();

        _cam = new OrthographicCamera(Configs.WIDTH, Configs.HEIGHT);

        _cam.position.set(Configs.WIDTH / 2 , Configs.HEIGHT / 2, 0);

        _glViewPort = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public abstract String getTag();

    private Pixmap makeBackground(){
        Pixmap pixmap = new Pixmap(Configs.WIDTH, Configs.HEIGHT, Pixmap.Format.RGBA8888);

        pixmap.setColor(0, 0, 0, 1);

        pixmap.fillRectangle(0, 0, Configs.WIDTH, Configs.HEIGHT);

        return  pixmap;
    }

    public void startLoad(){
        _executer = Executors.newFixedThreadPool(1);

        _future = _executer.submit(this);
    }

    @Override
    public void draw(){
        if(_future != null && _future.isDone()){
            try {
                if (_listener != null) _listener.TaskComlete(getTag(), _future.get(), "OK");
            }catch (Exception e){
                if(_listener != null) _listener.TaskComlete(getTag(), null, "Error: " + e.getMessage());
            }
            _executer.shutdown();
            return;
        }

        getBatch().begin();

        GL20 gl = Gdx.gl20;

        gl.glViewport((int)_glViewPort.x, (int)_glViewPort.y, (int)_glViewPort.getWidth(), (int)_glViewPort.getHeight());

        _cam.update();

        getBatch().setProjectionMatrix(_cam.combined);

        getBatch().draw(_background, 0, 0);

        _preloaderActor.act(Gdx.graphics.getDeltaTime());

        _preloaderActor.draw(getBatch(), Gdx.graphics.getDeltaTime());

        getBatch().end();
    }

    @Override
    public T call() throws Exception{
        return TaskLoading();
    }

    public abstract T TaskLoading() throws InterruptedException;
}
