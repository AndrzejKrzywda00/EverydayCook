package com.example.everydaycook.Settings;

import android.content.Context;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;
import com.example.everydaycook.TagCreation.TagCreationActivity;

public class AlgorithmSettingsFragment extends Fragment {

    SwitchCompat defaultAlgorithmSwitch;
    SwitchCompat historyAlgorithmSwitch;
    SwitchCompat noMeatFridaySwitch;
    SwitchCompat preferRecentSwitch;
    SwitchCompat preferRareSwitch;

    private static final String DEFAULT = "default";

    Button saveDataButton;

    SettingsActivity myActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myActivity = (SettingsActivity) context;
    }

    public AlgorithmSettingsFragment() {
        super(R.layout.fragment_settings_algorithm);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_algorithm, container, false);
        defaultAlgorithmSwitch = view.findViewById(R.id.alg_switch1);
        historyAlgorithmSwitch = view.findViewById(R.id.alg_switch2);
        noMeatFridaySwitch = view.findViewById(R.id.alg_switch3);
        preferRecentSwitch = view.findViewById(R.id.alg_switch4);
        preferRareSwitch = view.findViewById(R.id.alg_switch5);
        saveDataButton = view.findViewById(R.id.save_agl_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    private void addListeners() {
        saveDataButton.setOnClickListener(v -> {

        });
    }

}
