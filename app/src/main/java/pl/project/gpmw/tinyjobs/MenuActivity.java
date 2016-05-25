package pl.project.gpmw.tinyjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity
{
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    public static String name;
    public static int id;

    Button button_findTinyJobs, button_placeTinyJobs, button_myTasks, button_getMorePoints;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //ipaddr = getResources().getString(R.string.ipAddressTel)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.menu_adView);
        //   adView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the2 test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();

        //STARTS FIND TINY JOBS ACTIVITY
        button_findTinyJobs = (Button) findViewById(R.id.button_findTinyJob);
        button_findTinyJobs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(v.getContext(), FindTinyJobsActivity.class);
                startActivity(i);
            }
        });

        //STARTS PLACE TINY JOB ACTIVITY
        button_placeTinyJobs = (Button) findViewById(R.id.button_placeTinyJob);
        button_placeTinyJobs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(v.getContext(), PlaceTinyJobActivity.class);
                startActivity(i);
            }
        });

        //STARTS MY TASKS ACTIVITY
        button_myTasks = (Button) findViewById(R.id.button_myTasks);
        button_myTasks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(v.getContext(), MyTasksActivity.class);
                startActivity(i);
            }
        });

        //STARTS GET MORE POINTS ACTIVITY
        button_getMorePoints = (Button) findViewById(R.id.button_getMorePoints);
        button_getMorePoints.setEnabled(false);
        button_getMorePoints.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Intent i = new Intent(v.getContext(), GetMorePointsActivity.class);
                // startActivity(i);

                showInterstitial();
            }
        });

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        TextView nameTextView = (TextView) findViewById(R.id.textView_userName);
        nameTextView.setText(MenuActivity.name);
    }

    private InterstitialAd newInterstitialAd()
    {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded()
            {
                button_getMorePoints.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode)
            {
                button_getMorePoints.setEnabled(true);
            }

            @Override
            public void onAdClosed()
            {
                // Proceed to the next level.
                mInterstitialAd = newInterstitialAd();
                loadInterstitial();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial()
    {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        } else
        {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            mInterstitialAd = newInterstitialAd();
            loadInterstitial();
        }
    }

    private void loadInterstitial()
    {
        // Disable the next level button and load the ad.
        button_getMorePoints.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendGet(View v)
    {
        while (true)
        {
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = "http://10.0.3.2:5000/todo1";

// prepare the Request
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response)
                        {
                            // display response
                            Log.d("Wyszlo Response", response.toString());
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Log.d("Zjebalo Error Response", error.toString());
                        }
                    }
            );

// add it to the RequestQueue
            queue.add(getRequest);
        }
    }


    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */

}
