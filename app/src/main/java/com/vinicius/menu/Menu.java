package com.vinicius.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
    private ArrayList<Food> allFoodItems;
    public static class SelectedItemsHolder {
        public static ArrayList<Food> selectedItems = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeFoodItems();
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
                    Toast.makeText(Menu.this, "Por favor, escolha pelo menos um prato.", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<Food> selectedItems = new ArrayList<>();
                    for (ArrayList<Food> category : selectedFoodItems.values()) {
                        for (Food food : category) {
                            if (food.isSelected()) {
                                selectedItems.add(food);
                            }
                        }
                    }
                    SelectedItemsHolder.selectedItems = selectedItems;
                    Intent intent = new Intent(Menu.this, Checkout.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void initializeFoodItems() {
        allFoodItems = new ArrayList<>();
        allFoodItems.add(new Food(
                12.50,
                10,
                "Batata Frita",
                "Deliciosas batatas fritas, douradas e crocantes, servidas em uma porção de 300g",
                R.drawable.batata,
                "Starter"
        ));
        allFoodItems.add(new Food(
                15.00,
                12,
                "Macaxeira Frita",
                "Macaxeira frita até atingir textura crocante, em uma porção de 100g",
                R.drawable.macaxeira,
                "Starter"
        ));
        allFoodItems.add(new Food(
                29.90,
                15,
                "Camarão Empanado",
                "Camarões frescos empanados, crocantes e dourados, porção de 400g com molho tártaro",
                R.drawable.camarao,
                "Starter"
        ));
        allFoodItems.add(new Food(
                10.00,
                8,
                "Pão de Alho",
                "Pães de alho assados com manteiga de alho, dourados e crocantes, porção de 450g",
                R.drawable.paodealho,
                "Starter"
        ));
        allFoodItems.add(new Food(
                65.00,
                30,
                "Bife à Parmegiana",
                "Bife empanado e frito, coberto com molho de tomate e queijo mussarela derretido, acompanhado de arroz branco e batatas fritas",
                R.drawable.bife,
                "Main"
        ));
        allFoodItems.add(new Food(
                68.00,
                30,
                "Lasanha à Bolonhesa",
                "Camadas de massa fresca com molho à bolonhesa, cobertas com bechamel cremoso e queijo mussarela gratinado",
                R.drawable.lasanha,
                "Main"
        ));
        allFoodItems.add(new Food(
                52.00,
                25,
                "Espaguete à Bolonhesa",
                "Espaguete al dente com molho à bolonhesa rico em sabor, feito com carne moída e tomates frescos",
                R.drawable.espaguete,
                "Main"
        ));
        allFoodItems.add(new Food(
                78.50,
                45,
                "Feijoada Completa",
                "Generosa porção de feijoada com carnes variadas, acompanhada de arroz, farofa crocante, couve refogada e laranja",
                R.drawable.feijoada,
                "Main"
        ));
        allFoodItems.add(new Food(
                18.00,
                5,
                "Suco de Acerola",
                "Suco fresco de acerola, rico em vitamina C e com um sabor tropical único",
                R.drawable.acerola,
                "Drinks"
        ));
        allFoodItems.add(new Food(
                18.00,
                5,
                "Suco de Laranja",
                "Suco natural de laranja, espremido na hora, cheio de vitaminas e sabor",
                R.drawable.laranja,
                "Drinks"
        ));
        allFoodItems.add(new Food(
                19.00,
                5,
                "Suco de Maracujá",
                "Delicioso suco de maracujá, refrescante e com um equilíbrio perfeito entre doce e azedo",
                R.drawable.maracuja,
                "Drinks"
        ));
        allFoodItems.add(new Food(
                20.00,
                5,
                "Caipirinha",
                "Clássica caipirinha brasileira feita com cachaça, limão, açúcar e gelo, perfeita para refrescar",
                R.drawable.caipirinha,
                "Drinks"
        ));
        allFoodItems.add(new Food(
                22.00,
                15,
                "Pudim de Leite",
                "Clássico pudim de leite condensado, com uma textura suave e uma calda de caramelo deliciosa",
                R.drawable.pudim,
                "Dessert"
        ));
        allFoodItems.add(new Food(
                24.00,
                20,
                "Torta de Limão",
                "Torta de limão com uma base crocante de biscoito, recheio cremoso de limão e cobertura de merengue levemente tostado",
                R.drawable.torta,
                "Dessert"
        ));
        allFoodItems.add(new Food(
                25.00,
                10,
                "Mousse de Chocolate",
                "Mousse de chocolate rico e aveludado, feito com chocolate de alta qualidade e um toque de chantilly",
                R.drawable.mousse,
                "Dessert"
        ));
        allFoodItems.add(new Food(
                23.00,
                10,
                "Cheesecake Frutas Vermelhas",
                "Cheesecake suave e cremoso com uma camada generosa de geleia de frutas vermelhas no topo",
                R.drawable.cheesecake,
                "Dessert"
        ));
    }
    public ArrayList<Food> getFoodItemsByCategory(String category) {
        ArrayList<Food> filteredList = new ArrayList<>();
        for (Food food : allFoodItems) {
            if (food.getCategory().equals(category)) {
                filteredList.add(food);
            }
        }
        return filteredList;
    }
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
    private void updateAverageTimeAndPrice() {
        int maxTime = 0; // Usado para encontrar o maior tempo
        totalPrice = 0;
        for (ArrayList<Food> foods : selectedFoodItems.values()) {
            for (Food food : foods) {
                if (food.isSelected()) {
                    if (food.getTime() > maxTime) {
                        maxTime = food.getTime();
                    }
                    totalPrice += food.getPrice();
                }
            }
        }
        binding.textTimeNumber.setText(String.valueOf(maxTime) + "min");
        binding.textPriceNumber.setText(String.format("R$ %.2f", totalPrice));
    }
}