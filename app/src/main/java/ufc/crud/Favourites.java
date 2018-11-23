package ufc.crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ufc.activity.Contact;
import ufc.activity.R;
import ufc.activity.Register;
import ufc.activity.Settings;
import ufc.activity.UFC_Home;

public class Favourites extends AppCompatActivity {

    Button saveButton;
    EditText edtName;
    DatabaseReference databaseReference;
    ListView listViewFavourites;
    List<Model> models;
    public static String modelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        models = new ArrayList<Model>();
        databaseReference = FirebaseDatabase.getInstance().getReference("models");

        saveButton = (Button) findViewById(R.id.saveButton);
        edtName = (EditText) findViewById(R.id.edtName);
        listViewFavourites = (ListView) findViewById(R.id.listViewFavourites);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             String name = edtName.getText().toString();

             if(TextUtils.isEmpty(modelId)){
                 String id = databaseReference.push().getKey();
                 Model model = new Model(id, name);
                 databaseReference.child(id).setValue(model);

                 Toast.makeText(Favourites.this, "New fighter added to your favourites list!", Toast.LENGTH_LONG).show();
             } else{
                 databaseReference.child(modelId).child("name").setValue(name);
                 Toast.makeText(Favourites.this, "Favourites list updated successfully!", Toast.LENGTH_LONG).show();
             }
             edtName.setText(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                models.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Model model = postSnapshot.getValue(Model.class);
                    models.add(model);
                }
                ModelList modelAdapter = new ModelList(Favourites.this, models, databaseReference, edtName);
                listViewFavourites.setAdapter(modelAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
            case R.id.homeMenu:
                Toast.makeText(this, "Sign In Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, UFC_Home.class));
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
