package jefferyvicente.meetup;

/**
 * Created by Administrator on 9/21/15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/**
 * Shows the user profile. This simple activity can function regardless of whether the user
 * is currently logged in.
 */
public class SampleActivity extends Activity {
    private static final int LOGIN_REQUEST = 0;

    private TextView titleTextView;
    private TextView emailTextView;
    private TextView nameTextView;
    private Button loginOrLogoutButton;
    Button button;

    private ParseUser currentUser;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //toEventView();
        getActionBar().hide();
        setContentView(R.layout.activity_login);
        titleTextView = (TextView) findViewById(R.id.profile_title);
        emailTextView = (TextView) findViewById(R.id.profile_email);
        nameTextView = (TextView) findViewById(R.id.profile_name);
        loginOrLogoutButton = (Button) findViewById(R.id.login_or_logout_button);
        titleTextView.setText("You are logged in as");
        button = (Button)findViewById(R.id.continue_button);
        toEventView();
        loginOrLogoutButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    // User clicked to log out.
                    ParseUser.logOut();
                    currentUser = null;
                    showProfileLoggedOut();
                } else {
                    // User clicked to log in.
                    ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                            SampleActivity.this);
                    startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            showProfileLoggedIn();
        } else {
            showProfileLoggedOut();
        }
    }

    /**
     * Shows the profile of the given user.
     */
    private void showProfileLoggedIn() {
        View x = findViewById(R.id.continue_button);
        x.setVisibility(View.VISIBLE);

        titleTextView.setText("You are logged in as ");
        emailTextView.setText(currentUser.getEmail());
        String fullName = currentUser.getString("name");
        if (fullName != null) {
            nameTextView.setText(fullName);
        }
        loginOrLogoutButton.setText("Log out");
    }

    public void toEventView(){

        final Context context = this;
        //button = (Button)findViewById(R.id.continue_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(context, eventView.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Show a message asking the user to log in, toggle login/logout button text.
     */
    private void showProfileLoggedOut() {
        titleTextView.setText("You must log in to view your profile");
        emailTextView.setText("");
        nameTextView.setText("");
        loginOrLogoutButton.setText("Log in ");
    }
}

