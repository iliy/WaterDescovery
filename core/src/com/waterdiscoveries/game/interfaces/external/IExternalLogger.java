package com.waterdiscoveries.game.interfaces.external;

/**
 * Created by r2d2 on 24.01.17.
 */

public interface IExternalLogger {
    void ShowMessage(String tag, String message);
    void ShowError(String tag, String message);
}
