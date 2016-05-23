package pl.project.gpmw.tinyjobs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TakenRowDetail extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_description;
    private TextView textView_money;
    private TextView textView_date;
    private TextView textView_time;
    private ImageView imageView_icon;
    private TextView textView_address;
    private TextView textView_telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_row_detail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cancel this task ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*0.8) ,(int) (height*0.9));

        Bundle bundle = getIntent().getExtras();
        String DetailedTask[] = bundle.getString("details").split("@#");
        //this.taskDescr + " " + this.taskDescr_fullDescription + " " + getTaskMoney() + " " + getTime() + " " + getDate() + " " +
        // getImage() + " " + getAddress() + " " + getPhone();

        textView_title = (TextView) findViewById(R.id.textView_title_taken);
        textView_title.setText(DetailedTask[0]);

        textView_description = (TextView) findViewById(R.id.textView_description_taken);
        textView_description.setText(DetailedTask[1]);

        textView_money = (TextView) findViewById(R.id.textView_money_taken);
        textView_money.setText(DetailedTask[2]);

        textView_time = (TextView) findViewById(R.id.textView_time_taken);
        textView_time.setText(DetailedTask[3]);

        textView_date = (TextView) findViewById(R.id.textView_date_taken);
        textView_date.setText(DetailedTask[4]);

        imageView_icon = (ImageView) findViewById(R.id.imageView_icon_taken);
        imageView_icon.setImageResource(getApplicationContext().getResources().getIdentifier(DetailedTask[5],"drawable",getApplicationContext().getPackageName()));

        textView_address = (TextView) findViewById(R.id.textView_address_taken);
        textView_address.setText(DetailedTask[6]);

        textView_telephone = (TextView) findViewById(R.id.textView_telephone_taken);
        textView_telephone.setText(DetailedTask[7]);
    }


}
