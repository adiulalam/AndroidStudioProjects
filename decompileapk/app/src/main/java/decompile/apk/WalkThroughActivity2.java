package decompile.apk;

import Adapter.WalkThroughPagerAdapter;
import Constant.Constant;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.asharinfotech.muslimweddingcard.library.DatabaseHandler;
import me.relex.circleindicator.CircleIndicator;

public class WalkThroughActivity2 extends AppCompatActivity {
    String device_unique_id;
    TextView txtnext;
    TextView txtskip;
    private ViewPager viewPager;
    private PagerAdapter walkThroughPagerAdapter;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_walk_through2);
        this.device_unique_id = Secure.getString(getContentResolver(), "android_id");
        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        if (databaseHandler.getContactsCount() >= 1) {
            databaseHandler.getUserData();
            String str = databaseHandler.full_name;
            String str2 = databaseHandler.mobile;
            String str3 = databaseHandler.email;
            String str4 = databaseHandler.country;
            String str5 = databaseHandler.state;
            String str6 = databaseHandler.pincode;
            Constant.login_country = str4;
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("full_name", str);
            intent.putExtra("mobile", str2);
            intent.putExtra(NotificationCompat.CATEGORY_EMAIL, str3);
            intent.putExtra("country", str4);
            intent.putExtra("state", str5);
            intent.putExtra("pincode", str6);
            intent.putExtra("device_id", this.device_unique_id);
            startActivityForResult(intent, 0);
        }
        databaseHandler.close();
        this.txtskip = (TextView) findViewById(R.id.txtskip);
        this.txtnext = (TextView) findViewById(R.id.txtnext);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.indicator);
        WalkThroughPagerAdapter walkThroughPagerAdapter = new WalkThroughPagerAdapter(getSupportFragmentManager());
        this.walkThroughPagerAdapter = walkThroughPagerAdapter;
        this.viewPager.setAdapter(walkThroughPagerAdapter);
        circleIndicator.setViewPager(this.viewPager);
        this.walkThroughPagerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        this.viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (WalkThroughActivity2.this.viewPager.getCurrentItem() == 2) {
                    WalkThroughActivity2.this.txtskip.setVisibility(8);
                    WalkThroughActivity2.this.txtnext.setText("Get Started");
                    return;
                }
                WalkThroughActivity2.this.txtskip.setVisibility(0);
                WalkThroughActivity2.this.txtnext.setText("Next");
            }
        });
        this.txtskip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DatabaseHandler databaseHandler = new DatabaseHandler(WalkThroughActivity2.this.getApplicationContext());
                if (databaseHandler.getContactsCount() < 1) {
                    WalkThroughActivity2.this.startActivity(new Intent(WalkThroughActivity2.this, LoginSignupActivity.class));
                } else {
                    databaseHandler.getUserData();
                    String str = databaseHandler.full_name;
                    String str2 = databaseHandler.mobile;
                    String str3 = databaseHandler.email;
                    String str4 = databaseHandler.country;
                    String str5 = databaseHandler.state;
                    String str6 = databaseHandler.pincode;
                    Constant.login_country = str4;
                    Intent intent = new Intent(WalkThroughActivity2.this, HomePageActivity.class);
                    intent.putExtra("full_name", str);
                    intent.putExtra("mobile", str2);
                    intent.putExtra(NotificationCompat.CATEGORY_EMAIL, str3);
                    intent.putExtra("country", str4);
                    intent.putExtra("state", str5);
                    intent.putExtra("pincode", str6);
                    intent.putExtra("device_id", WalkThroughActivity2.this.device_unique_id);
                    WalkThroughActivity2.this.startActivityForResult(intent, 0);
                }
                databaseHandler.close();
            }
        });
        this.txtnext.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (WalkThroughActivity2.this.viewPager.getCurrentItem() != 2) {
                    WalkThroughActivity2.this.viewPager.setCurrentItem(WalkThroughActivity2.this.viewPager.getCurrentItem() + 1);
                    return;
                }
                WalkThroughActivity2.this.startActivity(new Intent(WalkThroughActivity2.this, LoginSignupActivity.class));
            }
        });
    }
}
