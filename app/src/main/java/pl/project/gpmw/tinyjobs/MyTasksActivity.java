package pl.project.gpmw.tinyjobs;

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

        tab0 = tabHost.newTabSpec("finished");
        tab1 = tabHost.newTabSpec("raised");
        tab2 = tabHost.newTabSpec("taken");

        tab0.setIndicator("FINISHED");
        Intent i0 = new Intent(getApplicationContext(), TabFinished.class);
        tab0.setContent(i0);

        tab1.setIndicator("RAISED");
        Intent i1 = new Intent(getApplicationContext(), TabRaised.class);
        tab1.setContent(i1);

        tab2.setIndicator("TAKEN");
        Intent i2 = new Intent(getApplicationContext(), TabTaken.class);
        tab2.setContent(i2);

        tabHost.addTab(tab0);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
    }

}


