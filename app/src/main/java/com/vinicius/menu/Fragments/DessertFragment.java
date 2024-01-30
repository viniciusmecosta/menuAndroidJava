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
import com.vinicius.menu.databinding.FragmentDessertBinding;
import java.util.ArrayList;

public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding;
    private final ArrayList<Food> foodList = new ArrayList<>();
    private DessertFragmentCallback callback;

    public interface DessertFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    public DessertFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DessertFragmentCallback) {
            callback = (DessertFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DessertFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDessertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewFood = binding.recycleDessert;
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
    private void getFood(){
        foodList.clear();
        Food food13 = new Food(
                22.00,
                15,
                "Pudim de Leite",
                "Clássico pudim de leite condensado, com uma textura suave e uma calda de caramelo deliciosa",
                R.drawable.pudim,
                "Dessert"
        );
        Food food14 = new Food(
                24.00,
                20,
                "Torta de Limão",
                "Torta de limão com uma base crocante de biscoito, recheio cremoso de limão e cobertura de merengue levemente tostado",
                R.drawable.torta,
                "Dessert"
        );
        Food food15 = new Food(
                25.00,
                10,
                "Mousse de Chocolate",
                "Mousse de chocolate rico e aveludado, feito com chocolate de alta qualidade e um toque de chantilly",
                R.drawable.mousse,
                "Dessert"
        );
        Food food16 = new Food(
                23.00,
                10,
                "Cheesecake de Frutas Vermelhas",
                "Cheesecake suave e cremoso com uma camada generosa de geleia de frutas vermelhas no topo",
                R.drawable.cheesecake,
                "Dessert"
        );
        foodList.add(food13);
        foodList.add(food14);
        foodList.add(food15);
        foodList.add(food16);

    }

}