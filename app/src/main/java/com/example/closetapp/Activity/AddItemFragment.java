package com.example.closetapp.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Helper.DatabaseHelper;
import com.example.closetapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddItemFragment extends Fragment {
    private ImageView imagePreview;
    private Spinner categorySpinner, colorSpinner;
    private EditText brand;
    private Button selectImageBtn;
    private TextView addItemBtn, cancelBtn;

    private ClothingDomain clothingItem;
    private byte[] selectedImage;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_item, container, false);

        initView();
        spinnerCategory();
        spinnerColor();
        selectImage();

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        return view;

    }


    private void addItem(){
                try {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                    clothingItem = new ClothingDomain(-1, selectedImage, brand.getText().toString(),
                            categorySpinner.getSelectedItemPosition(), colorSpinner.getSelectedItemPosition(), false);
                    Boolean success = databaseHelper.addOne(clothingItem);

                    if (success == true) {
//                        ((MainActivity) getActivity()).categorizeItems();
                        ((MainActivity) getActivity()).recyclerViewMain();
                        getActivity().getSupportFragmentManager().beginTransaction().remove(AddItemFragment.this).commit();
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(), "Please check your input.", Toast.LENGTH_SHORT).show();
                    Log.e("error", e.getMessage());
                }
    }

    private void selectImage() {
        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                launchSomeActivity.launch(i);

            }
        });
    }

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                                    getActivity().getContentResolver(), selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
                        selectedImage = outputStream.toByteArray();


                        imagePreview.setImageBitmap(selectedImageBitmap);
                        imagePreview.setVisibility(View.VISIBLE);
                        selectImageBtn.setVisibility(View.GONE);

                    }
                }
            }
    );
//
    private void spinnerCategory() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_categories, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

    }

    private void spinnerColor() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_colors, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

    }

    private void initView() {

        imagePreview = view.findViewById(R.id.ivPreview);
        categorySpinner = view.findViewById(R.id.spinnerAddCategory);
        colorSpinner = view.findViewById(R.id.spinnerAddColor);
        brand = view.findViewById(R.id.etAddBrand);
        selectImageBtn = view.findViewById(R.id.btnSelectImage);
        addItemBtn = view.findViewById(R.id.btnAddItem);
        cancelBtn = view.findViewById(R.id.btnCancel);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(AddItemFragment.this).commit();
            }
        });
    }
}