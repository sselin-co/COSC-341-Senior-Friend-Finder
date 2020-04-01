package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsMenu extends AppCompatActivity{

    public static boolean isNotificationsOn, isActiveStatusOn;

    Switch sw_notifications, sw_activeStatus;
    EditText et_minAge, et_maxAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //values to set up at onCreate
        setContentView(R.layout.activity_settings_menu);

        //Link to IDs
        sw_notifications = findViewById(R.id.settings_switch_notifications);
        sw_activeStatus = findViewById(R.id.settings_switch_activeStatus);
        et_minAge = findViewById(R.id.settings_edit_minAge);
        et_maxAge = findViewById(R.id.settings_edit_maxAge);

        //set up variables and views with these methods
        setAgeRangeValues();
        setNotificationValues();

        //When the user types into the min/max age range values, provide feedback to the user
        et_minAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int minAgeEntered = getMinAgeEntered(et_minAge);
                int maxAgeEntered = getMaxAgeEntered(et_maxAge);
                if(minAgeEntered > maxAgeEntered){
                    Toast.makeText(SettingsMenu.this, "Min age cannot be greater than max age", Toast.LENGTH_SHORT).show();
                    return true;
                }else if(minAgeEntered < AppGlobals.getMinAgeLimit()){
                    Toast.makeText(SettingsMenu.this, "Min age cannot be less than " + AppGlobals.getMinAgeLimit(), Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    AppGlobals.setMinAge(minAgeEntered);
                    Toast.makeText(SettingsMenu.this, "Min age of potential friends is now " + minAgeEntered, Toast.LENGTH_SHORT).show();
                    et_minAge.clearFocus();
                    return false;
                }
            }
        });

        et_maxAge.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int minAgeEntered = getMinAgeEntered(et_minAge);
                int maxAgeEntered = getMaxAgeEntered(et_maxAge);
                if(maxAgeEntered < minAgeEntered){
                    Toast.makeText(SettingsMenu.this, "Max age cannot be less than min age", Toast.LENGTH_SHORT).show();
                    return true;
                }else if(maxAgeEntered > AppGlobals.getMaxAgeLimit()){
                    Toast.makeText(SettingsMenu.this, "Max age cannot be greater than " + AppGlobals.getMaxAgeLimit(), Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    AppGlobals.setMaxAge(maxAgeEntered);
                    Toast.makeText(SettingsMenu.this, "Age range of potential friends is now " + minAgeEntered + " to " + maxAgeEntered, Toast.LENGTH_SHORT).show();
                    et_maxAge.clearFocus();
                    return false;
                }
            }
        });

    }
    private void setNotificationValues(){
        isNotificationsOn = sw_notifications.isChecked();
        isActiveStatusOn = sw_activeStatus.isChecked();
    }

    private void setAgeRangeValues(){
        et_minAge.setText(String.valueOf(AppGlobals.getMinAge()));
        et_maxAge.setText(String.valueOf(AppGlobals.getMaxAge()));
    }

    private int getMinAgeEntered(EditText editText){
        String minAgeText = editText.getText().toString();
        if(minAgeText.isEmpty()){
            return AppGlobals.getMinAgeLimit();
        }else{
            return Integer.parseInt(minAgeText);
        }
    }
    private int getMaxAgeEntered(EditText editText){
        String maxAgeText = editText.getText().toString();
        if(maxAgeText.isEmpty()){
            return AppGlobals.getMaxAgeLimit();
        }else{
            return Integer.parseInt(maxAgeText);
        }
    }

    public void toggleNotifications(View view){ //when the switch is toggled -- NOTE: this does NOT actually control if notifications are on or off. It just creates a notification to "fake it" to the user
        if(sw_notifications.isChecked()){ //what is the state of the switched? If it is checked
            isNotificationsOn = true; //change the global variable to true;
            sendNotification(this,"AgConnect Notifications", "You turned notifications on!");
        }else{
            isNotificationsOn = false;
            Toast.makeText(this, "You turned notifications off", Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendNotification(Context context, String contentTitle, String contentText){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if(isNotificationsOn){
            Notification notification = new NotificationCompat.Builder(context, AppGlobals.CHANNEL_1_ID) //this is how to build the notification
                    .setSmallIcon(R.drawable.ic_notifications_active_black_24dp) //choose an icon
                    .setContentTitle(contentTitle) //title is received as a method parameter
                    .setContentText(contentText) //text is received as a method parameter
                    .setPriority(NotificationCompat.PRIORITY_HIGH) //this MUST match the priority level of the channel in the Java class AppGlobals
                    .setCategory(NotificationCompat.CATEGORY_STATUS) //the category depends on what it is for -- see documentation if you plan to copy and paste this code to create a notification
                    .build(); //this is the last step in building the notification
            notificationManager.notify(1, notification); // use the notification manager to send the notification
        }
    }

    public void themeToColor(View view){ //TODO: figure out how to change the theme for all activities
        this.setTheme(R.style.AgConnectTheme_Color);
    }

    public void themeToSimple(View view){ //TODO: figure out how to change the theme here, too
        this.setTheme(R.style.AgConnectTheme_Simple);
    }

    public void toggleProfileStatus(View view) {
        isActiveStatusOn = sw_activeStatus.isChecked();
    }

    public void toConnect(View view){
        Intent intent = new Intent(SettingsMenu.this, Connect.class);
        startActivity(intent);
        finish();
    }

    public void toViewEditProfile(View view){
        Intent intent = new Intent(SettingsMenu.this, ViewEditProfile.class);
        startActivity(intent);
        finish();
    }

    public void toAbout(View view){
        Intent intent = new Intent(SettingsMenu.this, About.class);
        startActivity(intent);
    }

    public void deleteAccount(View view){
        Intent intent = new Intent(SettingsMenu.this, Dialog_DeleteWarning.class);
        intent.putExtra("origin","deleteAccount");
        intent.putExtra("title", getString(R.string.btn_delAcc));
        intent.putExtra("message", getString(R.string.ins_delAccount));
        startActivity(intent);
        finish();
    }

    public void toPreviousActivity(View view){
        super.onBackPressed();
    }

}
