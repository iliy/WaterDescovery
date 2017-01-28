package com.waterdiscoveries.game.stages;

import com.waterdiscoveries.game.interfaces.ITaskListener;
import com.waterdiscoveries.game.stages.abstr.ALoadStage;

/**
 * Created by nobos on 27.01.2017.
 */

public class TestLoadedStage extends ALoadStage<String, String> {
    public static final String TAG = TestLoadedStage.class.getSimpleName();

    public TestLoadedStage(ITaskListener listener, String data) {
        super(listener, data);
    }

    public String getTag(){ return  TAG; }

    @Override
    public String TaskLoading() throws InterruptedException {
//        Thread.sleep(5000);

        String result = "Youre message: " + _data + "; Message length: " + _data.length();

        return result;
    }
}
