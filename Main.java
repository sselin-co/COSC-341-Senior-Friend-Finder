package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toEvents(View view){
        Intent intent = new Intent(Main.this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(Main.this, MyConnections.class);
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(Main.this, Connect.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(Main.this, SettingsMenu.class);
        startActivity(intent);
    }


}
