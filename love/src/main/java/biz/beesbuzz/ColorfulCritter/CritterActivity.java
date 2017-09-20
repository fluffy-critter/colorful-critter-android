package biz.beesbuzz.ColorfulCritter;

import android.os.Bundle;

public class CritterActivity extends org.love2d.android.GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersiveMode(true);
    }
}
