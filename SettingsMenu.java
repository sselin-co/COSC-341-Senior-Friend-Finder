package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class SettingsMenu extends AppCompatActivity{

    Switch sw_notifications, sw_activeStatus;
    EditText et_minAge, et_maxAge;
    ChipGroup chipGroup;
    Chip selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);

        //TODO: figure out how to save settings and load them

    }

    public void toggleNotifications(View view){ //TODO: figure out how to toggle notifications

    }

    public void themeToColor(View view){ //TODO: figure out how to change the theme for all activities
        this.setTheme(R.style.AgConnectTheme_Color);
    }

    public void themeToSimple(View view){ //TODO: figure out how to change the theme here, too
        this.setTheme(R.style.AgConnectTheme_Simple);
    }

    public void toggleProfileStatus(View view) { //TODO: decide what to do/show when active status is toggled

    }

    public void viewEditProfile(View view){
        Intent intent = new Intent(SettingsMenu.this, UserProfile.class);
        startActivity(intent);
        finish();
    }

    public void readAbout(View view){
        Intent intent = new Intent(SettingsMenu.this, About.class);
        startActivity(intent);
    }

    public void deleteAccount(View view){
        Intent intent = new Intent(SettingsMenu.this, Dialog_DeleteWarning.class);
        intent.putExtra("origin","deleteAccount");
        intent.putExtra("title", R.string.btn_delAcc);
        intent.putExtra("message", R.string.ins_delAccount);
        startActivity(intent);
    }

}
