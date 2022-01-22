package decompile.apk;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import fragment.HomeFragment;
import fragment.ProfileFragment;
import java.lang.reflect.Field;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    LinearLayout city_linear;
    String firstKeyName0;
    String firstKeyName1;
    String firstKeyName2;
    String firstKeyName3;
    String firstKeyName4;
    String firstKeyName5;
    FrameLayout frameLayout;
    LinearLayout linear;
    protected OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            String str = "Feature will be added soon...";
            switch (menuItem.getItemId()) {
                case R.id.navigation_cart /*2131362126*/:
                    Toast.makeText(((BottomNavigationMenuView) ((BottomNavigationView) HomePageActivity.this.findViewById(R.id.navigation)).getChildAt(0)).getContext(), str, 0).show();
                    return true;
                case R.id.navigation_exit /*2131362127*/:
                    HomePageActivity.this.showAlert();
                    return true;
                case R.id.navigation_gifts /*2131362128*/:
                    Toast.makeText(((BottomNavigationMenuView) ((BottomNavigationView) HomePageActivity.this.findViewById(R.id.navigation)).getChildAt(0)).getContext(), str, 0).show();
                    return true;
                case R.id.navigation_profile /*2131362130*/:
                    HomePageActivity.this.city_linear.setVisibility(8);
                    HomePageActivity.this.title.setText("Profile");
                    Bundle bundle = new Bundle();
                    bundle.putString("full_name", HomePageActivity.this.firstKeyName0);
                    bundle.putString("mobile", HomePageActivity.this.firstKeyName1);
                    bundle.putString(NotificationCompat.CATEGORY_EMAIL, HomePageActivity.this.firstKeyName2);
                    bundle.putString("country", HomePageActivity.this.firstKeyName3);
                    bundle.putString("state", HomePageActivity.this.firstKeyName4);
                    bundle.putString("pincode", HomePageActivity.this.firstKeyName5);
                    ProfileFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    HomePageActivity.this.loadFragment(profileFragment);
                    return true;
                case R.id.navigation_shop /*2131362131*/:
                    HomePageActivity.this.city_linear.setVisibility(0);
                    HomePageActivity.this.title.setText("");
                    HomePageActivity.this.loadFragment(new HomeFragment());
                    return true;
                default:
                    return false;
            }
        }
    };
    TextView title;

    public static class BottomNavigationViewHelper {
        public static void disableShiftMode(BottomNavigationView bottomNavigationView) {
            String str = "BNVHelper";
            int i = 0;
            BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
            try {
                Field declaredField = bottomNavigationMenuView.getClass().getDeclaredField("mShiftingMode");
                declaredField.setAccessible(true);
                declaredField.setBoolean(bottomNavigationMenuView, false);
                declaredField.setAccessible(false);
                while (i < bottomNavigationMenuView.getChildCount()) {
                    BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) bottomNavigationMenuView.getChildAt(i);
                    bottomNavigationItemView.setChecked(bottomNavigationItemView.getItemData().isChecked());
                    i++;
                }
            } catch (NoSuchFieldException e) {
                Log.e(str, "Unable to get shift mode field", e);
            } catch (IllegalAccessException e2) {
                Log.e(str, "Unable to change value of shift mode", e2);
            }
        }
    }

    public HomePageActivity() {
        String str = "";
        this.firstKeyName0 = str;
        this.firstKeyName1 = str;
        this.firstKeyName2 = str;
        this.firstKeyName3 = str;
        this.firstKeyName4 = str;
        this.firstKeyName5 = str;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_home_page);
        Intent intent = getIntent();
        this.firstKeyName0 = intent.getStringExtra("full_name");
        this.firstKeyName1 = intent.getStringExtra("mobile");
        this.firstKeyName2 = intent.getStringExtra(NotificationCompat.CATEGORY_EMAIL);
        this.firstKeyName3 = intent.getStringExtra("country");
        this.firstKeyName4 = intent.getStringExtra("state");
        this.firstKeyName5 = intent.getStringExtra("pincode");
        this.city_linear = (LinearLayout) findViewById(R.id.city_linear);
        this.title = (TextView) findViewById(R.id.title);
        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        int i = 0;
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        while (i < bottomNavigationMenuView.getChildCount()) {
            View findViewById = bottomNavigationMenuView.getChildAt(i).findViewById(R.id.icon);
            LayoutParams layoutParams = findViewById.getLayoutParams();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(1, 18.0f, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(1, 18.0f, displayMetrics);
            findViewById.setLayoutParams(layoutParams);
            i++;
        }
        Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.ACCESS_NETWORK_STATE").withListener(new MultiplePermissionsListener() {
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
            }

            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(HomePageActivity.this.getApplicationContext(), "Permission Granted", 0).show();
            }
        }).check();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.framelayout, fragment);
        beginTransaction.commit();
    }

    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    public void showAlert() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.common_dialog2);
        Button button = (Button) dialog.findViewById(R.id.okButton);
        ((Button) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(HomePageActivity.this.getApplicationContext(), "Dismissed..!!", 0).show();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HomePageActivity.this.finishAffinity();
            }
        });
        dialog.show();
    }
}
