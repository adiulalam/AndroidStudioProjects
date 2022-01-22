package decompile.apk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.asharinfotech.muslimweddingcard.R;
import com.asharinfotech.muslimweddingcard.SplashScreenActivity;
import com.asharinfotech.muslimweddingcard.dialog.DateDialog;
import com.itextpdf.text.html.HtmlTags;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonalDetailsActivity extends AppCompatActivity {
    public String address_line1;
    public String address_line2;
    public String age;
    private Button back;
    public String blood_group;
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
    public String income_details;
    public EditText input_age;
    public String input_age1;
    public Spinner input_blood_group;
    public String input_blood_group1;
    public EditText input_caste;
    public String input_caste1;
    public Spinner input_complexion;
    public String input_complexion1;
    public EditText input_dob;
    public String input_dob1;
    public EditText input_full_name;
    public String input_full_name1;
    public Spinner input_height;
    public String input_height1;
    public EditText input_personal_contact_number;
    public String input_personal_contact_number1;
    public EditText input_personal_email;
    public String input_personal_email1;
    public EditText input_personal_hobbies;
    public String input_personal_hobbies1;
    public EditText input_religion;
    public String input_religion1;
    public String mangal;
    public String married_brothers;
    public String married_sisters;
    public String mother_name;
    public String mothers_occupation;
    private Button next;
    private Button next2;
    public String occupation_details;
    public String parents_contact;
    public String partner_expectations;
    public String personal_contact_number;
    public String personal_email;
    public String personal_hobbies;
    public String pincode;
    public String pob;
    public String reference_address;
    public String reference_contact_no;
    public String reference_name;
    public String religion;
    public SimpleDateFormat sdf2;
    public String state;
    public String tob;
    public String total_brothers;
    public String total_sisters;

    public PersonalDetailsActivity() {
        String str = "";
        this.full_name = str;
        this.caste = str;
        this.religion = str;
        this.dob = str;
        this.age = str;
        this.height = str;
        this.blood_group = str;
        this.complexion = str;
        this.personal_contact_number = str;
        this.personal_email = str;
        this.personal_hobbies = str;
        this.tob = str;
        this.pob = str;
        this.mangal = str;
        this.gotra = str;
        this.education_details = str;
        this.education_details_two = str;
        this.education_details_three = str;
        this.education_address = str;
        this.occupation_details = str;
        this.income_details = str;
        this.father_name = str;
        this.fathers_occupation = str;
        this.mother_name = str;
        this.mothers_occupation = str;
        this.grand_father_name = str;
        this.grand_mother_name = str;
        this.total_brothers = str;
        this.married_brothers = str;
        this.parents_contact = str;
        this.partner_expectations = str;
        this.address_line1 = str;
        this.address_line2 = str;
        this.district = str;
        this.state = str;
        this.pincode = str;
        this.reference_name = str;
        this.reference_contact_no = str;
        this.reference_address = str;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_personal_details);
        requestPermission();
        this.input_full_name = (EditText) findViewById(R.id.input_full_name);
        this.input_religion = (EditText) findViewById(R.id.input_religion);
        this.input_caste = (EditText) findViewById(R.id.input_caste);
        this.input_dob = (EditText) findViewById(R.id.input_dob);
        this.input_age = (EditText) findViewById(R.id.input_age);
        this.input_height = (Spinner) findViewById(R.id.input_height);
        this.input_blood_group = (Spinner) findViewById(R.id.input_blood_group);
        this.input_complexion = (Spinner) findViewById(R.id.input_complexion);
        this.input_personal_contact_number = (EditText) findViewById(R.id.input_personal_contact_number);
        this.input_personal_email = (EditText) findViewById(R.id.input_personal_email);
        this.input_personal_hobbies = (EditText) findViewById(R.id.input_personal_hobbies);
        Intent intent = getIntent();
        this.full_name = intent.getStringExtra("full_name");
        this.religion = intent.getStringExtra("religion");
        this.caste = intent.getStringExtra("caste");
        this.dob = intent.getStringExtra("dob");
        this.age = intent.getStringExtra("age");
        this.height = intent.getStringExtra(HtmlTags.HEIGHT);
        this.blood_group = intent.getStringExtra("blood_group");
        this.complexion = intent.getStringExtra("complexion");
        this.personal_contact_number = intent.getStringExtra("personal_contact_number");
        this.personal_email = intent.getStringExtra("personal_email");
        this.personal_hobbies = intent.getStringExtra("personal_hobbies");
        this.tob = intent.getStringExtra("tob");
        this.pob = intent.getStringExtra("pob");
        this.mangal = intent.getStringExtra("mangal");
        this.gotra = intent.getStringExtra("gotra");
        this.education_details = intent.getStringExtra("education_details");
        this.education_details_two = intent.getStringExtra("education_details_two");
        this.education_details_three = intent.getStringExtra("education_details_three");
        this.education_address = intent.getStringExtra("education_address");
        this.occupation_details = intent.getStringExtra("occupation_details");
        this.income_details = intent.getStringExtra("income_details");
        this.father_name = intent.getStringExtra("father_name");
        this.fathers_occupation = intent.getStringExtra("fathers_occupation");
        this.mother_name = intent.getStringExtra("mother_name");
        this.mothers_occupation = intent.getStringExtra("mothers_occupation");
        this.grand_father_name = intent.getStringExtra("grand_father_name");
        this.grand_mother_name = intent.getStringExtra("grand_mother_name");
        this.total_brothers = intent.getStringExtra("total_brothers");
        this.married_brothers = intent.getStringExtra("married_brothers");
        this.total_sisters = intent.getStringExtra("total_sisters");
        this.parents_contact = intent.getStringExtra("parents_contact");
        this.partner_expectations = intent.getStringExtra("partner_expectations");
        this.address_line1 = intent.getStringExtra("address_line1");
        this.address_line2 = intent.getStringExtra("address_line2");
        this.district = intent.getStringExtra("district");
        this.state = intent.getStringExtra("state");
        this.pincode = intent.getStringExtra("pincode");
        this.reference_name = intent.getStringExtra("reference_name");
        this.reference_contact_no = intent.getStringExtra("reference_contact_no");
        this.reference_address = intent.getStringExtra("reference_address");
        this.input_full_name.setText(this.full_name);
        this.input_religion.setText(this.religion);
        this.input_caste.setText(this.caste);
        this.input_dob.setText(this.dob);
        this.input_age.setText(this.age);
        this.input_personal_contact_number.setText(this.personal_contact_number);
        this.input_personal_email.setText(this.personal_email);
        this.input_personal_hobbies.setText(this.personal_hobbies);
        ArrayAdapter createFromResource = ArrayAdapter.createFromResource(this, R.array.height, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.input_height.setAdapter(createFromResource);
        String str = this.height;
        if (str != null) {
            this.input_height.setSelection(createFromResource.getPosition(str));
        }
        createFromResource = ArrayAdapter.createFromResource(this, R.array.blood_group, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.input_blood_group.setAdapter(createFromResource);
        str = this.blood_group;
        if (str != null) {
            this.input_blood_group.setSelection(createFromResource.getPosition(str));
        }
        createFromResource = ArrayAdapter.createFromResource(this, R.array.complexion, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.input_complexion.setAdapter(createFromResource);
        String str2 = this.complexion;
        if (str2 != null) {
            this.input_complexion.setSelection(createFromResource.getPosition(str2));
        }
        this.input_dob.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DateDialog(view).show(PersonalDetailsActivity.this.getFragmentManager().beginTransaction(), "DatePicker");
            }
        });
        Button button = (Button) findViewById(R.id.back);
        this.back = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersonalDetailsActivity.this.finishAffinity();
                PersonalDetailsActivity.this.startActivity(new Intent(PersonalDetailsActivity.this.getApplicationContext(), SplashScreenActivity.class));
            }
        });
        button = (Button) findViewById(R.id.next);
        this.next = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersonalDetailsActivity.this.personalDetails();
            }
        });
        button = (Button) findViewById(R.id.next2);
        this.next2 = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PersonalDetailsActivity.this.personalDetails();
            }
        });
    }

    public void ageCalculator() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(simpleDateFormat.parse(this.input_dob.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String age = getAge(instance.get(1), instance.get(2), instance.get(5));
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Age :");
        stringBuilder.append(age);
        printStream.println(stringBuilder.toString());
        this.input_age.setText(age);
    }

    public void personalDetails() {
        this.input_full_name1 = this.input_full_name.getText().toString();
        this.input_religion1 = this.input_religion.getText().toString();
        this.input_caste1 = this.input_caste.getText().toString();
        this.input_dob1 = this.input_dob.getText().toString();
        this.input_age1 = this.input_age.getText().toString();
        this.input_height1 = this.input_height.getSelectedItem().toString();
        this.input_blood_group1 = this.input_blood_group.getSelectedItem().toString();
        this.input_complexion1 = this.input_complexion.getSelectedItem().toString();
        this.input_personal_contact_number1 = this.input_personal_contact_number.getText().toString();
        this.input_personal_email1 = this.input_personal_email.getText().toString();
        this.input_personal_hobbies1 = this.input_personal_hobbies.getText().toString();
        Intent intent = new Intent(getApplicationContext(), EducationDetailsActivity.class);
        intent.putExtra("full_name", this.input_full_name1);
        intent.putExtra("religion", this.input_religion1);
        intent.putExtra("caste", this.input_caste1);
        intent.putExtra("dob", this.input_dob1);
        intent.putExtra("age", this.input_age1);
        intent.putExtra(HtmlTags.HEIGHT, this.input_height1);
        intent.putExtra("blood_group", this.input_blood_group1);
        intent.putExtra("complexion", this.input_complexion1);
        intent.putExtra("personal_contact_number", this.input_personal_contact_number1);
        intent.putExtra("personal_email", this.input_personal_email1);
        intent.putExtra("personal_hobbies", this.input_personal_hobbies1);
        intent.putExtra("tob", this.tob);
        intent.putExtra("pob", this.pob);
        intent.putExtra("mangal", this.mangal);
        intent.putExtra("gotra", this.gotra);
        intent.putExtra("education_details", this.education_details);
        intent.putExtra("education_details_two", this.education_details_two);
        intent.putExtra("education_details_three", this.education_details_three);
        intent.putExtra("education_address", this.education_address);
        intent.putExtra("occupation_details", this.occupation_details);
        intent.putExtra("income_details", this.income_details);
        intent.putExtra("father_name", this.father_name);
        intent.putExtra("fathers_occupation", this.fathers_occupation);
        intent.putExtra("mother_name", this.mother_name);
        intent.putExtra("mothers_occupation", this.mothers_occupation);
        intent.putExtra("grand_father_name", this.grand_father_name);
        intent.putExtra("grand_mother_name", this.grand_mother_name);
        intent.putExtra("total_brothers", this.total_brothers);
        intent.putExtra("married_brothers", this.married_brothers);
        intent.putExtra("total_sisters", this.total_sisters);
        intent.putExtra("parents_contact", this.parents_contact);
        intent.putExtra("partner_expectations", this.partner_expectations);
        intent.putExtra("address_line1", this.address_line1);
        intent.putExtra("address_line2", this.address_line2);
        intent.putExtra("district", this.district);
        intent.putExtra("state", this.state);
        intent.putExtra("pincode", this.pincode);
        intent.putExtra("reference_name", this.reference_name);
        intent.putExtra("reference_contact_no", this.reference_contact_no);
        intent.putExtra("reference_address", this.reference_address);
        startActivity(intent);
    }

    private void requestPermission() {
        String str = "android.permission.WRITE_EXTERNAL_STORAGE";
        if (ContextCompat.checkSelfPermission(this, str) != 0) {
            ActivityCompat.requestPermissions(this, new String[]{str}, 1);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr[0] == 0) {
            Toast.makeText(this, "Permission granted now you can read the storage", 1).show();
        }
    }

    public static String getAge(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.set(i, i2, i3);
        i2 = instance2.get(2);
        i = instance.get(2);
        int i4 = instance2.get(1) - instance.get(1);
        if (i > i2 || (i == i2 && instance.get(5) > instance2.get(5))) {
            i4--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i4);
        stringBuilder.append("");
        return stringBuilder.toString();
    }
}
