package pl.project.gpmw.tinyjobs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyTasksActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String json;

    private Bundle bundleRaised;
    private Bundle bundleTaken;
    private Bundle bundleFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        GetTasks getTasks = new GetTasks();
        getTasks.execute();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        TabRaised tabRaised = new TabRaised();
        tabRaised.setArguments(bundleRaised);

        TabTaken tabTaken = new TabTaken();
        tabTaken.setArguments(bundleTaken);

        TabFinished tabFinished = new TabFinished();
        tabFinished.setArguments(bundleFinished);

        adapter.addFragment(tabRaised, "RAISED");
        adapter.addFragment(tabTaken, "TAKEN");
        adapter.addFragment(tabFinished, "FINISHED");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            /*android.support.v4.app.ListFragment lf = new android.support.v4.app.ListFragment();
            Bundle args = new Bundle();
            args.putString("json",json);
            Log.e("##############",json);
            lf.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(,lf)
                    .addToBackStack(null)
                    .commit();*/

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public String getJSONFile()
    {
        String json = null;
        try
        {
            InputStream is = getResources().openRawResource(R.raw.samplejson);
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

    private class GetTasks extends AsyncTask<String,Integer,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Retriving tasks...");
            progressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result="";

            bundleFinished = new Bundle();
            bundleFinished.putString("json",samplejson);

            bundleRaised = new Bundle();
            bundleRaised.putString("json",samplejson);

            bundleTaken = new Bundle();
            bundleTaken.putString("json",samplejson);


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if(progressDialog.isShowing()) progressDialog.cancel();

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