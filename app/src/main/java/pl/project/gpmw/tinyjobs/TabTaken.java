package pl.project.gpmw.tinyjobs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TabTaken extends ListFragment
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.taken, container, false);

        arrayAdapterr = new TaskAdapter(getActivity(), R.layout.row, parsedData);
        setListAdapter(arrayAdapterr);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
    }
}
