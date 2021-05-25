package algorithm;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import DBConnection.DatabaseHelper;
import Exceptions.TooSmallDataSetException;
import ModelObjects.Dish;

public class DishSelector {

    /*
    This class is designed to be layer between
    taking dishes from database
    and displaying it to application
     */

    private final String algorithmPreference;
    private final String modePreference;
    private final int NUMBER_OF_DISHES = 20;

    // first i need to take data set in constructor
    // bc i can't call SharedPreferences because i don't have context
    public DishSelector(SharedPreferences preferences) {
        algorithmPreference = preferences.getString("algorithm","default");
        modePreference = preferences.getString("mode","default");
    }

    public ArrayList<Dish> proposeDishes(Context c) throws TooSmallDataSetException {
        if(algorithmPreference.equals("default")) {
            return proposeDishesDefault(c);
        }
        if(algorithmPreference.equals("include_history")) {
            return proposeDishesWithHistory(c);
        }
        return new ArrayList<>();
    }

    /*
    Makes random choice of dishes from database
     */
    private ArrayList<Dish> proposeDishesDefault(Context c) throws TooSmallDataSetException {
        ArrayList<Dish> dishes = new ArrayList<>();
        DatabaseHelper dbConnection = new DatabaseHelper(c);
        int dishesImported = dbConnection.getNumberOfDishes();
        if(dishesImported >= NUMBER_OF_DISHES) {
            ArrayList<Integer> indexes = getRandomIntegers(dishesImported);
            ArrayList<Integer> ids = dbConnection.getIdsAtIndexes(indexes);
            for(Integer id : ids) {
                Dish dish = dbConnection.getDishAtID(id);
                dishes.add(dish);
            }
        }
        else {
            throw new TooSmallDataSetException();
        }
        return dishes;
    }

    /*
    take history into consideration
    idk how to do it right now
     */
    private ArrayList<Dish> proposeDishesWithHistory(Context c) {
        return new ArrayList<>();
    }

    // returns list of randomly chosen integers
    // between value 1 and q
    private ArrayList<Integer> getRandomIntegers(int q) {
        Random random = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        while(set.size() < NUMBER_OF_DISHES) {
            int number = random.nextInt(q);
            set.add(number);
        }
        return new ArrayList<>(set);
    }

}
