package com.example.everydaycook.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

import DBConnection.DatabaseHelper;

public class RemovingSettingsFragment extends Fragment {

    private Button removeDishesButton;
    private Button removeHistoryButton;
    private Button removeTagsButton;

    public RemovingSettingsFragment() {
        super(R.layout.fragment_settings_removing);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings_removing, container, false);
        removeDishesButton = view.findViewById(R.id.remove_dishes);
        removeTagsButton = view.findViewById(R.id.remove_tags);
        removeHistoryButton = view.findViewById(R.id.remove_history);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    private void addListeners() {
        removeDishesButton.setOnClickListener(view ->{
            DatabaseHelper dbConnection = new DatabaseHelper(getContext());
            dbConnection.deleteDishes();
        });

        removeHistoryButton.setOnClickListener(view -> {
            DatabaseHelper dbConnection = new DatabaseHelper(getContext());
            dbConnection.deleteHistory();
        });

        removeTagsButton.setOnClickListener(view ->{
            DatabaseHelper dbConnection = new DatabaseHelper(getContext());
            dbConnection.deleteTags();
        });
    }

}
