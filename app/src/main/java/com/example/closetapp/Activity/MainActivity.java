package com.example.closetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.closetapp.Adapter.ClosetAdapter;
import com.example.closetapp.Adapter.ClosetAdapter2;
//import com.example.closetapp.Adapter.MainAdapter;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Helper.DatabaseHelper;
import com.example.closetapp.Helper.ManagementDeleteButton;
import com.example.closetapp.Interface.ChangeNumberSelectedListener;
import com.example.closetapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter accessoriesAdapter, bottomsAdapter, dressesAdapter, outerwearAdapter, othersAdapter, topsAdapter;
    private static RecyclerView recyclerViewAccessoriesList, recyclerViewBottomsList, recyclerViewDressesList, recyclerViewOuterwearList, recyclerViewOthersList, recyclerViewTopsList;
    private ConstraintLayout clAccessories, clBottoms, clDresses, clOuterwear, clOthers, clTops;
    private ImageView ivAccessories, ivBottoms, ivDresses, ivOthers, ivOuterwear, ivTops;

    private TextView homePage, addItemPage, deleteItemsBtn;
    private ManagementDeleteButton managementDeleteButton;
    private ChangeNumberSelectedListener changeNumberSelectedListener;
    private List<ClothingDomain> everything;

    Boolean isCollapsed = false;

    ArrayList<ClothingDomain> arrayAccessories = new ArrayList<>();
    ArrayList<ClothingDomain> arrayBottoms = new ArrayList<>();
    ArrayList<ClothingDomain> arrayDresses = new ArrayList<>();
    ArrayList<ClothingDomain> arrayOthers = new ArrayList<>();
    ArrayList<ClothingDomain> arrayOuterwear = new ArrayList<>();
    ArrayList<ClothingDomain> arrayTops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        everything = databaseHelper.getItems();

        managementDeleteButton = new ManagementDeleteButton(this);

        initView();
        recyclerViewMain();
        bottomBar();
    }

    public void recyclerViewMain() {

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        everything = databaseHelper.getItems();

        arrayAccessories.clear();
        arrayBottoms.clear();
        arrayDresses.clear();
        arrayOthers.clear();
        arrayOuterwear.clear();
        arrayTops.clear();

        for (ClothingDomain item : everything) {
            switch (item.getCategoryPosition()) {
                case 0:
                    arrayAccessories.add(item);
                    break;

                case 1:
                    arrayBottoms.add(item);
                    break;

                case 2:
                    arrayDresses.add(item);
                    break;

                case 3:
                    arrayOuterwear.add(item);
                    break;

                case 4:
                    arrayTops.add(item);
                    break;

                case 5:
                    arrayOthers.add(item);
                    break;

                default:
                    break;

            }

        }

//        recyclerViewAccessoriesList = findViewById(R.id.rvAccessories);
//        clAccessories = findViewById(R.id.clAccessories);

        getRecyclerViewClothingList(recyclerViewAccessoriesList, arrayAccessories, clAccessories, ivAccessories);
        getRecyclerViewClothingList(recyclerViewBottomsList, arrayBottoms, clBottoms, ivBottoms);
        getRecyclerViewClothingList(recyclerViewDressesList, arrayDresses, clDresses, ivDresses);
        getRecyclerViewClothingList(recyclerViewOthersList, arrayOthers, clOthers, ivOthers);
        getRecyclerViewClothingList(recyclerViewOuterwearList, arrayOuterwear, clOuterwear, ivOuterwear);
        getRecyclerViewClothingList(recyclerViewTopsList, arrayTops, clTops, ivTops);

    }
    public void initView() {
        recyclerViewAccessoriesList = findViewById(R.id.rvAccessories);
        recyclerViewBottomsList = findViewById(R.id.rvBottoms);
        recyclerViewDressesList = findViewById(R.id.rvDresses);
        recyclerViewOthersList = findViewById(R.id.rvOthers);
        recyclerViewOuterwearList = findViewById(R.id.rvOuterwear);
        recyclerViewTopsList= findViewById(R.id.rvTops);

        clAccessories = findViewById(R.id.clAccessories);
        clBottoms = findViewById(R.id.clBottoms);
        clDresses = findViewById(R.id.clDresses);
        clOthers = findViewById(R.id.clOther);
        clOuterwear = findViewById(R.id.clOuterwear);
        clTops = findViewById(R.id.clTops);

        ivAccessories = findViewById(R.id.ivAccessories);
        ivBottoms = findViewById(R.id.ivBottoms);
        ivDresses = findViewById(R.id.ivDresses);
        ivOthers = findViewById(R.id.ivOther);
        ivOuterwear = findViewById(R.id.ivOuterwear);
        ivTops = findViewById(R.id.ivTops);
    }

    public void getRecyclerViewClothingList(RecyclerView recyclerView, ArrayList<ClothingDomain> arrayCategory,
                                            ConstraintLayout constraintLayout, ImageView imageView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.Adapter adapter = new ClosetAdapter(arrayCategory);
        recyclerView.setAdapter(adapter);


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCollapseBtn();
                recyclerView.setAdapter(isCollapsed ? null : adapter);
                Drawable res = getResources().getDrawable(isCollapsed ? R.drawable.ic_baseline_add_24 : R.drawable.ic_baseline_remove_24);
                imageView.setImageDrawable(res);

            }
        });

    }


    public Boolean toggleCollapseBtn() {
        isCollapsed = !isCollapsed;
        return isCollapsed;
    }


    private void bottomBar() {
        homePage = findViewById(R.id.btnHomePage);
        addItemPage = findViewById(R.id.btnAddItemPage);
        deleteItemsBtn = findViewById(R.id.btnDeleteItems);

        addItemPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AddItemFragment());
            }
        });

        deleteItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteItemActivity.class));
            }
        });
    }


    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.constraintLayout, fragment);
        fragmentTransaction.commit();
    }

}