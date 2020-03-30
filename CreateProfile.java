package com.example.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener{
    int sceneId = 1;
    Button btn_next;
    Button btn_back;
    Button btn_upload;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    StringBuilder strBld;
    EditText editText;
    TextView question;
    TextView subtext;
    TextView privacy;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_back = (Button)findViewById(R.id.btn_back);
        editText = (EditText)findViewById(R.id.editText);
        question = (TextView)findViewById(R.id.q_name);
        subtext = (TextView)findViewById(R.id.title_profileUser);
        privacy = (TextView)findViewById(R.id.privacy);
        strBld = new StringBuilder();

    }

    public void writeText(String record) { //method to write to a text file
        File destination = new File(getApplicationContext().getFilesDir(), "text"); //in the files directory, there is a text directory
        if (!destination.exists()) { //if it doesn't exist yet, create it
            destination.mkdir();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination + "/records.txt", true))) {  //try-with-resources make a bufferedWriter to append to a text file called "records.txt"
            writer.append(record);  //append so that the file is NOT overwritten

        } catch (Exception e) { //if any error occurs
            Log.d("My_Test", "Error: " + e.getMessage()); //send the error message to the log
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show(); //show a toast so the user knows an error occurred

        }
    }

    @Override
    public void onClick(View v) {
        if(sceneId == 1){
            strBld.append(editText.getText().toString() + ", ");
            question.setText(getResources().getString(R.string.q_objective));
            subtext.setText(getResources().getString(R.string.ins_selectAllApply));
            editText.setText("");
            editText.setVisibility(View.INVISIBLE);
            privacy.setText("");
            checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
            checkBox1.setText(getResources().getStringArray(R.array.objective_array)[0]);
            checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
            checkBox4.setText(getResources().getStringArray(R.array.objective_array)[1]);
            checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
            checkBox7.setText(getResources().getStringArray(R.array.objective_array)[2]);
            checkBox1.setVisibility(View.VISIBLE);
            checkBox4.setVisibility(View.VISIBLE);
            checkBox7.setVisibility(View.VISIBLE);
        }
        if(sceneId == 2){
            onCheckBoxClicked();
            question.setText(getResources().getString(R.string.q_interests));
            checkBox1.setText(getResources().getStringArray(R.array.interest_array)[0]);
            checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
            checkBox2.setText(getResources().getStringArray(R.array.interest_array)[1]);
            checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
            checkBox3.setText(getResources().getStringArray(R.array.interest_array)[2]);
            checkBox4.setText(getResources().getStringArray(R.array.interest_array)[3]);
            checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
            checkBox5.setText(getResources().getStringArray(R.array.interest_array)[4]);
            checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
            checkBox6.setText(getResources().getStringArray(R.array.interest_array)[5]);
            checkBox7.setText(getResources().getStringArray(R.array.interest_array)[6]);
            checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
            checkBox8.setText(getResources().getStringArray(R.array.interest_array)[7]);
            checkBox2.setVisibility(View.VISIBLE);
            checkBox3.setVisibility(View.VISIBLE);
            checkBox5.setVisibility(View.VISIBLE);
            checkBox6.setVisibility(View.VISIBLE);
            checkBox8.setVisibility(View.VISIBLE);
        }
        if(sceneId == 3){
            onCheckBoxClicked();
            checkBox1.setVisibility(View.GONE);
            checkBox2.setVisibility(View.GONE);
            checkBox3.setVisibility(View.GONE);
            checkBox4.setVisibility(View.GONE);
            checkBox5.setVisibility(View.GONE);
            checkBox6.setVisibility(View.GONE);
            checkBox7.setVisibility(View.GONE);
            checkBox8.setVisibility(View.GONE);
            question.setText(getResources().getString(R.string.q_dob));
            privacy.setText(getResources().getString(R.string.privacy_dob));
            subtext.setText("");
            editText.setVisibility(View.VISIBLE);

        }
        if(sceneId == 4){
            strBld.append(editText.getText().toString() + ", ");
            editText.setText("");
            //change to photo upload
            question.setText("");
            subtext.setText(getResources().getString(R.string.ins_pic));
            privacy.setText(getResources().getString(R.string.privacy_pic));
            btn_upload = (Button)findViewById(R.id.btn_upload);
            btn_upload.setVisibility(View.VISIBLE);
            editText.setVisibility(View.INVISIBLE);
        }
        if(sceneId == 5){
            strBld.append(imageUri + ", ");
            editText.setVisibility(View.VISIBLE);
            btn_upload.setVisibility(View.INVISIBLE);
            editText.setText("");
            question.setText(getResources().getString(R.string.ins_bio));
            subtext.setText("");
            privacy.setText(getResources().getString(R.string.privacy_bio));
        }
        if(sceneId == 6){
            strBld.append(editText.getText().toString() + ", ");
            String userInfo = strBld.toString();
            writeText(userInfo);
            Intent i = new Intent(CreateProfile.this, LoadingScreen.class);
            startActivity(i);
        }
        sceneId++;
    }

    public void onCheckBoxClicked() {
        if (sceneId == 2) {
            StringBuilder objectivesString = new StringBuilder();
            objectivesString.append("[");
            if (checkBox1.isChecked()) {
                objectivesString.append(checkBox1.getText() + ", ");
            }
            if (checkBox4.isChecked()) {
                objectivesString.append(checkBox4.getText() + ", ");
            }
            if (checkBox7.isChecked()) {
                objectivesString.append(checkBox7.getText() + ", ");
            }
            objectivesString.append(" ], ");
            strBld.append(objectivesString.toString());
        }

        if (sceneId == 3) {
            StringBuilder objectivesString = new StringBuilder();
            objectivesString.append("[");
            if (checkBox1.isChecked()) {
                objectivesString.append(checkBox1.getText() + ", ");
            }
            if (checkBox2.isChecked()) {
                objectivesString.append(checkBox2.getText() + ", ");
            }
            if (checkBox3.isChecked()) {
                objectivesString.append(checkBox3.getText() + ", ");
            }
            if (checkBox4.isChecked()) {
                objectivesString.append(checkBox4.getText() + ", ");
            }
            if (checkBox5.isChecked()) {
                objectivesString.append(checkBox5.getText() + ", ");
            }
            if (checkBox6.isChecked()) {
                objectivesString.append(checkBox6.getText() + ", ");
            }
            if (checkBox7.isChecked()) {
                objectivesString.append(checkBox7.getText() + ", ");
            }
            if (checkBox8.isChecked()) {
                objectivesString.append(checkBox8.getText() + ", ");
            }
            objectivesString.append(" ], ");
            strBld.append(objectivesString.toString());
        }
    }

    public void onClickUpload(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
               // ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

