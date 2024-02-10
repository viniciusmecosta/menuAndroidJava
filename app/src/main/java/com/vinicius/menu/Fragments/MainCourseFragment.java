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
import com.vinicius.menu.databinding.FragmentMainCourseBinding;
import java.util.ArrayList;

// MainCourseFragment é responsável por exibir os pratos principais disponíveis no menu.
public class MainCourseFragment extends Fragment {
    private FragmentMainCourseBinding binding; // Utiliza View Binding para acessar as views de forma segura e eficiente.
    private MainCourseFragmentCallback callback; // Interface de callback para comunicação com a atividade principal.

    // Interface para definir o callback que será chamado quando os itens de pratos principais forem atualizados.
    public interface MainCourseFragmentCallback {
        void onUpdateFoodItems(ArrayList<Food> foodList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verifica se a atividade contêiner implementou o callback necessário.
        if (context instanceof MainCourseFragmentCallback) {
            callback = (MainCourseFragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MainCourseFragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // Limpa a referência ao callback para evitar vazamentos de memória.
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla o layout do fragmento usando View Binding, facilitando o acesso aos componentes da UI.
        binding = FragmentMainCourseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Configura o RecyclerView com um LinearLayoutManager e o adapter para exibir os pratos principais.
        RecyclerView recyclerViewMainCourse = binding.recycleMainCourse;
        recyclerViewMainCourse.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewMainCourse.setHasFixedSize(true);

        // Obtém a lista de pratos principais da atividade principal, filtrada pela categoria "Main".
        Menu activity = (Menu) getActivity();
        ArrayList<Food> mainCourseFoodList = activity.getFoodItemsByCategory("Main");

        // Cria e configura o adapter para o RecyclerView com os itens de pratos principais.
        FoodAdapter foodAdapter = new FoodAdapter(mainCourseFoodList, requireContext());
        recyclerViewMainCourse.setAdapter(foodAdapter);

        // Define um listener no adapter para detectar mudanças na seleção dos itens.
        foodAdapter.setFoodItemChangeListener(() -> {
            if (callback != null) {
                // Notifica a atividade contêiner que os itens de pratos principais foram atualizados.
                callback.onUpdateFoodItems(mainCourseFoodList);
            }
        });
    }
}
