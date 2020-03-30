package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Connect extends AppCompatActivity {

    final private int[] profileImageArray = {R.drawable.profile_user, R.drawable.profile_harold, R.drawable.profile_thequeen};
    private int imgCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ImageView profileImage = findViewById(R.id.profile_image);
        profileImage.setImageDrawable(getDrawable(profileImageArray[imgCounter]));

        setProfileText();
    }

    public void toEvents(View view){
        Intent intent = new Intent(Connect.this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(Connect.this, MyConnections.class);
        startActivity(intent);
    }

    /**public void toConnect(View view){
        Intent intent = new Intent(Connect.this, Connect.class);
        startActivity(intent);
    }**/

    public void toSettings(View view){
        Intent intent = new Intent(Connect.this, SettingsMenu.class);
        startActivity(intent);
    }


    public void onProfileClick(View v){
        Intent i = new Intent(Connect.this, PotentialFriendProfile.class);

        TextView profileName = findViewById(R.id.profile_name);
        String name = profileName.getText().toString();
        String nameFormat = this.getString(R.string.user_name, name);
        i.putExtra("name", nameFormat);

        startActivity(i);
    }

    public void onNextClick(View v){
        ImageView profileImage = findViewById(R.id.profile_image);
        if (imgCounter != profileImageArray.length-1)  profileImage.setImageDrawable(getDrawable(profileImageArray[++imgCounter]));
        setProfileText();
    }

    public void onBackClick(View v){
        ImageView profileImage = findViewById(R.id.profile_image);
        if (imgCounter != 0)  profileImage.setImageDrawable(getDrawable(profileImageArray[--imgCounter]));
        setProfileText();
    }

    public void setProfileText(){
        TextView profileName = findViewById(R.id.profile_name);
        String format;
        if (profileImageArray[imgCounter] == R.drawable.profile_user){
            String name = this.getResources().getString(R.string.name_user);
            format = this.getString(R.string.user_name, name);
            profileName.setText(format);
        }
        if (profileImageArray[imgCounter] == R.drawable.profile_harold){
            String name = this.getResources().getString(R.string.name_harold);
            format = this.getString(R.string.user_name, name);
            profileName.setText(format);
        }
        if (profileImageArray[imgCounter] == R.drawable.profile_thequeen){
            String name = this.getResources().getString(R.string.name_queen);
            format = this.getString(R.string.user_name, name);
            profileName.setText(format);
        }
    }

}
