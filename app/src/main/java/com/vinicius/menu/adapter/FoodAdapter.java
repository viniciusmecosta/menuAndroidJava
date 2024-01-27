package com.vinicius.menu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinicius.menu.Models.Food;
import com.vinicius.menu.databinding.FoodItemBinding;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final ArrayList<Food> foodList;
    private final Context context;
    private FoodItemChangeListener foodItemChangeListener;

    public FoodAdapter(ArrayList<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    public interface FoodItemChangeListener {
        void onFoodItemSelectionChanged();
    }

    public void setFoodItemChangeListener(FoodItemChangeListener listener) {
        this.foodItemChangeListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding listItem;
        listItem = FoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FoodViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.imageFood.setBackgroundResource(foodList.get(position).getImgFood());
        holder.binding.textFoodName.setText(foodList.get(position).getName());
        holder.binding.textFoodDescription.setText(foodList.get(position).getDescription());
        holder.binding.textFoodPrice.setText(String.format("R$ %.2f", foodList.get(position).getPrice()));
        holder.binding.textFoodTime.setText(String.valueOf(foodList.get(position).getTime()));

        holder.binding.checkBuy.setOnCheckedChangeListener(null);
        holder.binding.checkBuy.setChecked(foodList.get(position).isSelected());
        holder.binding.checkBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                foodList.get(position).setSelected(isChecked);
                if (foodItemChangeListener != null) {
                    foodItemChangeListener.onFoodItemSelectionChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;

        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
