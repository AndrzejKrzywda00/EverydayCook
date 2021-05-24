package com.example.everydaycook.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.everydaycook.R;
import com.example.everydaycook.Start.StartActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import animation.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coLay;

    // this is main callback of the activity
    // this is called when system creates this class
    // source : https://developer.android.com/reference/android/app/Activity#onCreate(android.os.Bundle)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this can be effectively called only when there is small amount of data to be recreated
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     // defining layout of the view
        coLay = findViewById(R.id.main_menu);
        addListeners();

        // passing fragment
        MainMenuFragment fragment = MainMenuFragment.newInstance();       // here think newInstance or just new
        fragment.setArguments(getIntent().getExtras());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(0,fragment).commit();      // here is experimental int

    }

    // the next callback that will be called is starting one
    @Override
    public void onStart() {
        super.onStart();
        // defines amount of functionality available to the user
        // source : https://developer.android.com/reference/android/app/Activity#onStart()
    }

    @Override
    public void onResume() {
        super.onResume();
        // holds most of activity functionality together with fragments
        // source : https://developer.android.com/reference/android/app/Activity#onResume()
    }

    @Override
    public void onPause() {
        super.onPause();
        // called when activity freezes
        // for example when user clicked "recent" button on ones phone
        // source : https://developer.android.com/reference/android/app/Activity#onPause()
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addListeners() {
        coLay.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                startFirstScreen();
            }
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                return gestureDetector.onTouchEvent(e);
            }
        });
    }

    private void startFirstScreen() {
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }

    /*
    * BACKBONE SCHEME
    *
    *
    Add new dish - directs to editor to adding a new dish - connects to a database by room and writes
    Today's dish - directs to simple filter menu, then after clicking button to many fragments with options
    My dishes - a list with dishes available, listed by date of adding or alphabetically - database with all data and #id
    History - same as upper but with different color theme and less options - database with date and dish #id
    Settings - need a static file with configuration
        + show empty days
        + two versions of display
        + dark and light theme
        + cleaning memory (history or dishes or photos)
    Stats - shows window with  (when more than 30 dishes eaten) - need a database access
        + amount of calories eaten on graph
        + meat vs veg diet
        + amount of dishes added
        + most popular dishes
    Tags - redirects to tag creator - saving them in database
    Info - redirects to my information page, troubleshooting, patches, downloads
    *
    *
    *
     */

}