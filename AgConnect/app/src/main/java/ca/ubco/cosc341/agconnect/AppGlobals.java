package ca.ubco.cosc341.agconnect;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class AppGlobals extends Application {


    public static final String CHANNEL_1_ID = "channel1";

    private static final int MIN_AGE_LIMIT = 18, MAX_AGE_LIMIT = 130; //these are static

    private static int minAge = MIN_AGE_LIMIT, maxAge = MAX_AGE_LIMIT; //default values but will change

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

}
