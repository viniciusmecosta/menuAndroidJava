package com.vinicius.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.vinicius.menu.Models.Food;
import com.vinicius.menu.adapter.CheckoutAdapter;

import java.util.List;

public class Checkout extends AppCompatActivity {

    private RecyclerView checkoutRecyclerView;
    private TextView totalPriceTextView;
    private TextView estimatedTimeTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutRecyclerView = findViewById(R.id.recycle_checkout);
        totalPriceTextView = findViewById(R.id.txt_price);
        estimatedTimeTextView = findViewById(R.id.txt_time);
        submitButton = findViewById(R.id.button_conclued);

        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Food> selectedItems = Menu.SelectedItemsHolder.selectedItems;
        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(selectedItems, this); // Se necessário, ajuste o construtor do CheckoutAdapter para aceitar Context
        checkoutRecyclerView.setAdapter(checkoutAdapter);

        double total = calculateTotal(selectedItems);
        int maxTime = calculateMaxTime(selectedItems);

        submitButton.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout.this, Time.class);
            intent.putExtra("estimatedTime", maxTime);
            startActivity(intent);
        });
    }
    private double calculateTotal(List<Food> selectedItems) {
        double total = 0;
        for (Food item : selectedItems) {
            total += item.getPrice();
        }
        totalPriceTextView.setText(String.format("R$%.2f", total));
        return total;
    }

    private int calculateMaxTime(List<Food> selectedItems) {
        int maxTime = 0;
        for (Food item : selectedItems) {
            if (item.getTime() > maxTime) {
                maxTime = item.getTime();
            }
        }
        estimatedTimeTextView.setText(String.format("%d min", maxTime));
        return maxTime;
    }
}
