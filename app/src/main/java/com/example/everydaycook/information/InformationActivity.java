package com.example.everydaycook.information;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.everydaycook.R;

public class InformationActivity extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        goBack = findViewById(R.id.escape_information);
        addHandlers();
    }

    private void addHandlers() {
        goBack.setOnClickListener(v-> this.finish());
    }

}