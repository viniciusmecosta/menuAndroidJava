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
import com.vinicius.menu.databinding.FragmentMainCourseBinding;
import java.util.ArrayList;
public class MainCourseFragment extends Fragment {
    private FragmentMainCourseBinding binding;
    public double totalPrice = 0;
    private final ArrayList<Food> foodList = new ArrayList<>();
    public MainCourseFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewFood = binding.recycleMainCourse;
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
    @SuppressLint("DefaultLocale")
    private void updateAverageTimeAndPrice() {
        int totalTime = 0;
        int count = 0;
        totalPrice = 0;
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