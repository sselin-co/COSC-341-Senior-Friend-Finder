package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Dialog_DeleteWarning extends AppCompatActivity {

    TextView tv_title, tv_message;
    Button btn_cancel, btn_proceed;
    String origin;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_warning);

        //Link to id's
        tv_title = findViewById(R.id.delWarn_text_Title);
        tv_message = findViewById(R.id.delWarn_text_message);
        btn_cancel = findViewById(R.id.delWarn_button_cancel);
        btn_proceed = findViewById(R.id.delWarn_button_proceed);

        //Get the intent that started this activity and extract the extras
        extra = getIntent().getExtras();
        if(extra != null) {
            origin = extra.getString("origin");
            tv_title.setText(extra.getString("title"));
            tv_message.setText(extra.getString("message"));
        }else{
            tv_title.setText(R.string.error); //TODO: do we want to change the error message?
            tv_message.setText(R.string.desc_error);
        }


        //this is the default action of the cancel button -- only the "proceed" button will change (see below)
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        //The origin string determines the function of the "proceed" button
        switch (origin) {
            case "deleteAccount":
                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(Dialog_DeleteWarning.this, Dialog_DeleteWarning.class);
                        intent.putExtra("origin", "deleteAccountFINAL");
                        intent.putExtra("title", getString(R.string.btn_delAcc));
                        intent.putExtra("message", getString(R.string.ins_delAccountFINAL));
                        startActivity(intent);
                    }
                });
                break;
            case "deleteAccountFINAL":
                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Toast.makeText(Dialog_DeleteWarning.this, "Deleting account...", Toast.LENGTH_SHORT).show();

                        AppGlobals.user = null;
                        AppGlobals.answerRequestHarold = AppGlobals.friendsWithHarold = AppGlobals.requestSentQueen = AppGlobals.requestSentBea = AppGlobals.requestSentHarold = false;

                        File destination = new File(Dialog_DeleteWarning.this.getFilesDir(), "text"); //in the files directory, there is a text directory
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination + "/user.txt", false))) {
                            writer.write("");  //overwrite with nothing

                        } catch (Exception e) { //if any error occurs
                            Log.d("My_Test", "Error: " + e.getMessage()); //send the error message to the log
                            Toast.makeText(Dialog_DeleteWarning.this, "Error", Toast.LENGTH_SHORT).show(); //show a toast so the user knows an error occurred
                        }

                        //restart application
                        Intent restartIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        startActivity(restartIntent);

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent cancelIntent = new Intent(getApplicationContext(), SettingsMenu.class);
                        startActivity(cancelIntent);
                        finish();
                    }
                });
                break;
            case "deleteProfileItem":

                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String tag = extra.getString("tag");

                        assert tag != null;
                        switch (tag) {
                            case "picture":
                                AppGlobals.user.deleteProfilePicture();
                                Toast.makeText(Dialog_DeleteWarning.this, "Profile picture deleted", Toast.LENGTH_SHORT).show();
                                break;
                            case "goals":
                                AppGlobals.user.deleteGoals();
                                Toast.makeText(Dialog_DeleteWarning.this, "Goals deleted", Toast.LENGTH_SHORT).show();
                                break;
                            case "interests":
                                AppGlobals.user.deleteInterests();
                                Toast.makeText(Dialog_DeleteWarning.this, "Interests deleted ", Toast.LENGTH_SHORT).show();
                                break;
                            case "age":
                                AppGlobals.user.deleteBirthday();
                                Toast.makeText(Dialog_DeleteWarning.this, "Age deleted ", Toast.LENGTH_SHORT).show();
                                break;
                            case "pronoun":
                                AppGlobals.user.deletePronoun();
                                Toast.makeText(Dialog_DeleteWarning.this, "Preferred pronoun deleted", Toast.LENGTH_SHORT).show();
                                break;
                            case "bio":
                                AppGlobals.user.deleteBio();
                                Toast.makeText(Dialog_DeleteWarning.this, "Biography deleted", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(Dialog_DeleteWarning.this, "ERROR. Nothing deleted.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        Intent intent = new Intent(Dialog_DeleteWarning.this, ViewEditProfile.class);
                        startActivity(intent);
                        finish();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent cancelIntent = new Intent(getApplicationContext(), ViewEditProfile.class);
                        startActivity(cancelIntent);
                        finish();
                    }
                });
                break;
            case "unfriend":
                final String friendName = extra.getString("friendName");
                btn_proceed.setText(R.string.btn_unfriend);
                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(Dialog_DeleteWarning.this, "Unfriended " + friendName, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Dialog_DeleteWarning.this, MyConnections_MyFriends.class);
                        AppGlobals.answerRequestHarold = true;
                        AppGlobals.friendsWithHarold = false;
                        startActivity(intent);
                        finish();
                    }
                });
                btn_cancel.setText(R.string.btn_stayFriends);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AppGlobals.answerRequestHarold = true;
                        AppGlobals.friendsWithHarold = true;
                        Intent cancelIntent = new Intent(getApplicationContext(), FriendProfile.class);
                        cancelIntent.putExtra("name", friendName);
                        startActivity(cancelIntent);
                        finish();
                    }
                });

                break;
            case "addDeclineRequest":
                AppGlobals.answerRequestHarold = true;
                btn_proceed.setText(R.string.btn_decline);
                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AppGlobals.friendsWithHarold = false;
                        Intent cancelIntent = new Intent(getApplicationContext(), PotentialFriendProfile.class);
                        cancelIntent.putExtra("declined", "declined");
                        startActivity(cancelIntent);
                        finish();
                    }
                });
                btn_cancel.setText(R.string.btn_addFriend);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AppGlobals.friendsWithHarold = true;
                        Intent intent = new Intent(getApplicationContext(), MyConnections_MyFriends.class);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
