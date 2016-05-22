package pl.project.gpmw.tinyjobs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TabFinished extends Activity
{
   private Task[] parsedDataa = {
           new Task("ic_launcher","Zakupy",2),
           new Task("ic_launcher","Pranie",5),
           new Task("ic_launcher","Sprzątnie",1),
           new Task("ic_launcher","Zakupki dla Andrzejka",15),
           new Task("ic_launcher","Rab to drewno",20),
           new Task("ic_launcher","Projekt z WTI",999),
           new Task("ic_launcher","Matura",2),
           new Task("ic_launcher","Przeczytaj za mnie lekture",10),
           new Task("ic_launcher","Zrob sok",1),
           new Task("ic_launcher","Skos trawe",25),
           new Task("ic_launcher","Poziomki",2),
           new Task("ic_launcher","Wino",3),
           new Task("ic_launcher","Jagody",4),
   };

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_finished);

        listView = (ListView) findViewById(R.id.listView_finished);

        arrayAdapter = new TaskAdapter(this, R.layout.row, parsedDataa);

        if (listView != null) listView.setAdapter(arrayAdapter);


        //creating a new queue, which will send one request only
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //IP address specific for localhost from the point of view of the virtual machine
        final String suffix = "getjobs";
        String url = getResources().getString(R.string.ipAddressVm) + suffix;
        StringRequest putRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("Response", response);
                try
                {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject task = new JSONObject(jsonObject.getString("results"));
                    parsedDataa[0] = new Task("ic_launcher", jsonObject.toString(), 10);
                } catch (JSONException e)
                {
                    Log.d("", e.toString());
                }
            }
        }, new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Something went wrong", error.toString());
            }
        });
        requestQueue.add(putRequest);
        arrayAdapter = new TaskAdapter(this, R.layout.row, parsedDataa);

        if (listView != null) listView.setAdapter(arrayAdapter);
    }
}
