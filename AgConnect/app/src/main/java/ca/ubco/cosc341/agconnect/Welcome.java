package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Welcome extends AppCompatActivity {

    TextView tv_title;
    Button btn_next;
    User user;
    boolean hasProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv_title = findViewById(R.id.welcome_text_title);
        btn_next = findViewById(R.id.welcome_button_next);

        hasProfile = loadUser();
        if(hasProfile){
            tv_title.append(" back, " + user.getName());
            btn_next.setText(getString(R.string.btn_signIn));
        }else{
            tv_title.append(" to AgConnect!");
            btn_next.setText(getString(R.string.btn_signUp));
        }
    }

    private boolean loadUser(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getAssets().open("user.txt")))){
            String line = bufferedReader.readLine();
            if(line == null){
                return false;
            }else {
                String[] userData = line.split("@@@");
                Date birthday = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA).parse(userData[5]);
                user = new User(userData[2], userData[3], userData[4], birthday, userData[6], userData[7], userData[8], this);
                return true;
            }
        }catch(Exception e){
            Toast.makeText(this, "Error loading user data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void toNext(View view){
        if(hasProfile){
            toViewEditProfile(view);
        }else{
            toSignUp(view);
        }
    }

    public void toViewEditProfile(View view){
        Intent intent = new Intent(this,ViewEditProfile.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void toSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
}
