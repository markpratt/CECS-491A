package jefferyvicente.meetup;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import java.util.List;

public class EventDetails extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        getActionBar().hide();

        // Get extras from eventView activity
        Bundle myInput = this.getIntent().getExtras();
        if(myInput == null)
            Log.d("debug", "Intent was null");
        else
        {
            Log.d("debug", "Intent was ok");

            final TextView ntv = (TextView) this.findViewById(R.id.name_TextView);
            final TextView dtv = (TextView) this.findViewById(R.id.details_TextView);
            final TextView ltv = (TextView) this.findViewById(R.id.location_TextView);
            final TextView datv = (TextView) this.findViewById(R.id.date_TextView);
            final TextView ttv = (TextView) this.findViewById(R.id.time_TextView);

            // Assume eventName is unique. Perform query to get other data for event and then set TextViews.
            ParseQuery<ParseObject> query = ParseQuery.getQuery("event");
            query.whereEqualTo("eventName", myInput.getString("selectedName"));
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (object == null) {
                        Log.d("event", "The getFirst request failed.");
                    } else {
                        Log.d("event", "Retrieved the object.");

                        String eventName = object.getString("eventName");
                        String eventDate = object.getString("eventDate");
                        String eventTime = object.getString("eventTime");
                        String eventDetails = object.getString("eventDetails");
                        String eventLocationAddress = object.getString("eventLocationAddress");

                        ntv.setText(eventName);
                        datv.setText(eventDate);
                        ttv.setText(eventTime);
                        dtv.setText(eventDetails);
                        ltv.setText(eventLocationAddress);
                    }
                }
            });

        }

    }
}
