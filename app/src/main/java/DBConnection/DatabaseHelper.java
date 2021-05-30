package DBConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import ModelObjects.Dish;
import ModelObjects.HistoryRowDish;
import ModelObjects.SmallDishModel;
import ModelObjects.Tag;

public class DatabaseHelper extends SQLiteOpenHelper {

    // version
    private static final int DATABASE_VERSION = 4;

    // database name
    private static final String DATABASE_NAME = "EC_DATA";

    // table names
    private static final String TABLE_DISHES = "dishes";
    private static final String TABLE_HISTORY = "history";
    private static final String TABLE_TAGS = "tags";

    // columns names for DISHES
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String INGREDIENTS = "ingredients";
    private static final String RECIPE = "recipe";
    private static final String KILO_CALORIES = "kilo_calories";
    private static final String TYPE = "type";
    private static final String COOKING_TIME = "cooking_time";
    private static final String TAGS_ADDED = "tags_added";

    // columns names for HISTORY
    private static final String DISH_ID = "dish_id";
    private static final String ADDED_AT = "added_at";

    // columns names for TAGS
    private static final String COLOR = "color";

    // creating table DISHES
    // (id, name, description, ingredients, recipe, kilo_calories, type, cooking_time, tags)
    // the format for tags is: (they are in a single string):
    // tagID1, tagID2, tagID5, .. and so on
    private static final String createTableDishes = "CREATE TABLE " + TABLE_DISHES + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT NOT NULL,"
            + DESCRIPTION + " TEXT NOT NULL,"
            + INGREDIENTS + " TEXT,"
            + RECIPE + " TEXT,"
            + KILO_CALORIES + " INTEGER,"
            + TYPE + " TEXT,"
            + COOKING_TIME + " TEXT,"
            + TAGS_ADDED + " TEXT" + ")";

    // creating table HISTORY
    // (id, added_at, dish_id)
    private static final String createTableHistory = "CREATE TABLE " + TABLE_HISTORY + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ADDED_AT + " TEXT NOT NULL,"
            + DISH_ID + " REFERENCES " + TABLE_DISHES + "(" + ID + "))";

    // creating table TAGS
    // now tags will have to direct relation to other two dbs
    // (id, name, color)
    private static final String createTableTags = "CREATE TABLE " + TABLE_TAGS + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT NOT NULL,"
            + COLOR + " TEXT NOT NULL" + ")";

    // constructor
    public DatabaseHelper(Context context) {
        // just running the database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // called when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(createTableDishes);
        db.execSQL(createTableHistory);
        db.execSQL(createTableTags);
    }

