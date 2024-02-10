package com.vinicius.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

// MainActivity é a atividade inicial do aplicativo, onde o usuário pode escolher uma mesa e digitar seu nome.
public class MainActivity extends AppCompatActivity {
    // Definição de arrays e variáveis para controle dos elementos de UI.
    String[] tables = {"01", "02", "03", "04", "05"}; // Array com os números das mesas disponíveis.
    AutoCompleteTextView autoCompleteTextView; // Campo de texto com sugestões automáticas para as mesas.
    ArrayAdapter<String> adapterItems; // Adaptador para o AutoCompleteTextView.
    AutoCompleteTextView inputTable; // Referência ao campo de texto para escolha da mesa.
    EditText inputName; // Campo de texto para entrada do nome do usuário.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os elementos de UI baseados em seus IDs.
        autoCompleteTextView = findViewById(R.id.auto_initial);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, tables); // Configura o adaptador com o layout e os dados das mesas.
        autoCompleteTextView.setAdapter(adapterItems); // Define o adaptador para o AutoCompleteTextView.

        // Referências diretas aos campos de entrada do usuário.
        inputTable = findViewById(R.id.auto_initial);
        inputName = findViewById(R.id.edit_name);

        // Listener para tratar o evento de clique em um item das sugestões de mesas.
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString(); // Pega o item selecionado.
            }
        });
    }

    // Método chamado ao pressionar o botão para ir ao menu.
    public void goMenu(View view) {
        // Verifica se os campos de mesa e nome não estão vazios.
        if (!TextUtils.isEmpty(inputTable.getText().toString()) && !TextUtils.isEmpty(inputName.getText().toString())) {
            // Se ambos os campos estiverem preenchidos, inicia a atividade Menu.
            Intent in = new Intent(MainActivity.this, Menu.class);
            startActivity(in);
        } else {
            // Caso contrário, mostra mensagens de erro específicas para cada campo vazio.
            if (TextUtils.isEmpty(inputTable.getText().toString()) && TextUtils.isEmpty(inputName.getText().toString())) {
                Toast.makeText(MainActivity.this, "Preencha o campo de Nome e selecione a sua Mesa.", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(inputTable.getText().toString())) {
                Toast.makeText(MainActivity.this, "Por favor! Selecione a sua mesa.", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(inputName.getText().toString())) {
                Toast.makeText(MainActivity.this, "Por favor! Preencha o campo Nome.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
