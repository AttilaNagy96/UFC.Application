package ufc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ufc.divisions.Bantamweight;
import ufc.divisions.Featherweight;
import ufc.divisions.Flyweight;
import ufc.divisions.Heavyweight;
import ufc.divisions.LHweight;
import ufc.divisions.Lightweight;
import ufc.divisions.Middleweight;
import ufc.divisions.Welterweight;

public class Fighters extends AppCompatActivity {

    ListView listView;

    static final String[] divisions = new String[]{
            "Flyweight Division",
            "Bantamweight Division",
            "Featherweight Division",
            "Lightweight Division",
            "Welterweight Division",
            "Middleweight Division",
            "Light-Heavyweight Division",
            "Heavyweight Division"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighters);

        listView = findViewById(R.id.divisionsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, divisions);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(),Flyweight.class);
                    startActivityForResult(myintent, 0);
                }
                if(position==1){
                    Intent myintent = new Intent(view.getContext(),Bantamweight.class);
                    startActivityForResult(myintent,1);
                }
                if(position==2) {
                    Intent myintent = new Intent(view.getContext(),Featherweight.class);
                    startActivityForResult(myintent, 2);
                }
                if(position==3){
                    Intent myintent = new Intent(view.getContext(),Lightweight.class);
                    startActivityForResult(myintent,3);
                }
                if(position==4) {
                    Intent myintent = new Intent(view.getContext(),Welterweight.class);
                    startActivityForResult(myintent, 4);
                }
                if(position==5){
                    Intent myintent = new Intent(view.getContext(),Middleweight.class);
                    startActivityForResult(myintent,5);
                }
                if(position==6) {
                    Intent myintent = new Intent(view.getContext(),LHweight.class);
                    startActivityForResult(myintent, 6);
                }
                if(position==7){
                    Intent myintent = new Intent(view.getContext(),Heavyweight.class);
                    startActivityForResult(myintent,7);
                }
            }
        });
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
    public void pfpMenuPressed (View view)
    {
        Log.v("PFP", "Pound for Pound Menu Pressed!");
        startActivity (new Intent(this, PFP.class));
    }
    public void eventsMenuPressed (View view)
    {
        Log.v("Events", "Events Menu Pressed!");
        startActivity (new Intent(this, Events.class));
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
