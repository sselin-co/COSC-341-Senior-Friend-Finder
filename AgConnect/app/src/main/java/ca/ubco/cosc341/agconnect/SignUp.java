package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText email, password;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void onClick(View v) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        user.setLoginCredentials(emailString, passwordString);

        // Currently only checks if the fields have anything in them
        // Needs strings to be taken from strings.xml
        if (emailString.equals("") && passwordString.equals("")){
            Toast toast = Toast.makeText(this, getString(R.string.ins_enterEandP), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else if (emailString.equals("")){
            Toast toast = Toast.makeText(this, getString(R.string.ins_enterE), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else if (passwordString.equals("")){
            Toast toast = Toast.makeText(this, getString(R.string.ins_enterP), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(this, getString(R.string.ins_signUpThank), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 800);
            toast.show();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent intent = new Intent(SignUp.this, CreateProfile.class);
                    intent.putExtra("user", user);
                    Toast toast = Toast.makeText(SignUp.this, getString(R.string.ins_createProfile), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 800);
                    toast.show();
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }


}
