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

// Definição da classe CheckoutAdapter que estende RecyclerView.Adapter para adaptar itens de comida para a view.
public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    // Lista de itens de comida selecionados para o checkout.
    private final List<Food> foodList;
    // Contexto para acesso a recursos, como strings e layouts.
    private final Context context;

    // Construtor do adaptador que inicializa a lista de itens de comida e o contexto.
    public CheckoutAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    // Método chamado para criar novos ViewHolders, inflando o layout de cada item de comida.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item de checkout usando LayoutInflater.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
        // Retorna um novo ViewHolder que contém o layout do item.
        return new ViewHolder(view);
    }

    // Método chamado para associar dados do item de comida a um ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtém o item de comida na posição especificada.
        Food food = foodList.get(position);
        // Configura os textos dos views no ViewHolder com informações do item de comida.
        holder.textFoodName.setText("1x " + food.getName()); // Quantidade fixa (1x) seguida do nome do item.
        holder.textFoodPrice.setText(String.format("R$ %.2f", food.getPrice())); // Preço formatado.
        holder.textFoodDescription.setText(food.getDescription()); // Descrição do item.
        holder.textFoodTime.setText(food.getTime() + "min"); // Tempo estimado para preparo em minutos.

        // Verifica se existe uma observação para o item e atualiza o texto do botão de observação.
        if (food.getObservation() != null && !food.getObservation().isEmpty()) {
            holder.buttonObs.setText(food.getObservation());
        } else {
            // Se não houver observação, define o texto do botão para adicionar observação.
            holder.buttonObs.setText(context.getString(R.string.add_observation));
        }

        // Configura um listener para o botão de observação para abrir um AlertDialog para adicionar/editar a observação.
        holder.buttonObs.setOnClickListener(v -> {
            // Infla o layout personalizado do AlertDialog.
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);

            // Configura o TextInputLayout e TextInputEditText com informações necessárias.
            TextInputLayout inputLayout = dialogView.findViewById(R.id.input_observation_layout);
            TextInputEditText input = dialogView.findViewById(R.id.observation);
            input.setHint(context.getString(R.string.observation_hint)); // Define a dica (hint) para o campo de texto.
            input.setMinLines(3); // Mínimo de linhas visíveis no campo de texto.
            input.setMaxLines(5); // Máximo de linhas visíveis no campo de texto.

            // Cria e exibe o AlertDialog, permitindo ao usuário adicionar/editar uma observação.
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getString(R.string.add_observation))
                    .setView(dialogView)
                    .setPositiveButton(context.getString(R.string.save), (dialog, which) -> {
                        // Quando o usuário clica em salvar, atualiza a observação do item de comida.
                        String observation = "OBS: " + input.getText().toString();
                        food.setObservation(observation);
                        // Notifica que os dados do item mudaram para atualizar a view.
                        notifyItemChanged(position);
                    })
                    .setNegativeButton(context.getString(R.string.cancel), null)
                    .show();
        });
    }

    // Método que retorna o número total de itens de comida selecionados.
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    // Classe interna ViewHolder que contém as referências para os views de um item de checkout.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textFoodName, textFoodPrice, textFoodDescription, textFoodTime, buttonObs;

        // Construtor do ViewHolder que inicializa as referências dos views.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa as referências dos views com base nos IDs.
            textFoodName = itemView.findViewById(R.id.text_food_name);
            textFoodPrice = itemView.findViewById(R.id.text_food_price);
            textFoodDescription = itemView.findViewById(R.id.text_food_description);
            textFoodTime = itemView.findViewById(R.id.text_food_time);
            buttonObs = itemView.findViewById(R.id.button_obs);
        }
    }
}
