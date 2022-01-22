package decompile.apk.activity.card.reception;

import Adapter.ReceptionCardCategoryRecycleAdapter;
import ModelClass.CardCategoryModelClass;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.asharinfotech.muslimweddingcard.R;
import java.util.ArrayList;

public class ReceptionCardSelectActivity extends AppCompatActivity {
    public String address_line1;
    public String address_line2;
    public String age;
    public String blood_group;
    private ArrayList<CardCategoryModelClass> cardCategoryModelClass;
    public String caste;
    public String complexion;
    public String district;
    public String dob;
    public String education_address;
    public String education_details;
    public String education_details_three;
    public String education_details_two;
    public String father_name;
    public String fathers_occupation;
    public String full_name;
    public String gotra;
    public String grand_father_name;
    public String grand_mother_name;
    public String height;
    private Integer[] image = new Integer[]{Integer.valueOf(R.drawable.reception_card_one_preview), Integer.valueOf(R.drawable.reception_card_two_preview), Integer.valueOf(R.drawable.reception_card_three_preview), Integer.valueOf(R.drawable.reception_card_four_preview), Integer.valueOf(R.drawable.reception_card_five_preview), Integer.valueOf(R.drawable.reception_card_six_preview)};
    public String imagepath;
    public String income_details;
    public String mangal;
    public String married_brothers;
    public String married_sisters;
    public String mother_name;
    public String mothers_occupation;
    public String occupation_details;
    public String parents_contact;
    public String partner_expectations;
    public String personal_contact_number;
    public String personal_email;
    public String personal_hobbies;
    public String pincode;
    public String pob;
    private ReceptionCardCategoryRecycleAdapter receptionCardCategoryRecycleAdapter;
    private RecyclerView recyclerView;
    public String reference_address;
    public String reference_contact_no;
    public String reference_name;
    public String religion;
    public String state;
    private String[] title = new String[]{"Card One", "Card Two", "Card Three", "Card Four", "Card Five", "Card Six"};
    public String tob;
    public String total_brothers;
    public String total_sisters;

    public ReceptionCardSelectActivity() {
        String str = "";
        this.education_details_two = str;
        this.education_details_three = str;
        this.father_name = str;
        this.fathers_occupation = str;
        this.mother_name = str;
        this.mothers_occupation = str;
        this.grand_father_name = str;
        this.grand_mother_name = str;
        this.total_brothers = str;
        this.married_brothers = str;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_reception_card_select);
        this.recyclerView = (RecyclerView) findViewById(R.id.CategoryRecyclerview);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.cardCategoryModelClass = new ArrayList();
        int i = 0;
        while (true) {
            Integer[] numArr = this.image;
            if (i < numArr.length) {
                this.cardCategoryModelClass.add(new CardCategoryModelClass(numArr[i], this.title[i]));
                i++;
            } else {
                ReceptionCardCategoryRecycleAdapter receptionCardCategoryRecycleAdapter = new ReceptionCardCategoryRecycleAdapter(getApplicationContext(), this.cardCategoryModelClass);
                this.receptionCardCategoryRecycleAdapter = receptionCardCategoryRecycleAdapter;
                this.recyclerView.setAdapter(receptionCardCategoryRecycleAdapter);
                return;
            }
        }
    }
}
