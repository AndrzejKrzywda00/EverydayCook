package com.example.everydaycook.History;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

import java.util.ArrayList;

import DBConnection.DatabaseHelper;
import ModelObjects.SmallDishModel;

public class DishHistoryListFragment extends Fragment {

    private ListView historyElements;
    private String[] historyNumbers;
    private Uri[] historyImages;
    private String[] historyNames;
    private String[] historyDates;

    public DishHistoryListFragment() {
        super(R.layout.fragment_dish_history);
    }

    public static DishHistoryListFragment newInstance() {
        return new DishHistoryListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            historyElements = getActivity().findViewById(R.id.history_list);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        HistoryListAdapter adapter = new HistoryListAdapter(getContext(), historyNumbers, historyImages, historyNames, historyDates);
        historyElements.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_dish_history, container, false);
    }

    private void importData() {
        DatabaseHelper dbConnection = new DatabaseHelper(getContext());
        ArrayList<SmallDishModel> smallDishModels = dbConnection.getHistory();
        instantiateDataArrays(smallDishModels.size());

        int hPos = 0;
        for(SmallDishModel model : smallDishModels) {
            historyNumbers[hPos] = (hPos + 1) + ".";
            historyNames[hPos] = model.getName();
            historyDates[hPos] = model.getAddedAt();
            // TODO -- historyImages[hPos] make this work
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        addListeners();
    }

    private void addListeners() {

    }

    private void instantiateDataArrays(int size) {
        historyNumbers = new String[size];
        historyImages = new Uri[size];
        historyNames = new String[size];
        historyDates = new String[size];
    }

    // Adapter class
    private class HistoryListAdapter extends ArrayAdapter<String> {

        private final String[] numbers;
        private final Uri[] images;
        private final String[] names;
        private final String[] dates;

        public HistoryListAdapter(Context context, String[] numbers, Uri[] images, String[] names, String[] dates) {
            super(context, R.layout.dish_history_row, R.id.dish_history_name, names);
            this.numbers = numbers;
            this.images = images;
            this.names = names;
            this.dates = dates;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.dish_history_row, parent, false);
            TextView number = row.findViewById(R.id.dish_history_number);
            ImageView image = row.findViewById(R.id.dish_history_image);
            TextView name = row.findViewById(R.id.dish_history_name);
            TextView date = row.findViewById(R.id.dish_history_date);

            number.setText(numbers[position]);
            image.setImageURI(images[position]);
            name.setText(names[position]);
            date.setText(dates[position]);
            return row;
        }

    }

}
