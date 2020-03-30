package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.MyProfile);

    }

    public void toEvents(View view){
        Intent intent = new Intent(MyProfile.this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(MyProfile.this, MyConnetions_MyFriends.class);
        startActivity(intent);
    }

    public void toRequestProfile1(View view){
        Intent intent = new Intent(MyProfile.this, requestProfile1.class);
        startActivity(intent);
    }

    public void toRequestProfile2(View view){
        Intent intent = new Intent(MyProfile.this, requestProfile2.class);
        startActivity(intent);
    }

    public void toRequestProfile3(View view) {
        Intent intent = new Intent(MyProfile.this, requestProfile3.class);
        startActivity(intent);
    }

    public void toRequestProfile4(View view) {
        Intent intent = new Intent(MyProfile.this, requestProfile4.class);
        startActivity(intent);
    }

}