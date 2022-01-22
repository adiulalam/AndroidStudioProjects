package decompile.apk.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.asharinfotech.muslimweddingcard.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONObject;

public class PaymentRazorpay extends Activity implements PaymentResultListener {
    private static final String TAG = "PaymentRazorpay";
    public String address_line1;
    public String address_line2;
    public String age;
    public String blood_group;
    public String caste;
    public String complexion;
    String dateToStr;
    public String district;
    public String dob;
    public String education_details;
    String email;
    public String father_name;
    public String fathers_occupation;
    public String full_name;
    public String gotra;
    public String height;
    String id;
    public String imagepath;
    public String income_details;
    public String mangal;
    public String married_brothers;
    public String married_sisters;
    String mobile;
    public String mother_name;
    public String mothers_occupation;
    String newDate;
    public String occupation_details;
    public String parents_contact;
    public String partner_expectations;
    public String personal_contact_number;
    public String personal_email;
    public String pincode;
    public String pob;
    ProgressDialog progressDialog;
    public String religion;
    public String state;
    public String tob;
    public String total_brothers;
    public String total_sisters;
    String username;
    String validity;

    public PaymentRazorpay() {
        String str = "";
        this.tob = str;
        this.pob = str;
        this.mangal = str;
        this.gotra = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_payment_razorpay);
        Intent intent = getIntent();
        this.full_name = intent.getStringExtra("full_name");
        this.religion = intent.getStringExtra("religion");
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
        this.education_details = intent.getStringExtra("education_details");
        this.occupation_details = intent.getStringExtra("occupation_details");
        this.income_details = intent.getStringExtra("income_details");
        this.father_name = intent.getStringExtra("father_name");
        this.fathers_occupation = intent.getStringExtra("fathers_occupation");
        this.mother_name = intent.getStringExtra("mother_name");
        this.mothers_occupation = intent.getStringExtra("mothers_occupation");
        this.total_brothers = intent.getStringExtra("total_brothers");
        this.married_brothers = intent.getStringExtra("married_brothers");
        this.parents_contact = intent.getStringExtra("parents_contact");
        this.partner_expectations = intent.getStringExtra("partner_expectations");
        this.address_line1 = intent.getStringExtra("address_line1");
        this.address_line2 = intent.getStringExtra("address_line2");
        this.district = intent.getStringExtra("district");
        this.state = intent.getStringExtra("state");
        this.pincode = intent.getStringExtra("pincode");
        this.imagepath = intent.getStringExtra("imagepath");
        String str = "";
        if (this.state.equals("Select State") || this.state.equals(str)) {
            this.state = str;
        }
        if (this.height.equals("Select Height") || this.height.equals(str)) {
            this.height = str;
        }
        if (this.blood_group.equals("Select Blood Group") || this.blood_group.equals(str)) {
            this.blood_group = str;
        }
        if (this.complexion.equals("Select Complexion") || this.complexion.equals(str)) {
            this.complexion = str;
        }
        if (this.tob.equals(str) && this.pob.equals(str) && this.mangal.equals(str)) {
            this.gotra.equals(str);
        }
        System.out.println("Hello World");
        String str2 = "dd/MM/yyyy";
        this.dateToStr = new SimpleDateFormat(str2).format(new Date());
        System.out.println(this.dateToStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        Calendar instance = Calendar.getInstance();
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current Date: ");
        stringBuilder.append(simpleDateFormat.format(instance.getTime()));
        printStream.println(stringBuilder.toString());
        instance.add(5, 365);
        this.newDate = simpleDateFormat.format(instance.getTime());
        PrintStream printStream2 = System.out;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Date after Addition: ");
        stringBuilder2.append(this.newDate);
        printStream2.println(stringBuilder2.toString());
        Checkout.preload(getApplicationContext());
        ((Button) findViewById(R.id.btn_pay)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PaymentRazorpay.this.startPayment();
            }
        });
    }

    public void startPayment() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.progressDialog = progressDialog;
        progressDialog.setProgressStyle(1);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
        Checkout checkout = new Checkout();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NamingTable.TAG, "Muslim Marriage Biodata Maker");
            jSONObject.put(DublinCoreProperties.DESCRIPTION, "PDF Download Charges");
            jSONObject.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            jSONObject.put("currency", "INR");
            jSONObject.put("amount", "100");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(NotificationCompat.CATEGORY_EMAIL, this.email);
            jSONObject2.put("contact", this.mobile);
            jSONObject.put("prefill", jSONObject2);
            checkout.open(this, jSONObject);
        } catch (Exception e) {
            this.progressDialog.dismiss();
            Context applicationContext = getApplicationContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error in payment: ");
            stringBuilder.append(e.getMessage());
            Toast.makeText(applicationContext, stringBuilder.toString(), 0).show();
            e.printStackTrace();
        }
    }

    public void onPaymentSuccess(String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Payment Successful: ");
            stringBuilder.append(str);
            Toast.makeText(this, stringBuilder.toString(), 0).show();
            createPdf();
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    public void onPaymentError(int i, String str) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Payment failed: ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(str);
            Toast.makeText(this, stringBuilder.toString(), 0).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    private void createPdf() {
        String str = ".pdf";
        String str2 = "PDFCreator";
        String str3 = "\n";
        String str4 = "";
        Document document = new Document(PageSize.A4);
        StringBuilder stringBuilder;
        try {
            Font font;
            Phrase paragraph;
            PdfPCell pdfPCell;
            Intent intent;
            String file = Environment.getExternalStorageDirectory().toString();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file);
            stringBuilder2.append("/resumepdf");
            File file2 = new File(stringBuilder2.toString());
            file2.mkdirs();
            file = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(file);
            stringBuilder3.append(str);
            File file3 = new File(file2, stringBuilder3.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(file3));
            document.open();
            PdfContentByte directContentUnder = instance.getDirectContentUnder();
            Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.frame_one)).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            Image instance2 = Image.getInstance(byteArrayOutputStream.toByteArray());
            instance2.scaleAbsolute(PageSize.A4);
            instance2.setAbsolutePosition(0.0f, 0.0f);
            directContentUnder.addImage(instance2);
            PdfPTable pdfPTable = new PdfPTable(1);
            PdfPCell pdfPCell2 = new PdfPCell(new Phrase("\n\n\n"));
            pdfPCell2.setUseVariableBorders(true);
            pdfPCell2.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell2);
            document.add(pdfPTable);
            Font font2 = new Font(FontFamily.TIMES_ROMAN, 25.0f, 5, BaseColor.GRAY);
            Paragraph paragraph2 = new Paragraph();
            paragraph2.setFont(font2);
            paragraph2.add("Marriage Biodata");
            paragraph2.setAlignment(1);
            paragraph2.setFont(font2);
            document.add(paragraph2);
            if (!this.imagepath.equals(str4)) {
                ((BitmapDrawable) getResources().getDrawable(R.drawable.frame_one)).getBitmap().compress(CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                byteArrayOutputStream.toByteArray();
                Image instance3 = Image.getInstance(this.imagepath);
                instance3.scaleToFit(100.0f, 120.0f);
                instance3.setAbsolutePosition(455.0f, 600.0f);
                document.add(instance3);
            }
            Paragraph paragraph3 = new Paragraph();
            pdfPTable = new PdfPTable(1);
            PdfPCell pdfPCell3 = new PdfPCell(new Phrase(str3));
            pdfPCell3.setUseVariableBorders(true);
            pdfPCell3.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell3);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(1);
            pdfPCell3 = new PdfPCell(new Phrase(str3));
            pdfPCell3.setUseVariableBorders(true);
            pdfPCell3.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell3);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(1);
            pdfPCell3 = new PdfPCell(new Phrase(str3));
            pdfPCell3.setUseVariableBorders(true);
            pdfPCell3.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell3);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(1);
            Font font3 = new Font(FontFamily.TIMES_ROMAN, 15.0f, 1, BaseColor.GRAY);
            Phrase paragraph4 = new Paragraph();
            paragraph4.setFont(font3);
            paragraph4.add("Personal Details\n");
            pdfPCell3 = new PdfPCell(paragraph4);
            pdfPCell3.setUseVariableBorders(true);
            pdfPCell3.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell3);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Full Name:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.full_name));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Religion:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.religion));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Date of Birth:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.dob));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            String str5 = "Age:";
            if (this.age.equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell2 = new PdfPCell(new Phrase(str5));
                pdfPCell2.setUseVariableBorders(true);
                pdfPCell2.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell2);
                pdfPCell4 = new PdfPCell(new Phrase(str4));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            } else {
                pdfPTable = new PdfPTable(2);
                pdfPCell2 = new PdfPCell(new Phrase(str5));
                pdfPCell2.setUseVariableBorders(true);
                pdfPCell2.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell2);
                pdfPCell4 = new PdfPCell(new Phrase(this.age));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            if (!this.height.equals("Select Height")) {
                if (!this.height.equals(str4)) {
                    pdfPTable = new PdfPTable(2);
                    pdfPCell4 = new PdfPCell(new Phrase("Height:"));
                    pdfPCell4.setUseVariableBorders(true);
                    pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable.addCell(pdfPCell4);
                    pdfPCell4 = new PdfPCell(new Phrase(this.height));
                    pdfPCell4.setUseVariableBorders(true);
                    pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable.addCell(pdfPCell4);
                    pdfPTable.setWidthPercentage(80.0f);
                    document.add(pdfPTable);
                }
            }
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Blood Group:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.blood_group));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            if (!this.complexion.equals("Select Complexion")) {
                if (!this.complexion.equals(str4)) {
                    pdfPTable = new PdfPTable(2);
                    pdfPCell4 = new PdfPCell(new Phrase("Complexion:"));
                    pdfPCell4.setUseVariableBorders(true);
                    pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable.addCell(pdfPCell4);
                    pdfPCell4 = new PdfPCell(new Phrase(this.complexion));
                    pdfPCell4.setUseVariableBorders(true);
                    pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable.addCell(pdfPCell4);
                    pdfPTable.setWidthPercentage(80.0f);
                    document.add(pdfPTable);
                }
            }
            if (!this.personal_contact_number.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Personal Contact Number:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.personal_contact_number));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            if (!this.personal_email.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Email:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.personal_email));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            pdfPTable = new PdfPTable(1);
            pdfPCell4 = new PdfPCell(new Phrase(str3));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell4);
            document.add(pdfPTable);
            if (!this.tob.equals(str4) || !this.pob.equals(str4) || !this.mangal.equals("No") || !this.gotra.equals(str4)) {
                pdfPTable = new PdfPTable(1);
                font = new Font(FontFamily.TIMES_ROMAN, 15.0f, 1, BaseColor.GRAY);
                paragraph = new Paragraph();
                paragraph.setFont(font);
                paragraph.add("Horoscope Details\n");
                pdfPCell4 = new PdfPCell(paragraph);
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.setWidthPercentage(80.0f);
                pdfPTable.addCell(pdfPCell4);
                document.add(pdfPTable);
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Time of Birth:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.tob));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Place of Birth:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.pob));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Mangal:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.mangal));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Kuldevak / Gotra:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.gotra));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
                pdfPTable = new PdfPTable(1);
                pdfPCell4 = new PdfPCell(new Phrase(str3));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.setWidthPercentage(80.0f);
                pdfPTable.addCell(pdfPCell4);
                document.add(pdfPTable);
            }
            pdfPTable = new PdfPTable(1);
            font = new Font(FontFamily.TIMES_ROMAN, 15.0f, 1, BaseColor.GRAY);
            paragraph = new Paragraph();
            paragraph.setFont(font);
            paragraph.add("Education & Occupation Details\n");
            pdfPCell4 = new PdfPCell(paragraph);
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell4);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Education Details:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.education_details));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Occupation Details:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.occupation_details));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            if (!this.income_details.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Income Details:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.income_details));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            pdfPTable = new PdfPTable(1);
            pdfPCell4 = new PdfPCell(new Phrase(str3));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell4);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(1);
            font = new Font(FontFamily.TIMES_ROMAN, 15.0f, 1, BaseColor.GRAY);
            paragraph = new Paragraph();
            paragraph.setFont(font);
            paragraph.add("Family Details\n");
            pdfPCell4 = new PdfPCell(paragraph);
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell4);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Fathers Name:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.father_name));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Fathers Occupation:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.fathers_occupation));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Mothers Name:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.mother_name));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            pdfPTable = new PdfPTable(2);
            pdfPCell4 = new PdfPCell(new Phrase("Mothers Occupation:"));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPCell4 = new PdfPCell(new Phrase(this.mothers_occupation));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.setWidthPercentage(80.0f);
            document.add(pdfPTable);
            if (!this.total_brothers.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("No. of Siblings:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.total_brothers));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            if (!this.married_brothers.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Siblings Details:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.married_brothers));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            if (!this.parents_contact.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Parents Contact Number:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.parents_contact));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            if (!this.partner_expectations.equals(str4)) {
                pdfPTable = new PdfPTable(2);
                pdfPCell4 = new PdfPCell(new Phrase("Partners Expectations:"));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPCell4 = new PdfPCell(new Phrase(this.partner_expectations));
                pdfPCell4.setUseVariableBorders(true);
                pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable.addCell(pdfPCell4);
                pdfPTable.setWidthPercentage(80.0f);
                document.add(pdfPTable);
            }
            pdfPTable = new PdfPTable(1);
            pdfPCell4 = new PdfPCell(new Phrase(str3));
            pdfPCell4.setUseVariableBorders(true);
            pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable.setWidthPercentage(80.0f);
            pdfPTable.addCell(pdfPCell4);
            document.add(pdfPTable);
            PdfPTable pdfPTable2 = new PdfPTable(1);
            font2 = new Font(FontFamily.TIMES_ROMAN, 15.0f, 1, BaseColor.GRAY);
            paragraph4 = new Paragraph();
            paragraph4.setFont(font2);
            paragraph4.add("Address Details\n");
            PdfPCell pdfPCell5 = new PdfPCell(paragraph4);
            pdfPCell5.setUseVariableBorders(true);
            pdfPCell5.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.setWidthPercentage(80.0f);
            pdfPTable2.addCell(pdfPCell5);
            document.add(pdfPTable2);
            String str6 = "Address Line:";
            if (this.address_line1.equals(str4)) {
                if (this.address_line2.equals(str4)) {
                    pdfPTable2 = new PdfPTable(2);
                    pdfPCell4 = new PdfPCell(new Phrase(str6));
                    pdfPCell4.setUseVariableBorders(true);
                    pdfPCell4.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell4);
                    pdfPCell5 = new PdfPCell(new Phrase(str4));
                    pdfPCell5.setUseVariableBorders(true);
                    pdfPCell5.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell5);
                    pdfPTable2.setWidthPercentage(80.0f);
                    document.add(pdfPTable2);
                    pdfPTable2 = new PdfPTable(2);
                    pdfPCell = new PdfPCell(new Phrase("District:"));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPCell = new PdfPCell(new Phrase(this.district));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPTable2.setWidthPercentage(80.0f);
                    document.add(pdfPTable2);
                    pdfPTable2 = new PdfPTable(2);
                    pdfPCell = new PdfPCell(new Phrase("State:"));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPCell = new PdfPCell(new Phrase(this.state));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPTable2.setWidthPercentage(80.0f);
                    document.add(pdfPTable2);
                    pdfPTable2 = new PdfPTable(2);
                    pdfPCell = new PdfPCell(new Phrase("Pincode:"));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPCell = new PdfPCell(new Phrase(this.pincode));
                    pdfPCell.setUseVariableBorders(true);
                    pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                    pdfPTable2.addCell(pdfPCell);
                    pdfPTable2.setWidthPercentage(80.0f);
                    document.add(pdfPTable2);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(Environment.getExternalStorageDirectory().toString());
                    stringBuilder.append("/resumepdf/");
                    stringBuilder.append(file);
                    stringBuilder.append(str);
                    str = stringBuilder.toString();
                    intent = new Intent(getApplicationContext(), ViewPdf.class);
                    intent.putExtra("pdf_path", str);
                    intent.putExtra("full_name", this.full_name);
                    startActivity(intent);
                    document.close();
                }
            }
            if (this.address_line1.equals(str4)) {
                pdfPTable2 = new PdfPTable(2);
                pdfPCell = new PdfPCell(new Phrase(str6));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                pdfPCell = new PdfPCell(new Phrase(this.address_line2));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                pdfPTable2.setWidthPercentage(80.0f);
                document.add(pdfPTable2);
            } else if (this.address_line2.equals(str4)) {
                pdfPTable2 = new PdfPTable(2);
                pdfPCell = new PdfPCell(new Phrase(str6));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                pdfPCell = new PdfPCell(new Phrase(this.address_line1));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                pdfPTable2.setWidthPercentage(80.0f);
                document.add(pdfPTable2);
            } else {
                pdfPTable2 = new PdfPTable(2);
                pdfPCell = new PdfPCell(new Phrase(str6));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(this.address_line1);
                stringBuilder4.append(",\n");
                stringBuilder4.append(this.address_line2);
                pdfPCell = new PdfPCell(new Phrase(stringBuilder4.toString()));
                pdfPCell.setUseVariableBorders(true);
                pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                pdfPTable2.addCell(pdfPCell);
                pdfPTable2.setWidthPercentage(80.0f);
                document.add(pdfPTable2);
            }
            pdfPTable2 = new PdfPTable(2);
            pdfPCell = new PdfPCell(new Phrase("District:"));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPCell = new PdfPCell(new Phrase(this.district));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPTable2.setWidthPercentage(80.0f);
            document.add(pdfPTable2);
            pdfPTable2 = new PdfPTable(2);
            pdfPCell = new PdfPCell(new Phrase("State:"));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPCell = new PdfPCell(new Phrase(this.state));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPTable2.setWidthPercentage(80.0f);
            document.add(pdfPTable2);
            pdfPTable2 = new PdfPTable(2);
            pdfPCell = new PdfPCell(new Phrase("Pincode:"));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPCell = new PdfPCell(new Phrase(this.pincode));
            pdfPCell.setUseVariableBorders(true);
            pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
            pdfPTable2.addCell(pdfPCell);
            pdfPTable2.setWidthPercentage(80.0f);
            document.add(pdfPTable2);
            stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory().toString());
            stringBuilder.append("/resumepdf/");
            stringBuilder.append(file);
            stringBuilder.append(str);
            str = stringBuilder.toString();
            intent = new Intent(getApplicationContext(), ViewPdf.class);
            intent.putExtra("pdf_path", str);
            intent.putExtra("full_name", this.full_name);
            startActivity(intent);
        } catch (DocumentException e) {
            DocumentException documentException = e;
            stringBuilder = new StringBuilder();
            stringBuilder.append("DocumentException:");
            stringBuilder.append(documentException);
            Log.e(str2, stringBuilder.toString());
        } catch (IOException e2) {
            IOException iOException = e2;
            stringBuilder = new StringBuilder();
            stringBuilder.append("ioException:");
            stringBuilder.append(iOException);
            Log.e(str2, stringBuilder.toString());
        } catch (Throwable th) {
            Throwable th2 = th;
            document.close();
        }
        document.close();
    }
}
