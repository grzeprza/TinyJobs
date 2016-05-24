package pl.project.gpmw.tinyjobs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlaceTinyJobActivity extends AppCompatActivity
{

    Calendar myCalendar = Calendar.getInstance();
    EditText editText_date;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_tiny_job);

        date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editText_date = (EditText) findViewById(R.id.editText_placeTinyJob_finalDate);
        editText_date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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

    public void onClick_placeJob(View v)
    {
        final String taskName = ((EditText) findViewById(R.id.editText_placeTinyJob_taskName)).getText().toString();
        final String taskDescription = ((EditText) findViewById(R.id.editText_placeTinyJob_taskDescription)).getText().toString();
        final String taskAddress = ((EditText) findViewById(R.id.editText_placeTinyJob_finalAddress)).getText().toString();
        final String taskDate = ((EditText) findViewById(R.id.editText_placeTinyJob_finalDate)).getText().toString();
        final String taskTime = ((TimePicker) findViewById(R.id.editText_placeTinyJob_finalTime)).getCurrentHour().toString()
                + ":" + ((TimePicker) findViewById(R.id.editText_placeTinyJob_finalTime)).getCurrentMinute().toString();
        final String taskPhone = ((EditText) findViewById(R.id.editText_placeTinyJob_contactPhone)).getText().toString();
        final String taskProfit = ((EditText) findViewById(R.id.editText_placeTinyJob_profit)).getText().toString();



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String suffix = "putjob";
        String url = MenuActivity.ipaddr + suffix;
        StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Something went wrong", error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("user", MenuActivity.name);
                params.put("name", taskName);
                params.put("description", taskDescription);
                params.put("address", taskAddress);
                params.put("date", taskDate);
                params.put("time", taskTime);
                params.put("phone", taskPhone);
                params.put("profit", taskProfit);
                params.put("latitude", String.valueOf(MenuActivity.latitude));
                params.put("longitude", String.valueOf(MenuActivity.longitude));
                return params;
            }
        };
        requestQueue.add(putRequest);

    }
}
