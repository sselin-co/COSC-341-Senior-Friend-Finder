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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class About extends AppCompatActivity {

    TextView tv_title, tv_contents;
    Button btn_purpose, btn_goals, btn_research;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tv_title = findViewById(R.id.about_text_title);
        tv_contents = findViewById(R.id.about_text_content);
        btn_purpose = findViewById(R.id.about_button_purpose);
        btn_goals = findViewById(R.id.about_button_goals);
        btn_research = findViewById(R.id.about_button_research);

    }

    public void readPurpose(View view){
        setup("purpose.txt", R.string.btn_tellMe1);
    }

    public void readGoals(View view){
        setup("goals.txt", R.string.btn_tellMe2);
    }

    public void readResearch(View view){
        setup("research.txt", R.string.btn_tellMe3);
    }

    private void setup(String filename, int titleStringResource){
        tv_title.setText(titleStringResource);
        tv_contents.setText(readFile(filename));
        tv_contents.setVisibility(View.VISIBLE);
        btn_purpose.setVisibility(View.INVISIBLE);
        btn_goals.setVisibility(View.INVISIBLE);
        btn_research.setVisibility(View.INVISIBLE);
    }

    private String readFile(String filename){
        StringBuilder contents = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getAssets().open(filename)))){
            String line = bufferedReader.readLine();
            while(line != null){
                contents.append(line).append('\n').append('\n');
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            contents.replace(0,contents.length(),"Sorry! It looks like we missed something!");
        }

        return contents.toString();

    }

    public void back(View view){
        if(tv_contents.getVisibility() == View.VISIBLE){
            tv_title.setText(R.string.btn_tellMe);
            tv_contents.setVisibility(View.INVISIBLE);
            btn_purpose.setVisibility(View.VISIBLE);
            btn_goals.setVisibility(View.VISIBLE);
            btn_research.setVisibility(View.VISIBLE);
        }else {
            super.onBackPressed();
        }
    }
    public void toSettings(View view){
        Intent intent = new Intent(this, SettingsMenu.class);
        finish();
        startActivity(intent);
    }
}
