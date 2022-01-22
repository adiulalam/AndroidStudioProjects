package decompile.apk.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import com.theartofdev.edmodo.cropper.CropImageView.Guidelines;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileImageActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    public String address_line1_1;
    public String address_line2_1;
    public String age;
    private Button back;
    public String blood_group;
    Button button;
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
    Uri imageUri;
    ImageView imageView;
    public String income_details;
    public EditText input_address_line1;
    public String input_address_line1_1;
    public EditText input_address_line2;
    public String input_address_line2_1;
    public EditText input_district;
    public String input_district1;
    public EditText input_pincode;
    public String input_pincode1;
    public Spinner input_state;
    public String input_state1;
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
    String picturePath;
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
    Uri uri;

    public void onBackPressed() {
    }

    public ProfileImageActivity() {
        String str = "";
        this.picturePath = str;
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
        setContentView((int) R.layout.activity_profile_image);
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
        this.address_line1_1 = intent.getStringExtra("address_line1");
        this.address_line2_1 = intent.getStringExtra("address_line2");
        this.district = intent.getStringExtra("district");
        this.state = intent.getStringExtra("state");
        this.pincode = intent.getStringExtra("pincode");
        this.reference_name = intent.getStringExtra("reference_name");
        this.reference_contact_no = intent.getStringExtra("reference_contact_no");
        this.reference_address = intent.getStringExtra("reference_address");
        this.imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.buttonLoadPicture);
        this.button = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CropImage.startPickImageActivity(ProfileImageActivity.this);
            }
        });
        button = (Button) findViewById(R.id.back);
        this.back = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileImageActivity.this.backData();
                ProfileImageActivity.this.finish();
            }
        });
        button = (Button) findViewById(R.id.next);
        this.next = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileImageActivity.this.addressDetails();
            }
        });
        button = (Button) findViewById(R.id.next2);
        this.next2 = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProfileImageActivity.this.addressDetails();
            }
        });
    }

    public void addressDetails() {
        Intent intent = new Intent(getApplicationContext(), FrameSelectActivity.class);
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
        intent.putExtra("address_line1", this.address_line1_1);
        intent.putExtra("address_line2", this.address_line2_1);
        intent.putExtra("district", this.district);
        intent.putExtra("state", this.state);
        intent.putExtra("pincode", this.pincode);
        intent.putExtra("reference_name", this.reference_name);
        intent.putExtra("reference_contact_no", this.reference_contact_no);
        intent.putExtra("reference_address", this.reference_address);
        intent.putExtra("imagepath", this.picturePath);
        startActivity(intent);
    }

    public void backData() {
        Intent intent = new Intent(getApplicationContext(), ReferenceDetailsActivity.class);
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
        intent.putExtra("address_line1", this.address_line1_1);
        intent.putExtra("address_line2", this.address_line2_1);
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

    private void openGallery() {
        startActivityForResult(new Intent("android.intent.action.PICK", Media.INTERNAL_CONTENT_URI), 100);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 200 && i2 == -1) {
            Uri pickImageResultUri = CropImage.getPickImageResultUri(this, intent);
            if (CropImage.isReadExternalStoragePermissionsRequired(this, pickImageResultUri)) {
                this.uri = pickImageResultUri;
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 201);
            } else {
                startCrop(pickImageResultUri);
            }
        }
        if (i == 203) {
            ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
                this.imageUri = activityResult.getUri();
                try {
                    Bitmap bitmap = Media.getBitmap(getContentResolver(), this.imageUri);
                    this.imageView.setImageBitmap(bitmap);
                    imageStore(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startCrop(Uri uri) {
        CropImage.activity(uri).setGuidelines(Guidelines.ON).setMultiTouchEnabled(true).start(this);
        String realPathFromURI = getRealPathFromURI(uri);
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("motor car -------------------------------------------------------------------");
        stringBuilder.append(realPathFromURI);
        printStream.println(stringBuilder.toString());
    }

    public String getRealPathFromURI(Uri uri) {
        String str = "_data";
        Cursor query = getContentResolver().query(uri, new String[]{str}, null, null, null);
        str = query.moveToFirst() ? query.getString(query.getColumnIndexOrThrow(str)) : null;
        query.close();
        return str;
    }

    public void imageStore(Bitmap bitmap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getPath());
        stringBuilder.append("/profileimage/");
        String stringBuilder2 = stringBuilder.toString();
        File file = new File(stringBuilder2);
        if (!file.exists()) {
            file.mkdirs();
        }
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("IMG_");
        stringBuilder3.append(format);
        stringBuilder3.append(".jpg");
        File file2 = new File(stringBuilder2, stringBuilder3.toString());
        stringBuilder2 = file2.toString();
        if (!file2.exists()) {
            Log.d("path", file2.toString());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.picturePath = stringBuilder2;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPath(Context context, Uri uri) {
        String[] strArr = new String[]{"_data"};
        Cursor query = context.getContentResolver().query(uri, strArr, null, null, null);
        String str = null;
        if (query != null) {
            if (query.moveToFirst()) {
                str = query.getString(query.getColumnIndexOrThrow(strArr[0]));
            }
            query.close();
        }
        return str == null ? "Not found" : str;
    }
}
