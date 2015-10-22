package jefferyvicente.meetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


import com.parse.Parse;
import com.parse.*;

import com.parse.ParseUser;

import java.util.ArrayList;

public class newEventInfo extends Activity {

    private Button button;
    private NewEventCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //button = (Button)findViewById(R.id.createEventButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_info);
        getActionBar().hide();

        // Set the ListView adapter
        adapter = new NewEventCustomAdapter(this);
        adapter.setTextKey("inviteeName");
        ListView listView = (ListView) findViewById(R.id.invitee_listView);
        listView.setAdapter(adapter);

        //createEvent();
        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event_info, menu);
        return true;
    }

    private void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.saveEvent_button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText name_editText = (EditText) findViewById(R.id.name_editText);
                String name = name_editText.getText().toString();

                EditText details_editText = (EditText) findViewById(R.id.details_editText);
                String details = details_editText.getText().toString();

                EditText location_editText = (EditText) findViewById(R.id.location_editText);
                String location = location_editText.getText().toString();

                EditText date_editText = (EditText) findViewById(R.id.date_editText);
                String date = date_editText.getText().toString();

                EditText time_editText = (EditText) findViewById(R.id.time_editText);
                String time = time_editText.getText().toString();

                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserString = ParseUser.getCurrentUser().getUsername();

                ParseObject eventinfo = new ParseObject("event");
                eventinfo.put("eventName", name);
                eventinfo.put("eventDetails", details);
                eventinfo.put("eventLocationAddress", location);
                eventinfo.put("eventDate", date);
                eventinfo.put("eventTime", time);
                eventinfo.put("eventCreator", currentUser);
                eventinfo.saveInBackground();
                System.out.println("Saved in background");
                System.out.println("Current User ID: " + currentUser);
                System.out.println("Current User ID String:" + currentUserString);

                // Get the names of the friends who were checked, send them a notification
                ArrayList<String> inviteeList = adapter.getInviteeList();
                if(inviteeList.isEmpty())
                    System.out.println("inviteeList was empty");
                for(String invitee : inviteeList)
                {
                    ParseQuery query = ParseInstallation.getQuery();
                    query.whereEqualTo("name", invitee);
                    ParsePush.sendMessageInBackground("You've been invited to a Meetup Event!", query);
                }

                // After new event created, go back to eventView
                Intent intent = new Intent(context, eventView.class);
                startActivity(intent);
            }

        });
    }


    private void createEvent(){

        final Context context = this;
       // Button button = (Button)findViewById(R.id.createEventButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                EditText name_editText = (EditText)findViewById(R.id.name_editText);
                String name = name_editText.getText().toString();

                EditText details_editText = (EditText)findViewById(R.id.details_editText);
                String details = details_editText.getText().toString();

                EditText location_editText = (EditText)findViewById(R.id.location_editText);
                String location = location_editText.getText().toString();

                EditText date_editText = (EditText)findViewById(R.id.date_editText);
                String date = date_editText.getText().toString();

                EditText time_editText = (EditText)findViewById(R.id.time_editText);
                String time = time_editText.getText().toString();

                ParseObject eventinfo = new ParseObject("event");
                eventinfo.put("eventName", name);
                eventinfo.put("eventDetails", details);
                eventinfo.put("eventLocationAddress", location);
                eventinfo.put("eventDate", date);
                eventinfo.put("eventTime", time);
                eventinfo.saveInBackground();


                //Intent intent = new Intent(context, newEventInfo.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}