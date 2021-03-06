package com.example.everydaycook.DishDisplay;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import Enums.DietType;
import ModelObjects.Dish;
import ModelObjects.Tag;

public class DishDisplayFragment extends Fragment {

    /*
    This one is big fragment that has all necessary data to display screen
     */

    // abstract UI parent element
    private DisplayDishActivity myActivity;

    // THE dish
    Dish dish;

    // access fields
    ImageView dishImage;
    TextView dishName;
    TextView dishDescription;
    TextView dishRecipe;
    TextView dishIngredients;
    TextView dishCalories;
    TextView dishDietType;
    ImageView vegImg;
    TextView dishCookingTime;
    ImageButton chooseButton;
    Button nextDishButton;
    ChipGroup dishChips;

    private final String NO_INFORMATION = "brak informacji";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (DisplayDishActivity) context;
    }

    public static DishDisplayFragment newInstance() {
        return new DishDisplayFragment();
    }

    public DishDisplayFragment() {
        super(R.layout.dish_display_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dish_display_fragment, parent, false);
        importInstances(view);
        return view;
    }

    private void addListeners() {
        chooseButton.setOnClickListener(v -> myActivity.swapRight());   // accept
        nextDishButton.setOnClickListener(v -> myActivity.swapLeft());  // reject
    }

    /***
     * Changes all exemplary data to dish object data
     */
    protected void displayDish() {
        dishImage.setImageURI(dish.getImage());
        dishName.setText(dish.getName());
        dishDescription.setText(dish.getDescription());

        if(dish.getRecipe() != null) {
            dishRecipe.setText(dish.getRecipe());
        }
        else {
            dishRecipe.setText(NO_INFORMATION);
        }

        if(dish.getIngredients() != null) {
            dishIngredients.setText(dish.getIngredients());
        }
        else {
            dishIngredients.setText(NO_INFORMATION);
        }
        dishCalories.setText(dish.getKiloCalories());
        if(dish.getType() != null) {
            DietType diet = dish.getType();
            if(diet == DietType.Standard) {
                dishDietType.setText(diet.toString());
                dishDietType.setTextColor(getResources().getColor(R.color.black));
                vegImg.setVisibility(View.INVISIBLE);
            }
            else {
                dishDietType.setText(diet.toString());
            }
        }
        if(dish.getCookingTime() != null) {
            dishCookingTime.setText(dish.getCookingTime());
        }
        ArrayList<Tag> tags = dish.getTags();
        for(Tag tag : tags) {
            Chip chip = new Chip(requireContext());
            chip.setText(tag.getContent());
            dishChips.addView(chip);
        }

    }

    protected void setDish(Dish dish) {
        this.dish = dish;
    }

    private void importInstances(View view) {
        dishImage = view.findViewById(R.id.dish_image);
        dishName = view.findViewById(R.id.dish_title);
        dishDescription = view.findViewById(R.id.dish_description);
        dishRecipe = view.findViewById(R.id.recipe_text);
        dishIngredients = view.findViewById(R.id.dish_ingredients);
        dishCalories = view.findViewById(R.id.kilocalories);
        dishDietType = view.findViewById(R.id.diet_name);
        vegImg = view.findViewById(R.id.veg_img);
        dishCookingTime = view.findViewById(R.id.time_cooking);
        chooseButton = view.findViewById(R.id.confirm_choice_button);
        nextDishButton = view.findViewById(R.id.next_dish_button);
        dishChips = view.findViewById(R.id.dish_chips);
    }

}
