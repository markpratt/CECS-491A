package jefferyvicente.meetup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;




public class CustomAdapter extends ParseQueryAdapter<ParseObject> {

    public CustomAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("event");
                query.whereEqualTo("eventCreator", ParseUser.getCurrentUser());
                //query.orderByAscending("createdAt");
                query.orderByDescending("createdAt");
                return query;
            }
        });
    }


    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent)
    {
        if(v == null){
            v = View.inflate(getContext(),R.layout.listview,null);
        }


        super.getItemView(object, v, parent);

        //Add the title view to
        TextView titleTextView = (TextView) v.findViewById(R.id.eventName_textView);
        titleTextView.setText("Name: " + object.getString("eventName"));

        //Adds the time where the object was created.
        TextView eventTime = (TextView) v.findViewById(R.id.date_textView);
        eventTime.setText("Date: " + object.getString("eventDate") + " at " + object.getString("eventTime"));

        //Adds the location to the list view.
        TextView eventLocation = (TextView) v.findViewById(R.id.location_textView);
        eventLocation.setText("Location: "+object.getString("eventLocationAddress"));


        return v;
    }
}