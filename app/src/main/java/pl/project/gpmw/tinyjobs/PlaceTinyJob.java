package pl.project.gpmw.tinyjobs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PlaceTinyJob extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    EditText editText_date;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_tiny_job);

        date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editText_date = (EditText) findViewById(R.id.editText_placeTinyJob_finalDate);
        editText_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(),
                                        date,
                                        myCalendar.get(Calendar.YEAR),
                                        myCalendar.get(Calendar.MONTH),
                                        myCalendar.get(Calendar.DAY_OF_MONTH)
                                        ).show();
            }
        });
    }

    private void updateLabel()
    {
        String dateForme = "dd/mm/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateForme, Locale.US);
        editText_date.setText(simpleDateFormat.format(myCalendar.getTime()));
    }
}
