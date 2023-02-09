//package com.example.closetapp.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.closetapp.Domain.ClothingDomain;
//import com.example.closetapp.R;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
//    ArrayList<ClothingDomain> clothingDomains;
//    ArrayList<ClothingDomain> clothing = new ArrayList<>();
//
//    public MainAdapter(ArrayList<ClothingDomain> clothingDomains) {
//        this.clothingDomains = clothingDomains;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_clothing_recyclerview, parent, false);
//        return new MainAdapter.ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return clothingDomains.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView categoryName;
//        ImageView collapseBtn;
//        RecyclerView recyclerView;
//        Boolean isCollapsed = false;
//        RecyclerView recyclerViewItemsList;
//        RecyclerView.Adapter adapter;
//        List<ClothingDomain> everything;
////        ClothingDomain[][] categorizedClothing = new ClothingDomain[6][];
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            categoryName = itemView.findViewById(R.id.categoryName);
//            collapseBtn = itemView.findViewById(R.id.collapseBtn);
//            recyclerView = itemView.findViewById(R.id.recyclerView);
//            recyclerViewItemsList = itemView.findViewById(R.id.rvTops);
//            collapseBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    isCollapsed = !isCollapsed;
//                    if (isCollapsed == true) {
//                        recyclerView.setVisibility(View.GONE);
//                        collapseBtn.setImageResource(R.drawable.ic_baseline_add_24);
//                    } else if (isCollapsed == false) {
//                        recyclerView.setVisibility(View.VISIBLE);
//                        collapseBtn.setImageResource(R.drawable.ic_baseline_remove_24);
//                    }
//                }
//            });
//        }
//
////        public void recyclerViewItems() {
////            clothing = getItems();
////
////            adapter = new ClosetAdapter2(clothing);
////            recyclerViewItemsList.setAdapter(adapter);
////        }
//
//        private void getItems() {
////            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
////                    LinearLayoutManager.HORIZONTAL, false);
////            recyclerViewItemsList.setLayoutManager(linearLayoutManager);
//
//            ArrayList<ClothingDomain> arrayAccessories = new ArrayList<>();
//            ArrayList<ClothingDomain> arrayBottoms = new ArrayList<>();
//            ArrayList<ClothingDomain> arrayDresses = new ArrayList<>();
//            ArrayList<ClothingDomain> arrayOthers = new ArrayList<>();
//            ArrayList<ClothingDomain> arrayOuterwear = new ArrayList<>();
//            ArrayList<ClothingDomain> arrayTops = new ArrayList<>();
//
//            for (ClothingDomain item : everything) {
//                switch (item.getCategoryPosition()) {
//                    case 0:
//                        arrayAccessories.add(item);
//                        break;
//
//                    case 1:
//                        arrayBottoms.add(item);
//                        break;
//
//                    case 2:
//                        arrayDresses.add(item);
//                        break;
//
//                    case 3:
//                        arrayOthers.add(item);
//                        break;
//
//                    case 4:
//                        arrayOuterwear.add(item);
//                        break;
//
//                    case 5:
//                        arrayTops.add(item);
//                        break;
//
//                    default:
//                        break;
//
//                }
//
//            }
//
//        }
//
//    }

//    public void getCategories() {
////        List<String> categoryNames = Arrays.asList("Tops", "Bottoms", "Dresses & Jumpsuits", "Outerwear", "Accessories", "Other");
//        List<Integer> categoryNames = Arrays.asList(5, 1, 2, 4, 0, 3);
//        List<ClothingDomain> everything;
//
//
//
//
//        for (ClothingDomain item : everything) {
//            if (item.getCategoryPosition() == 0) {
//                items.add(item);
//            }
//        }
//    }
//}
