package pl.project.gpmw.tinyjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TabTaken extends ListFragment
{
    private ListView listVieww;
    private ArrayAdapter aarraayAdapter;


    Task myTasksArray[] = {};
    String iconName = "ic_launcher";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        String suffix = "taken";
        String url = LoginActivity.ipaddr + suffix;
        StringRequest putRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("WERRTaken", response);
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray resultArray = jsonObject.getJSONArray("results");

                    myTasksArray = new Task[resultArray.length()];

                    for(int i =0; i<resultArray.length(); i++)
                    {
                        JSONObject jsonObjectRow = resultArray.getJSONObject(i);

                        String id = jsonObjectRow.getString("id");
                        String imageName = "ic_launcher";
                        String taskDescr = jsonObjectRow.getString("name");
                        String taskDescr_fullDescription = jsonObjectRow.getString("description");
                        String address = jsonObjectRow.getString("address");
                        String date = jsonObjectRow.getString("date");
                        String time = jsonObjectRow.getString("time");
                        String phone = jsonObjectRow.getString("phone");
                        int taskMoney = jsonObjectRow.getInt("profit");

                        Task newTask = new Task(id,imageName,taskDescr,taskDescr_fullDescription, address, date, time,  phone, taskMoney);
                        myTasksArray[i] = newTask;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                aarraayAdapter = new TaskAdapter(getActivity(), R.layout.row, myTasksArray);
                setListAdapter(aarraayAdapter);

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
                //parameters are send as a dictionary
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(LoginActivity.userID));
                return params;
            }
        };
        requestQueue.add(putRequest);
        View rootView = inflater.inflate(R.layout.raised, container, false);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getContext(), TakenRowDetail.class);
        intent.putExtra("details",myTasksArray[position].toString());
        startActivity(intent);
    }

    public void show()
    {


    }
}
