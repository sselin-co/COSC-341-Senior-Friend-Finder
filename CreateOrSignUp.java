package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateOrSignUp extends AppCompatActivity implements View.OnClickListener {
    Button createProfile;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_sign_up);
        createProfile = findViewById(R.id.createProfile);
        signUp = findViewById(R.id.SignUp);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent( CreateOrSignUp.this, CreateProfile.class);
        startActivity(i);
    }

    /*
    public void onClickSignUp(View v){
        Intent i = new Intent(CreateOrSignUp.this, SignUp.class);
        startActivity(i);
    }
    */
}
