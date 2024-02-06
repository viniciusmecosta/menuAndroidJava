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
public class MainActivity extends AppCompatActivity {
    String[] tables = {"01", "02", "03", "04", "05"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView inputTable;
    EditText inputName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.auto_initial);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, tables);
        autoCompleteTextView.setAdapter(adapterItems);
        inputTable = findViewById(R.id.auto_initial);
        inputName = findViewById(R.id.edit_name);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    public void goMenu(View view) {
        if (!TextUtils.isEmpty(inputTable.getText().toString()) && !TextUtils.isEmpty(inputName.getText().toString())) {
            Intent in = new Intent(MainActivity.this, Menu.class);
            startActivity(in);
        } else {
            if (TextUtils.isEmpty(inputTable.getText().toString()) && TextUtils.isEmpty(inputName.getText().toString())) {
                Toast.makeText(MainActivity.this, "Por favor! Preencha o campo de Nome e selecione a sua mesa.", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(inputTable.getText().toString())) {
                Toast.makeText(MainActivity.this, "Por favor! Selecione a sua mesa.", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(inputName.getText().toString())) {
                Toast.makeText(MainActivity.this, "Por favor! Preencha o campo Nome.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


