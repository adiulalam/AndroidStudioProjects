package decompile.apk.activity;

import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.asharinfotech.muslimweddingcard.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EducationDetailsActivity extends AppCompatActivity {
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
    public EditText input_education_address;
    public String input_education_address1;
    public EditText input_education_details;
    public String input_education_details1;
    public EditText input_education_details_three;
    public String input_education_details_three1;
    public EditText input_education_details_two;
    public String input_education_details_two1;
    public EditText input_income_details;
    public String input_income_details1;
    public EditText input_occupation_details;
    public String input_occupation_details1;
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
    public String state;
    public String tob;
    public String total_brothers;
    public String total_sisters;

    public void onBackPressed() {
    }

    public EducationDetailsActivity() {
        String str = "";
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
        setContentView((int) R.layout.activity_education_details);
        this.input_education_details = (EditText) findViewById(R.id.input_education_details);
        this.input_education_details_two = (EditText) findViewById(R.id.input_education_details_two);
        this.input_education_details_three = (EditText) findViewById(R.id.input_education_details_three);
        this.input_education_address = (EditText) findViewById(R.id.input_education_address);
        this.input_occupation_details = (EditText) findViewById(R.id.input_occupation_details);
        this.input_income_details = (EditText) findViewById(R.id.input_income_details);
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
        this.input_education_details.setText(this.education_details);
        this.input_education_details_two.setText(this.education_details_two);
        this.input_education_details_three.setText(this.education_details_three);
        this.input_occupation_details.setText(this.occupation_details);
        this.input_income_details.setText(this.income_details);
        Button button = (Button) findViewById(R.id.back);
        this.back = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EducationDetailsActivity.this.backData();
                EducationDetailsActivity.this.finish();
            }
        });
        button = (Button) findViewById(R.id.next);
        this.next = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EducationDetailsActivity.this.horoscopeDetails();
            }
        });
        button = (Button) findViewById(R.id.next2);
        this.next2 = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EducationDetailsActivity.this.horoscopeDetails();
            }
        });
    }

    public void horoscopeDetails() {
        this.input_education_details1 = this.input_education_details.getText().toString();
        this.input_education_details_two1 = this.input_education_details_two.getText().toString();
        this.input_education_details_three1 = this.input_education_details_three.getText().toString();
        this.input_education_address1 = this.input_education_address.getText().toString();
        this.input_occupation_details1 = this.input_occupation_details.getText().toString();
        this.input_income_details1 = this.input_income_details.getText().toString();
        Intent intent = new Intent(getApplicationContext(), FamilyDetailsActivity.class);
        intent.putExtra("full_name", this.full_name);
        intent.putExtra("religion", this.religion);
        intent.putExtra("caste", this.caste);
        intent.putExtra("dob", this.dob);
        intent.putExtra("age", this.age);
        intent.putExtra(HtmlTags.HEIGHT, this.height);
        intent.putExtra("blood_group", this.blood_group);
        intent.putExtra("complexion", this.complexion);
        intent.putExtra("personal_contact_number", this.personal_contact_number);
        intent.putExtra("personal_email", this.personal_email);
        intent.putExtra("personal_hobbies", this.personal_hobbies);
        intent.putExtra("tob", this.tob);
        intent.putExtra("pob", this.pob);
        intent.putExtra("mangal", this.mangal);
        intent.putExtra("gotra", this.gotra);
        intent.putExtra("education_details", this.input_education_details1);
        intent.putExtra("education_details_two", this.input_education_details_two1);
        intent.putExtra("education_details_three", this.input_education_details_three1);
        intent.putExtra("education_address", this.input_education_address1);
        intent.putExtra("occupation_details", this.input_occupation_details1);
        intent.putExtra("income_details", this.input_income_details1);
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

    public void backData() {
        this.input_education_details1 = this.input_education_details.getText().toString();
        this.input_education_details_two1 = this.input_education_details_two.getText().toString();
        this.input_education_details_three1 = this.input_education_details_three.getText().toString();
        this.input_education_address1 = this.input_education_address.getText().toString();
        this.input_occupation_details1 = this.input_occupation_details.getText().toString();
        this.input_income_details1 = this.input_income_details.getText().toString();
        Intent intent = new Intent(getApplicationContext(), PersonalDetailsActivity.class);
        intent.putExtra("full_name", this.full_name);
        intent.putExtra("religion", this.religion);
        intent.putExtra("caste", this.caste);
        intent.putExtra("dob", this.dob);
        intent.putExtra("age", this.age);
        intent.putExtra(HtmlTags.HEIGHT, this.height);
        intent.putExtra("blood_group", this.blood_group);
        intent.putExtra("complexion", this.complexion);
        intent.putExtra("personal_contact_number", this.personal_contact_number);
        intent.putExtra("personal_email", this.personal_email);
        intent.putExtra("personal_hobbies", this.personal_hobbies);
        intent.putExtra("tob", this.tob);
        intent.putExtra("pob", this.pob);
        intent.putExtra("mangal", this.mangal);
        intent.putExtra("gotra", this.gotra);
        intent.putExtra("education_details", this.input_education_details1);
        intent.putExtra("education_details_two", this.input_education_details_two1);
        intent.putExtra("education_details_three", this.input_education_details_three1);
        intent.putExtra("education_address", this.input_education_address1);
        intent.putExtra("occupation_details", this.input_occupation_details1);
        intent.putExtra("income_details", this.input_income_details1);
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

    private void createPdf() {
        StringBuilder stringBuilder;
        String str = "PDFCreator";
        Document document = new Document();
        try {
            String file = Environment.getExternalStorageDirectory().toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file);
            stringBuilder2.append("/resumepdf");
            File file2 = new File(stringBuilder2.toString());
            file2.mkdirs();
            PdfWriter.getInstance(document, new FileOutputStream(new File(file2, "sample.pdf")));
            document.open();
            Paragraph paragraph = new Paragraph("Sample PDF CREATION USING IText");
            Font font = new Font(FontFamily.COURIER);
            paragraph.setAlignment(1);
            paragraph.setFont(font);
            document.add(paragraph);
            paragraph = new Paragraph("This is an example of a simple paragraph");
            font = new Font(FontFamily.COURIER, 14.0f, 0, CMYKColor.GREEN);
            paragraph.setAlignment(1);
            paragraph.setFont(font);
            document.add(paragraph);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher).compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            Image instance = Image.getInstance(byteArrayOutputStream.toByteArray());
            instance.setAlignment(1);
            document.add(instance);
        } catch (DocumentException e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("DocumentException:");
            stringBuilder.append(e);
            Log.e(str, stringBuilder.toString());
        } catch (IOException e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ioException:");
            stringBuilder.append(e2);
            Log.e(str, stringBuilder.toString());
        } catch (Throwable th) {
            document.close();
        }
        document.close();
    }
}
