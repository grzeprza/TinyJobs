package pl.project.gpmw.tinyjobs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TabFinished extends Activity
{
   private Task[] parsedDataa = {
           new Task("ic_launcher","Zakupy",2),
           new Task("ic_launcher","Pranie",5),
           new Task("ic_launcher","Sprzątnie",1),
           new Task("ic_launcher","Zakupki dla Andrzejka",15),
           new Task("ic_launcher","Rab to drewno",20),
           new Task("ic_launcher","Projekt z WTI",999),
           new Task("ic_launcher","Matura",2),
           new Task("ic_launcher","Przeczytaj za mnie lekture",10),
           new Task("ic_launcher","Zrob sok",1),
           new Task("ic_launcher","Skos trawe",25),
           new Task("ic_launcher","Poziomki",2),
           new Task("ic_launcher","Wino",3),
           new Task("ic_launcher","Jagody",4),
   };

    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_finished);

        listView = (ListView) findViewById(R.id.listView_finished);

        arrayAdapter = new TaskAdapter(this, R.layout.row, parsedDataa);

        if (listView != null) listView.setAdapter(arrayAdapter);


    }
}
