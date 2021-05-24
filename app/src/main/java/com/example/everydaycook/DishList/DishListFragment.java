package com.example.everydaycook.DishList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

import java.util.ArrayList;

import DBConnection.DatabaseHelper;
import ModelObjects.SmallDishModel;

public class DishListFragment extends Fragment {

    /*
    This fragment is responsible for holding
    and displaying all rows in dish display screen
     */

    // these constitute full necessary elements
    ListView dishElements;      // full elements from .xml
    String[] names;             // dishes names from db
    String[] descriptions;      // dishes descriptions from db
    String[] numbers;           // numbers on display from db
    // images too!!!

    private static final int MAX_NUMBER_OF_ELEMENTS = 200;

    public static DishListFragment newInstance() {
        return new DishListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            dishElements = getActivity().findViewById(R.id.dish_list);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        DishListAdapter adapter = new DishListAdapter(this.getContext(), names, descriptions, numbers);
        dishElements.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_dish_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        addDishListListeners();
    }

    private void importData() {
        // just taking data from db
        DatabaseHelper dbConnection = new DatabaseHelper(getContext());
        ArrayList<SmallDishModel> smallDishModels = dbConnection.getAllDishes();

        // make this actually work well
        if(smallDishModels.size() <= MAX_NUMBER_OF_ELEMENTS) {
            this.instantiateDataArrays(smallDishModels.size());
        }
        else {
            this.instantiateDataArrays(MAX_NUMBER_OF_ELEMENTS);
        }

        int cPos = 0;
        for(SmallDishModel model : smallDishModels) {
            names[cPos] = model.getName();
            descriptions[cPos] = model.getDescription();
            numbers[cPos] = (cPos + 1) + ".";
            cPos++;
        }
    }

    private void instantiateDataArrays(int length) {
        names = new String[length];
        descriptions = new String[length];
        numbers = new String[length];
    }

    /***
     * Adapter class to fill data into list view
     */
    class DishListAdapter extends ArrayAdapter<String> {

        Context context;
        String[] dishListNames;
        String[] dishListDescriptions;
        String[] dishListNumbers;

        public DishListAdapter(@NonNull Context context, String[] names, String[] descriptions, String[] numbers) {
            // TODO -- make sens with this constructor
            super(context, R.layout.dish_list_row, R.id.dish_list_name, names);
            this.context = context;
            this.dishListNames = names;
            this.dishListDescriptions = descriptions;
            this.dishListNumbers = numbers;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.dish_list_row, parent, false);

            TextView number = row.findViewById(R.id.dish_list_number);
            TextView name = row.findViewById(R.id.dish_list_name);
            TextView description = row.findViewById(R.id.dish_list_description);
            // make this work with photo

            number.setText(dishListNumbers[position]);
            name.setText(dishListNames[position]);
            description.setText(dishListDescriptions[position]);
            return row;
        }
    }

    private void addDishListListeners() {

        dishElements.setOnItemClickListener((parent, view, position, id) ->{
            // just open the clicked one
        });

    }

}
