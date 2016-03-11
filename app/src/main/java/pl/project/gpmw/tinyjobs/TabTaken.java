package pl.project.gpmw.tinyjobs;

import android.app.Activity;
import android.os.Bundle;

public class TabTaken extends Activity
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
