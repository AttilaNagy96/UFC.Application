package ufc.divisions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ufc.activity.Contact;
import ufc.activity.Events;
import ufc.activity.Fighters;
import ufc.activity.Login;
import ufc.activity.PFP;
import ufc.activity.R;
import ufc.activity.Settings;
import ufc.activity.UFC_Home;

public class Lightweight extends AppCompatActivity {

    int[] IMAGES = {R.drawable.khabib, R.drawable.conor, R.drawable.ferguson,
            R.drawable.nate_diaz, R.drawable.a_pettis, R.drawable.alvarez};
    String[] NAMES = {"Khabib Nurmagomedov", "Conor McGregor", "Tony Ferguson", "Nate Diaz", "Anthony Pettis", "Eddie Alvarez"};
    String [] DESCRIPTIONS = {"Champion", "Ranked No.1", "Ranked No.2", "Ranked No.3", "Ranked No.4", "Ranked No.5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightweight);

        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
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
    public void backButtonPressed (View view)
    {
        Log.v("Events", "Events Menu Pressed!");
        startActivity (new Intent(this, Fighters.class));
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

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
            TextView textView_name=(TextView)view.findViewById(R.id.textView_name);
            TextView textView_description=(TextView)view.findViewById(R.id.textView_description);

            imageView.setImageResource(IMAGES[i]);
            textView_name.setText(NAMES[i]);
            textView_description.setText(DESCRIPTIONS[i]);

            return view;
        }
    }
}
