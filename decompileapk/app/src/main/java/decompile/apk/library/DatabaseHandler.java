package decompile.apk.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.core.app.NotificationCompat;
import com.itextpdf.text.Annotation;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "muslim_marriage_biodata";
    private static final int DATABASE_VERSION = 1;
    public String country;
    public String email;
    public String full_name;
    public String mobile;
    public String pincode;
    public String state;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE  `user` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `full_name` TEXT, `mobile` TEXT, `email` TEXT, `country` TEXT, `state` TEXT, `pincode` TEXT, `device_id` TEXT)");
        sQLiteDatabase.execSQL("CREATE TABLE  `file` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `file_name` TEXT, `file_path` TEXT, `created_date` TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS file");
        onCreate(sQLiteDatabase);
    }

    public void addMember(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("full_name", str);
        contentValues.put("mobile", str2);
        contentValues.put(NotificationCompat.CATEGORY_EMAIL, str3);
        contentValues.put("country", str4);
        contentValues.put("state", str5);
        contentValues.put("pincode", str6);
        contentValues.put("device_id", str7);
        writableDatabase.insert("user", null, contentValues);
        writableDatabase.close();
    }

    public void addFile(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("file_name", str);
        contentValues.put("file_path", str2);
        contentValues.put("created_date", str3);
        writableDatabase.insert(Annotation.FILE, null, contentValues);
        writableDatabase.close();
    }

    public List<String> getAllLabels() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT  * FROM company_details", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(1));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public List<String> getAllstateLabels() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT  * FROM state_name order by state_name", null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(rawQuery.getString(1));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public int getContactsCount() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT  * FROM user", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        return count;
    }

    public void getUserData() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = "SELECT  * FROM user";
        Cursor rawQuery = readableDatabase.rawQuery(str, null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.full_name = rawQuery.getString(rawQuery.getColumnIndex("full_name"));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        rawQuery = readableDatabase.rawQuery(str, null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.mobile = rawQuery.getString(rawQuery.getColumnIndex("mobile"));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        rawQuery = readableDatabase.rawQuery(str, null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.email = rawQuery.getString(rawQuery.getColumnIndex(NotificationCompat.CATEGORY_EMAIL));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        rawQuery = readableDatabase.rawQuery(str, null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.country = rawQuery.getString(rawQuery.getColumnIndex("country"));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        rawQuery = readableDatabase.rawQuery(str, null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.state = rawQuery.getString(rawQuery.getColumnIndex("state"));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        Cursor rawQuery2 = readableDatabase.rawQuery(str, null);
        if (rawQuery2 != null) {
            rawQuery2.moveToFirst();
            this.pincode = rawQuery2.getString(rawQuery2.getColumnIndex("pincode"));
        }
        rawQuery2.close();
        readableDatabase.close();
    }

    public void getFileData() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT  * FROM file", null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.mobile = rawQuery.getString(rawQuery.getColumnIndex("mobile"));
        }
        rawQuery.close();
        readableDatabase.close();
        readableDatabase = getReadableDatabase();
        rawQuery = readableDatabase.rawQuery("SELECT  * FROM user", null);
        if (rawQuery != null) {
            rawQuery.moveToFirst();
            this.email = rawQuery.getString(rawQuery.getColumnIndex(NotificationCompat.CATEGORY_EMAIL));
        }
        rawQuery.close();
        readableDatabase.close();
    }
}
