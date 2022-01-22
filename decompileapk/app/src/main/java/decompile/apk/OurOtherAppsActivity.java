package decompile.apk;

import Adapter.OtherAppAdapter;
import Model.OtherAppModel;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class OurOtherAppsActivity extends AppCompatActivity {
    OtherAppAdapter otherAppAdapter;
    List<OtherAppModel> otherAppModelList = new ArrayList();
    RecyclerView recyclerView;
    Timer timer;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_our_other_apps);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        this.otherAppModelList.add(new OtherAppModel("Basic Marriage Biodata Maker", R.drawable.basic_logo));
        this.otherAppModelList.add(new OtherAppModel("Muslim Marriage Biodata Maker", R.drawable.muslim_biodata));
        this.otherAppModelList.add(new OtherAppModel("Catalog Maker", R.drawable.catalog_maker));
        this.otherAppModelList.add(new OtherAppModel("Invoice Maker", R.drawable.invoice_maker));
        this.otherAppModelList.add(new OtherAppModel("Medicine Stockist", R.drawable.medicine_stockist));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OtherAppAdapter otherAppAdapter = new OtherAppAdapter(this.otherAppModelList);
        this.otherAppAdapter = otherAppAdapter;
        this.recyclerView.setAdapter(otherAppAdapter);
        this.otherAppAdapter.notifyDataSetChanged();
    }

    private void refreshAd() {
        System.out.println("native_ADS_refreshed..............");
    }
}
