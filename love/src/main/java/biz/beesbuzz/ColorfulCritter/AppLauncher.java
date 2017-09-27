package biz.beesbuzz.ColorfulCritter;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fluffy on 9/27/17.
 */

public class AppLauncher extends Service {
    private static final String LOG_TAG = "cc-AppLauncher";
    private static final String TARGET = "org.love2d.android";

    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    private final Runnable enforceAppRunning = new Runnable() {
        @Override
        public void run() {
            Log.d(LOG_TAG, "checking");

            SharedPreferences sp = CritterActivity.getPrefs(AppLauncher.this);
            if (!sp.getBoolean("running", false)) {
                Log.d(LOG_TAG, "launching");
                Intent intent = new Intent(AppLauncher.this, CritterActivity.class);
                startActivity(intent);
            } else {
                Log.d(LOG_TAG, "already running");
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate");

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(enforceAppRunning);
            }
        }, 0, 5000);

         listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                mHandler.post(enforceAppRunning);
            }
        };

        CritterActivity.getPrefs(this).registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
    }
}
