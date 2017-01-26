package com.waterdiscoveries.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.waterdiscoveries.game.WaterDiscoveries;
import com.waterdiscoveries.game.external.ExternalListener;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new WaterDiscoveries(new ExternalListener()), config);
	}
}
