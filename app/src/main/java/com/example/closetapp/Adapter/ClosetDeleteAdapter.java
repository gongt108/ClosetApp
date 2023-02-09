package com.example.closetapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.closetapp.Activity.ItemDescriptionActivity;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.Helper.ManagementDeleteButton;
import com.example.closetapp.Interface.ChangeNumberSelectedListener;
import com.example.closetapp.R;

import java.util.ArrayList;
import java.util.List;

public class ClosetDeleteAdapter extends RecyclerView.Adapter<ClosetDeleteAdapter.ViewHolder> {
    ArrayList<ClothingDomain> clothingDomains;
    private ManagementDeleteButton managementDeleteButton;
    private ChangeNumberSelectedListener changeNumberSelectedListener;

    public ClosetDeleteAdapter(ArrayList<ClothingDomain> clothingDomains, Context context, ChangeNumberSelectedListener changeNumberSelectedListener) {
        this.clothingDomains = clothingDomains;
        this.managementDeleteButton = new ManagementDeleteButton(context);
        this.changeNumberSelectedListener = changeNumberSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_clothing_delete, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        byte[] str = clothingDomains.get(position).getPic();
        Bitmap bitmap = BitmapFactory.decodeByteArray(str, 0, str.length);

        Glide.with(holder.itemView.getContext())
                .load(bitmap)
                .into(holder.pic);

        holder.checkBox.isChecked();


        holder.clClothingDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked() == true) {
                    holder.checkBox.setChecked(false);
                    managementDeleteButton.uncheckToDelete(clothingDomains.get(position), new ChangeNumberSelectedListener() {
                        @Override
                        public void changed() {
                            notifyDataSetChanged();
                            changeNumberSelectedListener.changed();
                        }
                    });
                } else {
                    holder.checkBox.setChecked(true);
                    managementDeleteButton.checkToDelete(clothingDomains.get(position), new ChangeNumberSelectedListener() {
                        @Override
                        public void changed() {
                            notifyDataSetChanged();
                            changeNumberSelectedListener.changed();
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clothingDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clClothingDeleteView;
        ImageView pic;
        CheckBox checkBox;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clClothingDeleteView = itemView.findViewById(R.id.clClothingDeleteView);
            pic = itemView.findViewById(R.id.ivClothing);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }
}
