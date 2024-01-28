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
import com.vinicius.menu.databinding.FragmentDrinksBinding;
import java.util.ArrayList;
public class DrinksFragment extends Fragment {
    private FragmentDrinksBinding binding;
    public double totalPrice = 0;
    private final ArrayList<Food> foodList = new ArrayList<>();
    public DrinksFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @SuppressLint("NotifyDataSetChanged")
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
        foodAdapter.notifyDataSetChanged();
    }
    private void getFood() {
        foodList.clear();
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
    @SuppressLint("DefaultLocale")
    public void updateAverageTimeAndPrice() {
        int totalTime = 0;
        totalPrice = 0;
        int count = 0;
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
