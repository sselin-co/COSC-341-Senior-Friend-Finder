package ca.ubco.cosc341.agconnect;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Event{
    private final String eventName;
    private final String eventDateTime;
    private Date eventDate;
    private final String eventLocation;
    private final double eventPrice;
    private final String eventDetails;
    private final String eventPhotoName;

    Event(String eventName, String eventDateTime, String eventLocation, double price, String details, String eventPhotoName) {
        this.eventName = eventName;
        this.eventDateTime = setEventDateTime(eventDateTime);
        this.eventLocation = eventLocation;
        this.eventPrice = price;
        this.eventDetails = details;
        this.eventPhotoName = eventPhotoName;
    }

    String getEventName() {
        return eventName;
    }

    String getEventDateTime() {
        return eventDateTime;
    }

    private String setEventDateTime(String inputDate){
        Date date;
        try{
            date = new SimpleDateFormat("dd MMMMM yyyy HH:mm",Locale.CANADA).parse(inputDate);

            this.eventDate = date;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.CANADA);
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.CANADA);

            assert date != null;
            String formattedDate = "" + dateFormat.format(date);
            String formattedTime = "" + timeFormat.format(date);

            return formattedDate + " at " + formattedTime;
        }catch(Exception e){
            Log.d("MY_TAG", "setEventDateTime: " + e.getMessage());
            return "Error retrieving date and time";
        }

    }

    Date getEventDate(){
        return eventDate;
    }

    String getEventLocation() {
        return eventLocation;
    }

    String getEventPriceS() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.eventPrice);
    }

    double getEventPrice(){
        return eventPrice;
    }

    String getEventDetails() {
        return eventDetails;
    }
    String getEventPhotoName(){
        return eventPhotoName;
    }

}
