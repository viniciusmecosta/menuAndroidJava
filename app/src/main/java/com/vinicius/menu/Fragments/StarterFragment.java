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
import com.vinicius.menu.databinding.FragmentStarterBinding;
import java.util.ArrayList;

// StarterFragment é responsável por exibir os itens de entrada disponíveis no menu.
public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding; // Utiliza View Binding para acessar as views de forma mais segura.
    private StarterFragmentCallback callback; // Interface de callback para comunicação com a atividade principal.

    // Interface para definir o callback que será chamado quando os itens de entrada forem atualizados.
    public interface StarterFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verifica se a atividade contêiner implementou o callback necessário.
        if (context instanceof StarterFragmentCallback) {
            callback = (StarterFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement StarterFragmentCallback");
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
        binding = FragmentStarterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configura o RecyclerView com um LinearLayoutManager e o adapter.
        RecyclerView recyclerViewStarter = binding.recycleStarter;
        recyclerViewStarter.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewStarter.setHasFixedSize(true);

        // Obtém a lista de itens de entrada da atividade principal.
        Menu activity = (Menu) getActivity();
        ArrayList<Food> starterFoodList = activity.getFoodItemsByCategory("Starter");

        // Cria e configura o adapter para o RecyclerView com os itens de entrada.
        FoodAdapter foodAdapter = new FoodAdapter(starterFoodList, requireContext());
        recyclerViewStarter.setAdapter(foodAdapter);

        // Define um listener no adapter para detectar mudanças na seleção dos itens.
        foodAdapter.setFoodItemChangeListener(() -> {
            if (callback != null) {
                // Notifica a atividade contêiner que os itens de entrada foram atualizados.
                callback.onUpdateFoodItems(starterFoodList);
            }
        });
    }
}
