package ca.ubco.cosc341.agconnect;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

import static android.os.Build.VERSION_CODES.P;

public class CreateProfile extends AppCompatActivity{

    private int sceneId = 0;
    private Button btn_upload, btn_back, btn_next;
    private CheckBox cb_interest1, cb_interest2, cb_interest3, cb_interest4, cb_interest5, cb_interest6, cb_interest7, cb_interest8, cb_goal1, cb_goal2, cb_goal3;
    private RadioGroup rg_pronoun;
    private EditText edit_name, edit_bio;
    private TextView tv_question, tv_subtext, tv_privacy, tv_count;
    private ImageView iv_profilePicture;
    private static final int PICK_IMAGE = 1;

    private Uri imageUri;

    private TextView tv_dob;
    private DatePickerDialog.OnDateSetListener dobDateListener;
    private String birthday = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        edit_name = findViewById(R.id.create_edit_name);
        edit_bio = findViewById(R.id.create_edit_bio);
        tv_dob = findViewById(R.id.create_text_dob);
        tv_question = findViewById(R.id.create_text_title);
        tv_subtext = findViewById(R.id.create_text_instructions);
        tv_privacy = findViewById(R.id.create_text_privacy);
        tv_count = findViewById(R.id.create_text_count);
        cb_goal1 = findViewById(R.id.create_cb_goal1);
        cb_goal2 = findViewById(R.id.create_cb_goal2);
        cb_goal3 = findViewById(R.id.create_cb_goal3);
        cb_interest1 = findViewById(R.id.create_cb_interest1);
        cb_interest2 = findViewById(R.id.create_cb_interest2);
        cb_interest3 = findViewById(R.id.create_cb_interest3);
        cb_interest4 = findViewById(R.id.create_cb_interest4);
        cb_interest5 = findViewById(R.id.create_cb_interest5);
        cb_interest6 = findViewById(R.id.create_cb_interest6);
        cb_interest7 = findViewById(R.id.create_cb_interest7);
        cb_interest8 = findViewById(R.id.create_cb_interest8);
        rg_pronoun = findViewById(R.id.create_rg_pronouns);
        iv_profilePicture = findViewById(R.id.create_profilePicture);
        btn_back = findViewById(R.id.create_button_back);
        btn_next = findViewById(R.id.create_button_next);
        btn_upload = findViewById(R.id.create_button_upload);


