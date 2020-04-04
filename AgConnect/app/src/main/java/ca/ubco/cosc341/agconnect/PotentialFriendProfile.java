package ca.ubco.cosc341.agconnect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;
import java.util.Objects;

public class PotentialFriendProfile extends AppCompatActivity {

    Button btn_request;
    TextView warning;
    String name;
    User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potential_friend_profile);
        AppGlobals.saveUserData(this);
        user = (User) getIntent().getSerializableExtra("user");

        Intent i = getIntent();
        name = i.getStringExtra("name");

        warning = findViewById(R.id.warning);
        btn_request = findViewById(R.id.friend_request_btn);
        TextView profileName = findViewById(R.id.profile_name2);
        profileName.setText(name);

        assert name != null;
        setProfileImage(name);

        setBio(name);

    }

    private void setProfileImage(String name){
        ImageView profileImage = findViewById(R.id.profile_image2);
        if (name.equals(this.getResources().getString(R.string.name_bea))){
            profileImage.setImageDrawable(getDrawable(R.drawable.ic_user));
        }
        if (name.equals(this.getResources().getString(R.string.name_harold))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_harold));
        }
        if (name.equals(this.getResources().getString(R.string.name_queen))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_thequeen));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        if (name.equals(this.getString(R.string.name_bea))){
            format = this.getString(R.string.user_objectives, objectiveArray[0]);
            objective.setText(format);

            age.setText(this.getString(R.string.age_bea));
            pronoun.setText(this.getString(R.string.pro_they));

            format = this.getString(R.string.user_interests, interestsArray[4]);
            interests.setText(format);
            moreString = this.getString(R.string.more_bea);
            format = this.getString(R.string.user_bio, moreString);
            more.setText(format);

            if(AppGlobals.requestSentBea){
                btn_request.setBackgroundTintList(this.getColorStateList(R.color.design_default_color_error));  //change to delete
                btn_request.setText(this.getString(R.string.btn_requestDelete));
                warning.setText(this.getString(R.string.request_sent)); //put text about sent request
            }
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


            if(!AppGlobals.answerRequestHarold){
                btn_request.setText(this.getString(R.string.btn_requestReceived));
                btn_request.setBackgroundTintList(this.getColorStateList(R.color.colorAccentTeal));
                warning.setText(this.getString(R.string.request_received));
            }else if(!AppGlobals.friendsWithHarold){
                btn_request.setBackgroundTintList(this.getColorStateList(R.color.colorAccentTeal)); //revert back to being able to send a request
                btn_request.setText(this.getString(R.string.btn_requestSend));
                warning.setText(this.getString(R.string.ins_profile_request));
            }else{
                Toast.makeText(this, "You are already friends. Going to friend profile.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, FriendProfile.class);
                intent.putExtra("name", getString(R.string.name_harold));
                startActivity(intent);
                finish();
            }

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

            if(AppGlobals.requestSentQueen){
                btn_request.setBackgroundTintList(this.getColorStateList(R.color.design_default_color_error));  //change to delete
                btn_request.setText(this.getString(R.string.btn_requestDelete));
                warning.setText(this.getString(R.string.request_sent)); //put text about sent request
            }
        }

    }

    public void toEvents(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, MyConnections_MyFriends.class);
        startActivity(intent);
    }

    public void toConnect(View view){
         Intent intent = new Intent(PotentialFriendProfile.this, Connect.class);
         startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(PotentialFriendProfile.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void onBackClick(View v){
        finish();
    }

    public void onRequestClick(View v){
        if (btn_request.getText().toString().equals(this.getString(R.string.btn_requestDelete))){ //after deleting request
            btn_request.setBackgroundTintList(this.getColorStateList(R.color.colorAccentTeal)); //revert back to being able to send a request
            btn_request.setText(this.getString(R.string.btn_requestSend));
            warning.setText(this.getString(R.string.ins_profile_request));
            if (name.equals(this.getResources().getString(R.string.name_bea))){
                AppGlobals.requestSentBea = false;
            }
            if (name.equals(this.getResources().getString(R.string.name_harold))){
                AppGlobals.requestSentHarold = false;
            }
            if (name.equals(this.getResources().getString(R.string.name_queen))){
                AppGlobals.requestSentQueen = false;
            }
        }else if(btn_request.getText().toString().equals(this.getString(R.string.btn_requestSend))){ //after sending a request
            btn_request.setBackgroundTintList(this.getColorStateList(R.color.design_default_color_error));  //change to delete
            btn_request.setText(this.getString(R.string.btn_requestDelete));
            warning.setText(this.getString(R.string.request_sent, new Date().toString())); //put text about sent request
            if (name.equals(this.getResources().getString(R.string.name_bea))){
                AppGlobals.requestSentBea = true;
            }
            if (name.equals(this.getResources().getString(R.string.name_harold))){
                AppGlobals.requestSentHarold = true;
            }
            if (name.equals(this.getResources().getString(R.string.name_queen))){
                AppGlobals.requestSentQueen = true;
            }
        }else{ //accept or decline
            if (btn_request.getText().toString().equals(this.getString(R.string.btn_requestReceived))){
                Intent intent = new Intent(this, Dialog_DeleteWarning.class);
                intent.putExtra("title", "Add or Decline?");
                intent.putExtra("message","Do you want to add or decline this friend request?");
                intent.putExtra("origin","addDeclineRequest");
                startActivity(intent);
            }
        }
    }
}
