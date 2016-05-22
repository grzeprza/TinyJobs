package pl.project.gpmw.tinyjobs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TabFinished extends ListFragment
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.finished, container, false);

        arrayAdapter = new TaskAdapter(getActivity(),R.layout.row,parsedDataa);
        setListAdapter(arrayAdapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
    }
}
