package com.vinicius.menu.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinicius.menu.Menu;
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentDessertBinding;
import java.util.ArrayList;

public class DessertFragment extends Fragment {
    private FragmentDessertBinding binding;
    private DessertFragmentCallback callback;

    public interface DessertFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DessertFragmentCallback) {
            callback = (DessertFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DessertFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDessertBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewFood = binding.recycleDessert;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewFood.setHasFixedSize(true);

        Menu activity = (Menu) getActivity();
        ArrayList<Food> dessertFoodList = activity.getFoodItemsByCategory("Dessert");
        FoodAdapter foodAdapter = new FoodAdapter(dessertFoodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);

        foodAdapter.setFoodItemChangeListener(new FoodAdapter.FoodItemChangeListener() {
            @Override
            public void onFoodItemSelectionChanged() {
                if (callback != null) {
                    callback.onUpdateFoodItems(dessertFoodList);
                }
            }
        });
    }
}
