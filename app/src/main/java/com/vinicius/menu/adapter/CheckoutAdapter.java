package com.vinicius.menu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    private final List<Food> foodList;
    private final Context context; // Store the context

    // Update the constructor to accept Context
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
        holder.textFoodName.setText(food.getName());
        holder.textFoodPrice.setText(String.format("R$ %.2f", food.getPrice()));
        holder.textFoodDescription.setText(food.getDescription());
        holder.textFoodTime.setText(food.getTime() + " min");

        // Update observation text or set default
        if (food.getObservation() != null && !food.getObservation().isEmpty()) {
            holder.buttonObs.setText(food.getObservation());
        } else {
            holder.buttonObs.setText(context.getString(R.string.add_observation));
        }

        // Observation button click listener
        holder.buttonObs.setOnClickListener(v -> {
            EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            input.setMinLines(1);
            input.setMaxLines(5);
            input.setHint("Digite sua observação aqui");

            new AlertDialog.Builder(context)
                    .setTitle("Adicionar Observação")
                    .setView(input)
                    .setPositiveButton("Salvar", (dialog, which) -> {
                        String observation = "OBS: " + input.getText().toString();
                        food.setObservation(observation);
                        notifyItemChanged(position);
                    })
                    .setNegativeButton("Cancelar", null)
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
