package pl.project.gpmw.tinyjobs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FindTinyJobsActivity extends AppCompatActivity {

    private Task[] myTasksArray;
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private Task[] sortedArrayList;

    private Button button_time, button_profit, button_rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tiny_jobs);

        button_time = (Button) findViewById(R.id.button_sortByTime);
        button_profit =(Button) findViewById(R.id.button_sortByProfit);
        button_rank = (Button) findViewById(R.id.button_sortByReputation);

        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortedArrayList = myTasksArray.clone();

                Collections.sort(Arrays.asList(sortedArrayList), new Comparator<Task>() {

                    @Override
                    public int compare(Task lhs, Task rhs) {
                        return lhs.getDate().compareTo(rhs.getDate());
                    }
                });
            }

            //inflate listView
        });

        button_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Not implemented",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            }
        });

        button_profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortedArrayList = myTasksArray.clone();

                Collections.sort(Arrays.asList(sortedArrayList), new Comparator<Task>() {

                    @Override
                    public int compare(Task lhs, Task rhs) {
                        return (lhs.getMoney() > rhs.getMoney() ? 1:0);
                    }
                });
            }
        });

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

        listView = (ListView) findViewById(R.id.listView_tinyJobsList);

        arrayAdapter = new TaskAdapter(getApplicationContext(),R.layout.row,myTasksArray);
        listView.setAdapter(arrayAdapter);

    }

    private class GetTasks extends AsyncTask<String,Integer,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //progressDialog = new ProgressDialog(getApplicationContext());
            //progressDialog.setMessage("Retriving tasks...");
           // progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result="";

            /*Tutaj polaczenie z serwerem*/

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //if(progressDialog.isShowing()) progressDialog.cancel();

        }
    }

    //just sample JSON - problem with accessing file in res/raw/*
    String samplejson = "{\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo 3\",\n" +
            "      \"date\": \"03/15/16\",\n" +
            "      \"description\": \"Zrob projekt i dostan pozytywna ocene\",\n" +
            "      \"id\": \"1\",\n" +
            "      \"name\": \"Zrob projekt\",\n" +
            "      \"phone\": \"123456789\",\n" +
            "      \"profit\": 15,\n" +
            "      \"time\": \"10:20\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"address\": \"Piotrowo\",\n" +
            "      \"date\": \"16/07/16\",\n" +
            "      \"description\": \"napij sie piwa\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"name\": \"Idz na piwo\",\n" +
            "      \"phone\": \"7777777777\",\n" +
            "      \"profit\": 31,\n" +
            "      \"time\": \"10:7\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
