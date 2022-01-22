package decompile.apk.handler;

import com.itextpdf.text.xml.xmp.XmpWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

public class RequestHandler {
    public String sendPostRequest(String str, HashMap<String, String> hashMap) {
        Exception e;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, XmpWriter.UTF8));
            bufferedWriter.write(getPostDataString(hashMap));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder2 = new StringBuilder();
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder2.append(readLine);
                    } catch (Exception e2) {
                        e = e2;
                        stringBuilder = stringBuilder2;
                        e.printStackTrace();
                        return stringBuilder.toString();
                    }
                }
                stringBuilder = stringBuilder2;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return stringBuilder.toString();
        }
        return stringBuilder.toString();
    }

    public String sendGetRequest(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(str).openConnection()).getInputStream()));
            while (true) {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("\n");
                stringBuilder.append(stringBuilder2.toString());
            }
        } catch (Exception unused) {
        }
        return stringBuilder.toString();
    }

    public String sendGetRequestParam(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(str2);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(stringBuilder2.toString()).openConnection()).getInputStream()));
            while (true) {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str);
                stringBuilder3.append("\n");
                stringBuilder.append(stringBuilder3.toString());
            }
        } catch (Exception unused) {
        }
        return stringBuilder.toString();
    }

    private String getPostDataString(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : hashMap.entrySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            String str = (String) entry.getKey();
            String str2 = XmpWriter.UTF8;
            stringBuilder.append(URLEncoder.encode(str, str2));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str2));
        }
        return stringBuilder.toString();
    }
}
