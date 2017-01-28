package com.waterdiscoveries.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.waterdiscoveries.game.infrastructure.CustomInputProcessor;
import com.waterdiscoveries.game.interfaces.IInputListener;
import com.waterdiscoveries.game.interfaces.ITaskListener;
import com.waterdiscoveries.game.interfaces.external.IExternalInterface;
import com.waterdiscoveries.game.screens.TestScreen;
import com.waterdiscoveries.game.stages.TestLoadedStage;
import com.waterdiscoveries.game.stages.TestStage;

import java.util.ArrayList;

public class WaterDiscoveries extends Game implements ITaskListener {
	public static final String TAG = WaterDiscoveries.class.getSimpleName();

	private Stage _currentStage;

	private IExternalInterface _external;


	private static WaterDiscoveries _instance;

	public static WaterDiscoveries getInstance(){
		return  _instance;
	}

	public IExternalInterface getExternal(){
		return _external;
	}

	public WaterDiscoveries(IExternalInterface external){
		_external = external;

		_instance = this;
	}

	public void setCurrentSage(Stage stage){
		_currentStage = stage;

		if(_currentStage instanceof IInputListener)
			CustomInputProcessor.getInstance().setInputListener((IInputListener)_currentStage);
		else
			CustomInputProcessor.getInstance().setInputListener(null);
	}



	@Override
	public void create () {
		_external.GetLogger().ShowMessage(TAG,"Class was created");

		_currentStage = new TestLoadedStage(this, "Test message");

		Gdx.input.setInputProcessor(CustomInputProcessor.getInstance());

		setCurrentSage(_currentStage);

		((TestLoadedStage)_currentStage).startLoad();
	}

	@Override
	public void render () {
		super.render();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_currentStage.act(Gdx.graphics.getDeltaTime());

		_currentStage.draw();
	}
	
	@Override
	public void dispose () {
		if(_currentStage != null) _currentStage.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);
		_external.GetLogger().ShowMessage(TAG, "w:" + width + "; h:" + height);
	}

	@Override
	public void TaskComlete(String tag, Object data, String message) {
		if(tag == null) return;

		_external.GetLogger().ShowMessage(tag, message);

		setCurrentSage(new TestStage());
	}
}
