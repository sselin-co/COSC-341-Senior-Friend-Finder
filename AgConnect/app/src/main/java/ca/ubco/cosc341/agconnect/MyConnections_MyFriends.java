package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MyConnections_MyFriends extends AppCompatActivity {

    LinearLayout haroldLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_connections);
        AppGlobals.saveUserData(this);
        haroldLinearLayout = findViewById(R.id.harold_linearLayout);

        if(AppGlobals.friendsWithHarold){
            haroldLinearLayout.setVisibility(View.VISIBLE);
        }

    }

    public void toEvents(View view){
        Intent intent = new Intent(this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(this, MyConnections_MyFriends.class);
        startActivity(intent);
    }

    public void toFriendProfileHarold(View view){
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_harold));
        startActivity(intent);
    }

    public void toFriendProfileAgHelp(View view) {
        Intent intent = new Intent(this, FriendProfile.class);
        intent.putExtra("name",getString(R.string.name_AgConnectHelp));
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

    public void toRequests(View view){
        Intent intent = new Intent (this, Requests.class);
        startActivity(intent);
    }

}
