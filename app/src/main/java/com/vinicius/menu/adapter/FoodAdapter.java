package com.vinicius.menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinicius.menu.Models.Dish;
import com.vinicius.menu.databinding.FoodItemBinding;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final ArrayList<Dish> dishList;
    private final Context context;

    public FoodAdapter(ArrayList<Dish> dishList, Context context) {
        this.dishList = dishList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding listItem;
        listItem = FoodItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FoodViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.binding.imgFood.setBackgroundResource(dishList.get(position).getImgFood());
        holder.binding.txtFoodName.setText(dishList.get(position).getName());
        holder.binding.txtFoodDescription.setText(dishList.get(position).getDescription());
        holder.binding.txtFoodPrice.setText(String.valueOf(dishList.get(position).getPrice()));
        holder.binding.txtFoodTime.setText(String.valueOf(dishList.get(position).getTime()));

    }
    @Override
    public int getItemCount() {
        return dishList.size();
    }
    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        FoodItemBinding binding;
        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
