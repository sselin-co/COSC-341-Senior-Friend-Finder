package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

    }

    public void onClick(View v) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        // Currently only checks if the fields have anything in them
        // Needs strings to be taken from strings.xml
        if (emailString.equals("") && passwordString.equals("")){
            Toast toast = Toast.makeText(this, this.getString(R.string.ins_signUp), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else if (emailString.equals("")){
            Toast toast = Toast.makeText(this, this.getString(R.string.ins_email_error), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else if (passwordString.equals("")){
            Toast toast = Toast.makeText(this, this.getString(R.string.ins_password_error), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(this, this.getString(R.string.ins_success), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    CreateOrSignUp.setAndGetSignedUp(true);
                    Toast toast = Toast.makeText(SignUp.this, SignUp.this.getString(R.string.ins_success2), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 800);
                    toast.show();
                    finish();
                }
            }, 1000);
        }

    }




}
