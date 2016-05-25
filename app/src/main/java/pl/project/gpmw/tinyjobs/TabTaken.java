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
        View rootView = inflater.inflate(R.layout.raised, container, false);
        try {


        }

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getContext(), TakenRowDetail.class);
        intent.putExtra("details",myTasksArray[position].toString());
        startActivity(intent);
    }
}
