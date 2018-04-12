package info.saniulahsan.omdbapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginactivity = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginactivity);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
