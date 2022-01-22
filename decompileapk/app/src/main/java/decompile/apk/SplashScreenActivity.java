package decompile.apk;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_splash_screen);
        new Thread() {
            public void run() {
                try {
                    AnonymousClass1.sleep(2000);
                    SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, WalkThroughActivity2.class));
                    SplashScreenActivity.this.finish();
                } catch (Exception unused) {
                }
            }
        }.start();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
