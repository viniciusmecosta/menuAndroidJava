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
import com.vinicius.menu.databinding.FragmentMainCourseBinding;

import java.util.ArrayList;

public class MainCourseFragment extends Fragment {
    private FragmentMainCourseBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> foodList = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MainCourseFragment() {
    }

    public static MainCourseFragment newInstance(String param1, String param2) {
        MainCourseFragment fragment = new MainCourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFood = binding.recycleMainCourse;
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
        Food food5 = new Food(
                65.00,
                30,
                "Bife à Parmegiana",
                "Bife empanado e frito, coberto com molho de tomate e queijo mussarela derretido, acompanhado de arroz branco e batatas fritas",
                R.drawable.bife
        );

        Food food6 = new Food(
                68.00,
                30,
                "Lasanha à Bolonhesa",
                "Camadas de massa fresca com molho à bolonhesa, cobertas com bechamel cremoso e queijo mussarela gratinado",
                R.drawable.lasanha
        );

        Food food7 = new Food(
                52.00,
                25,
                "Espaguete à Bolonhesa",
                "Espaguete al dente com molho à bolonhesa rico em sabor, feito com carne moída e tomates frescos",
                R.drawable.espaguete
        );

        Food food8 = new Food(
                78.50,
                45,
                "Feijoada Completa",
                "Generosa porção de feijoada com carnes variadas, acompanhada de arroz, farofa crocante, couve refogada e laranja",
                R.drawable.feijoada
        );

        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
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