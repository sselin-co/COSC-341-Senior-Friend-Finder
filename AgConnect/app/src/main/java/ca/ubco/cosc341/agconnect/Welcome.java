package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Welcome extends AppCompatActivity {

    TextView tv_title;
    Button btn_next;
    boolean hasProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv_title = findViewById(R.id.welcome_text_title);
        btn_next = findViewById(R.id.welcome_button_next);

        hasProfile = loadUser();
        if(hasProfile){
            tv_title.append(" back, " + AppGlobals.user.getName());
            btn_next.setText(getString(R.string.btn_signIn));
        }else{
            tv_title.append(" to AgConnect!");
            btn_next.setText(getString(R.string.btn_signUp));
        }
    }

    private boolean loadUser(){
        File sourceFile = new File(getApplicationContext().getFilesDir(), "text");
        if(!sourceFile.exists()){
            return false;
        }else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile + "/user.txt"))) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return false;
                } else {
                    String[] userData = line.split("@@@");
                    Date birthday;
                    if(!userData[6].equals("null")){
                        birthday = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA).parse(userData[6]);
                    }else{
                        birthday = null;
                    }
                    AppGlobals.user = new User(userData[2], userData[3], userData[4], userData[5], birthday, userData[7], userData[8], this);
                    AppGlobals.answerRequestHarold = Boolean.parseBoolean(userData[9]);
                    AppGlobals.friendsWithHarold = Boolean.parseBoolean(userData[10]);
                    AppGlobals.requestSentQueen = Boolean.parseBoolean(userData[11]);
                    AppGlobals.requestSentBea = Boolean.parseBoolean(userData[12]);
                    AppGlobals.requestSentHarold = Boolean.parseBoolean(userData[13]);
                    return true;
                }
            } catch (Exception e) {
                Log.d("My_Test", "Error in Welcome.loadUser(): " + e.getMessage()); //send the error message to the log
                //Toast.makeText(this, "New user detected.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    public void toNext(View view){
        if(hasProfile){
            tv_title.setText(getResources().getString(R.string.ins_loading));
            btn_next.setVisibility(View.INVISIBLE);
            toViewEditProfile(view);
        }else{
            toSignUp(view);
        }
    }

    public void toViewEditProfile(View view){
        Intent intent = new Intent(this,ViewEditProfile.class);
        startActivity(intent);
        finish();
    }

    public void toSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
}
