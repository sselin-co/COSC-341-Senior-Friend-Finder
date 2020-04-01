package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Requests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

    }

    public void toEvents(View view){
        Intent intent = new Intent(this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(this, MyConnections_MyFriends.class);
        startActivity(intent);
    }

    public void toRequestProfile1(View view){
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_harold));
        startActivity(intent);
    }

    public void toRequestProfile2(View view){
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_queen));
        startActivity(intent);
    }

    public void toRequestProfile3(View view) {
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_friendY));
        startActivity(intent);
    }

    public void toRequestProfile4(View view) {
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_friendZ));
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(this, Connect.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(this, SettingsMenu.class);
        startActivity(intent);
    }

}