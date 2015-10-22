package jefferyvicente.meetup;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class EventDetails extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Get the data passed from eventView and populate this activity
        Bundle myInput = this.getIntent().getExtras();
        if(myInput == null)
            Log.d("debug", "Intent was null");
        else
        {
            Log.d("debug", "Intent was ok");

            TextView ntv = (TextView) this.findViewById(R.id.name_TextView);
            String eventName = myInput.getString("selectedName");
            ntv.setText(eventName);

            TextView dtv = (TextView) this.findViewById(R.id.details_TextView);
            String eventDetails = myInput.getString("selectedDetail");
            dtv.setText(eventDetails);

            TextView ltv = (TextView) this.findViewById(R.id.location_TextView);
            String eventLocation = myInput.getString("selectedAddress");
            ltv.setText(eventLocation);

            TextView datv = (TextView) this.findViewById(R.id.date_TextView);
            String eventDate = myInput.getString("selectedDate");
            datv.setText(eventDate);

            TextView ttv = (TextView) this.findViewById(R.id.time_TextView);
            String eventTime = myInput.getString("selectedTime");
            ttv.setText(eventTime);



        }

    }
}
