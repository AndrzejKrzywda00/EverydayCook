package com.example.everydaycook.DishDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.everydaycook.R;

import java.util.ArrayList;

import Exceptions.TooSmallDataSetException;
import ModelObjects.Dish;
import algorithm.DishSelector;

public class DisplayDishActivity extends AppCompatActivity {

    /*
    This activity displays multiple or one
    Model view of dish
    with all necessary data
    So what i need to do here is
    to create fixed amount of dish display
    fragments, put them in sliding gallery
    order and make a system to handle incoming
    calls
     */

    private DishSelector engine;
    private int dishPosition;
    private ArrayList<Dish> dishes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dish);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        engine = new DishSelector(preferences);
        manageDisplay();
    }

    // This function calls two underlying
    private void manageDisplay() {
        dishPosition = 0;
        dishes = engine.proposeDishes(getApplicationContext());
        displayAllDishes();
    }

    // This function displays screen that says there is no dishes available
    private void displayNoDishes() {
        NoDishesFragment fragment = NoDishesFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_no_data, fragment).commit();
    }

    private void displayExemplaryFragment() {

    }

    // This method displays all dishes by
    // running DishSelector engine
    // then creating suitable amount of fragments
    // and then creating a gallery out of them
    private void displayAllDishes() {
        DishDisplayFragment fragment = DishDisplayFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_no_data, fragment).commit();
    }

    /***
     * This method makes decision to
     * choose certain dish and add to history
     */
    protected void chooseDish() {

    }

    /***
     * This method handles swap right
     * call to show next dish
     */
    protected void swapRight() {

    }

    /***
     * This method handles swap left
     * call to show previous dish
     */
    protected void swapLeft() {

    }


}