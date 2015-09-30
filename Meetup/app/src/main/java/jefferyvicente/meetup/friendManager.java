package jefferyvicente.meetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.Parse.*;
import com.parse.ParseUser;
import com.parse.ParseQuery;
import com.parse.*;
import com.parse.ParseException;
import java.util.List;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.lang.Object;
import java.io.*;

import 	java.lang.String;

public class friendManager extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_manager);

        toFriendManger();



    }

    public void toFriendManger(){




        final Context context = this;
        Button button = (Button)findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                EditText editText = (EditText) findViewById(R.id.friendSeach_editText);
                String searchfeild  = editText.getText().toString();
                System.out.println(searchfeild);

                if(editText.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),
                            "Search Feild is Empty", Toast.LENGTH_LONG).show();
                }



            }
        });

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
