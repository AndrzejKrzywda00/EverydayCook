package com.example.everydaycook.DishCreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import DBConnection.DatabaseHelper;
import ModelObjects.Tag;

public class DishTagsDataFragment extends Fragment {

    private ArrayList<Tag> tags;
    private final ArrayList<Chip> chips = new ArrayList<>();
    private ChipGroup chipsContainer;

    public DishTagsDataFragment() {
        super(R.layout.fragment_dish_tags_data);
    }

    public static DishTagsDataFragment newInstance() {
        return new DishTagsDataFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_tags_data, container, false);
        chipsContainer = view.findViewById(R.id.chips_container);
        tags = getAllTags();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        //this.addListeners();
        populateChipGroup();
    }

    // getting all tags to fill chips with their data
    private ArrayList<Tag> getAllTags() {
        DatabaseHelper dbConnection = new DatabaseHelper(getContext());
        return dbConnection.getTags();
    }

    // take ArrayList<Tag> -> ChipGroup{Chip1, Chip2, Chip3, ...}
    private void populateChipGroup() {
        for(Tag tag : tags) {
            String content = tag.getContent();
            //String colorHEX = tag.getColor(); USE LATER
            Chip chip = new Chip(requireContext());
            chip.setCheckable(true);
            chip.setText(content);
            chips.add(chip);
        }
        for(Chip chip: chips) {
            chipsContainer.addView(chip);
        }
    }

    protected ArrayList<Tag> getActiveTags() {

        for(Chip chip : chips) {
            if(chip.isChecked()) {
                // solve this
            }
        }

        ArrayList<Tag> usedTags = new ArrayList<>();
        for(Tag tag : tags) {
            if(tag.used()) {
                usedTags.add(tag);
            }
        }
        return usedTags;
    }

}