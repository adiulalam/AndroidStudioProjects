package decompile.apk.dialog;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

public class DateDialog extends DialogFragment implements OnDateSetListener {
    EditText txtDate;

    public DateDialog(View view) {
        this.txtDate = (EditText) view;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Calendar instance = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), this, instance.get(1), instance.get(2), instance.get(5));
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i3);
        String str = "/";
        stringBuilder.append(str);
        stringBuilder.append(i2 + 1);
        stringBuilder.append(str);
        stringBuilder.append(i);
        this.txtDate.setText(stringBuilder.toString());
    }
}
