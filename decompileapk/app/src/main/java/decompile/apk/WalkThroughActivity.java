package decompile.apk;

import Extra.PrefManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;

public class WalkThroughActivity extends AppCompatActivity {
    private TextView[] dots;
    LinearLayout dotsLayout;
    private int[] layouts;
    MyViewPagerAdapter myViewPagerAdapter;
    PrefManager prefManager;
    TextView txtnext;
    TextView txtskip;
    ViewPager viewPager;
    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            WalkThroughActivity.this.addBottomDots(i);
            if (i == WalkThroughActivity.this.layouts.length - 2) {
                WalkThroughActivity.this.txtnext.setText(WalkThroughActivity.this.getString(R.string.next));
                WalkThroughActivity.this.txtskip.setText(WalkThroughActivity.this.getString(R.string.skip));
                return;
            }
            WalkThroughActivity.this.txtnext.setText(WalkThroughActivity.this.getString(R.string.start));
            WalkThroughActivity.this.txtskip.setText(WalkThroughActivity.this.getString(R.string.skip));
            WalkThroughActivity.this.txtskip.setVisibility(8);
        }
    };

    private class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = (LayoutInflater) WalkThroughActivity.this.getSystemService("layout_inflater");
            this.layoutInflater = layoutInflater;
            View inflate = layoutInflater.inflate(WalkThroughActivity.this.layouts[i], viewGroup, false);
            viewGroup.addView(inflate);
            return inflate;
        }

        public int getCount() {
            return WalkThroughActivity.this.layouts.length;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PrefManager prefManager = new PrefManager(this);
        this.prefManager = prefManager;
        prefManager.isFirstTimeLaunch();
        setContentView((int) R.layout.activity_walk_through);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        this.dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        this.txtskip = (TextView) findViewById(R.id.txtskip);
        this.txtnext = (TextView) findViewById(R.id.txtnext);
        this.txtskip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int access$000 = WalkThroughActivity.this.getItem(-1);
                if (access$000 > WalkThroughActivity.this.layouts.length) {
                    WalkThroughActivity.this.viewPager.setCurrentItem(access$000);
                } else {
                    WalkThroughActivity.this.launchHomeScreen();
                }
            }
        });
        this.txtnext.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int access$000 = WalkThroughActivity.this.getItem(1);
                if (access$000 < WalkThroughActivity.this.layouts.length) {
                    WalkThroughActivity.this.viewPager.setCurrentItem(access$000);
                    return;
                }
                WalkThroughActivity.this.startActivity(new Intent(WalkThroughActivity.this, LoginSignupActivity.class));
            }
        });
        this.layouts = new int[]{R.layout.slide_one, R.layout.slide_three, R.layout.slide_two};
        addBottomDots(0);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        this.myViewPagerAdapter = myViewPagerAdapter;
        this.viewPager.setAdapter(myViewPagerAdapter);
        this.viewPager.addOnPageChangeListener(this.viewPagerPageChangeListener);
    }

    private void addBottomDots(int i) {
        TextView[] textViewArr;
        this.dots = new TextView[this.layouts.length];
        int[] intArray = getResources().getIntArray(R.array.array_dot_active);
        int[] intArray2 = getResources().getIntArray(R.array.array_dot_inactive);
        this.dotsLayout.removeAllViews();
        int i2 = 0;
        while (true) {
            textViewArr = this.dots;
            if (i2 >= textViewArr.length) {
                break;
            }
            textViewArr[i2] = new TextView(this);
            this.dots[i2].setText(Html.fromHtml("&#8226;"));
            this.dots[i2].setTextSize(35.0f);
            this.dots[i2].setTextColor(intArray2[i]);
            this.dotsLayout.addView(this.dots[i2]);
            i2++;
        }
        if (textViewArr.length > 0) {
            textViewArr[i].setTextColor(intArray[i]);
        }
    }

    private int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        this.prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, LoginSignupActivity.class));
        finish();
    }
}
