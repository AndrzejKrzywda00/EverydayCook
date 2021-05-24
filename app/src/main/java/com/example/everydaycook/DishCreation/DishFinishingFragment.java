package com.example.everydaycook.DishCreation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.example.everydaycook.R;

public class DishFinishingFragment extends Fragment {

    ImageButton cancelButton;
    ImageButton ereaseButton;
    ImageButton confirmButton;

    public DishFinishingFragment() {
        super(R.layout.fragment_dish_finishing);
    }

    public static DishFinishingFragment newInstance() {
        return new DishFinishingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_finishing, container, false);
        cancelButton = view.findViewById(R.id.cancel_dish_button);
        ereaseButton = view.findViewById(R.id.erease_dish_button);
        confirmButton = view.findViewById(R.id.confirm_dish_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    private void addListeners() {
        cancelButton.setOnClickListener(view -> requireActivity().finish());

        ereaseButton.setOnClickListener(view -> {
            DishCreatorActivity parentActivity = (DishCreatorActivity) getActivity();
            assert parentActivity != null;
            parentActivity.eraseData();
        });

        confirmButton.setOnClickListener(view -> {
            DishCreatorActivity parentActivity = (DishCreatorActivity) getActivity();
            assert parentActivity != null;
            parentActivity.injectDishData();
        });
    }
}