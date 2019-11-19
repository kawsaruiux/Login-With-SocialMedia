package com.myproject.kawsarit.facebookloginfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AnotherActivity extends AppCompatActivity {

    private Button logoutButton;
    private TextView congressText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        mAuth = FirebaseAuth.getInstance();

        logoutButton = (Button) findViewById(R.id.logout_button);
        congressText = (TextView) findViewById(R.id.congress_text);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();    //signout from firebase
                LoginManager.getInstance().logOut();    //Logout from facebook

                updateUI();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            updateUI();
        }
    }

    private void updateUI() {
        Toast.makeText(AnotherActivity.this, "You are loged Out", Toast.LENGTH_SHORT).show();

        Intent accoutIntent = new Intent(AnotherActivity.this, MainActivity.class);
        startActivity(accoutIntent);
        finish();
    }
}
