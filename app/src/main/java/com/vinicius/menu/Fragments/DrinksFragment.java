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
import com.vinicius.menu.databinding.FragmentDrinksBinding;
import java.util.ArrayList;

// DrinksFragment é responsável por exibir os itens de bebidas disponíveis no menu.
public class DrinksFragment extends Fragment {
    private FragmentDrinksBinding binding; // Utiliza View Binding para acessar as views.
    private DrinksFragmentCallback callback; // Interface de callback para comunicação com a atividade principal.

    // Interface para definir o callback que será chamado quando os itens de bebida forem atualizados.
    public interface DrinksFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verifica se a atividade contêiner implementou o callback necessário.
        if (context instanceof DrinksFragmentCallback) {
            callback = (DrinksFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DrinksFragmentCallback");
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
        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configura o RecyclerView com um LinearLayoutManager e o adapter.
        RecyclerView recyclerViewDrinks = binding.recycleDrinks;
        recyclerViewDrinks.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewDrinks.setHasFixedSize(true);

        // Obtém a lista de itens de bebida da atividade principal.
        Menu activity = (Menu) getActivity();
        ArrayList<Food> drinksFoodList = activity.getFoodItemsByCategory("Drinks");

        // Cria e configura o adapter para o RecyclerView com os itens de bebida.
        FoodAdapter foodAdapter = new FoodAdapter(drinksFoodList, requireContext());
        recyclerViewDrinks.setAdapter(foodAdapter);

        // Define um listener no adapter para detectar mudanças na seleção dos itens.
        foodAdapter.setFoodItemChangeListener(() -> {
            if (callback != null) {
                // Notifica a atividade contêiner que os itens de bebida foram atualizados.
                callback.onUpdateFoodItems(drinksFoodList);
            }
        });
    }
}
