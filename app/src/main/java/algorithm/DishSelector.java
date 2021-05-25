package algorithm;

import android.content.SharedPreferences;

public class DishSelector {

    /*
    This class is designed to be layer between
    taking dishes from database
    and displaying it to application
     */

    private String algorithmPreference;
    private String modePreference;

    // first i need to take data set in constructor
    // bc i can't call SharedPreferences because i don't have context
    public DishSelector(SharedPreferences preferences) {
        algorithmPreference = preferences.getString("algorithm","default");
        modePreference = preferences.getString("mode","default");
    }

}
