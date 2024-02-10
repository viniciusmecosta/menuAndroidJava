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
// A classe Menu gerencia a visualização dos itens do menu em diferentes categorias usando fragments.
public class Menu extends AppCompatActivity implements
        StarterFragment.StarterFragmentCallback,
        MainCourseFragment.MainCourseFragmentCallback,
        DrinksFragment.DrinksFragmentCallback,
        DessertFragment.DessertFragmentCallback {

    private ActivityMenuBinding binding; // Binding para acessar os elementos da view de forma segura.
    private double totalPrice = 0; // Armazena o preço total dos itens selecionados.
    // Mapeia categorias de alimentos a suas listas de itens selecionados.
    private final Map<String, ArrayList<Food>> selectedFoodItems = new HashMap<>();
    // Lista para armazenar todos os itens de comida disponíveis no menu.
    private ArrayList<Food> allFoodItems;

    // Classe interna estática para manter os itens selecionados durante a navegação entre activities.
    public static class SelectedItemsHolder {
        public static ArrayList<Food> selectedItems = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeFoodItems(); // Inicializa os itens do menu chamando o método correspondente.

        // Inicializa o adaptador para gerenciar as abas das categorias de alimentos usando FragmentPagerItemAdapter.
// O FragmentPagerItemAdapter é responsável por gerenciar os fragments que representam cada categoria do menu.
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), // Obtém o FragmentManager para interação com os fragments associados a este contexto.
                FragmentPagerItems.with(this) // Inicia a construção de itens de página (tabs) para o adaptador.
                        .add(R.string.starter_Page, StarterFragment.class) // Adiciona o fragment de entradas ao ViewPager.
                        .add(R.string.maincourse_Page, MainCourseFragment.class) // Adiciona o fragment de pratos principais.
                        .add(R.string.drinks_Page, DrinksFragment.class) // Adiciona o fragment de bebidas.
                        .add(R.string.dessert_Page, DessertFragment.class) // Adiciona o fragment de sobremesas.
                        .create() // Finaliza a criação dos itens e retorna a configuração para o adaptador.
        );

// Configura o ViewPager com o adaptador criado, que contém os fragments para cada categoria.
        binding.menuPager.setAdapter(adapter);

// Associa o SmartTabLayout ao ViewPager.
// Isso permite que as abas sejam sincronizadas com as páginas do ViewPager, facilitando a navegação entre as categorias.
        binding.tabMenu.setViewPager(binding.menuPager);

// Configura o listener de clique para o botão de checkout.
        binding.buttonCheckout.setOnClickListener(v -> {
            // Verifica se o preço total dos itens selecionados é igual a zero, indicando que nenhum item foi selecionado.
            if (totalPrice == 0) {
                // Exibe uma mensagem pedindo que o usuário selecione pelo menos um prato antes de prosseguir.
                Toast.makeText(Menu.this, "Por favor, escolha pelo menos um prato.", Toast.LENGTH_SHORT).show();
            } else {
                // Caso haja itens selecionados, prepara a lista de itens selecionados.
                SelectedItemsHolder.selectedItems = new ArrayList<>();
                // Itera sobre todas as categorias de itens selecionados.
                for (ArrayList<Food> category : selectedFoodItems.values()) {
                    // Itera sobre os itens dentro de cada categoria.
                    for (Food food : category) {
                        // Verifica se o item está selecionado.
                        if (food.isSelected()) {
                            // Adiciona o item selecionado à lista de itens para checkout.
                            SelectedItemsHolder.selectedItems.add(food);
                        }
                    }
                }
                // Inicia a atividade de checkout, passando a lista de itens selecionados para ela.
                startActivity(new Intent(Menu.this, Checkout.class));
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

    // Método para recuperar uma lista de itens de Food filtrados por categoria.
    public ArrayList<Food> getFoodItemsByCategory(String category) {
        // Cria uma nova lista vazia para armazenar os itens de comida filtrados.
        ArrayList<Food> filteredList = new ArrayList<>();
        // Itera sobre todos os itens de comida disponíveis armazenados na lista allFoodItems.
        for (Food food : allFoodItems) {
            // Verifica se a categoria do item atual é igual à categoria desejada.
            if (food.getCategory().equals(category)) {
                // Se for verdadeiro, adiciona o item de comida atual à lista filtrada.
                filteredList.add(food);
            }
        }
        // Retorna a lista de itens de comida filtrados pela categoria especificada.
        return filteredList;
    }

    // Método chamado quando há uma atualização nos itens de comida selecionados em algum fragmento.
    @Override
    public void onUpdateFoodItems(ArrayList<Food> foodList) {
        // Atualiza a lista de itens selecionados com base na lista recebida.
        updateSelectedFoodItems(foodList);
        // Recalcula o preço total e o tempo máximo de preparo após a atualização.
        updateAverageTimeAndPrice();
    }

    // Atualiza o mapeamento de itens de comida selecionados com base na lista recebida de um fragmento.
    private void updateSelectedFoodItems(ArrayList<Food> foodList) {
        // Verifica se a lista recebida não está vazia para evitar a substituição por uma lista vazia.
        if (!foodList.isEmpty()) {
            // Insere ou atualiza a lista de itens selecionados para a categoria específica.
            // Usa a categoria do primeiro item como chave, assumindo que todos os itens na lista têm a mesma categoria.
            selectedFoodItems.put(foodList.get(0).getCategory(), foodList);
        }
    }

    // Atualiza o tempo de preparo médio e o preço total com base nos itens selecionados.
    private void updateAverageTimeAndPrice() {
        int maxTime = 0; // Inicializa a variável para armazenar o maior tempo de preparo entre os itens selecionados.
        totalPrice = 0; // Reseta o preço total a cada chamada do método para garantir que o cálculo seja correto.

        // Itera sobre cada categoria de alimentos selecionados.
        for (ArrayList<Food> foods : selectedFoodItems.values()) {
            // Itera sobre cada item de comida na categoria atual.
            for (Food food : foods) {
                // Verifica se o item atual está selecionado.
                if (food.isSelected()) {
                    // Atualiza o maxTime se o tempo de preparo do item atual for maior que o valor armazenado em maxTime.
                    if (food.getTime() > maxTime) {
                        maxTime = food.getTime(); // Atualiza o maior tempo de preparo encontrado.
                    }
                    // Adiciona o preço do item atual ao totalPrice.
                    totalPrice += food.getPrice(); // Soma o preço do item selecionado ao total.
                }
            }
        }
        // Atualiza os elementos de UI com os valores calculados para tempo e preço.
        binding.textTimeNumber.setText(String.valueOf(maxTime) + "min"); // Exibe o maior tempo de preparo em minutos.
        binding.textPriceNumber.setText(String.format("R$ %.2f", totalPrice)); // Exibe o preço total formatado em reais.
    }
}