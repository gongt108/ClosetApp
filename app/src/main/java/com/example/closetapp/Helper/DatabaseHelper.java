package com.example.closetapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.closetapp.Domain.ClothingDomain;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CLOTHING_TABLE = "CLOTHING_TABLE";
    public static final String COLUMN_PIC = "PIC";
    public static final String COLUMN_BRAND_NAME = "BRAND_NAME";
    public static final String COLUMN_CATEGORY_POSITION = "CATEGORY_POSITION";
    public static final String COLUMN_COLOR_POSITION = "COLOR_POSITION";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CHECKED = "CHECKED";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "clothing.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CLOTHING_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PIC + " BITMAP, " + COLUMN_BRAND_NAME + " TEXT, " + COLUMN_CATEGORY_POSITION + " INT, "
                + COLUMN_COLOR_POSITION + " INT, " + COLUMN_CHECKED + " BOOL)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ClothingDomain clothingDomain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PIC, clothingDomain.getPic());
        cv.put(COLUMN_BRAND_NAME, clothingDomain.getBrand());
        cv.put(COLUMN_CATEGORY_POSITION, clothingDomain.getCategoryPosition());
        cv.put(COLUMN_COLOR_POSITION, clothingDomain.getColorPosition());
        cv.put(COLUMN_CHECKED, clothingDomain.isChecked());

        long insert = db.insert(CLOTHING_TABLE, null, cv);
        // positive means good, negative means fail
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateOne(int id, int selectedCategoryPosition, String brand, int selectedColorPosition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRAND_NAME, brand);
        cv.put(COLUMN_CATEGORY_POSITION, selectedCategoryPosition);
        cv.put(COLUMN_COLOR_POSITION, selectedColorPosition);

//        String queryString = "UPDATE " + CLOTHING_TABLE + " SET " + COLUMN_CATEGORY_POSITION + " = " + selectedCategoryPosition
//                + ", " + COLUMN_BRAND_NAME + " = " + brand + ", " + COLUMN_COLOR_POSITION + " = " + selectedColorPosition
//                + " WHERE " + COLUMN_ID + " = " + id;

        long update = db.update(CLOTHING_TABLE, cv, COLUMN_ID + " = " + id , null);

//        Cursor cursor = db.rawQuery(queryString, null);

        if (update == -1) {
            return false;
        } else {
            return true;
        }

//        if (cursor.moveToFirst()) {
//            return true;
//        } else {
//            return false;
//        }

    }

    public boolean deleteOne(int clothingDomainId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CLOTHING_TABLE + " WHERE " + COLUMN_ID + " = " + clothingDomainId;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<ClothingDomain> getItems() {
        List<ClothingDomain> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CLOTHING_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        // cursor is your result set
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor and create new clothing objects. put them into return list.
            do {
                int clothingID = cursor.getInt(0);
                byte[] clothingPic = cursor.getBlob(1);
                String clothingBrand = cursor.getString(2);
                int clothingCategoryPosition = cursor.getInt(3);
                int clothingColorPosition = cursor.getInt(4);
                boolean isChecked = cursor.getInt(5) == 1 ? true : false;

                ClothingDomain newClothingItem = new ClothingDomain(clothingID, clothingPic,
                        clothingBrand, clothingCategoryPosition,
                        clothingColorPosition, isChecked);
                returnList.add(newClothingItem);

            } while (cursor.moveToNext());

        } else {
            // failure. do not add to list
        }

        // close cursor and db when done
        cursor.close();
        db.close();

        return returnList;
    }
}
