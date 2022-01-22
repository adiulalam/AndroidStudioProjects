package decompile.apk.activity;

import Constant.Constant;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore.Images.Media;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import com.asharinfotech.muslimweddingcard.R;
import com.asharinfotech.muslimweddingcard.library.DatabaseHandler;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FileUtils;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.shockwave.pdfium.PdfDocument.Bookmark;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.rendering.PDFRenderer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class ViewPdf extends Activity implements OnPageChangeListener, OnLoadCompleteListener, PaymentResultListener {
    public static final String SAMPLE_FILE = "";
    private static final String TAG = "ViewPdf";
    String date;
    File destination;
    String destinationPath;
    Button download;
    Button download2;
    String email;
    File file1;
    String file_name;
    int flag;
    String full_name;
    HandlerThread handlerThread;
    ImageView imageView;
    String mobile;
    Integer pageNumber = Integer.valueOf(0);
    String pdfFileName;
    PDFView pdfView;
    String pdf_path;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    Button share;
    Button share2;
    Button share3;
    File source;
    String sourcePath;

    public ViewPdf() {
        String str = "";
        this.sourcePath = str;
        this.destinationPath = str;
        this.flag = 0;
        this.handlerThread = new HandlerThread("TesHandlerThread");
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        setContentView(R.layout.activity_pdf_viewer);
        this.share2 = (Button) findViewById(R.id.share2);
        if (Constant.login_country.equals("India")) {
            this.share2.setText("To Download Make Payment\nRs.40 only");
        } else {
            this.share2.setText("To Download Make Payment\nUSD 2.00 only");
        }
        Checkout.preload(getApplicationContext());
        Intent intent = getIntent();
        this.pdf_path = intent.getStringExtra("pdf_path");
        this.full_name = intent.getStringExtra("full_name");
        this.email = intent.getStringExtra(NotificationCompat.CATEGORY_EMAIL);
        this.mobile = intent.getStringExtra("mobile");
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.pdfView = (PDFView) findViewById(R.id.pdfView);
        this.file1 = new File(this.pdf_path);
        displayFromAsset(this.pdf_path);
        this.sourcePath = this.pdf_path;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        stringBuilder.append("/");
        stringBuilder.append(this.full_name);
        String str = "_Marriage_Biodata.pdf";
        stringBuilder.append(str);
        this.destinationPath = stringBuilder.toString();
        System.out.println(this.destinationPath);
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.full_name);
        stringBuilder.append(str);
        this.file_name = stringBuilder.toString();
        this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.source = new File(this.sourcePath);
        this.destination = new File(this.destinationPath);
        this.share2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ViewPdf.this.flag == 0) {
                    Toast.makeText(ViewPdf.this, "Please make the payment", 0).show();
                    ViewPdf.this.startPayment();
                    return;
                }
                ViewPdf.this.share();
            }
        });
        Button button = (Button) findViewById(R.id.share3);
        this.share3 = button;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ViewPdf.this.flag == 0) {
                    Toast.makeText(ViewPdf.this, "Please make the payment", 0).show();
                    ViewPdf.this.startPayment();
                    return;
                }
                ViewPdf.this.share();
            }
        });
    }

    public void pdftoimage() {
        PDDocument load;
        try {
            load = PDDocument.load(new File(this.sourcePath));
        } catch (IOException e) {
            e.printStackTrace();
            load = null;
        }
        PDFRenderer pDFRenderer = new PDFRenderer(load);
        try {
            this.imageView = (ImageView) findViewById(R.id.imageView);
            Bitmap renderImageWithDPI = pDFRenderer.renderImageWithDPI(0, 300.0f);
            try {
                renderImageWithDPI.compress(CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                Uri parse = Uri.parse(Media.insertImage(getContentResolver(), renderImageWithDPI, "Title", null));
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.STREAM", parse);
                startActivity(Intent.createChooser(intent, "Share with"));
            } catch (Exception e2) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(e2);
                stringBuilder.append(" ");
                Log.e("Error on sharing", stringBuilder.toString());
                Toast.makeText(this, "App not Installed", 0).show();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x005e */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:4|5|6|7|8|14) */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void download() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar = progressBar;
        progressBar.setVisibility(0);
        try {
            copyFile(this.source, this.destination);
            Toast.makeText(this, "PDF Saved in Download Folder", 0).show();
            if (this.destination.exists()) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(268435456);
                Uri uriForFile = FileProvider.getUriForFile(this, "com.asharinfotech.muslimweddingcard.provider", this.destination);
                intent.addFlags(2);
                intent.addFlags(1);
                intent.setDataAndType(uriForFile, "application/pdf");
                ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar);
                this.progressBar = progressBar2;
                progressBar2.setVisibility(8);
                startActivity(intent);
                Toast.makeText(this, "No Application available to view pdf", 1).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void share() {
        try {
            copyFile(this.source, this.destination);
            Toast.makeText(this, "PDF Saved in Download Folder", 0).show();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(FileProvider.getUriForFile(this, "com.asharinfotech.muslimweddingcard.provider", new File(this.destinationPath)).toString()));
            try {
                startActivity(Intent.createChooser(intent, "Share..."));
            } catch (ActivityNotFoundException unused) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileUtils.copy(new FileInputStream(file), file2);
    }

    private void displayFromAsset(String str) {
        this.pdfFileName = str;
        this.pdfView.fromFile(this.file1).defaultPage(this.pageNumber.intValue()).enableSwipe(true).swipeHorizontal(false).onPageChange(this).enableAnnotationRendering(true).onLoad(this).scrollHandle(new DefaultScrollHandle(this)).load();
    }

    public void onPageChanged(int i, int i2) {
        this.pageNumber = Integer.valueOf(i);
        setTitle(String.format("%s %s / %s", new Object[]{this.pdfFileName, Integer.valueOf(i + 1), Integer.valueOf(i2)}));
    }

    public void loadComplete(int i) {
        this.pdfView.getDocumentMeta();
        printBookmarksTree(this.pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<Bookmark> list, String str) {
        for (Bookmark bookmark : list) {
            Log.e(TAG, String.format("%s %s, p %d", new Object[]{str, bookmark.getTitle(), Long.valueOf(bookmark.getPageIdx())}));
            if (bookmark.hasChildren()) {
                List children = bookmark.getChildren();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("-");
                printBookmarksTree(children, stringBuilder.toString());
            }
        }
    }

    public void imageorpdf() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.common_dialog_pdf_image);
        Button button = (Button) dialog.findViewById(R.id.okButton);
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.pdf_image);
        ((Button) dialog.findViewById(R.id.cancelButton)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(ViewPdf.this.getApplicationContext(), "Dismissed..!!", 0).show();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0086 */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|18) */
            /* JADX WARNING: Missing block: B:17:?, code skipped:
            return;
     */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(View view) {
                String str = "Please wait...";
                if (spinner.getSelectedItem().equals("As Image")) {
                    Toast.makeText(ViewPdf.this, str, 0).show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            PDDocument load;
                            try {
                                load = PDDocument.load(new File(ViewPdf.this.sourcePath));
                            } catch (IOException e) {
                                e.printStackTrace();
                                load = null;
                            }
                            PDFRenderer pDFRenderer = new PDFRenderer(load);
                            try {
                                ViewPdf.this.imageView = (ImageView) ViewPdf.this.findViewById(R.id.imageView);
                                Bitmap renderImageWithDPI = pDFRenderer.renderImageWithDPI(0, 300.0f);
                                try {
                                    renderImageWithDPI.compress(CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                                    Uri parse = Uri.parse(Media.insertImage(ViewPdf.this.getContentResolver(), renderImageWithDPI, "Title", null));
                                    Intent intent = new Intent("android.intent.action.SEND");
                                    intent.setType("image/*");
                                    intent.putExtra("android.intent.extra.STREAM", parse);
                                    ViewPdf.this.startActivity(Intent.createChooser(intent, "Share with"));
                                    dialog.cancel();
                                } catch (Exception e2) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(e2);
                                    stringBuilder.append(" ");
                                    Log.e("Error on sharing", stringBuilder.toString());
                                    Toast.makeText(ViewPdf.this.getApplicationContext(), "App not Installed", 0).show();
                                }
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }, 100);
                    return;
                }
                Toast.makeText(ViewPdf.this, str, 0).show();
                try {
                    ViewPdf.copyFile(ViewPdf.this.source, ViewPdf.this.destination);
                    Toast.makeText(ViewPdf.this, "PDF Saved in Download Folder", 0).show();
                    if (ViewPdf.this.destination.exists()) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(268435456);
                        ViewPdf viewPdf = ViewPdf.this;
                        Uri uriForFile = FileProvider.getUriForFile(viewPdf, "com.asharinfotech.muslimweddingcard.provider", viewPdf.destination);
                        intent.addFlags(2);
                        intent.addFlags(1);
                        intent.setDataAndType(uriForFile, "application/pdf");
                        ViewPdf.this.startActivity(intent);
                        dialog.cancel();
                        Toast.makeText(ViewPdf.this, "No Application available to view pdf", 1).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }

    private void takeScreenshot() {
        Date date = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Environment.getExternalStorageDirectory().toString());
            stringBuilder.append("/");
            stringBuilder.append(date);
            stringBuilder.append(".jpg");
            String stringBuilder2 = stringBuilder.toString();
            PDFView pDFView = (PDFView) findViewById(R.id.pdfView);
            this.pdfView = pDFView;
            pDFView.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(this.pdfView.getDrawingCache());
            this.pdfView.setDrawingCacheEnabled(false);
            File file = new File(stringBuilder2);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            createBitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(FileProvider.getUriForFile(this, "com.asharinfotech.muslimweddingcard.provider", file).toString()));
            try {
                startActivity(Intent.createChooser(intent, "Share..."));
            } catch (ActivityNotFoundException unused) {
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
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
            String str = "amount";
            String str2 = "currency";
            if (Constant.login_country.equals("India")) {
                jSONObject.put(str2, "INR");
                jSONObject.put(str, "4000");
            } else {
                jSONObject.put(str2, "USD");
                jSONObject.put(str, "200");
            }
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
            this.progressDialog.dismiss();
            this.flag = 1;
            new DatabaseHandler(this).addFile(this.file_name, this.destinationPath, this.date);
            Intent intent = new Intent(getApplicationContext(), ViewPdf2.class);
            intent.putExtra("pdf_path", this.pdf_path);
            intent.putExtra("full_name", this.full_name);
            startActivity(intent);
        } catch (Exception e) {
            this.progressDialog.dismiss();
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    public void onPaymentError(int i, String str) {
        try {
            this.progressDialog.dismiss();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Payment failed: ");
            stringBuilder.append(i);
            stringBuilder.append(" ");
            stringBuilder.append(str);
            Toast.makeText(this, stringBuilder.toString(), 0).show();
        } catch (Exception e) {
            this.progressDialog.dismiss();
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    public void onBackPressed() {
        if (this.file1.exists()) {
            this.file1.delete();
        }
        finish();
    }
}
