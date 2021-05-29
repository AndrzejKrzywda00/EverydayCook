package com.example.everydaycook.TagCreation;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

import DBConnection.DatabaseHelper;
import ModelObjects.Tag;

public class TagCreationFragment extends Fragment {

    // abstract UI values
    private TagCreationActivity myActivity;
    private Button chooseColorButton;
    private Button addTagButton;
    private Button chipAdded;
    private ColorPicker cp;
    private EditText textField;
    private TextView tagInformationText;

    // values to inject to db
    private String selectedColor;
    private String tagName;

    // TODO -- implement chips if enough time

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (TagCreationActivity) context;
    }

    public TagCreationFragment() {
        super(R.layout.content_tag_creation);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
        tagInformationText.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // defining which .xml file is the content of this fragment
        View view = inflater.inflate(R.layout.content_tag_creation, parent, false);
        chooseColorButton = view.findViewById(R.id.choose_color_button);
        addTagButton = view.findViewById(R.id.add_tag_button);
        textField = view.findViewById(R.id.tag_name_text);
        chipAdded = view.findViewById(R.id.chip_added);
        tagInformationText = view.findViewById(R.id.tag_information);
        chipAdded.setVisibility(View.INVISIBLE);
        cp = getColorPickerInstance();
        return view;
    }

    // provides with starting cp instance
    private ColorPicker getColorPickerInstance() {
        // starting position will be black color - seems fine for label
        int defaultColorR = 0;
        int defaultColorG = 0;
        int defaultColorB = 0;
        // the problem seems to be with Fragment -> Activity connection
        return new ColorPicker(this.myActivity, defaultColorR, defaultColorG, defaultColorB);
    }

    private void addListeners() {
        chooseColorButton.setOnClickListener(view -> cp.show());

        cp.setCallback(color -> {
            selectedColor = Integer.toHexString(color);
            cp.dismiss();
            chipAdded.setBackgroundColor(color);
        });

        addTagButton.setOnClickListener(view -> {
            tagName = textField.getText().toString();
            if(!tagName.equals("") && selectedColor != null) {
                Tag tag = new Tag(tagName, selectedColor);
                DatabaseHelper dbConnection = new DatabaseHelper(getContext());
                dbConnection.addTag(tag);
                textField.setText("");  // cleaning
                tagInformationText.setText(R.string.tag_success);
                tagInformationText.setTextColor(getResources().getColor(R.color.green));
            }
            else {
                tagInformationText.setText(R.string.tag_problem);
                tagInformationText.setTextColor(getResources().getColor(R.color.red));
            }
        });

        textField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                chipAdded.setVisibility(View.VISIBLE);
                tagInformationText.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")) {
                    chipAdded.setText(s.toString());
                }
                else {
                    chipAdded.setVisibility(View.INVISIBLE);
                }
            }
        });

    }



}
