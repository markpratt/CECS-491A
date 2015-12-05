package jefferyvicente.meetup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.*;

import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class newEventInfo extends Activity {

    private Button button;
    private NewEventCustomAdapter adapter;
    private ParseObject eventinfo;
    public ParseGeoPoint point;

    private static final int REQUEST_PLACE_PICKER = 1;
    Context mContext = this;


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
        editTextClickerListener();
        editTextDateListener();
        editTextTimeListener();
        addListenerOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_event_info, menu);
        return true;
    }

    private void editTextClickerListener(){
        EditText x = (EditText) findViewById(R.id.location_editText);

        x.setClickable(true);
        x.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                placepicker();
                System.out.println("IT WORKS!!!");
            }
        });
    }

    private void editTextDateListener()
    {
        final EditText dateText = (EditText) findViewById(R.id.date_editText);
        dateText.setInputType(InputType.TYPE_NULL);
        dateText.requestFocus();

        final DatePickerDialog DatePickerDialog;
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        dateText.setClickable(true);
        // Display the DatePickerDialog when date_editText clicked
        dateText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {DatePickerDialog.show();
            }
        });
    }

    private void editTextTimeListener()
    {
        final EditText timeText = (EditText) findViewById(R.id.time_editText);
        timeText.setInputType(InputType.TYPE_NULL);
        timeText.requestFocus();

        final TimePickerDialog TimePickerDialog;
        final SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        TimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                Calendar newDate = Calendar.getInstance();
                // year, month, and day are set to 1, 1, 1.  This doesn't matter if we're only displaying/sending HH:mm strings
                newDate.set(1, 1, 1, hourOfDay, minute);
                timeText.setText(timeFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), true);

        timeText.setClickable(true);
        // Display the TimePickerDialog when time_editText clicked
        timeText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {TimePickerDialog.show();
            }
        });
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

                // point == null MUST be checked, else eventinfo.put("placeLocation", point) may throw exception
                if(point == null)
                    Toast.makeText(getApplicationContext(), "You must choose a location", Toast.LENGTH_LONG).show();
                if(name.equals(""))
                    Toast.makeText(getApplicationContext(), "You must enter a name", Toast.LENGTH_LONG).show();
                if(location.equals(""))
                    Toast.makeText(getApplicationContext(), "You must choose a location", Toast.LENGTH_LONG).show();
                if(date.equals(""))
                    Toast.makeText(getApplicationContext(), "You must choose a date", Toast.LENGTH_LONG).show();
                if(time.equals(""))
                    Toast.makeText(getApplicationContext(), "You must choose a time", Toast.LENGTH_LONG).show();
                else
                {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    String currentUserString = ParseUser.getCurrentUser().getUsername();
                    eventinfo = new ParseObject("event");
                    eventinfo.put("eventName", name);
                    eventinfo.put("eventDetails", details);
                    eventinfo.put("eventLocationAddress", location);
                    eventinfo.put("eventDate", date);
                    eventinfo.put("eventTime", time);
                    eventinfo.put("eventCreator", currentUser);
                    eventinfo.put("placeLocation", point);

                    System.out.println("Current User ID: " + currentUser);
                    System.out.println("Current User ID String:" + currentUserString);

                    // Get the names of the friends who were checked
                    ArrayList<String> inviteeList = adapter.getInviteeList();
                    if (inviteeList.isEmpty())
                        System.out.println("inviteeList was empty");
                    // Add friends who were checked to invitees relation, send them a notification
                    ParseRelation<ParseUser> inv_relation = eventinfo.getRelation("invitees");
                    for (String invitee : inviteeList)
                    {
                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                        query.whereEqualTo("name", invitee);
                        try
                        {
                        /*  Normally, query.getFirstInBackground would be used, but we want query to
                            finish before saveInBackground */
                            ParseUser user = (ParseUser) query.getFirst();
                            inv_relation.add(user);
                        } catch (ParseException e)
                        {
                            System.out.println("Query didn't work");
                        }

                        // Send each invitee a push notification
                        ParseQuery query2 = ParseInstallation.getQuery();
                        query2.whereEqualTo("name", invitee);
                        ParsePush.sendMessageInBackground("You've been invited to a Meetup Event!", query2);

                    }

                    // Add current user (the event creator) to attendees list
                    ParseRelation<ParseUser> att_relation = eventinfo.getRelation("attendees");
                    att_relation.add(ParseUser.getCurrentUser());

                    // Save collected data to event class
                    eventinfo.saveInBackground();

                    // After new event created, go back to eventView
                    Intent intent = new Intent(context, eventView.class);
                    startActivity(intent);
                }
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


    public void placepicker()
    {
        try{

            PlacePicker.IntentBuilder intentbuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentbuilder.build(this);

            startActivityForResult(intent,REQUEST_PLACE_PICKER);

        }catch (GooglePlayServicesRepairableException e){
            System.out.println("Error");
        }catch (GooglePlayServicesNotAvailableException e){
            System.out.println("Error");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER) {

            if (resultCode == RESULT_OK) {


                final Place place = PlacePicker.getPlace(data, mContext);

               // String toastMsg = String.format(place.getName());
                EditText finaladdress = (EditText)findViewById(R.id.location_editText);
                finaladdress.setText(place.getAddress());

                double temp = place.getLatLng().longitude;
                double temp2 = place.getLatLng().latitude;
                point = new ParseGeoPoint(temp2,temp);
                System.out.println(point);

                Toast.makeText(this, place.getAddress(), Toast.LENGTH_LONG).show();
            }
        }
    }



}
