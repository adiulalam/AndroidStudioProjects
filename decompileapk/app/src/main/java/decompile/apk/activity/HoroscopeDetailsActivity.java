package decompile.apk.activity;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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
import java.util.Calendar;

public class HoroscopeDetailsActivity extends AppCompatActivity {
    public String age;
    private Button back;
    public String blood_group;
    public String caste;
    public String complexion;
    public String dob;
    public String full_name;
    public String gotra;
    public String height;
    public EditText input_kuldevak_gotra;
    public String input_kuldevak_gotra1;
    public Spinner input_mangal;
    public String input_mangal1;
    public EditText input_place_of_birth;
    public String input_place_of_birth1;
    public EditText input_time_of_birth;
    public String input_time_of_birth1;
    public String mangal;
    private Button next;
    private Button next2;
    public String personal_contact_number;
    public String personal_email;
    public String pob;
    public String religion;
    public String tob;

    public void onBackPressed() {
    }

    public HoroscopeDetailsActivity() {
        String str = "";
        this.tob = str;
        this.pob = str;
        this.mangal = str;
        this.gotra = str;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_horoscope_details);
        this.input_time_of_birth = (EditText) findViewById(R.id.input_time_of_birth);
        this.input_place_of_birth = (EditText) findViewById(R.id.input_place_of_birth);
        this.input_mangal = (Spinner) findViewById(R.id.input_mangal);
        this.input_kuldevak_gotra = (EditText) findViewById(R.id.input_kuldevak_gotra);
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
        this.tob = intent.getStringExtra("tob");
        this.pob = intent.getStringExtra("pob");
        this.mangal = intent.getStringExtra("mangal");
        this.gotra = intent.getStringExtra("gotra");
        this.input_time_of_birth.setText(this.tob);
        this.input_place_of_birth.setText(this.pob);
        this.input_time_of_birth.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Calendar instance = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(HoroscopeDetailsActivity.this, new OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        EditText editText = HoroscopeDetailsActivity.this.input_time_of_birth;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(i);
                        stringBuilder.append(":");
                        stringBuilder.append(i2);
                        editText.setText(stringBuilder.toString());
                    }
                }, instance.get(11), instance.get(12), true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        ArrayAdapter createFromResource = ArrayAdapter.createFromResource(this, R.array.yes_no, 17367048);
        createFromResource.setDropDownViewResource(17367049);
        this.input_mangal.setAdapter(createFromResource);
        String str = this.mangal;
        if (str != null) {
            this.input_mangal.setSelection(createFromResource.getPosition(str));
        }
        this.input_kuldevak_gotra.setText(this.gotra);
        Button button = (Button) findViewById(R.id.back);
        this.back = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HoroscopeDetailsActivity.this.backData();
                HoroscopeDetailsActivity.this.finish();
            }
        });
        button = (Button) findViewById(R.id.next);
        this.next = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HoroscopeDetailsActivity.this.horoscopeDetails();
            }
        });
        button = (Button) findViewById(R.id.next2);
        this.next2 = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HoroscopeDetailsActivity.this.horoscopeDetails();
            }
        });
    }

    public void horoscopeDetails() {
        this.input_time_of_birth1 = this.input_time_of_birth.getText().toString();
        this.input_place_of_birth1 = this.input_place_of_birth.getText().toString();
        this.input_mangal1 = this.input_mangal.getSelectedItem().toString();
        this.input_kuldevak_gotra1 = this.input_kuldevak_gotra.getText().toString();
        Intent intent = new Intent(getApplicationContext(), EducationDetailsActivity.class);
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
        intent.putExtra("tob", this.input_time_of_birth1);
        intent.putExtra("pob", this.input_place_of_birth1);
        intent.putExtra("mangal", this.input_mangal1);
        intent.putExtra("gotra", this.input_kuldevak_gotra1);
        startActivity(intent);
    }

    public void backData() {
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
