package ufc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.home:
                Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, UFC_Home.class));
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
