package com.example.closetapp.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.closetapp.Activity.ItemDescriptionActivity;
import com.example.closetapp.Domain.ClothingDomain;
import com.example.closetapp.R;

import java.util.ArrayList;

public class ClosetAdapter2 extends RecyclerView.Adapter<ClosetAdapter2.ViewHolder> {
    ArrayList<ClothingDomain> clothingDomains;

    public ClosetAdapter2(ArrayList<ClothingDomain> clothingDomains) {
        this.clothingDomains = clothingDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_clothing, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int categoryPosition = clothingDomains.get(position).getCategoryPosition();


        byte[] str = clothingDomains.get(position).getPic();
        Bitmap bitmap = BitmapFactory.decodeByteArray(str, 0, str.length);

        Glide.with(holder.itemView.getContext())
                .load(bitmap)
                .into(holder.pic);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ItemDescriptionActivity.class);
                intent.putExtra("clothingItem", clothingDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return clothingDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.ivClothing);
        }
    }


}
