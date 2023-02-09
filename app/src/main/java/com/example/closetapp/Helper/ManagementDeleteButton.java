package com.example.closetapp.Helper;

import static com.example.closetapp.Helper.DatabaseHelper.CLOTHING_TABLE;
import static com.example.closetapp.Helper.DatabaseHelper.COLUMN_CHECKED;
import static com.example.closetapp.Helper.DatabaseHelper.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.closetapp.Activity.AddItemFragment;
import com.example.closetapp.Activity.DeleteItemActivity;
import com.example.closetapp.Activity.MainActivity;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Interface.ChangeNumberSelectedListener;

import java.util.ArrayList;

public class ManagementDeleteButton {
    private Context context;
    DatabaseHelper databaseHelper;
    int isSelected;
    public static int buttonNumber = 0;
    public static ArrayList<String> selectedIdList = new ArrayList<>();


    public ManagementDeleteButton(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void editSelectedItem(int id, int selectedCategoryPosition, String brand, int selectedColorPosition) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.updateOne(id, selectedCategoryPosition, brand, selectedColorPosition);

        } catch (Exception e){
            Log.e("error", e.getMessage());
        }

        databaseHelper.close();
    }

    public void deleteSelectedItems() {
        for (String selectedItemId : selectedIdList) {
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.deleteOne(Integer.parseInt(selectedItemId));
            } catch (Exception e){
                Log.e("error", e.getMessage());
            }
        }

        buttonNumber = 0;
        selectedIdList.clear();
        databaseHelper.close();
    }

    public void uncheckToDelete(ClothingDomain clothingItem, ChangeNumberSelectedListener changeNumberSelectedListener) {
//        if (buttonNumber > 1) {
//            buttonNumber--;
//        } else {
//            buttonNumber = 0;
//        }

        try {
            buttonNumber--;
            selectedIdList.remove(String.valueOf(clothingItem.getId()));
            changeNumberSelectedListener.changed();
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }

//        changeNumberSelectedListener.changed();
    }

    public void checkToDelete(ClothingDomain clothingItem, ChangeNumberSelectedListener changeNumberSelectedListener) {
        this.buttonNumber++;
        selectedIdList.add(String.valueOf(clothingItem.getId()));
        changeNumberSelectedListener.changed();
    }

    public int updateButton() {
        int returnedNumber = buttonNumber;
        return returnedNumber;
    }

    public void cancelDelete() {
        buttonNumber = 0;
        selectedIdList.clear();
    }



}
