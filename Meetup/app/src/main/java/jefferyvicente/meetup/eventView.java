package jefferyvicente.meetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.ParseUser;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.FindCallback;
import java.util.List;



public class eventView extends Activity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    //private CustomAdapter urgentTodosAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        getActionBar().hide();
        toNewEventInfo();



/*
        // Create query to get all events where eventCreator matches current user's id.  Pass to ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, new ParseQueryAdapter.QueryFactory<ParseObject>()
        {
            public ParseQuery create()
            {
                ParseQuery<ParseObject> eventQuery = ParseQuery.getQuery("event");
                eventQuery.whereEqualTo("eventCreator", ParseUser.getCurrentUser());
                return eventQuery;
            }
        }); */

        //mainAdapter = new ParseQueryAdapter<ParseObject>(this, "event");
        mainAdapter.setTextKey("eventName");
        //mainAdapter.setImageKey("image");
        //mainAdapter.setTextKey("eventDetails");


        // Initialize the subclass of ParseQueryAdapter
       //urgentTodosAdapter = new CustomAdapter(this);


        listView = (ListView) findViewById(R.id.event_listView);
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_view, menu);
        return true;
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


    public void toNewEventInfo(){

        final Context context = this;
        Button button = (Button)findViewById(R.id.createEventButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(context, newEventInfo.class);
                startActivity(intent);
            }
        });

    }
}
