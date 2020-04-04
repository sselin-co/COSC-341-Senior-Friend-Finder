package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Requests extends AppCompatActivity {

    LinearLayout harold, queen, bea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        AppGlobals.saveUserData(this);

        harold = findViewById(R.id.harold_linearLayout);
        queen = findViewById(R.id.queen_linearLayout);
        bea = findViewById(R.id.Bea_linearLayout);

        if(AppGlobals.requestSentHarold || !AppGlobals.answerRequestHarold){
            harold.setVisibility(View.VISIBLE);
            if(!AppGlobals.answerRequestHarold){
                TextView text = findViewById(R.id.requests_type_harold);
                text.setText(getString(R.string.requestReceived_harold));
            }
        }else{
            harold.setVisibility(View.GONE);
        }

        if(AppGlobals.requestSentQueen){
            queen.setVisibility(View.VISIBLE);
        }else{
            queen.setVisibility(View.GONE);
        }

        if(AppGlobals.requestSentBea){
            bea.setVisibility(View.VISIBLE);
        }else{
            bea.setVisibility(View.GONE);
        }
    }

    public void toEvents(View view){
        Intent intent = new Intent(this, Events.class);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(this, MyConnections_MyFriends.class);
        startActivity(intent);
    }

    public void toHarold(View view){
        Intent intent = new Intent(this, PotentialFriendProfile.class);
        intent.putExtra("name",getString(R.string.name_harold));
        startActivity(intent);
    }

    public void toQueen(View view){
        Intent intent = new Intent(this, PotentialFriendProfile.class);
        intent.putExtra("name",getString(R.string.name_queen));
        startActivity(intent);
    }

    public void toBea(View view){
        Intent intent = new Intent(this, PotentialFriendProfile.class);
        intent.putExtra("name",getString(R.string.name_bea));
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(this, Connect.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(this, SettingsMenu.class);
        startActivity(intent);
    }

}