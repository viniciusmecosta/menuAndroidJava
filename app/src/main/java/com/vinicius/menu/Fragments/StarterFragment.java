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
import com.vinicius.menu.databinding.FragmentStarterBinding;
import java.util.ArrayList;

public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding;
    private StarterFragmentCallback callback;

    public interface StarterFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StarterFragmentCallback) {
            callback = (StarterFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement StarterFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
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

        Menu activity = (Menu) getActivity();
        ArrayList<Food> starterFoodList = activity.getFoodItemsByCategory("Starter");
        FoodAdapter foodAdapter = new FoodAdapter(starterFoodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);

        foodAdapter.setFoodItemChangeListener(new FoodAdapter.FoodItemChangeListener() {
            @Override
            public void onFoodItemSelectionChanged() {
                if (callback != null) {
                    callback.onUpdateFoodItems(starterFoodList);
                }
            }
        });
    }
}
