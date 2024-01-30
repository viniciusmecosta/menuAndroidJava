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
import com.vinicius.menu.Models.Food;
import com.vinicius.menu.R;
import com.vinicius.menu.adapter.FoodAdapter;
import com.vinicius.menu.databinding.FragmentMainCourseBinding;
import java.util.ArrayList;

public class MainCourseFragment extends Fragment {
    private FragmentMainCourseBinding binding;
    private final ArrayList<Food> foodList = new ArrayList<>();
    private MainCourseFragmentCallback callback;

    public interface MainCourseFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    public MainCourseFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCourseFragmentCallback) {
            callback = (MainCourseFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainCourseFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

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
                if (callback != null) {
                    callback.onUpdateFoodItems(foodList);
                }
            }
        });
        getFood();
    }
    private void getFood(){
        foodList.clear();
        Food food5 = new Food(
                65.00,
                30,
                "Bife à Parmegiana",
                "Bife empanado e frito, coberto com molho de tomate e queijo mussarela derretido, acompanhado de arroz branco e batatas fritas",
                R.drawable.bife,
                "Main"
        );
        Food food6 = new Food(
                68.00,
                30,
                "Lasanha à Bolonhesa",
                "Camadas de massa fresca com molho à bolonhesa, cobertas com bechamel cremoso e queijo mussarela gratinado",
                R.drawable.lasanha,
                "Main"
        );
        Food food7 = new Food(
                52.00,
                25,
                "Espaguete à Bolonhesa",
                "Espaguete al dente com molho à bolonhesa rico em sabor, feito com carne moída e tomates frescos",
                R.drawable.espaguete,
                "Main"
        );
        Food food8 = new Food(
                78.50,
                45,
                "Feijoada Completa",
                "Generosa porção de feijoada com carnes variadas, acompanhada de arroz, farofa crocante, couve refogada e laranja",
                R.drawable.feijoada,
                "Main"
        );
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
    }
}