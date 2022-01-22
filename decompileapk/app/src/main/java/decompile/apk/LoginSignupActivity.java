package decompile.apk;

import Constant.Constant;
import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.asharinfotech.muslimweddingcard.activity.URLs;
import com.asharinfotech.muslimweddingcard.handler.RequestHandler;
import com.asharinfotech.muslimweddingcard.library.DatabaseHandler;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginSignupActivity extends AppCompatActivity {
    Button button;
    Spinner country;
    String country1;
    DatabaseHandler db = new DatabaseHandler(this);
    String device_id1;
    String device_unique_id;
    EditText email;
    String email1;
    EditText full_name;
    String full_name1;
    ImageView img;
    EditText mobile;
    String mobile1;
    EditText pincode;
    String pincode1;
    TextView privacy_policy;
    Dialog slideDialog;
    Spinner spinner;
    LinearLayout sppiner;
    String state1 = "";
    EditText state_edittext;
    Spinner state_spinner;
    TextView terms_and_conditions;
    TextView txt;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_login_signup);
        this.button = (Button) findViewById(R.id.button);
        this.img = (ImageView) findViewById(R.id.image);
        this.terms_and_conditions = (TextView) findViewById(R.id.terms_and_conditions);
        this.privacy_policy = (TextView) findViewById(R.id.privacy_policy);
        this.full_name = (EditText) findViewById(R.id.full_name);
        this.mobile = (EditText) findViewById(R.id.mobile);
        this.email = (EditText) findViewById(R.id.email);
        this.country = (Spinner) findViewById(R.id.country);
        this.state_spinner = (Spinner) findViewById(R.id.state_spinner);
        this.state_edittext = (EditText) findViewById(R.id.state_edittext);
        this.pincode = (EditText) findViewById(R.id.pincode);
        this.device_unique_id = Secure.getString(getContentResolver(), "android_id");
        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        if (databaseHandler.getContactsCount() >= 1) {
            databaseHandler.getUserData();
            String str = databaseHandler.full_name;
            String str2 = databaseHandler.mobile;
            String str3 = databaseHandler.email;
            String str4 = databaseHandler.country;
            String str5 = databaseHandler.state;
            String str6 = databaseHandler.pincode;
            Constant.login_country = str4;
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("full_name", str);
            intent.putExtra("mobile", str2);
            intent.putExtra(NotificationCompat.CATEGORY_EMAIL, str3);
            intent.putExtra("country", str4);
            intent.putExtra("state", str5);
            intent.putExtra("pincode", str6);
            intent.putExtra("device_id", this.device_unique_id);
            startActivityForResult(intent, 0);
        }
        databaseHandler.close();
        this.terms_and_conditions.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginSignupActivity.this.startActivity(new Intent(LoginSignupActivity.this, TermsAndConditionsActivity.class));
            }
        });
        this.privacy_policy.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginSignupActivity.this.startActivity(new Intent(LoginSignupActivity.this, PrivacyPolicyActivity.class));
            }
        });
        this.country.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (LoginSignupActivity.this.country.getSelectedItem().toString().trim().equals("India")) {
                    LoginSignupActivity.this.state_spinner.setVisibility(0);
                    LoginSignupActivity.this.state_edittext.setVisibility(8);
                    return;
                }
                LoginSignupActivity.this.state_spinner.setVisibility(8);
                LoginSignupActivity.this.state_edittext.setVisibility(0);
            }
        });
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (LoginSignupActivity.this.isNetworkConnected()) {
                    LoginSignupActivity.this.registerUser();
                } else {
                    Toast.makeText(LoginSignupActivity.this.getApplicationContext(), "Please check your internet connection", 0).show();
                }
            }
        });
        Dexter.withContext(this).withPermissions("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.ACCESS_NETWORK_STATE").withListener(new MultiplePermissionsListener() {
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
            }

            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(LoginSignupActivity.this.getApplicationContext(), "Permission Granted", 0).show();
            }
        }).check();
    }

    private void registerUser() {
        this.full_name1 = this.full_name.getText().toString().trim();
        this.mobile1 = this.mobile.getText().toString().trim();
        this.email1 = this.email.getText().toString().trim();
        this.country1 = this.country.getSelectedItem().toString().trim();
        this.pincode1 = this.pincode.getText().toString().trim();
        this.device_id1 = this.device_unique_id;
        System.out.println("Soumya");
        if (TextUtils.isEmpty(this.full_name1)) {
            this.full_name.setError("Enter your full name");
            this.full_name.requestFocus();
        } else if (TextUtils.isEmpty(this.mobile1)) {
            this.mobile.setError("Enter valid mobile number");
            this.mobile.requestFocus();
        } else {
            String str = "Enter your valid email";
            if (TextUtils.isEmpty(this.email1)) {
                this.email.setError(str);
                this.email.requestFocus();
            } else if (Patterns.EMAIL_ADDRESS.matcher(this.email1).matches()) {
                str = "Select Country";
                if (this.country1.equals(str)) {
                    ((TextView) this.country.getSelectedView()).setError(str);
                    this.country.requestFocus();
                    return;
                }
                if (this.state_spinner.getVisibility() == 0) {
                    String trim = this.state_spinner.getSelectedItem().toString().trim();
                    this.state1 = trim;
                    str = "Select State";
                    if (trim.equals(str)) {
                        ((TextView) this.state_spinner.getSelectedView()).setError(str);
                        this.state_spinner.requestFocus();
                        return;
                    }
                }
                this.state1 = this.state_edittext.getText().toString().trim();
                System.out.println("Soumya1");
                new AsyncTask<Void, Void, String>() {
                    private ProgressBar progressBar;

                    /* Access modifiers changed, original: protected|varargs */
                    public String doInBackground(Void... voidArr) {
                        System.out.println("Soumya2");
                        RequestHandler requestHandler = new RequestHandler();
                        System.out.println("Soumya3");
                        HashMap hashMap = new HashMap();
                        hashMap.put("full_name", LoginSignupActivity.this.full_name1);
                        hashMap.put("mobile", LoginSignupActivity.this.mobile1);
                        hashMap.put(NotificationCompat.CATEGORY_EMAIL, LoginSignupActivity.this.email1);
                        hashMap.put("country", LoginSignupActivity.this.country1);
                        hashMap.put("state", LoginSignupActivity.this.state1);
                        hashMap.put("pincode", LoginSignupActivity.this.pincode1);
                        hashMap.put("device_id", LoginSignupActivity.this.device_id1);
                        System.out.println("Soumya4");
                        return requestHandler.sendPostRequest(URLs.URL_REGISTER, hashMap);
                    }

                    /* Access modifiers changed, original: protected */
                    public void onPreExecute() {
                        System.out.println("Soumya5");
                        super.onPreExecute();
                        ProgressBar progressBar = (ProgressBar) LoginSignupActivity.this.findViewById(R.id.progressBar);
                        this.progressBar = progressBar;
                        progressBar.setVisibility(0);
                        System.out.println("Soumya6");
                    }

                    /* Access modifiers changed, original: protected */
                    public void onPostExecute(String str) {
                        System.out.println("Soumya7");
                        super.onPostExecute(str);
                        this.progressBar.setVisibility(8);
                        System.out.println("Soumya8");
                        try {
                            System.out.println("Soumya9");
                            System.out.println(str);
                            JSONObject jSONObject = new JSONObject(str);
                            System.out.println(str);
                            String str2 = "message";
                            if (jSONObject.getBoolean("error")) {
                                Toast.makeText(LoginSignupActivity.this.getApplicationContext(), jSONObject.getString(str2), 0).show();
                                return;
                            }
                            System.out.println("Soumya11");
                            Toast.makeText(LoginSignupActivity.this.getApplicationContext(), jSONObject.getString(str2), 0).show();
                            LoginSignupActivity.this.finish();
                            LoginSignupActivity.this.db.addMember(LoginSignupActivity.this.full_name1, LoginSignupActivity.this.mobile1, LoginSignupActivity.this.email1, LoginSignupActivity.this.country1, LoginSignupActivity.this.state1, LoginSignupActivity.this.pincode1, LoginSignupActivity.this.device_id1);
                            Constant.login_country = LoginSignupActivity.this.country1;
                            Intent intent = new Intent(LoginSignupActivity.this.getApplicationContext(), HomePageActivity.class);
                            intent.putExtra("full_name", LoginSignupActivity.this.full_name1);
                            intent.putExtra("mobile", LoginSignupActivity.this.mobile1);
                            intent.putExtra(NotificationCompat.CATEGORY_EMAIL, LoginSignupActivity.this.email1);
                            intent.putExtra("country", LoginSignupActivity.this.country1);
                            intent.putExtra("state", LoginSignupActivity.this.state1);
                            intent.putExtra("pincode", LoginSignupActivity.this.pincode1);
                            intent.putExtra("device_id", LoginSignupActivity.this.device_id1);
                            LoginSignupActivity.this.startActivityForResult(intent, 0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute(new Void[0]);
            } else {
                this.email.setError(str);
                this.email.requestFocus();
            }
        }
    }

    private boolean isNetworkConnected() {
        return ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    public void selectedCountry(Integer num, String str) {
        this.img.setImageResource(num.intValue());
        this.txt.setText(str);
        this.slideDialog.dismiss();
    }
}
