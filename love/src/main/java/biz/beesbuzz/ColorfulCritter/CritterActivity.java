package biz.beesbuzz.ColorfulCritter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CritterActivity extends org.love2d.android.GameActivity {
        public static String LOG_TAG = "cc.CritterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRunning(true);

        Log.d(LOG_TAG, "onCreate");

        // apparently doesn't work on KF8 :(
        setImmersiveMode(true);

        // start up the background checker thing
        startService(new Intent(this, AppLauncher.class));
    }

    @Override
    public void onResume() {
        super.onResume();

        setRunning(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

        setRunning(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        setRunning(false);
    }

    static SharedPreferences getPrefs(Context ctx) {
        return ctx.getSharedPreferences("running", MODE_PRIVATE);
    }

    void setRunning(boolean val) {
        Log.d(LOG_TAG, "Setting running state to " + val);
        SharedPreferences.Editor edit = getPrefs(this).edit();
        edit.putBoolean("running", val);
        edit.commit();
    }
}
