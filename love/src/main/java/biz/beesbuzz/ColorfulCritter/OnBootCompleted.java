package biz.beesbuzz.ColorfulCritter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by fluffy on 9/27/17.
 */

public class OnBootCompleted extends BroadcastReceiver {
    private static final String LOG_TAG = "cc-OnBootCompleted";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "Received onBootCompleted message");
        context.startService(new Intent(context, AppLauncher.class));
    }


}
