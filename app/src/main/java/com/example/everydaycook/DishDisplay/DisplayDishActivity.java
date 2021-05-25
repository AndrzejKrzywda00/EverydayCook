package com.example.everydaycook.DishDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.everydaycook.R;

public class DisplayDishActivity extends AppCompatActivity {

    /*
    This activity displays multiple or one
    Model view of dish
    with all necessary data
     */

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dish);
        displayNoDishes();
    }

    // This function calls two underlying
    private void manageDisplay() {

    }

    // This function displays screen that says there is no dishes available
    private void displayNoDishes() {
        NoDishesFragment fragment = NoDishesFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_no_data, fragment).commit();
    }

    // This method displays all dishes by
    // running DishSelector engine
    // then creating suitable amount of fragments
    // and then creating a gallery out of them
    private void displayAllDishes() {

    }

}