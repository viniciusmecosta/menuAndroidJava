package com.vinicius.menu.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinicius.menu.Menu;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentDessertBinding;
import java.util.ArrayList;

// DessertFragment é responsável por exibir os itens de sobremesa disponíveis no menu.
public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding; // Utiliza View Binding para acessar as views.
    private DessertFragmentCallback callback; // Interface callback para comunicação com a atividade.

    // Interface para definir o callback que será chamado quando os itens de sobremesa forem atualizados.
    public interface DessertFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Assegura que a atividade contêiner implementou o callback.
        if (context instanceof DessertFragmentCallback) {
            callback = (DessertFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DessertFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // Limpa a referência ao callback para evitar vazamentos de memória.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla o layout do fragmento usando View Binding.
        binding = FragmentDessertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configura o RecyclerView com um LinearLayoutManager e o adapter.
        RecyclerView recyclerViewFood = binding.recycleDessert;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewFood.setHasFixedSize(true);

        // Obtém a lista de itens de sobremesa da atividade principal.
        Menu activity = (Menu) getActivity();
        ArrayList<Food> dessertFoodList = activity.getFoodItemsByCategory("Dessert");

        // Cria e configura o adapter para o RecyclerView com os itens de sobremesa.
        FoodAdapter foodAdapter = new FoodAdapter(dessertFoodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);

        // Define um listener no adapter para detectar mudanças na seleção dos itens.
        foodAdapter.setFoodItemChangeListener(() -> {
            if (callback != null) {
                // Notifica a atividade contêiner que os itens de sobremesa foram atualizados.
                callback.onUpdateFoodItems(dessertFoodList);
            }
        });
    }
}
