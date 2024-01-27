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
import com.vinicius.menu.databinding.FragmentStarterBinding;

import java.util.ArrayList;

public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> foodList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public StarterFragment() {
        // Required empty public constructor
    }

    public static StarterFragment newInstance(String param1, String param2) {
        StarterFragment fragment = new StarterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStarterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFood = binding.recycleStarter;
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
    }
    private void getFood(){
        Food food1 = new Food(
                12.50,
                10,
                "Batata Frita",
                "Deliciosas batatas fritas, douradas e crocantes, servidas em uma porção de 300g",
                R.drawable.batata
        );

        Food food2 = new Food(
                15.00,
                12,
                "Macaxeira Frita",
                "Macaxeira frita até atingir textura crocante, em uma porção de 100g",
                R.drawable.macaxeira
        );

        Food food3 = new Food(
                29.90,
                15,
                "Camarão Empanado",
                "Camarões frescos empanados, crocantes e dourados, porção de 400g com molho tártaro",
                R.drawable.camarao
        );

        Food food4 = new Food(
                10.00,
                8,
                "Pão de Alho",
                "Pães de alho assados com manteiga de alho, dourados e crocantes, porção de 450g",
                R.drawable.paodealho
        );

        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
    }
    private void updateAverageTimeAndPrice() {
        int totalTime = 0;
        int count = 0;
        double totalPrice = 0;
        for (Food food : foodList) {
            if (food.isSelected()) {
                totalTime += food.getTime();
                totalPrice += food.getPrice();;
                count++;
            }
        }
        int averageTime = count > 0 ? totalTime / count : 0;
        binding.textTimeNumber.setText(String.valueOf(averageTime));
        binding.textPriceNumber.setText(String.format("R$ %.2f", totalPrice));
    }
}