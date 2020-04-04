package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Events extends AppCompatActivity {

    private LinearLayout layout_eventList;
    private TextView text_sortByDate, text_sortByLocation, text_sortByPrice;
    private EventList date_asc = new EventList(), loc_asc = new EventList(), price_asc = new EventList(); //ascending: 1 2 3
    private EventList date_des = new EventList(), loc_des = new EventList(), price_des = new EventList(); // descending 4 5 6
    private int eventListNum = 1;
    private int eventIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //Link to ID's
        layout_eventList = findViewById(R.id.events_layout_eventList);
        text_sortByDate = findViewById(R.id.events_text_filter1);
        text_sortByLocation = findViewById(R.id.events_text_filter2);
        text_sortByPrice = findViewById(R.id.events_text_filter3);

        //Set tags for sorting. 0 = no sort. 1 = ascending. 2 = descending.
        text_sortByDate.setTag(0);
        text_sortByLocation.setTag(0);
        text_sortByPrice.setTag(0);

        //set sort icon (default is date)
        setSortImgTag(text_sortByDate);

        //reads from text file in assets and loads events into linked list
        loadEvents(); // note: the events that were read from the file are already sorted by date

        initializeEventLists();

        //displays the events
        displayEvents(date_asc);
        eventListNum = 1;

        //Filter by buttons
        text_sortByDate.setOnClickListener(new View.OnClickListener() { //when the SORT-BY-DATE is tapped
            @Override
            public void onClick(View v) {
                removeSortImgTag(text_sortByLocation); //remove the icon from location
                removeSortImgTag(text_sortByPrice); //remove the icon from price
                setSortImgTag(text_sortByDate); // show icon on date
                eventListNum = 1;
                setSort(text_sortByDate, date_asc); //take this linked list, check its tag, sort it accordingly, and display it
            }
        });

        text_sortByLocation.setOnClickListener(new View.OnClickListener() { //when the SORT-BY-LOCATION is tapped
            @Override
            public void onClick(View v) {
                removeSortImgTag(text_sortByDate); //remove the icon on date
                removeSortImgTag(text_sortByPrice); //remove the icon on price
                setSortImgTag(text_sortByLocation); //show icon on location
                eventListNum = 2;
                setSort(text_sortByLocation, loc_asc); //take this linked list, check its tag, sort it accordingly, and display it
            }
        });

        text_sortByPrice.setOnClickListener(new View.OnClickListener() { //when the SORT-BY-PRICE is tapped
            @Override
            public void onClick(View v) {
                removeSortImgTag(text_sortByDate); //remove the icon on date
                removeSortImgTag(text_sortByLocation); //remove the icon on location
                setSortImgTag(text_sortByPrice); //show icon on price
                eventListNum = 3;
                setSort(text_sortByPrice, price_asc); //take this linked list, check its tag, sort it accordingly, and display it

            }
        });
    }

    private void setSort(TextView textView, EventList asc){
        if( (int)textView.getTag() == 1){ // if the tag = 1
            //eventListNum += 0;
            displayEvents(asc); //display the linked list received
        }else if( (int)textView.getTag() == 2){ // if the tag = 2
            eventListNum += 3;
            displayEvents(reverse(asc)); // display list in reverse order
        }
    }

    private void setSortImgTag(TextView textView){
        if((int)textView.getTag() == 0 || (int)textView.getTag() == 2){
            setSortDown(textView); //icon and tag change
        }else if((int)textView.getTag() == 1){
            setSortUp(textView); //icon and tag change
        }
    }

    private void setSortDown(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_down_24dp, 0, 0, 0); //have the arrow face down
        textView.setTag(1); //tag is now 1
    }

    private void setSortUp(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_up_24dp, 0, 0, 0);
        textView.setTag(2);

    }

    private void removeSortImgTag(TextView textView){
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        textView.setTag(0);

    }

    private void initializeEventLists(){
        //ascending
        loc_asc = date_asc.sortEventsByLocation();
        price_asc = date_asc.sortEventsByPrice();

        //descending
        date_des = reverse(date_asc);
        loc_des = reverse(loc_asc);
        price_des = reverse(price_asc);
    }

    private EventList reverse(EventList ascending){
        EventList descending = new EventList(); // take the list in ascending order and reverse it
        int max = ascending.size();
        for(eventIdx = 0; eventIdx < max; eventIdx++){
            descending.add(eventIdx, ascending.get(max-eventIdx-1));
        }
        return descending;
    }

    private void displayEvents(EventList eventList){
        clearEvents(); //when sorting, it displays more, so we need to clear the events every time it's called
        for(eventIdx = 0; eventIdx < eventList.size(); eventIdx++){
            LinearLayout layout_event = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8,8,8,8);
                layout_event.setLayoutParams(params);
                layout_event.setOrientation(LinearLayout.HORIZONTAL);
                layout_event.setClickable(true);
                layout_event.setTag(eventIdx + ";" + eventListNum);
                layout_event.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(createIntent(v.getTag().toString()));
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

    private Intent createIntent(String tag){
        String[] tags = tag.split(";");
        int eventId = Integer.parseInt(tags[0]);
        int listId = Integer.parseInt(tags[1]);
        EventList eventList = new EventList();
        switch(listId){
            case 1: eventList = date_asc; break;
            case 2: eventList = loc_asc; break;
            case 3: eventList = price_asc; break;
            case 4: eventList = date_des; break;
            case 5: eventList = loc_des; break;
            case 6: eventList = price_des; break;
        }
        Log.d("My_Test", "createIntent:  eventId: " + eventId + " listId: " + listId + " eventList: " + eventList);
        Intent intent = new Intent(Events.this,ViewEvent.class);
        intent.putExtra("eventName", eventList.get(eventId).getEventName());
        intent.putExtra("eventDate", eventList.get(eventId).getEventDateTime());
        intent.putExtra("eventLocation", eventList.get(eventId).getEventLocation());
        intent.putExtra("eventPrice",""+ eventList.get(eventId).getEventPriceS());
        intent.putExtra("eventDetails", eventList.get(eventId).getEventDetails());
        intent.putExtra("eventImg", eventList.get(eventId).getEventPhotoName());
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
                date_asc.add(eventIdx, new Event(record[0], record[1], record[2], Double.parseDouble(record[3]), record[4], record[5]));
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
        Intent intent = new Intent(Events.this, MyConnections_MyFriends.class);
        startActivity(intent);
        finish();
    }
}
