package pl.project.gpmw.tinyjobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GrzegorzLap on 2016-04-26.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    Context context;
    int layoutId;
    Task tasks[] = null;


    public TaskAdapter(Context context, int resource,  Task[] objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutId = resource;
        this.tasks = objects;

    }

    @Override
    public Task getItem(int position){return super.getItem(position);}

    @Override
    public View getView( int position, View contentView, ViewGroup parent){
        View row = contentView;

        LayoutInflater inflater = LayoutInflater.from(context);
        row = inflater.inflate(layoutId,parent,false);

        ImageView picture = (ImageView) row.findViewById(R.id.imageView_rowtaskOwner);
        TextView taskName = (TextView) row.findViewById(R.id.textView_rowtaskName);
        TextView earned = (TextView) row.findViewById(R. id. textView_rowEarnedMoney);

        Task task = tasks[position];

        taskName.setText(task.getTaskDescr());
        earned.setText(task.getTaskMoney());

        int resId = context.getResources().getIdentifier(task.getImage(),"drawable",context.getPackageName());
        picture.setImageResource(resId);

        return row;
    }
}
