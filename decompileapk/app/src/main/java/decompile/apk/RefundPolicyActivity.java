package decompile.apk;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class RefundPolicyActivity extends AppCompatActivity {
    private WebView myWebView;
    ProgressBar progressBar;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_refund_policy);
        WebView webView = (WebView) findViewById(R.id.webview);
        this.myWebView = webView;
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.myWebView.loadUrl("file:///android_asset/html/refund_policy.html");
        this.myWebView.getSettings().setJavaScriptEnabled(true);
    }
}
