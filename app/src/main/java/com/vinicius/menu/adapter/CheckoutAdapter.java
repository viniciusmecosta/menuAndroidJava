package com.vinicius.menu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    private final List<Food> foodList;
    private final Context context; // Store the context

    public CheckoutAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context; // Initialize the context
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.textFoodName.setText("1x " + food.getName());
        holder.textFoodPrice.setText(String.format("R$ %.2f", food.getPrice()));
        holder.textFoodDescription.setText(food.getDescription());
        holder.textFoodTime.setText(food.getTime() + "min");

        if (food.getObservation() != null && !food.getObservation().isEmpty()) {
            holder.buttonObs.setText(food.getObservation());
        } else {
            holder.buttonObs.setText(context.getString(R.string.add_observation));
        }

        holder.buttonObs.setOnClickListener(v -> {
            // Inflate the custom layout for AlertDialog
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);

            // Configuring TextInputLayout and TextInputEditText
            TextInputLayout inputLayout = dialogView.findViewById(R.id.input_observation_layout);
            TextInputEditText input = dialogView.findViewById(R.id.observation);

            // Setting up hint, inputType, etc., for TextInputEditText as needed
            input.setHint(context.getString(R.string.observation_hint)); // Ensure you have a corresponding string resource for the hint
            input.setMinLines(3);
            input.setMaxLines(5);

            // Create and show the AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getString(R.string.add_observation))
                    .setView(dialogView)
                    .setPositiveButton(context.getString(R.string.save), (dialog, which) -> {
                        String observation = "OBS: " + input.getText().toString();
                        food.setObservation(observation);
                        notifyItemChanged(position);
                    })
                    .setNegativeButton(context.getString(R.string.cancel), null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFoodName, textFoodPrice, textFoodDescription, textFoodTime, buttonObs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textFoodName = itemView.findViewById(R.id.text_food_name);
            textFoodPrice = itemView.findViewById(R.id.text_food_price);
            textFoodDescription = itemView.findViewById(R.id.text_food_description);
            textFoodTime = itemView.findViewById(R.id.text_food_time);
            buttonObs = itemView.findViewById(R.id.button_obs);
        }
    }
}
