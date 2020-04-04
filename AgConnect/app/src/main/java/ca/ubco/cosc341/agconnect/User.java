package ca.ubco.cosc341.agconnect;

import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    private String birthdayString;
    private Uri profilePicture;

    //CONSTRUCTOR
    public User(){

    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(Uri profilePicture, String name, String goals, String interests, Date birthday, String pronoun, String bio) {
        this.profilePicture = profilePicture;
        this.name = name;
        this.goals = goals;
        this.interests = interests;
        this.birthday = birthday;
        this.pronoun = pronoun;
        this.bio = bio;
    }

    Uri getProfilePicture(){
        return this.profilePicture;
    }
    private String getProfilePictureString(){
        if(this.profilePicture == null){
            return "";
        }else{
            return getProfilePicture().toString();
        }
    }
    void setProfilePicture(Uri profilePicture){
        this.profilePicture = profilePicture;
    }
    void deleteProfilePicture(){
        setProfilePicture(null);
    }
    //GETTERS, SETTERS, AND DELETERS
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getGoals() {
        return goals;
    }

    void addGoal(String goal){
        if(this.goals == null){
            setGoals(goal);
        }else{
            this.goals += ", " + goal;
        }
    }

    void setGoals(String goals) {
        this.goals = goals;
    }
    void deleteGoals(){
        this.goals = null;
    }

    Date getBirthday() {
        return birthday;
    }
    private String getBirthdayString(){
        return birthdayString;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    void setBirthday(String birthday) throws ParseException {
        this.birthdayString = birthday;
        this.birthday = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA).parse(birthday);
    }
    
    void deleteBirthday(){
        this.birthday = null;
    }

    String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    void deletePronoun(){
        this.pronoun = null;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    void addInterest(String interest){
        if(this.interests == null){
            setInterests(interest);
        }else{
            this.interests += ", " + interest;
        }
    }

    void deleteInterests(){
        this.interests = null;
    }

    String getBio() {
        return bio;
    }

    void setBio(String bio) {
        this.bio = bio;
    }

    void deleteBio(){
        this.bio = null;
    }

    @Override
    public String toString(){
        return email +"@@@"+ password +"@@@"+ getProfilePictureString() +"@@@"+getName()+"@@@"+getGoals()+"@@@"+getInterests()+"@@@"+getBirthdayString()+"@@@"+getPronoun()+"@@@"+getBio();
    }

    //CUSTOMIZED METHODS
    @RequiresApi(api = Build.VERSION_CODES.O)
    String getAge(){
        LocalDate localBirthDate = this.birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentDate = LocalDate.now();
        return ChronoUnit.YEARS.between(localBirthDate, localCurrentDate) + "";
    }
}
