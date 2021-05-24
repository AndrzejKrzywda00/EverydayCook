package com.example.everydaycook.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.everydaycook.R;

public class SettingsActivity extends AppCompatActivity {

    // all usable fragments
    AlgorithmSettingsFragment algoFragment;
    OtherSettingsFragment otherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        importFragmentsInstances();
    }

    /*
    It takes pair key : value and stores it in shared preferences for this app
     */
    private void savePreference(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private void importFragmentsInstances() {
        algoFragment = (AlgorithmSettingsFragment) getSupportFragmentManager().findFragmentById(R.id.settings_algorithm);
        otherFragment = (OtherSettingsFragment) getSupportFragmentManager().findFragmentById(R.id.other_settings_fragment);
    }

    protected void saveAlgorithmPreference(String preference) {
        savePreference("algorithm", preference);
    }



}