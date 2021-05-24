package com.example.everydaycook.DishDisplay;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

public class NoDishesFragment extends Fragment {

    private DisplayDishActivity myActivity;

    /*
    Displays simple information that there is no data to show
     */

    public NoDishesFragment() {
        super(R.layout.fragment_no_dishes);
    }

    public static NoDishesFragment newInstance() {
        return new NoDishesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (DisplayDishActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // defining which .xml file is the content of this fragment
        View view =  inflater.inflate(R.layout.fragment_no_dishes, parent, false);
        return view;
    }

    private void addListeners() {

    }


}
