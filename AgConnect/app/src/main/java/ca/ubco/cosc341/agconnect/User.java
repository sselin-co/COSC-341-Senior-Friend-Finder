package ca.ubco.cosc341.agconnect;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class User implements Serializable {
    
    //ATTRIBUTES
    private String email;
    private String password;
    private String name;
    private String goals;
    private String interests;
    private Date birthday;
    private String pronoun;
    private String bio;
    private String pictureName;

    //CONSTRUCTOR
    public User(){

    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String name, String goals, String interests, Date birthday, String pronoun, String bio, String pictureName, Context context) {
        this.name = name;
        this.goals = goals;
        this.interests = interests;
        this.birthday = birthday;
        this.pronoun = pronoun;
        this.bio = bio;
        this.pictureName = pictureName;
    }

    //GETTERS, SETTERS, AND DELETERS
    public String getName() {
        return this.name;

    }

    public void setName(String name) {
        this.name = name;
//        if(name.isEmpty() && this.getName().isEmpty()){
//            Toast.makeText(context, "Please provide a name.", Toast.LENGTH_SHORT).show();
//        }else if(name.isEmpty() && !this.getName().isEmpty()){
//            Toast.makeText(context, "Name not changed.", Toast.LENGTH_SHORT).show();
//        }else{
//            this.name = name;
//            Toast.makeText(context, "Name successfully changed.", Toast.LENGTH_SHORT).show();
//        }
    }

    public String getGoals() {
        return goals;
    }

    public void addGoal(String goal){
        if(this.goals == null){
            setGoals(goal);
        }else{
            this.goals += goal;
        }
    }

    public void setGoals(String goals) {
        this.goals = goals;
//        if(goals.isEmpty() && !this.getGoals().isEmpty()){
//            Toast.makeText(context, "Goal(s) not changed", Toast.LENGTH_SHORT).show();
//        }else{
//            this.goals = goals;
//            Toast.makeText(context, "Goal(s) successfully changed", Toast.LENGTH_SHORT).show();
//        }
    }
    public void deleteGoals(){
        this.goals = null;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) throws ParseException {
        this.birthday = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA).parse(birthday);
    }
    
    public void deleteBirthday(){
        this.birthday = null;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public void deletePronoun(){
        this.pronoun = null;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void addInterest(String interest){
        if(this.interests == null){
            setInterests(interest);
        }else{
            this.interests += interest;
        }
    }

    public void deleteInterests(){
        this.interests = null;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void deleteBio(){
        this.bio = null;
    }

    public void setPictureName(String pictureName){
        this.pictureName = pictureName;
    }
    public String getPictureName(){
        return this.pictureName;
    }
    public void deletePictureName(){
        this.pictureName = null;
    }

    public void setLoginCredentials(String email, String password){
        this.email = email;
        this.password = password;
    }

    //CUSTOMIZED METHODS
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getAge(){
        LocalDate localBirthDate = this.birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(localBirthDate, localCurrentDate);

//        Calendar c = Calendar.getInstance();
//        c.setTime(birthday);
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH) + 1;
//        int date = c.get(Calendar.DATE);
//        LocalDate dateOfBirth = LocalDate.of(year, month, date);
//        LocalDate dateOfNow = LocalDate.now();
//        Period age = Period.between(dateOfBirth, dateOfNow);
//        return age.getYears();
    }
}
