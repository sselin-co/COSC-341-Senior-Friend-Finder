package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyProfile extends AppCompatActivity {

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

    public void toConnect(View view){
        Intent intent = new Intent(MyProfile.this, Connect.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(MyProfile.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void toChangeName(View view) {
        Intent intent = new Intent(MyProfile.this, ChangeName.class);
        startActivity(intent);
    }

    public void toChangeInterest(View view) {
        Intent intent = new Intent(MyProfile.this, ChangeInterest.class);
        startActivity(intent);
    }

    public void toChangeBio(View view) {
        Intent intent = new Intent(MyProfile.this, ChangeBio.class);
        startActivity(intent);
    }

    public void toChangeProfilePic(View view) {
        Intent intent = new Intent(MyProfile.this, ChangeProfilePic.class);
        startActivity(intent);
    }

    public void toChangeGoal(View view) {
        Intent intent = new Intent(MyProfile.this, ChangeGoal.class);
        startActivity(intent);
    }

}