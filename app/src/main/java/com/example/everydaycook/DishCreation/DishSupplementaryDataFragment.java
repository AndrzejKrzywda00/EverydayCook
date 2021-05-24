package com.example.everydaycook.DishCreation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.everydaycook.R;

import Enums.DietType;

public class DishSupplementaryDataFragment extends Fragment {

    private EditText calories;
    private EditText timeCooking;
    private SwitchCompat vegetarian;
    private SwitchCompat vegan;
    private DietType diet = DietType.Standard;  // by default

    public DishSupplementaryDataFragment() {
        super(R.layout.fragment_dish_supplementary_data);
    }

    public static DishSupplementaryDataFragment newInstance() {
        return new DishSupplementaryDataFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_supplementary_data, container, false);
        calories = view.findViewById(R.id.calories_text);
        timeCooking = view.findViewById(R.id.cooking_time_text);
        vegetarian = view.findViewById(R.id.switch_vegetarian);
        vegan = view.findViewById(R.id.switch_vegan);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    private void addListeners() {
        vegetarian.setOnClickListener(v -> {
            vegan.setChecked(false);
        });

        vegan.setOnClickListener(v -> {
            vegetarian.setChecked(false);
        });
    }

    /*
    API
     */
    protected String getCalories() {
        return calories.getText().toString();
    }

    protected String getCookingTime() {
        return timeCooking.getText().toString();
    }

    protected DietType getDietType() {
        if(vegetarian.isChecked()) {
            diet = DietType.Vegetarian;
        }
        if(vegan.isChecked()) {
            diet = DietType.Vegan;
        }
        return diet;
    }

}