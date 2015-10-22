package jefferyvicente.meetup;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;


public class login extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required - Initialize the Parse SDK
        Parse.initialize(this);

        /*
            Ideally, installation info would be saved once on signup.  As written, info may be saved
            every time Meetup runs.  However, Parse docs say that ParseInstallation is smart, and
            doesn't save if no change has been made to record, so current strategy may be ok.
            Maybe try later:  import other activities from ParseUILogin, modify SignUpFragment and
            try to get this app to to use the modified version.
         */
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("name", currentUser.getString("name"));
        installation.put("email", currentUser.getEmail());
        installation.saveInBackground();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        //ParseFacebookUtils.initialize(this);

        // Optional - If you don't want to allow Twitter login, you can
        // remove this line (and other related ParseTwitterUtils calls)
       // ParseTwitterUtils.initialize(getString(R.string.twitter_consumer_key),getString(R.string.twitter_consumer_secret));
    }
}