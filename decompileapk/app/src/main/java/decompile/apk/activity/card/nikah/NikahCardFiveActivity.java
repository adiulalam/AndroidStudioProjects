package decompile.apk.activity.card.nikah;

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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.xml.xmp.XmpWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NikahCardFiveActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.nikah_card_one_popup);
        Button button = (Button) findViewById(R.id.okButton);
        ((Button) findViewById(R.id.cancelButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NikahCardFiveActivity.this.finish();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "card_eight";
                NikahCardFiveActivity.this.createPdf(str, ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText01)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText02)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText03)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText04)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText05)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText06)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText07)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText08)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText09)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText10)).getText().toString(), ((EditText) NikahCardFiveActivity.this.findViewById(R.id.editText11)).getText().toString());
            }
        });
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:34:0x039d, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:41:0x03bb, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:42:0x03bc, code skipped:
            r3 = r5;
            r2 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createPdf(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Object obj;
        DocumentException e;
        IOException e2;
        String str13;
        StringBuilder stringBuilder;
        String str14 = str;
        String str15 = str3;
        String str16 = ".pdf";
        String str17 = "PDFCreator";
        String str18 = "\n\n";
        String str19 = XmpWriter.UTF8;
        String str20 = "";
        String str21 = "\n";
        File externalFilesDir = getExternalFilesDir(PdfObject.TEXT_PDFDOCENCODING);
        Document document = new Document(PageSize.A4);
        try {
            String str22;
            File file;
            String str23;
            StringBuilder stringBuilder2;
            StringBuilder stringBuilder3;
            if (!externalFilesDir.exists()) {
                try {
                    externalFilesDir.mkdir();
                } catch (DocumentException e3) {
                    obj = e3;
                    str15 = str17;
                } catch (IOException e22) {
                    obj = e22;
                    str13 = str17;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ioException:");
                    stringBuilder.append(obj);
                    Log.e(str13, stringBuilder.toString());
                    document.close();
                }
            }
            String format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").format(new Date());
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(format);
            stringBuilder4.append(str16);
            File file2 = new File(externalFilesDir, stringBuilder4.toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(file2));
            document.open();
            PdfContentByte directContentUnder = instance.getDirectContentUnder();
            if (str14.equals(str20)) {
                str22 = str16;
                str13 = str17;
                file = externalFilesDir;
                str23 = format;
            } else {
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("@drawable/");
                stringBuilder4.append(str14);
                str14 = stringBuilder4.toString();
                str13 = str17;
                try {
                    Bitmap bitmap = ((BitmapDrawable) getApplicationContext().getResources().getDrawable(getApplicationContext().getResources().getIdentifier(str14, null, getApplicationContext().getPackageName()))).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                    Image instance2 = Image.getInstance(byteArrayOutputStream.toByteArray());
                    instance2.scaleAbsolute(PageSize.A4);
                    instance2.setAbsolutePosition(0.0f, 0.0f);
                    directContentUnder.addImage(instance2);
                    BaseColor baseColor = new BaseColor(255, 255, 255);
                    BaseColor baseColor2 = new BaseColor(255, 255, 255);
                    BaseFont createFont = BaseFont.createFont("assets/fonts/libre_baskerville_regular.ttf", str19, true);
                    BaseFont createFont2 = BaseFont.createFont("assets/fonts/cinzel_decorative_Regular.otf", str19, true);
                    try {
                        BaseFont createFont3 = BaseFont.createFont("assets/fonts/bentham.otf", str19, true);
                        str22 = str16;
                        Font font = new Font(createFont, 13.0f, 0, baseColor);
                        str23 = format;
                        Font font2 = new Font(createFont2, 40.0f, 0, baseColor2);
                        Font font3 = new Font(createFont3, 18.0f, 0, baseColor);
                        Font font4 = new Font(createFont, 24.0f, 0, baseColor);
                        file = externalFilesDir;
                        Font font5 = new Font(createFont3, 20.0f, 0, baseColor);
                        Font font6 = new Font(createFont, 12.0f, 0, baseColor);
                        Paragraph paragraph = new Paragraph();
                        paragraph.setAlignment(1);
                        Phrase phrase = (Paragraph) paragraph.clone();
                        phrase.setFont(font);
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("\n\n\n\n\n\n\n\n\n\n\n\n");
                        stringBuilder4.append(str2);
                        stringBuilder4.append(str21);
                        phrase.add(stringBuilder4.toString());
                        paragraph.setAlignment(1);
                        PdfPTable pdfPTable = new PdfPTable(1);
                        PdfPCell pdfPCell = new PdfPCell(phrase);
                        pdfPCell.setHorizontalAlignment(1);
                        pdfPCell.setUseVariableBorders(true);
                        pdfPCell.setBorder(0);
                        Font font7 = font6;
                        Font font8 = font5;
                        pdfPCell.setBorderColor(new BaseColor(255, 255, 255, 0));
                        pdfPTable.addCell(pdfPCell);
                        pdfPTable.setWidthPercentage(75.0f);
                        document.add(pdfPTable);
                        Paragraph paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font2);
                        StringBuilder stringBuilder5 = new StringBuilder();
                        stringBuilder5.append("\n\n\n");
                        stringBuilder5.append(str15);
                        stringBuilder5.append(str21);
                        paragraph2.add(stringBuilder5.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        paragraph2.add("\nWeds\n");
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font2);
                        stringBuilder5 = new StringBuilder();
                        stringBuilder5.append(str18);
                        stringBuilder5.append(str4);
                        stringBuilder5.append(str21);
                        paragraph2.add(stringBuilder5.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        stringBuilder5 = new StringBuilder();
                        stringBuilder5.append(str18);
                        stringBuilder5.append(str5);
                        stringBuilder5.append(str21);
                        paragraph2.add(stringBuilder5.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font3);
                        StringBuilder stringBuilder6 = new StringBuilder();
                        stringBuilder6.append(str20);
                        stringBuilder6.append(str6);
                        stringBuilder6.append(str21);
                        paragraph2.add(stringBuilder6.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font4);
                        stringBuilder6 = new StringBuilder();
                        stringBuilder6.append(str18);
                        stringBuilder6.append(str7);
                        stringBuilder6.append(str21);
                        paragraph2.add(stringBuilder6.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        stringBuilder6 = new StringBuilder();
                        stringBuilder6.append(str21);
                        stringBuilder6.append(str8);
                        stringBuilder6.append(str21);
                        paragraph2.add(stringBuilder6.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        paragraph2 = (Paragraph) paragraph.clone();
                        paragraph2.setFont(font);
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str9);
                        stringBuilder2.append(str21);
                        paragraph2.add(stringBuilder2.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph2);
                        Paragraph paragraph3 = (Paragraph) paragraph.clone();
                        paragraph3.setFont(font4);
                        paragraph3.add("\n\nVenue\n");
                        paragraph.setAlignment(1);
                        document.add(paragraph3);
                        paragraph3 = (Paragraph) paragraph.clone();
                        paragraph3.setFont(font8);
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(str21);
                        stringBuilder3.append(str10);
                        stringBuilder3.append(str21);
                        paragraph3.add(stringBuilder3.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph3);
                        paragraph3 = (Paragraph) paragraph.clone();
                        Font font9 = font7;
                        paragraph3.setFont(font9);
                        stringBuilder6 = new StringBuilder();
                        stringBuilder6.append(str20);
                        stringBuilder6.append(str11);
                        stringBuilder6.append(str20);
                        paragraph3.add(stringBuilder6.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph3);
                        paragraph3 = (Paragraph) paragraph.clone();
                        paragraph3.setFont(font9);
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(str12);
                        stringBuilder3.append(str20);
                        paragraph3.add(stringBuilder3.toString());
                        paragraph.setAlignment(1);
                        document.add(paragraph3);
                    } catch (DocumentException e4) {
                        e3 = e4;
                        obj = e3;
                        str15 = str13;
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("DocumentException:");
                        stringBuilder3.append(obj);
                        Log.e(str15, stringBuilder3.toString());
                        document.close();
                    } catch (IOException e5) {
                        e22 = e5;
                        obj = e22;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("ioException:");
                        stringBuilder.append(obj);
                        Log.e(str13, stringBuilder.toString());
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
                    Log.e(str13, stringBuilder.toString());
                    document.close();
                }
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(file);
            stringBuilder2.append("/");
            stringBuilder2.append(str23);
            stringBuilder2.append(str22);
            String stringBuilder7 = stringBuilder2.toString();
            PrintStream printStream = System.out;
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("PDF View Path: ");
            stringBuilder3.append(stringBuilder7);
            printStream.println(stringBuilder3.toString());
            Intent intent = new Intent(getApplicationContext(), ViewPdf.class);
            intent.putExtra("pdf_path", stringBuilder7);
            intent.putExtra("full_name", str15);
            intent.putExtra(NotificationCompat.CATEGORY_EMAIL, str20);
            intent.putExtra("mobile", str20);
            startActivity(intent);
        } catch (DocumentException e32) {
            obj = e32;
            str15 = str17;
        } catch (IOException e8) {
            e22 = e8;
            str13 = str17;
            obj = e22;
            stringBuilder = new StringBuilder();
            stringBuilder.append("ioException:");
            stringBuilder.append(obj);
            Log.e(str13, stringBuilder.toString());
            document.close();
        }
        document.close();
    }
}
