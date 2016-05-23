package pl.project.gpmw.tinyjobs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class TabFinished extends ListFragment
{

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    Task myTasksArray[] = {};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String samplejson = getArguments().getString("json");

        View rootView = inflater.inflate(R.layout.finished, container, false);



        try {

            JSONObject jsonObject = new JSONObject(samplejson);
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

        arrayAdapter = new TaskAdapter(getActivity(),R.layout.row,myTasksArray);
        setListAdapter(arrayAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
    }

    public String getJSONFile()
    {
     String json = null;
        try
        {
            InputStream is = getResources().openRawResource(R.raw.samplejson2);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String (buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}

