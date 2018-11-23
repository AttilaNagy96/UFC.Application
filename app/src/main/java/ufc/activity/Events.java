package ufc.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import ufc.divisions.Bantamweight;
import ufc.divisions.Featherweight;
import ufc.divisions.Flyweight;
import ufc.divisions.Heavyweight;
import ufc.divisions.LHweight;
import ufc.divisions.Lightweight;
import ufc.divisions.Middleweight;
import ufc.divisions.Welterweight;
import ufc.events.UFC229;
import ufc.events.UFC230;
import ufc.events.UFC231;
import ufc.events.UFC232;
import ufc.events.UFC233;

public class Events extends AppCompatActivity {

    ListView Eventlist;
    String titles[] = {"UFC 229", "UFC 230", "UFC 231", "UFC 232", "UFC 233"};
    String descriptions[] = {"Previous Event", "Previous Event", "Upcoming Event", "Upcoming Event", "Upcoming Event"};
    int imgs[] = {R.drawable.ufc_229, R.drawable.ufc_230, R.drawable.ufc_231, R.drawable.ufc232, R.drawable.ufc_233};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Eventlist = findViewById(R.id.eventList);

        //instance of MyAdapter class
        MyAdapter adapter = new MyAdapter(this, titles, imgs, descriptions);

        //set adapter to list
        Eventlist.setAdapter(adapter);

        //itemClick
        Eventlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(), UFC229.class);
                    startActivityForResult(myintent, 0);
                }
                if (position == 1) {
                    Intent myintent = new Intent(view.getContext(), UFC230.class);
                    startActivityForResult(myintent, 1);
                }
                if (position == 2) {
                    Intent myintent = new Intent(view.getContext(), UFC231.class);
                    startActivityForResult(myintent, 2);
                }
                if (position == 3) {
                    Intent myintent = new Intent(view.getContext(), UFC232.class);
                    startActivityForResult(myintent, 3);
                }
                if (position == 4) {
                    Intent myintent = new Intent(view.getContext(), UFC233.class);
                    startActivityForResult(myintent, 4);
                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTitles[];
        String myDescriptions[];
        int[] imgs;

        MyAdapter(Context c, String[] titles, int[] imgs, String[] descriptions){
            super(c, R.layout.row, R.id.text1, titles);
            this.context=c;
            this.imgs=imgs;
            this.myTitles=titles;
            this.myDescriptions=descriptions;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.logo);
            TextView myTitles = row.findViewById(R.id.text1);
            TextView myDescriptions = row.findViewById(R.id.text2);
            images.setImageResource(imgs[position]);
            myTitles.setText(titles[position]);
            myDescriptions.setText(descriptions[position]);
            return row;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ufc__home, menu);
        return true;
    }

    public void homeMenuPressed (View view)
    {
        Log.v("Home", "Home Menu Pressed!");
        startActivity (new Intent(this, UFC_Home.class));
    }
    public void fightersMenuPressed (View view)
    {
        Log.v("Fighters", "Fighters Menu Pressed!");
        startActivity (new Intent(this, Fighters.class));
    }
    public void pfpMenuPressed (View view)
    {
        Log.v("PFP", "Pound for Pound Menu Pressed!");
        startActivity (new Intent(this, PFP.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.contact:
                Toast.makeText(this, "Contact Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Contact.class));
                break;
            case R.id.register:
                Toast.makeText(this, "Register Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Register.class));
                break;
            case R.id.signIn:
                Toast.makeText(this, "Sign In Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Login.class));
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
