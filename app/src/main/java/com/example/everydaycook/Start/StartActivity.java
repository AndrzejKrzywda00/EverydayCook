package com.example.everydaycook.Start;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.everydaycook.DishCreation.DishCreatorActivity;
import com.example.everydaycook.DishDisplay.DisplayDishActivity;
import com.example.everydaycook.R;
import com.example.everydaycook.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import animation.OnSwipeTouchListener;

public class StartActivity extends AppCompatActivity {

    /*
    This is starting point of the application EverydayCook
    As main display of the application - but it can now start anywhere actually
     */

    private CoordinatorLayout coLay;
    private Button showDishButton;
    private Button newDishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        coLay = findViewById(R.id.coordinator_start);
        showDishButton = findViewById(R.id.show_todays_dish_button);
        newDishButton = findViewById(R.id.new_dish_button);
        addListeners();
    }

    /***
     * Called when user slides the screen left
     */
    public void startMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_slide_in, R.anim.right_slide_out);
    }

    /***
     * Called when user slides the screen right
     */
    public void startDishDisplay() {
        Intent intent = new Intent(this, DisplayDishActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in, R.anim.left_slide_out);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addListeners() {
        coLay.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                startMainMenu();
            }
            @Override
            public void onSwipeLeft() {
                startDishDisplay();
            }
            public boolean onTouch(View v, MotionEvent e) {
                return gestureDetector.onTouchEvent(e);
            }
        });

        newDishButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DishCreatorActivity.class);
            startActivity(intent);
        });

    }

}