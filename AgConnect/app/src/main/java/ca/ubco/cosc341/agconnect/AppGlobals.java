package ca.ubco.cosc341.agconnect;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleObserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class AppGlobals extends Application implements LifecycleObserver {


    public static final String CHANNEL_1_ID = "channel1";

    private static final int MIN_AGE_LIMIT = 18, MAX_AGE_LIMIT = 130; //these are static

    private static int minAge = MIN_AGE_LIMIT, maxAge = MAX_AGE_LIMIT; //default values but will change

    public static boolean answerRequestHarold, friendsWithHarold, requestSentQueen, requestSentBea, requestSentHarold;
    public static User user;
    public static UserList friendList;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel  channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }


    public static int getMinAge(){
        return minAge;
    }

    public static int getMaxAge(){
        return maxAge;
    }

    public static int getMinAgeLimit(){
        return MIN_AGE_LIMIT;
    }

    public static int getMaxAgeLimit(){
        return MAX_AGE_LIMIT;
    }

    public static void setMinAge(int minAge){
        AppGlobals.minAge = minAge;
    }

    public static void setMaxAge(int maxAge){
        AppGlobals.maxAge = maxAge;
    }

    public static void saveUserData(Context context){
        File destination = new File(context.getApplicationContext().getFilesDir(), "text"); //in the files directory, there is a text directory
        if (!destination.exists()) { //if it doesn't exist yet, create it
            destination.mkdir();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination + "/user.txt", false))) {
            writer.write(AppGlobals.user.toString() + "@@@"+ AppGlobals.answerRequestHarold + "@@@" + AppGlobals.friendsWithHarold + "@@@" + AppGlobals.requestSentQueen + "@@@" + AppGlobals.requestSentBea + "@@@" + AppGlobals.requestSentHarold);

        } catch (Exception e) { //if any error occurs
            Log.d("My_Test", "Error: " + e.getMessage()); //send the error message to the log
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show(); //show a toast so the user knows an error occurred
        }
    }

}
