package jefferyvicente.meetup;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import android.widget.Button;
import android.view.LayoutInflater;
import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;


public class NewEventCustomAdapter extends ParseQueryAdapter<ParseObject> {

    private ArrayList<String> inviteeList = new ArrayList<String>();

    public NewEventCustomAdapter(Context context) {

        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {

                /*  As written, query returns all the Users.  After friends class and add to friends
                    is done, change query to return all friends associated with this User. */
                ParseQuery query = ParseUser.getQuery();
                return query;
                /*
                    ParseUser user = new ParseUser();
                    ParseRelation relation = user.getRelation("friends");
                    ParseQuery query = relation.getQuery();
                    return query;
                 */

            }
        });
    }


    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent)
    {
        if(v == null){
            v = View.inflate(getContext(),R.layout.new_event_listview,null);
        }

        super.getItemView(object, v, parent);

        // If box is checked, add name to ArrayList; if not, remove
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.invitee_checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(checkBox.isChecked())
                {
                    inviteeList.add(object.getString("name"));
                }
                else
                    inviteeList.remove(object.getString("name"));
            }
        });


        // Add the User's name to ListView
        TextView nameTextView = (TextView) v.findViewById(R.id.invitee_name);
        nameTextView.setText(object.getString("name"));

        return v;
    }

    public ArrayList<String> getInviteeList()
    {
        return inviteeList;
    }

}
