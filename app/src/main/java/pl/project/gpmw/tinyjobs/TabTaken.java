package pl.project.gpmw.tinyjobs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabTaken extends Activity
{
    private Task[] parsedData = {
            new Task("ic_launcher","Zamiatanie pustyni",100),
            new Task("ic_launcher","Dyskusja o koranie",25),
            new Task("ic_launcher","Projekt WTI grupa 2",10),
            new Task("ic_launcher","PUT - odmalowac sciany, wszedzie",2000),
            new Task("ic_launcher","Rab to drewno",20)
    };

    private ListView listVieww;
    private ArrayAdapter arrayAdapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_taken);

        listVieww = (ListView) findViewById(R.id.listView_finished);

        arrayAdapterr = new TaskAdapter(this,R.layout.row, this.parsedData);

        if (listVieww != null) listVieww.setAdapter(arrayAdapterr);

    }
}
