package com.example.everydaycook.DishCreation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.R;

public class DishBasicDataFragment extends Fragment {

    /*
    This fragment is supposed to take
    name
    description
    photo
    of a new dish
    which is minimum amount of data possible
     */
    private Button takePhotoButton;
    private Uri imageUri;
    private ImageView thumbnail;
    private EditText name;
    private EditText description;

    // new version of launcher to pick photo
    ActivityResultLauncher<Intent> photoPicker = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    imageUri = data.getData();
                    thumbnail.setImageURI(imageUri);
                }
            }
    );

    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPicker.launch(intent);
    }

    public DishBasicDataFragment() {
        super(R.layout.fragment_dish_basic_data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dish_basic_data, container, false);
        takePhotoButton = view.findViewById(R.id.take_photo_button);
        thumbnail = view.findViewById(R.id.thumbnail_dish_creation);
        name = view.findViewById(R.id.dish_name_text);
        description = view.findViewById(R.id.dish_description_text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.addListeners();
    }

    private void addListeners() {
        takePhotoButton.setOnClickListener(v -> pickPhoto());
    }

    protected String getDishName() {
        return name.getText().toString();
    }

    protected String getDishDescription() {
        return description.getText().toString();
    }

    protected Uri getImageUri() {
        return imageUri;
    }

}