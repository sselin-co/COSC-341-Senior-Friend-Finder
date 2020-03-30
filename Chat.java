package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String name = this.getString(R.string.name_harold);
        String format = this.getString(R.string.title_chat, name);
        TextView nameText = findViewById(R.id.title_chat);
        nameText.setText(format);

        ImageView profile = findViewById(R.id.imageView4);
        profile.setImageDrawable(getDrawable(R.drawable.profile_harold));
    }

    public void onBackClick(View v){
        finish();
    }

    public void onSendMessage(View v){
        String format = this.getString(R.string.chat_sent, new Date().toString());
        Toast.makeText(this, format, Toast.LENGTH_LONG).show();
        Button button = findViewById(R.id.button);
        button.setEnabled(false);
    }

    public void toSettings(View view){
        Intent intent = new Intent(Chat.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void fillOne(View v){
        EditText chat = findViewById(R.id.editText);
        chat.setText(this.getString(R.string.prompt_question1));
    }

    public void fillTwo(View v){
        EditText chat = findViewById(R.id.editText);
        chat.setText(this.getString(R.string.prompt_question2));
    }

    public void fillThree(View v){
        EditText chat = findViewById(R.id.editText);
        chat.setText(this.getString(R.string.prompt_question3));
    }
}
