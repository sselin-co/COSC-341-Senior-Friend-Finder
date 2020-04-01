package ca.ubco.cosc341.agconnect;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class CreateProfile extends AppCompatActivity implements View.OnClickListener{
    int sceneId = 1;
    Button btn_next, btn_back, btn_upload;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;
    StringBuilder userDataString;
    EditText editText;
    TextView question, subtext, privacy;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        btn_next = (Button)findViewById(R.id.create_button_next);
        btn_back = (Button)findViewById(R.id.create_button_back);
        editText = (EditText)findViewById(R.id.create_editText);
        question = (TextView)findViewById(R.id.create_text_question);
        subtext = (TextView)findViewById(R.id.create_title_profileUser);
        privacy = (TextView)findViewById(R.id.create_text_privacy);
        userDataString = new StringBuilder();

        user = (User) getIntent().getSerializableExtra("user");

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
            String name = editText.getText().toString();
            userDataString.append(name + ", ");
            user.setName(name);

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
            editText.setHint("dd-MM-yyy");

        }
        if(sceneId == 4){
            String birthday = editText.getText().toString();
            userDataString.append(birthday + ", ");
            try {
                user.setBirthday(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            editText.setText("");
            //change to photo upload
            question.setText("");
            subtext.setText(getResources().getString(R.string.ins_pic));
            privacy.setText(getResources().getString(R.string.privacy_pic));

            btn_upload = (Button)findViewById(R.id.create_button_upload);
            btn_upload.setVisibility(View.VISIBLE);
            editText.setVisibility(View.INVISIBLE);

        }
        if(sceneId == 5){
            userDataString.append(imageUri + ", ");
            if(imageUri != null){
                user.setPictureName(imageUri.toString());
            }
            editText.setVisibility(View.VISIBLE);
            editText.setHint("");
            btn_upload.setVisibility(View.INVISIBLE);
            editText.setText("");
            question.setText(getResources().getString(R.string.ins_bio));
            subtext.setText("");
            privacy.setText(getResources().getString(R.string.privacy_bio));
        }
        if(sceneId == 6){
            String userBio = editText.getText().toString();
            user.setBio(userBio);
            userDataString.append(userBio + ", ");
            writeText(userDataString.toString());
            Intent i = new Intent(CreateProfile.this, ViewEditProfile.class);
            i.putExtra("user",user);
            startActivity(i);
        }
        sceneId++;
    }

    public void onCheckBoxClicked() {
        if (sceneId == 2) {
            StringBuilder goalsString = new StringBuilder();
            goalsString.append("[");
            if (checkBox1.isChecked()) {
                String goal1 = checkBox1.getText() + ", ";
                user.addGoal(goal1);
                goalsString.append(goal1);
            }
            if (checkBox4.isChecked()) {
                String goal2 = (String) checkBox4.getText();
                user.addGoal(goal2);
                goalsString.append(goal2);
            }
            if (checkBox7.isChecked()) {
                String goal3 = (String) checkBox7.getText();
                user.addGoal(goal3);
                goalsString.append(goal3);
            }
            goalsString.append(" ], ");
            userDataString.append(goalsString.toString());
        }

        if (sceneId == 3) {
            StringBuilder interestsString = new StringBuilder();
            interestsString.append("[");
            if (checkBox1.isChecked()) {
                String interest = checkBox1.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox2.isChecked()) {
                String interest = checkBox2.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox3.isChecked()) {
                String interest = checkBox3.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox4.isChecked()) {
                String interest = checkBox4.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox5.isChecked()) {
                String interest = checkBox5.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox6.isChecked()) {
                String interest = checkBox6.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox7.isChecked()) {
                String interest = checkBox7.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            if (checkBox8.isChecked()) {
                String interest = checkBox8.getText() + ", ";
                user.addInterest(interest);
                interestsString.append(interest);
            }
            interestsString.append(" ], ");
            userDataString.append(interestsString.toString());
        }
    }

    public void onClickUpload(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
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
