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
import com.vinicius.menu.databinding.FragmentDrinksBinding;
import java.util.ArrayList;

public class DrinksFragment extends Fragment {
    private FragmentDrinksBinding binding;
    private final ArrayList<Food> foodList = new ArrayList<>();
    private DrinksFragmentCallback callback;

    public interface DrinksFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    public DrinksFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DrinksFragmentCallback) {
            callback = (DrinksFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DrinksFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewFood = binding.recycleDrinks;
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
        Food food9 = new Food(
                18.00,
                5,
                "Suco de Acerola",
                "Suco fresco de acerola, rico em vitamina C e com um sabor tropical único",
                R.drawable.acerola,
                "Drinks"
        );
        Food food10 = new Food(
                18.00,
                5,
                "Suco de Laranja",
                "Suco natural de laranja, espremido na hora, cheio de vitaminas e sabor",
                R.drawable.laranja,
                "Drinks"
        );
        Food food11 = new Food(
                19.00,
                5,
                "Suco de Maracujá",
                "Delicioso suco de maracujá, refrescante e com um equilíbrio perfeito entre doce e azedo",
                R.drawable.maracuja,
                "Drinks"
        );
        Food food12 = new Food(
                20.00,
                5,
                "Caipirinha",
                "Clássica caipirinha brasileira feita com cachaça, limão, açúcar e gelo, perfeita para refrescar",
                R.drawable.caipirinha,
                "Drinks"
        );
        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);
        foodList.add(food12);
    }
}
