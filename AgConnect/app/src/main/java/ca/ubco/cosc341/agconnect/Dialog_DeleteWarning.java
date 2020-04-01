package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            tv_title.setText("Error"); //TODO: do we want to change the error message?
            tv_message.setText("Sorry! It looks like we missed something here.");
        }


        //this is the default action of the cancel button -- only the "proceed" button will change (see below)
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        //The origin string determines the function of the "proceed" button
        if(origin.equals("deleteAccount")){
            btn_proceed.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(Dialog_DeleteWarning.this, Dialog_DeleteWarning.class);
                    intent.putExtra("origin","deleteAccountFINAL");
                    intent.putExtra("title", getString(R.string.btn_delAcc));
                    intent.putExtra("message", getString(R.string.ins_delAccountFINAL));
                    startActivity(intent);
                }
            });
        }else if(origin.equals("deleteAccountFINAL")){
            btn_proceed.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //TODO: DONE? delete this toast that was used for testing
                    Toast.makeText(Dialog_DeleteWarning.this, "Deleting account...", Toast.LENGTH_SHORT).show();

                    //TODO: delete the user information
                    //code here

                    //restart application
                    Intent restartIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    startActivity(restartIntent);

                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent cancelIntent = new Intent(getApplicationContext(),SettingsMenu.class);
                    startActivity(cancelIntent);
                    finish();
                }
            });
        }else if(origin.equals("deleteProfileItem")){

            btn_proceed.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    User user = (User) getIntent().getSerializableExtra("user");
                    String tag = extra.getString("tag");

                    assert tag != null; assert user != null;
                    Log.d("MY_TAG", "onClick: " + tag);
                    switch (tag) {
                        case "pictureName":
                            user.deletePictureName();
                            Toast.makeText(Dialog_DeleteWarning.this, "Profile picture deleted", Toast.LENGTH_SHORT).show();
                            break;
                        case "goals":
                            user.deleteGoals();
                            Toast.makeText(Dialog_DeleteWarning.this, "Goals deleted", Toast.LENGTH_SHORT).show();
                            break;
                        case "interests":
                            user.deleteInterests();
                            Toast.makeText(Dialog_DeleteWarning.this, "Interests deleted ", Toast.LENGTH_SHORT).show();
                            break;
                        case "age":
                            user.deleteBirthday();
                            Toast.makeText(Dialog_DeleteWarning.this, "Age deleted ", Toast.LENGTH_SHORT).show();
                            break;
                        case "pronoun":
                            user.deletePronoun();
                            Toast.makeText(Dialog_DeleteWarning.this, "Preferred pronoun deleted", Toast.LENGTH_SHORT).show();
                            break;
                        case "bio":
                            user.deleteBio();
                            Toast.makeText(Dialog_DeleteWarning.this, "Biography deleted", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(Dialog_DeleteWarning.this, "ERROR. Nothing deleted.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    Intent intent = new Intent(Dialog_DeleteWarning.this, ViewEditProfile.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    User user = (User) getIntent().getSerializableExtra("user");
                    Intent cancelIntent = new Intent(getApplicationContext(),ViewEditProfile.class);
                    cancelIntent.putExtra("user",user);
                    startActivity(cancelIntent);
                    finish();
                }
            });
        }else{
            //TODO: another origin
            Toast.makeText(Dialog_DeleteWarning.this, "Deleting something...", Toast.LENGTH_SHORT).show();
        }
    }
}
