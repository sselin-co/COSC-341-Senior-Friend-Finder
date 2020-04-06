package ca.ubco.cosc341.agconnect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private Bitmap profilePicture;

    //CONSTRUCTOR
    public User(){

    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String profilePicture, String name, String goals, String interests, Date birthday, String pronoun, String bio, Context context) {
        setProfilePicture(profilePicture);
        this.name = name;
        this.goals = goals;
        this.interests = interests;
        this.birthday = birthday;
        this.pronoun = pronoun;
        this.bio = bio;
    }

    Bitmap getProfilePicture(){
        return this.profilePicture;
    }
    String getProfilePictureString(){
        if(this.profilePicture == null){
            return "";
        }else{
            return BitMapToString(profilePicture);
        }
    }
    void setProfilePicture(Context context, Uri profilePicture){
        try{
            this.profilePicture = MediaStore.Images.Media.getBitmap(context.getContentResolver() , profilePicture);
        }catch(Exception e){
            Log.d("My_Test", "Error in User.setProfilePicture: " + e.getMessage());
        }

    }

    void setProfilePicture(String bitmapString){
        this.profilePicture = StringToBitMap(bitmapString);
    }

    void deleteProfilePicture(){
        this.profilePicture = null;
    }

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
    String getBirthdayString(){
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
        return email +"@@@"+ password +"@@@"+ BitMapToString(profilePicture) +"@@@"+getName()+"@@@"+getGoals()+"@@@"+getInterests()+"@@@"+getBirthdayString()+"@@@"+getPronoun()+"@@@"+getBio();
    }

    //CUSTOMIZED METHODS
    @RequiresApi(api = Build.VERSION_CODES.O)
    String getAge(){
        LocalDate localBirthDate = this.birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentDate = LocalDate.now();
        return ChronoUnit.YEARS.between(localBirthDate, localCurrentDate) + "";
    }

    private String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String result = Base64.encodeToString(b, Base64.DEFAULT);
        return result.replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)", "");
    }

    private Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
