package com.example.closetapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.closetapp.Adapter.ClosetAdapter;
import com.example.closetapp.Adapter.ClosetDeleteAdapter;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Helper.DatabaseHelper;
import com.example.closetapp.Helper.ManagementDeleteButton;
import com.example.closetapp.Interface.ChangeNumberSelectedListener;
import com.example.closetapp.R;

import java.util.ArrayList;
import java.util.List;

public class DeleteItemActivity extends AppCompatActivity {

    private ImageView collapseBtn;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerViewClothingList;
    private Boolean isCollapsed = false;
    private ConstraintLayout clClothingView, clConfirmDelete;
    private TextView homePage, btnCancelDelete, tvNumberSelected;

    private ManagementDeleteButton managementDeleteButton;
    private ChangeNumberSelectedListener changeNumberSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        managementDeleteButton = new ManagementDeleteButton(this);

        recyclerViewClothingDelete();
        deleteButtons();
        bottomBar();
        calculateDeleteNumber();

    }

    @NonNull
    private ArrayList<ClothingDomain> getClothingDomainArrayList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerViewClothingList = findViewById(R.id.recyclerTest2);
        recyclerViewClothingList.setLayoutManager(linearLayoutManager);

        DatabaseHelper databaseHelper = new DatabaseHelper(DeleteItemActivity.this);
        List<ClothingDomain> everything = databaseHelper.getItems();

        return new ArrayList<>(everything);
    }

    private void recyclerViewClothingDelete() {
        ArrayList<ClothingDomain> clothing = getClothingDomainArrayList();

        adapter = new ClosetDeleteAdapter(clothing, this, new ChangeNumberSelectedListener() {
            @Override
            public void changed() {
                calculateDeleteNumber();
            }
        });
        recyclerViewClothingList.setAdapter(adapter);
    }

    private void deleteButtons() {
        clClothingView = findViewById(R.id.clClothingView);
        clConfirmDelete = findViewById(R.id.clConfirmDelete);
        tvNumberSelected = findViewById(R.id.tvNumberSelected);
        btnCancelDelete = findViewById(R.id.btnCancelDelete);


        btnCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementDeleteButton.cancelDelete();
                finish();
                startActivity(new Intent(DeleteItemActivity.this, MainActivity.class));
            }
        });

        clConfirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementDeleteButton.deleteSelectedItems();
                finish();
                startActivity(new Intent(DeleteItemActivity.this, MainActivity.class));
            }
        });
    }

    private void calculateDeleteNumber(){
        int deleteNumber = managementDeleteButton.updateButton();
        tvNumberSelected.setText("(" + deleteNumber + ")");
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