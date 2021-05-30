package com.example.everydaycook.DishCreation;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.example.everydaycook.R;

import java.io.File;
import java.io.IOException;

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
        takePhotoButton.setOnClickListener(this::takePhoto);
    }

//    @Override
//    public void onActivityResult(ActivityResult result) {
//        if(result.getResultCode() == Activity.RESULT_OK) {
//            Uri selectedImage = result.getData().getData();
//            imageUri = selectedImage;
//            thumbnail.setImageURI(selectedImage);
//        }
//    }

    public void takePhoto(View view) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        int TAKE_PICTURE = 0;
        //startActivityForResult(pickPhoto, TAKE_PICTURE);
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