    // called when database needs to be changed in some way
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop all tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAGS);

        // and re-create them
        onCreate(db);
    }

    // queries the table 'dishes' for dish with specific id
    public SmallDishModel getSmallDishModelAtID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + ID + "," + NAME + "," + DESCRIPTION + " FROM " + TABLE_DISHES + " WHERE " + ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);
        SmallDishModel dish = new SmallDishModel();     // filling with data

        if(c != null) {     // in case if no rows
            dish.setPosition(c.getInt(c.getColumnIndex(ID)));
            dish.setName(c.getString(c.getColumnIndex(NAME)));
            dish.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));
        }
        c.close();          // closing reading connection
        return dish;
    }

    // queries the table 'dishes' for all dishes
    public ArrayList<SmallDishModel> getAllDishes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + ID + "," + NAME + "," + DESCRIPTION + " FROM " + TABLE_DISHES;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<SmallDishModel> dishes = new ArrayList<>();

        if(c.moveToFirst()) {
            do {
                SmallDishModel dish = new SmallDishModel();
                dish.setPosition(c.getInt(c.getColumnIndex(ID)));
                dish.setName(c.getString(c.getColumnIndex(NAME)));
                dish.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));
                dishes.add(dish);
            } while(c.moveToNext());
            c.close();
        }
        return dishes;
    }

    // removes oldest history elements but has safety mechanisms
    // you won't remove entire history because rows are necessary to perform
    // proposal algorithms so, limit is set to 30 rows, you can't go below that
    // returns true if successful
    // if not, no harm is done to history
    public boolean cleanHistory(int howManyRows) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_HISTORY;

        Cursor c = db.rawQuery(selectQuery, null);

        boolean removable = false;

        if(c != null) {
            c.moveToFirst();
            int OFFSET = 30;
            if(c.getInt(c.getColumnIndex("*")) <= howManyRows + OFFSET) {
                c.close();
                return false;
            }
            if(c.getInt(c.getColumnIndex("*")) > howManyRows + OFFSET) {
                removable = true;       // perform removing
            }
        }

        if(removable) {
            selectQuery = "DELETE FROM " + TABLE_HISTORY + " ORDER BY " + ADDED_AT + " ASC LIMIT " + howManyRows;
            c = db.rawQuery(selectQuery, null);
            if(c == null) {
                return false;
            }
            c.close();
        }
        return true;
    }

    // this selector takes dishes from history and uses limit as well
    public ArrayList<SmallDishModel> getHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_HISTORY;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<HistoryRowDish> historyRows = new ArrayList<>();
        if(c != null) {
            do {
                HistoryRowDish historyRow = new HistoryRowDish();
                historyRow.setDishId(c.getInt(c.getColumnIndex(DISH_ID)));
                historyRow.setAddedAt(c.getString(c.getColumnIndex(ADDED_AT)));
                historyRows.add(historyRow);
            } while(c.moveToNext());
        }
        c.close();

        // data transition between history rows and small dish model
        ArrayList<SmallDishModel> historyDishes = new ArrayList<>();
        for(HistoryRowDish historyRow : historyRows) {
            int dishId = historyRow.getDishId();
            SmallDishModel smallDish = getSmallDishModelAtID(dishId);
            smallDish.setAddedAt(historyRow.getAddedAt());
            historyDishes.add(smallDish);
        }
        return historyDishes;
    }

    /*
     adding a new dish to database
     returning true if successful, false otherwise
     i think parsing object to strings is best
     than doing it directly

     columns is a list of fields that are not necessary
     they should have this structure:
     column1;column2;column3
     so then i know in which fields to inject data
    */
    public void addDish(Dish dish) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, dish.getName());
        cv.put(DESCRIPTION, dish.getDescription());
        cv.put(RECIPE, dish.getRecipe());
        cv.put(INGREDIENTS, dish.getIngredients());
        cv.put(KILO_CALORIES, dish.getKiloCalories());
        cv.put(TYPE, dish.getType().toString());
        cv.put(COOKING_TIME, dish.getCookingTime());
        cv.put(TAGS_ADDED, dish.getTagsIds());

        db.insert(TABLE_DISHES, null, cv);
    }

    // returns just columns names for dish without id
    public String dishColumnsObtain() {
        return NAME + ", "
                + DESCRIPTION + ", "
                + INGREDIENTS + ", "
                + RECIPE + ", "
                + KILO_CALORIES + ", "
                + TYPE + ", "
                + COOKING_TIME + ", "
                + TAGS_ADDED;
    }

    // fetching a dish from database
    // same name as first function
    // but this returns not small model but whole data row
    public Dish getDishAtID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DISHES + " WHERE " + ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);
        Dish dish = new Dish();     // empty object

        if(c != null) {
            // setting all direct data
            dish.setName(c.getString(c.getColumnIndex(NAME)));
            dish.setDescription(c.getString(c.getColumnIndex(DESCRIPTION)));
            dish.setIngredients(c.getString(c.getColumnIndex(INGREDIENTS)));
            dish.setRecipe(c.getString(c.getColumnIndex(RECIPE)));
            dish.setKiloCalories(c.getInt(c.getColumnIndex(KILO_CALORIES)));
            dish.setType(c.getString(c.getColumnIndex(TYPE)));
            dish.setCookingTime(c.getString(c.getColumnIndex(COOKING_TIME)));

            // then parsing tags into form that can be injected into dish object
            ArrayList<Tag> tags = getTagsByIDs(c.getString(c.getColumnIndex(TAGS_ADDED)));
            dish.setTags(tags);
            c.close();
        }
        return dish;
    }

    /*
    This method imports number of dishes that are in a database
     */
    public int getNumberOfDishes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT COUNT(*) FROM " + TABLE_DISHES;

        Cursor c = db.rawQuery(selectQuery, null);
        int output = 0;
        if(c != null) {
            output = c.getCount();
            c.close();
        }
        return output;
    }

    /*
    This function takes in array list of indexes
    and returns array list of ids that are at
    these indexes
     */
    public ArrayList<Integer> getIdsAtIndexes(ArrayList<Integer> indexes) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + ID + " FROM " + TABLE_DISHES;

        Cursor c = db.rawQuery(selectQuery, null);

        int indexCounter = 1;
        ArrayList<Integer> output = new ArrayList<>();

        if(c.moveToFirst()) {
            do {
                if(indexes.contains(indexCounter)) {
                    output.add(c.getInt(c.getColumnIndex(ID)));
                }
                indexCounter++;
            } while(c.moveToNext());
            c.close();
        }
        return output;
    }

    // id list should be in format
    // id1, id2, id3
    // so in standard query format
    // returns array-list of objects of Tag class
    public ArrayList<Tag> getTagsByIDs(String idList) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_TAGS + " WHERE " + ID + " IN(" + idList + ")";

        Cursor c = db.rawQuery(selectQuery ,null);

        ArrayList<Tag> tags = new ArrayList<>();
        if(c != null) {
            c.moveToFirst();
            while(c.move(1)) {
                Tag tag = new Tag();
                tag.setContent(c.getString(c.getColumnIndex(NAME)));
                tag.setColor(c.getString(c.getColumnIndex(COLOR)));
                tag.setDatabaseId(c.getInt(c.getColumnIndex(ID)));
                tags.add(tag);
            }
            c.close();
        }
        return tags;
    }

    /*
    This function inserts new tag to database
     */
    public void addTag(Tag tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, tag.getContent());
        cv.put(COLOR, tag.getFixedColor());
        db.insert(TABLE_TAGS, null, cv);
    }

    /*
    returns all tags data from SQLite
     */
    public ArrayList<Tag> getTags() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_TAGS;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<Tag> tags = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                Tag tag = new Tag();
                tag.setContent(c.getString(c.getColumnIndex(NAME)));
                tag.setColor(c.getString(c.getColumnIndex(COLOR)));
                tag.setDatabaseId(c.getInt(c.getColumnIndex(ID)));
                tags.add(tag);
            } while(c.moveToNext());

        } c.close();
        return tags;
    }

    /*
    Deletes all records in dishes
     */
    public void deleteDishes() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_DISHES;
        db.execSQL(deleteQuery);
    }

    /*
    Deletes all records in tags
     */
    public void deleteTags() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_TAGS;
        db.execSQL(deleteQuery);
    }

    /*
    Deletes all history
     */
    public void deleteHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_HISTORY;
        db.execSQL(deleteQuery);
    }

}
