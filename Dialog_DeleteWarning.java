package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog_DeleteWarning extends AppCompatActivity {

    TextView tv_title, tv_message;
    Button btn_cancel, btn_proceed;
    String origin;

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
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            origin = extra.getString("origin");
            tv_title.setText(extra.getInt("title"));
            tv_message.setText(extra.getInt("message"));
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
                    intent.putExtra("title", R.string.btn_delAcc);
                    intent.putExtra("message", R.string.ins_delAccountFINAL);
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
        }else if(origin.equals("deleteItem")){
            btn_proceed.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //TODO: do something
                    Toast.makeText(Dialog_DeleteWarning.this, "Deleting item...", Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            //TODO: another origin
            Toast.makeText(Dialog_DeleteWarning.this, "Deleting something...", Toast.LENGTH_SHORT).show();
        }
    }
}
