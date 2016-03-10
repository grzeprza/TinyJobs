package pl.project.gpmw.tinyjobs;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MyTasksActivity extends TabActivity {

    TabHost tabHost;
    TabHost.TabSpec tab0,tab1,tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        tab0 = tabHost.newTabSpec("FINISHED");
        tab1 = tabHost.newTabSpec("RAISED");
        tab2 = tabHost.newTabSpec("TAKEN");

        tab0.setIndicator("tab0");
        Intent i0 = new Intent(getApplicationContext(), TabFinished.class);
        tab0.setContent(i0);

        tab1.setIndicator("tab1");
        Intent i1 = new Intent(getApplicationContext(), TabRaised.class);
        tab1.setContent(i1);

        tab2.setIndicator("tab2");
        Intent i2 = new Intent(getApplicationContext(), TabTaken.class);
        tab2.setContent(i2);

        tabHost.addTab(tab0);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
    }

}


class TabFinished extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

/*
        TextView  tv=new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Tab1 Activity");

        setContentView(tv);
*/
        setContentView(R.layout.tab_finished);


    }
}

class TabRaised extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_raised);

      /*  TextView  tv=new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Tab2 Activity");

        setContentView(tv);
*/    }
}

class TabTaken extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

/*
        TextView  tv=new TextView(this);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("This Is Tab2 Activity");

        setContentView(tv);
  */      setContentView(R.layout.tab_taken);
    }
}