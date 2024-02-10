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

// Adaptador para o RecyclerView que exibe itens de comida.
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final ArrayList<Food> foodList; // Lista de itens de comida a serem exibidos.
    private final Context context; // Contexto onde o adaptador é utilizado.
    private FoodItemChangeListener foodItemChangeListener; // Listener para mudanças de seleção de itens.

    // Construtor do adaptador.
    public FoodAdapter(ArrayList<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    // Interface para notificar mudanças na seleção de itens de comida.
    public interface FoodItemChangeListener {
        void onFoodItemSelectionChanged();
    }

    // Define o listener de mudança de seleção de itens.
    public void setFoodItemChangeListener(FoodItemChangeListener listener) {
        this.foodItemChangeListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item de comida usando o View Binding.
        FoodItemBinding listItem = FoodItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new FoodViewHolder(listItem); // Retorna um ViewHolder para o item.
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Food food = foodList.get(position); // Obtém o item de comida na posição especificada.

        // Configura os dados do item de comida nos views correspondentes.
        holder.binding.imageFood.setBackgroundResource(food.getImgFood());
        holder.binding.textFoodName.setText(food.getName());
        holder.binding.textFoodDescription.setText(food.getDescription());
        holder.binding.textFoodPrice.setText(String.format("R$ %.2f", food.getPrice()));
        holder.binding.textFoodTime.setText(food.getTime() + "min");

        // Remove o listener anterior para evitar chamadas redundantes.
        holder.binding.checkBuy.setOnCheckedChangeListener(null);
        // Define o estado do CheckBox de acordo com o item estar selecionado ou não.
        holder.binding.checkBuy.setChecked(food.isSelected());

        // Configura um novo listener para o CheckBox, atualizando o estado de seleção do item.
        holder.binding.checkBuy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                foodList.get(position).setSelected(isChecked); // Atualiza o estado de seleção do item.
                // Notifica o listener sobre a mudança de seleção.
                if (foodItemChangeListener != null) {
                    foodItemChangeListener.onFoodItemSelectionChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size(); // Retorna o número total de itens de comida.
    }

    // ViewHolder que contém o binding para os views de um item de comida.
    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding; // Binding para acessar os views do item de comida.

        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot()); // Passa a view raiz para o construtor da superclasse.
            this.binding = binding; // Armazena o binding para acessar os views.
        }
    }
}
