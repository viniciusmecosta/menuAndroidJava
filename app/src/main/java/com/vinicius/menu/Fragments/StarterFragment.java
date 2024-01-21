package com.vinicius.menu.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinicius.menu.Models.Dish;
import com.vinicius.menu.R;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentStarterBinding;

import java.util.ArrayList;

public class StarterFragment extends Fragment {
    private FragmentStarterBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Dish> foodList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public StarterFragment() {
        // Required empty public constructor
    }

    public static StarterFragment newInstance(String param1, String param2) {
        StarterFragment fragment = new StarterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStarterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewFood = binding.recycleViewStarter;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewFood.setHasFixedSize(true);

        foodAdapter = new FoodAdapter(foodList, requireContext());
        recyclerViewFood.setAdapter(foodAdapter);
        getDish();
    }
    private void getDish(){
        Dish dish1 = new Dish(
                20.25,
                12,
                "Batata Frita",
                "Batata Frita 300g",
                R.drawable.batata

        );
        Dish dish2 = new Dish(
                146.87,
                14,
                "Macaxeira Frita",
                "Macaxeira Frita 100g",
                R.drawable.macaxeira

        );
        Dish dish3 = new Dish(
                42.98,
                16,
                "Camarão Empanado",
                "Camarão Empanado 400g",
                R.drawable.camarao

        );
        Dish dish4 = new Dish(
                40.76,
                18,
                "Pão de Alho",
                "Pão de Alho 450g",
                R.drawable.paodealho
        );
        foodList.add(dish1);
        foodList.add(dish2);
        foodList.add(dish3);
        foodList.add(dish4);
    }
}
