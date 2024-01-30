package com.vinicius.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vinicius.menu.Fragments.DessertFragment;
import com.vinicius.menu.Fragments.DrinksFragment;
import com.vinicius.menu.Fragments.MainCourseFragment;
import com.vinicius.menu.Fragments.StarterFragment;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.databinding.ActivityMenuBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu extends AppCompatActivity implements
        StarterFragment.StarterFragmentCallback,
        MainCourseFragment.MainCourseFragmentCallback,
        DrinksFragment.DrinksFragmentCallback,
        DessertFragment.DessertFragmentCallback {

    private ActivityMenuBinding binding;
    private double totalPrice;
    private final Map<String, ArrayList<Food>> selectedFoodItems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.starter_Page, StarterFragment.class)
                .add(R.string.maincourse_Page, MainCourseFragment.class)
                .add(R.string.drinks_Page, DrinksFragment.class)
                .add(R.string.dessert_Page, DessertFragment.class)
                .create());
        binding.menuPager.setAdapter(adapter);
        binding.tabMenu.setViewPager(binding.menuPager);
        binding.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (totalPrice == 0) {
                    Toast.makeText(Menu.this, "Por favor, selecione algum item.", Toast.LENGTH_SHORT).show();
                } else {
                    // Implementar ação do checkout
                    // Exemplo: Iniciar a Activity de Checkout
                    Intent intent = new Intent(Menu.this, Checkout.class);
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onUpdateFoodItems(ArrayList<Food> foodList) {
        updateSelectedFoodItems(foodList);
        updateAverageTimeAndPrice();
    }

    private void updateSelectedFoodItems(ArrayList<Food> foodList) {
        if (!foodList.isEmpty()) {
            selectedFoodItems.put(foodList.get(0).getCategory(), foodList);
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateAverageTimeAndPrice() {
        int totalTime = 0;
        totalPrice = 0;
        int count = 0;
        for (ArrayList<Food> foods : selectedFoodItems.values()) {
            for (Food food : foods) {
                if (food.isSelected()) {
                    totalTime += food.getTime();
                    totalPrice += food.getPrice();
                    count++;
                }
            }
        }
        int averageTime = count > 0 ? totalTime / count : 0;
        binding.textTimeNumber.setText(String.valueOf(averageTime));
        binding.textPriceNumber.setText(String.format("R$ %.2f", totalPrice));
    }
}
