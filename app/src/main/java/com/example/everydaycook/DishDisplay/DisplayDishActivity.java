package com.example.everydaycook.DishDisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.example.everydaycook.R;

public class DisplayDishActivity extends AppCompatActivity {

    /*
    This activity displays multiple or one
    Model view of dish
    with all necessary data
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dish);

        NoDishesFragment fragment = NoDishesFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_no_data, fragment).commit();
    }

}