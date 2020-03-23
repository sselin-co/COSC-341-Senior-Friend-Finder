package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;            //set your time here......

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent i = new Intent(LoadingScreen.this, CreateOrSignUp.class);
                LoadingScreen.this.startActivity(i);
                LoadingScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}

