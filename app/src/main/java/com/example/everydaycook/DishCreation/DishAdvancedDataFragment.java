package com.example.everydaycook.DishCreation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.everydaycook.R;

public class DishAdvancedDataFragment extends Fragment {

    EditText ingredientsText;
    EditText recipeText;

    public DishAdvancedDataFragment() {
        super(R.layout.fragment_dish_advanced_data);
    }

    public static DishAdvancedDataFragment newInstance() {
        return new DishAdvancedDataFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_advanced_data, container, false);
        ingredientsText = (EditText) view.findViewById(R.id.ingredients_text);
        recipeText = (EditText) view.findViewById(R.id.recipe_text);
        return view;
    }

    protected String getRecipeText() {
        return recipeText.getText().toString();
    }
    protected String getIngredientsText() {
        return ingredientsText.getText().toString();
    }

}