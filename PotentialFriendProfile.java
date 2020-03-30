package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

public class PotentialFriendProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_friend_profile);

        Intent i = getIntent();
        String name = i.getStringExtra("name");

        TextView profileName = findViewById(R.id.profile_name2);
        profileName.setText(name);

        setProfileImage(name);

        setBio(name);


    }

    private void setProfileImage(String name){
        ImageView profileImage = findViewById(R.id.profile_image2);
        if (name.equals(this.getResources().getString(R.string.name_user))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_user));
        }
        if (name.equals(this.getResources().getString(R.string.name_harold))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_harold));
        }
        if (name.equals(this.getResources().getString(R.string.name_queen))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_thequeen));
        }
    }

    private void setBio(String name){
        TextView objective = findViewById(R.id.objective);
        String[] objectiveArray = this.getResources().getStringArray(R.array.objective_array);
        String format;

        TextView age = findViewById(R.id.age);
        TextView pronoun = findViewById(R.id.pronoun);

        TextView interests = findViewById(R.id.interests);
        String[] interestsArray = this.getResources().getStringArray(R.array.interest_array);
        TextView more = findViewById(R.id.more);
        String moreString;

        if (name.equals(this.getString(R.string.name_user))){
            format = this.getString(R.string.user_objectives, objectiveArray[0]);
            objective.setText(format);

            age.setText(this.getString(R.string.age_user));
            pronoun.setText(this.getString(R.string.pro_they));

            format = this.getString(R.string.user_interests, interestsArray[4]);
            interests.setText(format);
            moreString = this.getString(R.string.more_user);
            format = this.getString(R.string.user_bio, moreString);
            more.setText(format);
        }
        if (name.equals(this.getResources().getString(R.string.name_harold))){
            format = this.getString(R.string.user_objectives, objectiveArray[1]);
            objective.setText(format);

            age.setText(this.getString(R.string.age_harold));
            pronoun.setText(this.getString(R.string.pro_he));

            format = this.getString(R.string.user_interests, interestsArray[2]);
            interests.setText(format);
            moreString = this.getString(R.string.more_harold);
            format = this.getString(R.string.user_bio, moreString);
            more.setText(format);
        }
        if (name.equals(this.getResources().getString(R.string.name_queen))){
            format = this.getString(R.string.user_objectives, objectiveArray[2]);
            objective.setText(format);

            age.setText(this.getString(R.string.age_harold));
            pronoun.setText(this.getString(R.string.pro_she));

            format = this.getString(R.string.user_interests, interestsArray[3]);
            interests.setText(format);
            moreString = this.getString(R.string.more_queen);
            format = this.getString(R.string.user_bio, moreString);
            more.setText(format);
        }

    }

    public void toEvents(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, MyConnections.class);
        startActivity(intent);
    }

    /**public void toConnect(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, Connect.class);
        startActivity(intent);
    }**/

    public void toSettings(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void onBackClick(View v){
        finish();
    }

    public void onRequestClick(View v){
        Button button = findViewById(R.id.friend_request_btn);
        TextView warning = findViewById(R.id.warning);
        if (button.getText().toString().equals(this.getString(R.string.btn_requestDelete))){
            button.setBackgroundTintList(this.getColorStateList(R.color.yellow));
            button.setText(this.getString(R.string.btn_requestSend));
            warning.setText(this.getString(R.string.ins_profile_request));
        }
        button.setBackgroundTintList(this.getColorStateList(R.color.light_red));
        button.setText(this.getString(R.string.btn_requestDelete));

        warning.setText(this.getString(R.string.request_sent, new Date().toString()));
    }
}
