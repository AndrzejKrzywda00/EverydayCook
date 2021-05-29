package com.example.everydaycook.DishDisplay;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

public class DishDisplayFragment extends Fragment {

    // abstract UI parent element
    private DisplayDishActivity myActivity;

    // finishing button
    ImageButton chooseButton;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (DisplayDishActivity) context;
    }

    public DishDisplayFragment() {
        super(R.layout.dish_display_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dish_display_fragment, parent, false);
        chooseButton = view.findViewById(R.id.confirm_choice_button);
        return view;
    }

    private void addListeners() {
        chooseButton.setOnClickListener(v -> {

        });
    }


}
