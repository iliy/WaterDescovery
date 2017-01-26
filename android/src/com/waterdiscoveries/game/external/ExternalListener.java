package com.waterdiscoveries.game.external;

import com.waterdiscoveries.game.interfaces.external.IExternalInterface;
import com.waterdiscoveries.game.interfaces.external.IExternalLogger;

/**
 * Created by r2d2 on 24.01.17.
 */

public class ExternalListener implements IExternalInterface {
    private IExternalLogger _logger = new ExternalLogger();
    @Override
    public IExternalLogger GetLogger() {
        return _logger;
    }
}
