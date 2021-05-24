package ModelObjects;

import android.net.Uri;

import java.util.ArrayList;
import Enums.DietType;

public class Dish {

    private String name;            // is the big name displayed on the screen
    private String description;     // is the smaller information under the name
    private String ingredients;     // is the formatted string with ingredients
    private String recipe;          // is the large text containing information how to cook this
    private int kiloCalories;       // is amount of kcal in the dish
    private DietType type;          // is the type of diet for ex. "Vegan"
    private String cookingTime;     // is estimated cooking time - in minutes
    private ArrayList<Tag> tags;    // is the list of tags

    // parses data to SQLite query format
    public String SQLParse() {
        StringBuilder output = new StringBuilder();

        output.append(name);
        output.append(", ");
        output.append(description);
        output.append(", ");
        output.append(ingredients);
        output.append(", ");
        output.append(recipe);
        output.append(", ");
        output.append(kiloCalories);
        output.append(", ");
        output.append(type.toString());
        output.append(", ");
        output.append(cookingTime);
        for(Tag tag : tags) {
            output.append(", ");
            output.append(tag);
        }

        return output.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setKiloCalories(int kiloCalories) {
        this.kiloCalories = kiloCalories;
    }

    // enum type version
    public void setType(DietType type) {
        this.type = type;
    }

    // string version
    public void setType(String type) {
        if(type.equals("Vegetarian")) {
            this.type = DietType.Vegetarian;
        }
        if(type.equals("Vegan")) {
            this.type = DietType.Vegan;
        }
        if(type.equals("Standard")) {
            this.type = DietType.Standard;
        }
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }
    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public int getKiloCalories() {
        return kiloCalories;
    }

    public DietType getType() {
        return type;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    // formatted like
    // tag1;tag2;tag3
    public String getTagsIds() {
        StringBuilder sb = new StringBuilder();

        for(Tag tag : tags) {
            int tagId = tag.getDatabaseId();
            sb.append(tagId);
            sb.append(";");
        }
        if(sb.length() > 1) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
    }

}
