package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Events extends AppCompatActivity {

    private LinearLayout layout_eventList;
    private static final int MAX_NUM_EVENTS = 5, MAX_NUM_FIELDS = 6;
    private String[][] events_array = new String[MAX_NUM_EVENTS][MAX_NUM_FIELDS];
    private int eventIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        layout_eventList = findViewById(R.id.events_layout_eventList);

        loadEvents();
        displayEvents();

    }

    private void displayEvents(){
        for(eventIdx = 0; eventIdx < MAX_NUM_EVENTS; eventIdx++){

            LinearLayout layout_event = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
                params.setMargins(8,8,8,8);
                layout_event.setLayoutParams(params);
                layout_event.setOrientation(LinearLayout.HORIZONTAL);
                layout_event.setClickable(true);
                layout_event.setTag(eventIdx);
                layout_event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(createIntent((int) v.getTag()));
                    }
                });

            ImageView image_eventPicture = createImageView(events_array[eventIdx][5]);

            LinearLayout layout_eventText = createEventTextLayout();

            TextView text_title = createTextView(events_array[eventIdx][0]);
                text_title.setTextSize(24);

            TextView text_date = createTextView(events_array[eventIdx][1]);
                text_date.setTextSize(20);

            TextView text_price = createTextView(events_array[eventIdx][3]);
                text_price.setTextSize(20);

            layout_eventList.addView(layout_event);
            layout_event.addView(image_eventPicture);
            layout_event.addView(layout_eventText);
            layout_eventText.addView(text_title);
            layout_eventText.addView(text_date);
            layout_eventText.addView(text_price);
        }
        eventIdx = 0;
    }

    private Intent createIntent(int eventIdx){
        Intent intent = new Intent(Events.this,ViewEvent.class);
        intent.putExtra("eventName",events_array[eventIdx][0]);
        intent.putExtra("eventDate",events_array[eventIdx][1]);
        intent.putExtra("eventLocation",events_array[eventIdx][2]);
        intent.putExtra("eventPrice",events_array[eventIdx][3]);
        intent.putExtra("eventDetails",events_array[eventIdx][4]);
        intent.putExtra("eventImg",events_array[eventIdx][5]);
        return intent;
    }

    private LinearLayout createEventTextLayout(){
        LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        return linearLayout;
    }

    private ImageView createImageView(String fileName){
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMarginEnd(16);
        imageView.setLayoutParams(params);
        imageView.setAdjustViewBounds(true);
        int resID = getResources().getIdentifier(fileName,"drawable", getPackageName());
        imageView.setImageResource(resID);
        return imageView;
    }

    private TextView createTextView(String text){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(text);
        return textView;
    }

    private void loadEvents(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getAssets().open("events.txt")))){
            String line = bufferedReader.readLine();
            while(line != null){
                events_array[eventIdx] = line.split(",");
                eventIdx++;
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            Toast.makeText(this, "Error loading events list", Toast.LENGTH_SHORT).show();
        }
        Log.d("MY_TAG", "loadEvents: " + events_array[0][0] + " " + events_array[0][1] + " " + events_array[0][2] + " " + events_array[0][3] + " " + events_array[0][4] + " " + events_array[0][5]);
    }

    public void toSettings(View view){
        Intent intent = new Intent(Events.this, SettingsMenu.class);
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(Events.this, Connect.class);
        startActivity(intent);
        finish();
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(Events.this, MyConnections.class);
        startActivity(intent);
        finish();
    }
}
