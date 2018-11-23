package ufc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import ufc.crud.Favourites;
import ufc.events.UFC232;

public class UFC_Home extends AppCompatActivity {

    private Button fightersMenu;
    private Button pfpMenu;
    private Button eventsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Send Message", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fightersMenu = (Button) findViewById(R.id.fightersMenu);
        pfpMenu = (Button) findViewById(R.id.pfpMenu);
        eventsMenu = (Button) findViewById(R.id.eventsMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ufc__home, menu);
        return true;
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
    public void eventsMenuPressed (View view)
    {
        Log.v("Events", "Events Menu Pressed!");
        startActivity (new Intent(this, Events.class));
    }
    public void beltImagePressed (View view)
    {
        Log.v("Image", "Belt Image Pressed!");
        startActivity (new Intent(this, Favourites.class));
        Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
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
