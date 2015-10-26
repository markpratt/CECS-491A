package jefferyvicente.meetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import 	java.lang.String;

public class friendManager extends Activity {

    private friendManagerCustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_manager);
        getActionBar().hide();
        toFriendManger();





    }

    public void toFriendManger(){

        final Context context = this;
        Button button = (Button)findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                EditText editText = (EditText) findViewById(R.id.friendSeach_editText);
                //String searchfeild  = editText.getText().toString();
                if(editText.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),
                            "Search Feild is Empty", Toast.LENGTH_LONG).show();
                    ListView listView = (ListView) findViewById(R.id.friendManager_ListView);
                    listView.setAdapter(null);
                }else {

                    querySearch();

                }


            }
        });

    }

    public void querySearch(){
        EditText editText = (EditText) findViewById(R.id.friendSeach_editText);
        String searchfeild  = editText.getText().toString();
        System.out.println(searchfeild);

        /*

        //Querying and database conncetion test
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("name", searchfeild);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    System.out.println("Query FAILED");
                } else {
                    System.out.println("Query Successful");
                }
            }
        });

        */



        adapter = new friendManagerCustomAdapter(this,searchfeild);
        //adapter.setTextKey("name");


        ListView listView = (ListView) findViewById(R.id.friendManager_ListView);
        listView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_manager, menu);
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
}
