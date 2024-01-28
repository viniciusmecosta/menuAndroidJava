package com.vinicius.menu.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.vinicius.menu.Checkout;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentStarterBinding;
import java.util.ArrayList;

public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding;
    public double totalPrice = 0;
    private final ArrayList<Food> foodList = new ArrayList<>();
    public StarterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                updateAverageTimeAndPrice();
            }
        });
        getFood();
        binding.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPrice == 0) {
                    Toast.makeText(requireContext(), "Por favor, selecione algum item.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent in = new Intent(getActivity(), Checkout.class); // Supondo que a atividade se chama CheckoutActivity
                    startActivity(in);
                }
            }
        });
    }
    private void getFood() {
        foodList.clear();
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
    @SuppressLint("DefaultLocale")
    private void updateAverageTimeAndPrice() {
        int totalTime = 0;
        int count = 0;
        totalPrice = 0;
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
