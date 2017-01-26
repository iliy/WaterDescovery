package com.waterdiscoveries.game.external;

import android.util.Log;

import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 24.01.17.
 */

public class ExternalLogger implements IExternalLogger {


    @Override
    public void ShowMessage(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void ShowError(String tag, String message) {
        Log.e(tag, message);
    }
}
