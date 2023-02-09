package com.example.closetapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Helper.DatabaseHelper;
import com.example.closetapp.Helper.ManagementDeleteButton;
import com.example.closetapp.R;

public class ItemDescriptionActivity extends AppCompatActivity {
    private ImageView picClothing;
    private TextView category, brand, color;
    private EditText brandInput;
    private TextView editBtn, saveChangesBtn, cancelBtn;
    private ClothingDomain clothingItem;
    private Spinner categorySpinner, colorSpinner;
    private ConstraintLayout clEdit;
    private TextView homePage;
    private ManagementDeleteButton managementDeleteButton;

    private int selectedColorPosition, selectedCategoryPosition;
    private String selectedColor, selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        managementDeleteButton = new ManagementDeleteButton(this);

        initView();
        getBundle();
        spinnerCategory();
        spinnerColor();
        saveChanges();
        bottomBar();
    }

    private void saveChanges() {
        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = clothingItem.getId();
                    selectedCategoryPosition = categorySpinner.getSelectedItemPosition();
                    selectedColorPosition = colorSpinner.getSelectedItemPosition();

                    managementDeleteButton.editSelectedItem(id, selectedCategoryPosition, brandInput.getText().toString(), selectedColorPosition);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    // Clears all activities on top of this one.
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    Toast.makeText(v.getContext(), "There was an error. Please check your input.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void spinnerCategory() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_categories, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setSelection(clothingItem.getCategoryPosition());
        selectedCategory = categorySpinner.getSelectedItem().toString();
        category.setText(selectedCategory);

    }

    private void spinnerColor() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_colors, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        colorSpinner.setSelection(clothingItem.getColorPosition());
        selectedColor = colorSpinner.getSelectedItem().toString();
        color.setText(selectedColor);

    }

    private void getBundle() {
        clothingItem = (ClothingDomain) getIntent().getSerializableExtra("clothingItem");

        byte[] str = clothingItem.getPic();
        Bitmap bitmap = BitmapFactory.decodeByteArray(str, 0, str.length);

        Glide.with(this)
                .load(bitmap)
                .into(picClothing);

        brand.setText(clothingItem.getBrand());
        brandInput.setText(clothingItem.getBrand());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBtn.setVisibility(View.GONE);
                clEdit.setVisibility(View.VISIBLE);

                categorySpinner.setVisibility(View.VISIBLE);
                brandInput.setVisibility(View.VISIBLE);
                colorSpinner.setVisibility(View.VISIBLE);

                category.setVisibility(View.GONE);
                brand.setVisibility(View.GONE);
                color.setVisibility(View.GONE);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBtn.setVisibility(View.VISIBLE);
                clEdit.setVisibility(View.GONE);

                categorySpinner.setVisibility(View.GONE);
                brandInput.setVisibility(View.GONE);
                colorSpinner.setVisibility(View.GONE);

                category.setVisibility(View.VISIBLE);
                brand.setVisibility(View.VISIBLE);
                color.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView(){
        picClothing = findViewById(R.id.ivEditPic);
        category = findViewById(R.id.tvDescriptionCategory);
        brand = findViewById(R.id.tvDescriptionBrand);
        color = findViewById(R.id.tvDescriptionColor);

        categorySpinner = findViewById(R.id.spinnerEditCategory);
        brandInput = findViewById(R.id.etEditBrand);
        colorSpinner = findViewById(R.id.spinnerEditColor);

        editBtn = findViewById(R.id.btnEdit);
        saveChangesBtn = findViewById(R.id.btnSaveChanges);
        cancelBtn = findViewById(R.id.btnCancel);
        clEdit = findViewById(R.id.clEdit);
    }

    private void bottomBar() {
        homePage = findViewById(R.id.btnHomePage);

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}