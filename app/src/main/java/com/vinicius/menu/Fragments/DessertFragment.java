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
import com.vinicius.menu.databinding.FragmentMainCourseBinding;

import java.util.ArrayList;

public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> foodList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DessertFragment() {
        // Required empty public constructor
    }

    public static DessertFragment newInstance(String param1, String param2) {
        DessertFragment fragment = new DessertFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDessertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFood = binding.recycleDessert;
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
    private void getFood(){
        Food food13 = new Food(
                22.00,
                15,
                "Pudim de Leite",
                "Clássico pudim de leite condensado, com uma textura suave e uma calda de caramelo deliciosa",
                R.drawable.pudim
        );

        Food food14 = new Food(
                24.00,
                20,
                "Torta de Limão",
                "Torta de limão com uma base crocante de biscoito, recheio cremoso de limão e cobertura de merengue levemente tostado",
                R.drawable.torta
        );

        Food food15 = new Food(
                25.00,
                10,
                "Mousse de Chocolate",
                "Mousse de chocolate rico e aveludado, feito com chocolate de alta qualidade e um toque de chantilly",
                R.drawable.mousse
        );

        Food food16 = new Food(
                23.00,
                10,
                "Cheesecake de Frutas Vermelhas",
                "Cheesecake suave e cremoso com uma camada generosa de geleia de frutas vermelhas no topo",
                R.drawable.cheesecake
        );

        foodList.add(food13);
        foodList.add(food14);
        foodList.add(food15);
        foodList.add(food16);

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