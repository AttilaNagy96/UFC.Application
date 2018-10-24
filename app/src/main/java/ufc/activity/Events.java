package ufc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Events extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        String[] events={"UFC232","UFC231","UFC230","UFC229","UFC228","UFC227"};

        itemList=new ArrayList<String>(Arrays.asList(events));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtView,itemList);

        ListView listV=(ListView)findViewById(R.id.eventList);
        listV.setAdapter(adapter);

        editText=(EditText)findViewById(R.id.txtInput);
        Button btAdd=(Button)findViewById(R.id.buttonAdd);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
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
    public void fightersMenuPressed (View view)
    {
        Log.v("Fighters", "Fighters Menu Pressed!");
        startActivity (new Intent(this, Fighters.class));
    }
    public void pfpMenuPressed (View view)
    {
        Log.v("PFP", "Pound for Pund Menu Pressed!");
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
