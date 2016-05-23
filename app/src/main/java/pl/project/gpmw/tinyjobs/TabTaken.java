package pl.project.gpmw.tinyjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TabTaken extends ListFragment
{
    private ListView listVieww;
    private ArrayAdapter arrayAdapterr;

    Task myTasksArray[] = {};
    String iconName = "ic_launcher";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String samplejson = getArguments().getString("json");

        View rootView = inflater.inflate(R.layout.taken, container, false);


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


        arrayAdapterr = new TaskAdapter(getActivity(), R.layout.row, myTasksArray);
        setListAdapter(arrayAdapterr);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getContext(), TakenRowDetail.class);
        intent.putExtra("details",myTasksArray[position].toString());
        startActivity(intent);
    }
}
