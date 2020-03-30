package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Events extends AppCompatActivity {

    private LinearLayout layout_eventList;
    private TextView filter_date, filter_location, filter_price;
    private EventList allEvents = new EventList(), loc_asc = new EventList(), price_asc = new EventList();
    private int eventIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //Link to ID's
        layout_eventList = findViewById(R.id.events_layout_eventList);
        filter_date = findViewById(R.id.events_text_filter1);
        filter_location = findViewById(R.id.events_text_filter2);
        filter_price = findViewById(R.id.events_text_filter3);

        //Set tags for sorting
        filter_date.setTag(0);
        setSortImgTag(filter_date);
        filter_location.setTag(0);
        filter_price.setTag(0);

        //read from the text file and display the events
        loadEvents();
        displayEvents(allEvents);

        //Filter by buttons
        filter_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSortImgTag(filter_location);
                removeSortImgTag(filter_price);
                setSortImgTag(filter_date);
                setSort(filter_date, allEvents);
            }
        });

        filter_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSortImgTag(filter_date);
                removeSortImgTag(filter_price);
                setSortImgTag(filter_location);
                loc_asc = allEvents.sortEventsByLocation();
                setSort(filter_location, loc_asc);
            }
        });

        filter_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSortImgTag(filter_date);
                removeSortImgTag(filter_location);
                setSortImgTag(filter_price);
                price_asc = allEvents.sortEventsByPrice();
                setSort(filter_price, price_asc);

            }
        });
    }

    private void setSort(TextView textView, EventList asc){
        if( (int)textView.getTag() == 1){
            displayEvents(asc);
        }else if( (int)textView.getTag() == 2){
            EventList desc = new EventList();
            int max = asc.size();
            for(eventIdx = 0; eventIdx < max; eventIdx++){
                desc.add(eventIdx, asc.get(max-eventIdx-1));
            }
            displayEvents(desc);
        }
    }

    private void setSortImgTag(TextView textView){
        if((int)textView.getTag() == 0 || (int)textView.getTag() == 2){
            setSortDown(textView);
        }else if((int)textView.getTag() == 1){
            setSortUp(textView);
        }
    }

    private void setSortDown(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_down_24dp, 0, 0, 0);
        textView.setTag(1);
    }

    private void setSortUp(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_up_24dp, 0, 0, 0);
        textView.setTag(2);

    }

    private void removeSortImgTag(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        textView.setTag(0);

    }

    private void displayEvents(EventList eventList){
        clearEvents();
        for(eventIdx = 0; eventIdx < eventList.size(); eventIdx++){
            LinearLayout layout_event = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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

            ImageView image_eventPicture = createImageView(eventList.get(eventIdx).getEventPhotoName());

            LinearLayout layout_eventText = createEventTextLayout();

            TextView text_title = createTextView(eventList.get(eventIdx).getEventName());
                text_title.setTextSize(24);
                text_title.setTextColor(getColor(R.color.black));
                text_title.setTypeface(null, Typeface.BOLD);

            TextView text_date = createTextView(eventList.get(eventIdx).getEventDateTime());
                text_date.setTextSize(20);
                text_date.setTextColor(getColor(R.color.black));

            TextView text_location = createTextView(eventList.get(eventIdx).getEventLocation());
                text_location.setTextSize(18);
                text_location.setTextColor(getColor(R.color.black));

            TextView text_price = createTextView(getString(R.string.event_price, eventList.get(eventIdx).getEventPriceS()));
                text_price.setTextSize(20);
                text_price.setTextColor(getColor(R.color.colorAccentTeal));

            layout_eventList.addView(layout_event);
            layout_event.addView(image_eventPicture);
            layout_event.addView(layout_eventText);
            layout_eventText.addView(text_title);
            layout_eventText.addView(text_date);
            layout_eventText.addView(text_location);
            layout_eventText.addView(text_price);
        }
        eventIdx = 0;
    }

    private Intent createIntent(int eventIdx){
        Intent intent = new Intent(Events.this,ViewEvent.class);
        intent.putExtra("eventName",allEvents.get(eventIdx).getEventName());
        intent.putExtra("eventDate",allEvents.get(eventIdx).getEventDateTime());
        intent.putExtra("eventLocation",allEvents.get(eventIdx).getEventLocation());
        intent.putExtra("eventPrice",""+allEvents.get(eventIdx).getEventPriceS());
        intent.putExtra("eventDetails",allEvents.get(eventIdx).getEventDetails());
        intent.putExtra("eventImg",allEvents.get(eventIdx).getEventPhotoName());
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
                String[] record = line.split(";");
                allEvents.add(eventIdx, new Event(record[0], record[1], record[2], Double.parseDouble(record[3]), record[4], record[5]));
                eventIdx++;
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            Toast.makeText(this, "Error loading events list", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearEvents(){
        layout_eventList.removeAllViews();
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
