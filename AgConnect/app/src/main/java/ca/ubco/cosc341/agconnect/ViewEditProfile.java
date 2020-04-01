package ca.ubco.cosc341.agconnect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewEditProfile extends AppCompatActivity {

    User user;
    TextView text_name, text_goals, text_interests, text_age, text_pronoun, text_bio;
    ImageView picture, del_picture, del_goals, del_interests, del_age, del_pronoun, del_bio;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Link to ID's
        text_name = findViewById(R.id.profile_text_Name);
        text_goals = findViewById(R.id.profile_text_Goals);
        text_interests = findViewById(R.id.profile_text_Interests);
        text_age = findViewById(R.id.profile_text_Birthday);
        text_pronoun = findViewById(R.id.profile_text_Pronoun);
        text_bio = findViewById(R.id.profile_text_Bio);
        picture = findViewById(R.id.profile_image_Picture);
        del_picture = findViewById(R.id.profile_del_Picture);
        del_goals = findViewById(R.id.profile_del_Goals);
        del_interests = findViewById(R.id.profile_del_Interests);
        del_age = findViewById(R.id.profile_del_Birthday);
        del_pronoun = findViewById(R.id.profile_del_Pronoun);
        del_bio = findViewById(R.id.profile_del_Bio);

        //set tags for delete option
        del_picture.setTag("pictureName");
        del_goals.setTag("goals");
        del_interests.setTag("interests");
        del_age.setTag("age");
        del_pronoun.setTag("pronoun");
        del_bio.setTag("bio");

        //Get data from intent that started the activity
        user = (User) getIntent().getSerializableExtra("user");

        assert user != null;
        //writeToFile(user);
        setupProfile(user);

    }

 //   void writeToFile(User user){
        //TODO: to be done before submission
 //   }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void setupProfile(User user){
        //set values of TextViews for the profile
        assert user != null;
        text_name.setText(getString(R.string.user_name, user.getName()));

        if(user.getGoals() == null){
            text_goals.setText(R.string.user_goals_none);
        }else{

            text_goals.setText(getString(R.string.user_objectives, user.getGoals()));
        }

        if(user.getInterests() == null){
            text_interests.setText(R.string.user_interests_none);
        }else{
            text_interests.setText(getString(R.string.user_interests, user.getInterests()));
        }

        if(user.getBirthday() == null){
            text_age.setText(R.string.user_dob_none);
        }else {
            text_age.setText(getString(R.string.user_dob, user.getAge()));
        }

        if(user.getPronoun() == null){
            text_pronoun.setText(R.string.user_pronoun_none);
        }else {
            text_pronoun.setText(getString(R.string.user_pronoun, user.getPronoun()));
        }

        if(user.getBio() == null){
            text_bio.setText(R.string.user_bio_none);
        }else {
            text_bio.setText(getString(R.string.user_bio, user.getBio()));
        }
    }

    public void deleteItem(View view){
        String tag = (String) view.getTag();
        Intent intent = new Intent(this,Dialog_DeleteWarning.class);
        intent.putExtra("origin", "deleteProfileItem");
        intent.putExtra("tag", tag);
        intent.putExtra("user", user);
        switch (tag) {
            case "pictureName":
                intent.putExtra("title", "Delete Profile Picture");
                intent.putExtra("message", "Are you sure you want to delete your profile picture? Your picture will be blank to others.");
                break;
            case "goals":
                intent.putExtra("title", "Delete Goals");
                intent.putExtra("message", "Are you sure you want to delete your goals? It will be harder to match with potential friends.");
                break;
            case "interests":
                intent.putExtra("title", "Delete Interests");
                intent.putExtra("message", "Are you sure you want to delete your interest? It will be harder to match with potential friends.");
                break;
            case "age":
                intent.putExtra("title", "Delete Age");
                intent.putExtra("message", "Are you sure you want to delete your age? It will show as blank to others.");
                break;
            case "pronoun":
                intent.putExtra("title", "Delete Preferred Pronoun");
                intent.putExtra("message", "Are you sure you want to delete your preferred pronoun? It will show as blank to others.");
                break;
            case "bio":
                intent.putExtra("title", "Delete Biography");
                intent.putExtra("message", "Are you se you want to delete your biography? It will show as blank to others.");
                break;
            default:
                return;
        }
        startActivity(intent);
    }

    public void toEvents(View view){
        Intent intent = new Intent(ViewEditProfile.this, Events.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(ViewEditProfile.this, MyConnections_MyFriends.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(ViewEditProfile.this, Connect.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(ViewEditProfile.this, SettingsMenu.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

}
