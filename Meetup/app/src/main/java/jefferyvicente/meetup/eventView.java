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

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.Parse.*;
import com.parse.ParseUser;
import com.parse.ParseQuery;
//import java.text.ParseException;
import com.parse.*;
import com.parse.ParseException;
import java.util.List;

public class eventView extends Activity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    //private CustomAdapter urgentTodosAdapter;
    private ListView listView;
    private CustomAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        getActionBar().hide();
        toNewEventInfo();
        toFriendManger();
        final ParseUser currentUser = ParseUser.getCurrentUser();

        Intent i = getIntent();
        System.out.println("Intent: " + i);

        //ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "event");
        //adapter.setTextKey("eventName");

        /*ParseQueryAdapter<ParseObject> adapter =
                new ParseQueryAdapter<ParseObject>(this,new ParseQueryAdapter.QueryFactory<ParseObject>(){
                    public ParseQuery<ParseObject> create(){
                ParseQuery query = new ParseQuery("event");
                //query.whereContains("eventCreator", currentUser.getUsername());
                //query.whereContains("eventCreator", currentUser);
                query.whereEqualTo("eventCreator",currentUser);
                return query;
            }
        });



        //ParseQuery<ParseObject> query = ParseQuery.getQuery("event");
        //query.whereEqualTo("eventCreator", ParseUser.getCurrentUser());



        ParseQueryAdapter<ParseObject> adapter =
                new ParseQueryAdapter<ParseObject>(this,new ParseQueryAdapter.QueryFactory<ParseObject>(){
                    public ParseQuery<ParseObject> create() {

                        ParseQuery<ParseObject> eventQuery = ParseQuery.getQuery("event");
                        eventQuery.whereEqualTo("eventCreator", ParseUser.getCurrentUser());


                        eventQuery.findInBackground(new FindCallback<ParseObject>() {

                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {


                                }
                            }
                        });
                    return eventQuery;
                    }

                });

          */
        adapter2 = new CustomAdapter(this);
        adapter2.setTextKey("eventName");


        System.out.println("User Name 1: "+ ParseUser.getCurrentUser().toString());
        System.out.println("User Name 2: "+ currentUser.getUsername());

        ListView listView = (ListView) findViewById(R.id.event_listView);
        listView.setAdapter(adapter2);

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

    public void toFriendManger(){

        final Context context = this;
        Button button = (Button)findViewById(R.id.friendManageButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(context, friendManager.class);
                startActivity(intent);
            }
        });

    }
}
