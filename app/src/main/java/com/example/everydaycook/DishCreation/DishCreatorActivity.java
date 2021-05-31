package com.example.everydaycook.DishCreation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.everydaycook.R;
import DBConnection.DatabaseHelper;
import ModelObjects.Dish;

public class DishCreatorActivity extends AppCompatActivity {

    /*
    This is controller class for dish creation activity
    I will create custom fragments for each data field
    and they will handle all data interactions
    then data will be passed here and db connection class will do its job
     */

    DishBasicDataFragment basicData;
    DishAdvancedDataFragment advancedData;
    DishSupplementaryDataFragment supplementaryData;
    DishTagsDataFragment tagsData;
    DishFinishingFragment finishing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_creator);
        importFragmentsInstances();
    }

    private void importFragmentsInstances() {
        basicData = (DishBasicDataFragment) getSupportFragmentManager().findFragmentById(R.id.first_fragment);
        advancedData = (DishAdvancedDataFragment) getSupportFragmentManager().findFragmentById(R.id.second_fragment);
        supplementaryData = (DishSupplementaryDataFragment) getSupportFragmentManager().findFragmentById(R.id.third_fragment);
        tagsData = (DishTagsDataFragment) getSupportFragmentManager().findFragmentById(R.id.fourth_fragment);
        finishing = (DishFinishingFragment) getSupportFragmentManager().findFragmentById(R.id.fifth_fragment);
    }

    protected void eraseData() {
        // call all activities to erase their data
    }

    protected void injectDishData() {
        if(validateData()) {
            Dish dish = new Dish();     // building object
            dish.setName(basicData.getDishName());
            dish.setDescription(basicData.getDishDescription());
            dish.setRecipe(advancedData.getRecipeText());
            dish.setIngredients(advancedData.getIngredientsText());
            dish.setKiloCalories(Integer.parseInt(supplementaryData.getCalories()));
            dish.setCookingTime(supplementaryData.getCookingTime());
            dish.setType(supplementaryData.getDietType());
            dish.setImage(basicData.getImageUri());
            dish.setTags(tagsData.getActiveTags());

            // db connection
            DatabaseHelper dbConnection = new DatabaseHelper(getApplicationContext());
            dbConnection.addDish(dish);
        }
    }

    private boolean validateData() {

        // first fragment
        if(basicData.getDishName() != null && basicData.getDishDescription() != null) {
            if(basicData.getDishName().length() < 5 || basicData.getDishDescription().length() < 5) {
                return false;
            }
            if(basicData.getImageUri() == null) {
                return false;
            }

        }
        else {
            return false;
        }

        // calories
        if(supplementaryData.getCalories() != null) {
            try {
                int c = Integer.parseInt(supplementaryData.getCalories());
            } catch(NumberFormatException e) {
                return false;
            }
        }
        // all additional data is absolutely voluntary to provide
        return true;
    }


}