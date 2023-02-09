package com.example.closetapp.Helper;

import android.os.Bundle;

import androidx.recyclerview.widget.DiffUtil;

import com.example.closetapp.Domain.ClothingDomain;

import java.util.ArrayList;

public class MyDiffUtilCallbacks extends DiffUtil.Callback {

    ArrayList<ClothingDomain> oldList = new ArrayList<>();
    ArrayList<ClothingDomain> newList = new ArrayList<>();

    public MyDiffUtilCallbacks (ArrayList<ClothingDomain> oldList, ArrayList<ClothingDomain> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean results = newList.get(newItemPosition) == oldList.get(oldItemPosition);
        return results;
    }

    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ClothingDomain newItem = newList.get(newItemPosition);
        ClothingDomain oldItem = oldList.get(oldItemPosition);

        Bundle bundle = new Bundle();

        if(newItem.isChecked() != oldItem.isChecked()) {
            bundle.putBoolean("isChecked", newItem.isChecked());
        }

        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
