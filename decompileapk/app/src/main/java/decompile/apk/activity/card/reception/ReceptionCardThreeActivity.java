package decompile.apk.activity.card.reception;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.asharinfotech.muslimweddingcard.R;
import com.asharinfotech.muslimweddingcard.activity.card.ViewPdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.xmp.XmpWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.spongycastle.crypto.tls.CipherSuite;

public class ReceptionCardThreeActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.reception_card_one_popup);
        Button button = (Button) findViewById(R.id.okButton);
        ((Button) findViewById(R.id.cancelButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReceptionCardThreeActivity.this.finish();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "reception_card_three";
                ReceptionCardThreeActivity.this.createPdf(str, ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText01)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText02)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText03)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText04)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText05)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText06)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText07)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText08)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText09)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText10)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText11)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText12)).getText().toString(), ((EditText) ReceptionCardThreeActivity.this.findViewById(R.id.editText13)).getText().toString());
            }
        });
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:34:0x03c2, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:41:0x03e0, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:42:0x03e1, code skipped:
            r3 = r5;
            r2 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createPdf(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        Object obj;
        DocumentException e;
        IOException e2;
        String str15;
        StringBuilder stringBuilder;
        String str16 = str;
        String str17 = str3;
        String str18 = ".pdf";
        String str19 = "PDFCreator";
        String str20 = XmpWriter.UTF8;
        String str21 = "\n\n";
        String str22 = "";
        String str23 = "\n";
        File externalFilesDir = getExternalFilesDir(PdfObject.TEXT_PDFDOCENCODING);
        Document document = new Document(PageSize.A4);
        try {
            String str24;
            String str25;
            StringBuilder stringBuilder2;
            if (!externalFilesDir.exists()) {
                try {
                    externalFilesDir.mkdir();
                } catch (DocumentException e3) {
                    obj = e3;
                    str17 = str19;
                } catch (IOException e22) {
                    obj = e22;
                    str15 = str19;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ioException:");
                    stringBuilder.append(obj);
                    Log.e(str15, stringBuilder.toString());
                    document.close();
                }
            }
            String format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(format);
            stringBuilder3.append(str18);
            File file = new File(externalFilesDir, stringBuilder3.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfContentByte directContentUnder = instance.getDirectContentUnder();
            if (str16.equals(str22)) {
                str24 = str18;
                str15 = str19;
                str25 = format;
            } else {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("@drawable/");
                stringBuilder3.append(str16);
                str16 = stringBuilder3.toString();
                str15 = str19;
                try {
                    Bitmap bitmap = ((BitmapDrawable) getApplicationContext().getResources().getDrawable(getApplicationContext().getResources().getIdentifier(str16, null, getApplicationContext().getPackageName()))).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                    Image instance2 = Image.getInstance(byteArrayOutputStream.toByteArray());
                    instance2.scaleAbsolute(PageSize.A4);
                    instance2.setAbsolutePosition(0.0f, 0.0f);
                    directContentUnder.addImage(instance2);
                    BaseColor baseColor = new BaseColor(77, 76, 50);
                    BaseColor baseColor2 = new BaseColor((int) CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 111, 91);
                    try {
                        BaseColor baseColor3 = new BaseColor(255, 231, 200);
                        baseColor3 = new BaseColor(255, (int) CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 38);
                        BaseFont createFont = BaseFont.createFont("assets/fonts/texgyretermesregular.otf", str20, true);
                        BaseFont createFont2 = BaseFont.createFont("assets/fonts/amelia_giovani.otf", str20, true);
                        BaseFont createFont3 = BaseFont.createFont("assets/fonts/agency_fb.ttf", str20, true);
                        str24 = str18;
                        Font font = new Font(createFont, 15.0f, 0, baseColor);
                        str25 = format;
                        Font font2 = new Font(createFont2, 35.0f, 1, baseColor);
                        Font font3 = new Font(createFont3, 20.0f, 0, baseColor);
                        font3 = new Font(createFont, 19.0f, 0, baseColor2);
                        Font font4 = new Font(createFont, 19.0f, 0, baseColor);
                        font4 = new Font(createFont, 15.0f, 0, baseColor);
                        Paragraph paragraph = new Paragraph();
                        paragraph.setAlignment(1);
                        Paragraph paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("\n\n\n\n\n\n");
                        stringBuilder4.append(str2);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str22);
                        stringBuilder4.append(str17);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str22);
                        stringBuilder4.append(str4);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font2);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str21);
                        stringBuilder4.append(str7);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font2);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str21);
                        stringBuilder4.append(str8);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font2);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str21);
                        stringBuilder4.append(str9);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str21);
                        stringBuilder4.append(str10);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str21);
                        stringBuilder4.append(str5);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str23);
                        stringBuilder4.append(str6);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str23);
                        stringBuilder4.append(str11);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str23);
                        stringBuilder4.append(str12);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        paragraph2.add("\nVenue\n");
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(str23);
                        stringBuilder4.append(str13);
                        stringBuilder4.append(str23);
                        paragraph2.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str23);
                        stringBuilder2.append(str14);
                        stringBuilder2.append(str23);
                        paragraph2.add(stringBuilder2.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                    } catch (DocumentException e4) {
                        e3 = e4;
                        obj = e3;
                        str17 = str15;
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("DocumentException:");
                        stringBuilder2.append(obj);
                        Log.e(str17, stringBuilder2.toString());
                        document.close();
                    } catch (IOException e5) {
                        e22 = e5;
                        obj = e22;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("ioException:");
                        stringBuilder.append(obj);
                        Log.e(str15, stringBuilder.toString());
                        document.close();
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Throwable th3 = th2;
                        document.close();
                        throw th3;
                    }
                } catch (DocumentException e6) {
                    e3 = e6;
                } catch (IOException e7) {
                    e22 = e7;
                    obj = e22;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ioException:");
                    stringBuilder.append(obj);
                    Log.e(str15, stringBuilder.toString());
                    document.close();
                }
            }
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(externalFilesDir);
            stringBuilder5.append("/");
            stringBuilder5.append(str25);
            stringBuilder5.append(str24);
            String stringBuilder6 = stringBuilder5.toString();
            PrintStream printStream = System.out;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("PDF View Path: ");
            stringBuilder2.append(stringBuilder6);
            printStream.println(stringBuilder2.toString());
            Intent intent = new Intent(getApplicationContext(), ViewPdf.class);
            intent.putExtra("pdf_path", stringBuilder6);
            intent.putExtra("full_name", str17);
            intent.putExtra(NotificationCompat.CATEGORY_EMAIL, str22);
            intent.putExtra("mobile", str22);
            startActivity(intent);
        } catch (DocumentException e32) {
            obj = e32;
            str17 = str19;
        } catch (IOException e8) {
            e22 = e8;
            str15 = str19;
            obj = e22;
            stringBuilder = new StringBuilder();
            stringBuilder.append("ioException:");
            stringBuilder.append(obj);
            Log.e(str15, stringBuilder.toString());
            document.close();
        }
        document.close();
    }
}
