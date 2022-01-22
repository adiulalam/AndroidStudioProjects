package decompile.apk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.asharinfotech.muslimweddingcard.activity.card.wedding.WeddingCardSelectActivity;

public class MenuActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_menu);
        findViewById(R.id.wedding_ceremony).setOnClickListener(new -$$Lambda$MenuActivity$4WwUwP2fXGdnr8qPshUo4n18d7c(this));
        findViewById(R.id.wedding_ceremony).setOnClickListener(new -$$Lambda$MenuActivity$aCXAlv58Of8LhjqGF2E-rsXEI0g(this));
    }

    public /* synthetic */ void lambda$onCreate$0$MenuActivity(View view) {
        startActivity(new Intent(getApplicationContext(), WeddingCardSelectActivity.class));
    }

    public /* synthetic */ void lambda$onCreate$1$MenuActivity(View view) {
        startActivity(new Intent(getApplicationContext(), WeddingCardSelectActivity.class));
    }
}
