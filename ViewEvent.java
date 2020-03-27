package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewEvent extends AppCompatActivity {

    TextView text_Name, text_DateTime, text_Location, text_Price, text_Details;
    ImageView image_Picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        text_Name = findViewById(R.id.viewEvent_text_Name);
        text_DateTime = findViewById(R.id.viewEvent_text_DateTime);
        text_Location = findViewById(R.id.viewEvent_text_Location);
        text_Price = findViewById(R.id.viewEvent_text_Price);
        text_Details = findViewById(R.id.viewEvent_text_Details);
        image_Picture = findViewById(R.id.viewEvent_image_Picture);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            text_Name.setText(extra.getString("eventName"));
            text_DateTime.setText(extra.getString("eventDate"));
            text_Location.setText(extra.getString("eventLocation"));
            text_Price.setText(extra.getString("eventPrice"));
            text_Details.setText(extra.getString("eventDetails"));
            int resID = getResources().getIdentifier(extra.getString("eventImg"),"drawable", getPackageName());
            image_Picture.setImageResource(resID);
        }
    }

    public void toPrevious(View view){
        super.onBackPressed();
    }

    public void toSettings(View view){
        Intent intent = new Intent(ViewEvent.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(ViewEvent.this, Connect.class);
        startActivity(intent);
        finish();
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(ViewEvent.this, MyConnections.class);
        startActivity(intent);
        finish();
    }

    public void toEvents(View view){
        Intent intent = new Intent(ViewEvent.this, Events.class);
        startActivity(intent);
        finish();
    }
}
