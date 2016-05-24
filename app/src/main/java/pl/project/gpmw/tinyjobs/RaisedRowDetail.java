package pl.project.gpmw.tinyjobs;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class RaisedRowDetail extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{

    private TextView textView_title;
    private TextView textView_description;
    private TextView textView_money;
    private TextView textView_date;
    private TextView textView_time;
    private ImageView imageView_icon;
    private TextView textView_address;
    private TextView textView_telephone;

    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    protected GoogleApiClient mGoogleApiClient;
    public static double latitude;
    public static double longitude;
    public static String name;
    public static int id;
    public static String ipaddr;
    protected Location mLastLocation;

    FloatingActionButton fab;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raised_row_detail);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Take this task ", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                showInterstitial();

            }
        });

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        buildGoogleApiClient();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*0.8) ,(int) (height*0.9));

        Bundle bundle = getIntent().getExtras();
        String DetailedTask[] = bundle.getString("details").split("@#");
        //this.taskDescr + " " + this.taskDescr_fullDescription + " " + getTaskMoney() + " " + getTime() + " " + getDate() + " " +
        // getImage() + " " + getAddress() + " " + getPhone();

        textView_title = (TextView) findViewById(R.id.textView_title);
        textView_title.setText(DetailedTask[0]);

        textView_description = (TextView) findViewById(R.id.textView_description);
        textView_description.setText(DetailedTask[1]);

        textView_money = (TextView) findViewById(R.id.textView_money);
        textView_money.setText(DetailedTask[2]);

        textView_time = (TextView) findViewById(R.id.textView_time);
        textView_time.setText(DetailedTask[3]);

        textView_date = (TextView) findViewById(R.id.textView_date);
        textView_date.setText(DetailedTask[4]);

        imageView_icon = (ImageView) findViewById(R.id.imageView_icon);
        imageView_icon.setImageResource(getApplicationContext().getResources().getIdentifier(DetailedTask[5],"drawable",getApplicationContext().getPackageName()));

        textView_address = (TextView) findViewById(R.id.textView_address);
        textView_address.setText(DetailedTask[6]);

        textView_telephone = (TextView) findViewById(R.id.textView_telephone);
        textView_telephone.setText(DetailedTask[7]);
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
                fab.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode)
            {
                fab.setEnabled(true);
            }

            @Override
            public void onAdClosed()
            {
                // Proceed to the next level.
                //mInterstitialAd = newInterstitialAd();
                //loadInterstitial();

                Intent intent = new Intent(getApplicationContext() ,MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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
        fab.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    protected synchronized void buildGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint)
    {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            Log.d("werloc", String.valueOf(latitude));
            Log.d("werloc", String.valueOf(longitude));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i("Localization", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i("Localization", "Connection suspended");
        mGoogleApiClient.connect();
    }
}
