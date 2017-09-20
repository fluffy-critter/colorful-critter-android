package biz.beesbuzz.ColorfulCritter;

import android.os.Bundle;

/**
 * Created by fluffy on 9/20/17.
 */

public class CritterActivity extends org.love2d.android.GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersiveMode(true);
    }
}
