package ufc.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ufc.crud.Favourites;

public class LoggedInUser extends AppCompatActivity {

    private ImageView addImage;
    private Button logOut, deleteAccount;
    private TextView email;
    private FirebaseAuth auth;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_user);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.showEmail);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setDataToView(user);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(LoggedInUser.this, Login.class));
                    finish();
                }
            }
        };

        logOut = (Button) findViewById(R.id.logOut);
        deleteAccount = (Button) findViewById(R.id.deleteAccount);
        addImage = findViewById(R.id.addImage);

        addImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (Build.VERSION.SDK_INT >= 22){
                    CheckAndRequestForPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (user != null) {

                    user.delete()

                            .addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override

                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoggedInUser.this, "Your account was deleted!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoggedInUser.this, Register.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoggedInUser.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void openGallery() {
        //open gallery inent and wait for user to pick an image!
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);
    }

    private void CheckAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(LoggedInUser.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoggedInUser.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(LoggedInUser.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();
            }
            else
            {
              ActivityCompat.requestPermissions(LoggedInUser.this,
                                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                        PReqCode);
            }
        }
        else
            openGallery();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){
            //user has successfully picked an image - reference needs to be saved
            pickedImgUri = data.getData();
            addImage.setImageURI(pickedImgUri);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {

        email.setText("User Email: " + user.getEmail());
    }

    // this listener will be called when there is change in firebase user session
    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(LoggedInUser.this, Login.class));
                finish();
            } else {
                setDataToView(user);
            }
        }
    };

    //sign out method
    public void signOut() {
        auth.signOut();

    // this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(LoggedInUser.this, Login.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
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
                Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, UFC_Home.class));
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                startActivity (new Intent(this, Settings.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
}





