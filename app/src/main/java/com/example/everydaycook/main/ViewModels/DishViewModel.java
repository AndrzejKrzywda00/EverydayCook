package com.example.everydaycook.main.ViewModels;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class DishViewModel extends ViewModel {

    /*
    This view model type holds all data for a single dish to be displayed
    separating it from fragments and activities
     */

    // IMAGE - fixed size with gradient on it
    // NAME - taken form db
    // DESCRIPTION - taken from db
    // ---------- - line
    // table with:
    // ICON - COOKING TIME
    // ICON - CALORIES
    // ICON - TAGS (as colors rectangles possibly)
    // ---------- - line
    // INGREDIENTS - formatted string
    // ---------- - line
    // RECIPE - just formatted string

    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> recipe;
    private int cookingTime;
    private int kiloCalories;
}
