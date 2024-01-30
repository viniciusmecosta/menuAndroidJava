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
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentStarterBinding;
import java.util.ArrayList;

public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding;
    private final ArrayList<Food> foodList = new ArrayList<>();
    private StarterFragmentCallback callback;

    public interface StarterFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    public StarterFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StarterFragmentCallback) {
            callback = (StarterFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StarterFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStarterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewFood = binding.recycleStarter;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewFood.setHasFixedSize(true);
        FoodAdapter foodAdapter = new FoodAdapter(foodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);
        foodAdapter.setFoodItemChangeListener(new FoodAdapter.FoodItemChangeListener() {
            @Override
            public void onFoodItemSelectionChanged() {
                if (callback != null) {
                    callback.onUpdateFoodItems(foodList);
                }
            }
        });
        getFood();
    }

    private void getFood() {
        foodList.clear();
        Food food1 = new Food(
                12.50,
                10,
                "Batata Frita",
                "Deliciosas batatas fritas, douradas e crocantes, servidas em uma porção de 300g",
                R.drawable.batata,
                "Starter"
        );
        Food food2 = new Food(
                15.00,
                12,
                "Macaxeira Frita",
                "Macaxeira frita até atingir textura crocante, em uma porção de 100g",
                R.drawable.macaxeira,
                "Starter"
        );
        Food food3 = new Food(
                29.90,
                15,
                "Camarão Empanado",
                "Camarões frescos empanados, crocantes e dourados, porção de 400g com molho tártaro",
                R.drawable.camarao,
                "Starter"
        );
        Food food4 = new Food(
                10.00,
                8,
                "Pão de Alho",
                "Pães de alho assados com manteiga de alho, dourados e crocantes, porção de 450g",
                R.drawable.paodealho,
                "Starter"
        );
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
    }
}


