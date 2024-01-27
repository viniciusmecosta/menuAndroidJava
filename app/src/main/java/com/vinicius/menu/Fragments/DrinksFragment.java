package com.vinicius.menu.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentDessertBinding;
import com.vinicius.menu.databinding.FragmentDrinksBinding;
import com.vinicius.menu.databinding.FragmentMainCourseBinding;

import java.util.ArrayList;

public class DrinksFragment extends Fragment {
    private FragmentDrinksBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> foodList = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public DrinksFragment() {
    }

    public static DrinksFragment newInstance(String param1, String param2) {
        DrinksFragment fragment = new DrinksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFood = binding.recycleDrinks;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewFood.setHasFixedSize(true);

        foodAdapter = new FoodAdapter(foodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);

        foodAdapter.setFoodItemChangeListener(new FoodAdapter.FoodItemChangeListener() {
            @Override
            public void onFoodItemSelectionChanged() {
                updateAverageTimeAndPrice();
            }
        });

        getFood();
        foodAdapter.notifyDataSetChanged(); // Notificar o adaptador sobre a mudança de dados
    }

    private void getFood() {
        Food food9 = new Food(
                18.00,
                5,
                "Suco de Acerola",
                "Suco fresco de acerola, rico em vitamina C e com um sabor tropical único",
                R.drawable.acerola
        );

        Food food10 = new Food(
                18.00,
                5,
                "Suco de Laranja",
                "Suco natural de laranja, espremido na hora, cheio de vitaminas e sabor",
                R.drawable.laranja
        );

        Food food11 = new Food(
                19.00,
                5,
                "Suco de Maracujá",
                "Delicioso suco de maracujá, refrescante e com um equilíbrio perfeito entre doce e azedo",
                R.drawable.maracuja
        );

        Food food12 = new Food(
                20.00,
                5,
                "Caipirinha",
                "Clássica caipirinha brasileira feita com cachaça, limão, açúcar e gelo, perfeita para refrescar",
                R.drawable.caipirinha
        );

        foodList.add(food9);
        foodList.add(food10);
        foodList.add(food11);
        foodList.add(food12);
    }

    public void updateAverageTimeAndPrice() {
        int totalTime = 0;
        int count = 0;
        double totalPrice = 0;
        for (Food food : foodList) {
            if (food.isSelected()) {
                totalTime += food.getTime();
                totalPrice += food.getPrice();
                count++;
            }
        }
        int averageTime = count > 0 ? totalTime / count : 0;
        binding.textTimeNumber.setText(String.valueOf(averageTime));
        binding.textPriceNumber.setText(String.format("R$ %.2f", totalPrice));
    }
}
