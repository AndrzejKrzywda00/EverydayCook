package Enums;

import androidx.annotation.NonNull;

public enum DietType {

    Vegetarian("Vegetarian"),
    Vegan("Vegan"),
    Standard("Standard");

    private final String name;

    DietType(String name) {
        this.name = name;
    }

    @NonNull
    public String toString() {
        return this.name;
    }

}