        //setup pronouns
        String[] pronoun_array = getResources().getStringArray(R.array.pronoun_array);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 48, 0, 48);
        for(String pronoun : pronoun_array){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(pronoun);
            radioButton.setTextSize(20);
            radioButton.setLayoutParams(params);
            rg_pronoun.addView(radioButton);
        }

        //setup checkboxes
        cb_goal1.setText(getResources().getStringArray(R.array.objective_array)[0]);
        cb_goal2.setText(getResources().getStringArray(R.array.objective_array)[1]);
        cb_goal3.setText(getResources().getStringArray(R.array.objective_array)[2]);
        cb_interest1.setText(getResources().getStringArray(R.array.interest_array)[0]);
        cb_interest2.setText(getResources().getStringArray(R.array.interest_array)[1]);
        cb_interest3.setText(getResources().getStringArray(R.array.interest_array)[2]);
        cb_interest4.setText(getResources().getStringArray(R.array.interest_array)[3]);
        cb_interest5.setText(getResources().getStringArray(R.array.interest_array)[4]);
        cb_interest6.setText(getResources().getStringArray(R.array.interest_array)[5]);
        cb_interest7.setText(getResources().getStringArray(R.array.interest_array)[6]);
        cb_interest8.setText(getResources().getStringArray(R.array.interest_array)[7]);

        //setup the counter ("step _ of _")
        setStepCounter(sceneId+1);

        //setup the date picker
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = P)
            @Override
            public void onClick(View v) {
                int day, month, year;
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateProfile.this,
                        R.style.AgConnectDatePicker,
                        dobDateListener,
                        year, month, day);

                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button okButton = dialog.getButton(DatePickerDialog.BUTTON_POSITIVE);
                Button cancelButton = dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE);

                okButton.setElevation(0);
                okButton.setBackgroundColor(ContextCompat.getColor(CreateProfile.this, R.color.colorPrimary));
                okButton.setTextColor(Color.WHITE);
                okButton.setTextSize(24);

                cancelButton.setElevation(0);
                cancelButton.setBackgroundColor(ContextCompat.getColor(CreateProfile.this,R.color.colorAccentPink));
                cancelButton.setTextColor(Color.WHITE);
                cancelButton.setTextSize(24);
            }
        });
        dobDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dayString = addLeadZero(Integer.toString(dayOfMonth));
                String monthString = addLeadZero(Integer.toString(month));
                birthday += dayString +"/"+ monthString +"/"+ year;
                tv_dob.setTextColor(ContextCompat.getColor(CreateProfile.this, R.color.black));
                tv_dob.setText(birthday);
            }
        };
    }

    //for the date picker - need to make sure day and month are 2-digits
    public String addLeadZero(String string){
        if(string.length()==1){
            return "0" + string;
        }else{
            return string;
        }
    }

    //when the next button is clicked
    public void onNextClick(View v) {
        sceneId++;
        setStepCounter(sceneId+1);
        if (sceneId == 1) {
            if(saveName()){
                takeDownName();
                setupPronouns();
            }else{
                sceneId--;
            }
        }
        if(sceneId == 2){
            savePronouns();
            takeDownPronouns();
            setupObjectives();
        }
        if (sceneId == 3) {
            saveObjectives();
            takeDownObjectives();
            setupInterests();
        }
        if (sceneId == 4) {
            saveInterests();
            takeDownInterests();
            setupBirthday();
        }
        if (sceneId == 5) {
            saveBirthday();
            takeDownBirthday();
            setupPicture();
        }
        if (sceneId == 6) {
            savePicture();
            takeDownPicture();
            setupBio();
        }
        if (sceneId == 7) {
            saveBio();
            takeDownBio();
            toViewEditProfile();
        }

    }

    //when the back button is clicked
    public void onBackClick(View v){
        setStepCounter(sceneId);
        sceneId--;
        switch(sceneId){

            case 0:
                takeDownPronouns();
                setupName();
                break;
            case 1:
                takeDownObjectives();
                setupPronouns();
                break;
            case 2:
                takeDownInterests();
                setupObjectives();
                break;
            case 3:
                takeDownBirthday();
                setupInterests();
                break;
            case 4:
                takeDownPicture();
                setupBirthday();
                break;
            case 5:
                takeDownBio();
                setupPicture();
                break;
            case 6:
                setupBio();
                break;
            default:
                toViewEditProfile();
                break;
        }
    }

    private void setStepCounter(int scene){
        String status = "Step " + scene + " of 7";
        tv_count.setText(status);
    }

    private void setupName(){
        tv_question.setText(getResources().getString(R.string.q_name));
        tv_subtext.setText("");
        tv_privacy.setText(getResources().getString(R.string.privacy_name));
        edit_name.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.INVISIBLE);
    }
    private Boolean saveName(){
        String name = edit_name.getText().toString().trim();

        if(!Pattern.matches("^[a-zA-Z_\\-]+( [a-zA-Z_\\-]+)*$", name)){ //validate that the name only contains characters, hyphens, and spaces
            Toast.makeText(this, "Invalid name. Please try again.", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            AppGlobals.user.setName(name);
            return true;
        }
    }

    private void takeDownName(){
        edit_name.setVisibility(View.INVISIBLE);
    }

    private void setupPronouns(){
        tv_question.setText(getResources().getString(R.string.q_pronoun));
        tv_subtext.setText("");
        tv_privacy.setText("");
        rg_pronoun.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
    }

    private void savePronouns(){
        String pronoun = "";
        if(findViewById(rg_pronoun.getCheckedRadioButtonId()) != null){
            pronoun += ((RadioButton)findViewById(rg_pronoun.getCheckedRadioButtonId())).getText().toString();
        }
        AppGlobals.user.setPronoun(pronoun);
    }

    private void takeDownPronouns(){
        rg_pronoun.setVisibility(View.INVISIBLE);
    }

    private void setupObjectives(){
        tv_question.setText(getResources().getString(R.string.q_objective));
        tv_subtext.setText(getResources().getString(R.string.ins_selectAllApply));
        tv_privacy.setText("");
        cb_goal1.setVisibility(View.VISIBLE);
        cb_goal2.setVisibility(View.VISIBLE);
        cb_goal3.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
    }

    private void saveObjectives(){
        onCheckBoxClicked();
    }

    private void takeDownObjectives(){
        cb_goal1.setVisibility(View.INVISIBLE);
        cb_goal2.setVisibility(View.INVISIBLE);
        cb_goal3.setVisibility(View.INVISIBLE);
    }

    private void setupInterests(){
        tv_question.setText(getResources().getString(R.string.q_interests));
        tv_subtext.setText(getResources().getString(R.string.ins_selectAllApply));
        tv_privacy.setText("");
        cb_interest1.setVisibility(View.VISIBLE);
        cb_interest2.setVisibility(View.VISIBLE);
        cb_interest3.setVisibility(View.VISIBLE);
        cb_interest4.setVisibility(View.VISIBLE);
        cb_interest5.setVisibility(View.VISIBLE);
        cb_interest6.setVisibility(View.VISIBLE);
        cb_interest7.setVisibility(View.VISIBLE);
        cb_interest8.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
    }


    private void saveInterests(){
        onCheckBoxClicked();
    }
    private void takeDownInterests(){
        cb_interest1.setVisibility(View.GONE);
        cb_interest2.setVisibility(View.GONE);
        cb_interest3.setVisibility(View.GONE);
        cb_interest4.setVisibility(View.GONE);
        cb_interest5.setVisibility(View.GONE);
        cb_interest6.setVisibility(View.GONE);
        cb_interest7.setVisibility(View.GONE);
        cb_interest8.setVisibility(View.GONE);
    }

    private void setupBirthday(){
        tv_question.setText(getResources().getString(R.string.q_dob));
        tv_privacy.setText(getResources().getString(R.string.privacy_dob));
        tv_subtext.setText("");
        tv_dob.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
    }

    private void saveBirthday(){
        if(birthday.length()==10){
            try {
                AppGlobals.user.setBirthday(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void takeDownBirthday(){
        tv_dob.setVisibility(View.INVISIBLE);
    }

    private void setupPicture(){
        tv_question.setText(getResources().getString(R.string.q_picture));
        tv_subtext.setText(getResources().getString(R.string.ins_pic));
        tv_privacy.setText(getResources().getString(R.string.privacy_pic));
        iv_profilePicture.setVisibility(View.VISIBLE);
        btn_upload.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
    }

    private void savePicture(){
        if (imageUri != null) {
            AppGlobals.user.setProfilePicture(imageUri);
        }
    }

    private void takeDownPicture(){
        iv_profilePicture.setVisibility(View.INVISIBLE);
        btn_upload.setVisibility(View.INVISIBLE);
    }

    private void setupBio(){
        tv_question.setText(getResources().getString(R.string.ins_bio));
        tv_subtext.setText("");
        tv_privacy.setText(getResources().getString(R.string.privacy_bio));
        edit_bio.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
        btn_next.setText(getResources().getString(R.string.btn_save));
        btn_next.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccentTeal));
    }

    private void saveBio(){
        String userBio = edit_bio.getText().toString().trim();
        AppGlobals.user.setBio(userBio);
    }

    private void takeDownBio(){
        edit_bio.setVisibility(View.INVISIBLE);
        btn_next.setText(getResources().getString(R.string.btn_next));
        btn_next.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    private void toViewEditProfile(){
        Intent i = new Intent(CreateProfile.this, ViewEditProfile.class);
        startActivity(i);
    }


    //used to save objectives and interests because it's the onCheckBoxClicked method
    public void onCheckBoxClicked(){
        if (sceneId == 3) { // Goals/Objectives
            String goalList = "";
            if (cb_goal1.isChecked()) {
                String goal = cb_goal1.getText().toString().toLowerCase();
                goalList = addToList(goalList, goal);
            }
            if (cb_goal2.isChecked()) {
                String goal = cb_goal2.getText().toString().toLowerCase();
                goalList = addToList(goalList, goal);
            }
            if (cb_goal3.isChecked()) {
                String goal = cb_goal3.getText().toString().toLowerCase();
                goalList = addToList(goalList, goal);
            }
            AppGlobals.user.setGoals(goalList);
        }

        if (sceneId == 4) { //Interests
            String interestList = "";
            if (cb_interest1.isChecked()) {
                String interest = cb_interest1.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest2.isChecked()) {
                String interest = cb_interest2.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest3.isChecked()) {
                String interest = cb_interest3.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest4.isChecked()) {
                String interest = cb_interest4.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest5.isChecked()) {
                String interest = cb_interest5.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest6.isChecked()) {
                String interest = cb_interest6.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest7.isChecked()) {
                String interest = cb_interest7.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            if (cb_interest8.isChecked()) {
                String interest = cb_interest8.getText().toString().toLowerCase();
                interestList = addToList(interestList, interest);
            }
            AppGlobals.user.setInterests(interestList);
        }
    }

    public String addToList(String list, String item){
        if(list.isEmpty()){
            list = item;
        }else{
            list += ", " + item;
        }
        return list;
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
            iv_profilePicture.setImageURI(imageUri);
        }
    }
}
