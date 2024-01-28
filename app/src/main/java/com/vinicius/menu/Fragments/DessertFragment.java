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
import com.vinicius.menu.databinding.FragmentDessertBinding;
import java.util.ArrayList;

public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding;
    public double totalPrice = 0;
    private final ArrayList<Food> foodList = new ArrayList<>();
    public DessertFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDessertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @SuppressLint("NotifyDataSetChanged")
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
        foodAdapter.notifyDataSetChanged(); // Notificar o adaptador sobre a mudança de dados
    }
    private void getFood(){
        foodList.clear();
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