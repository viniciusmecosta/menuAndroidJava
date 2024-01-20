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
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    String[] tables = {"01","02","03","04","05","06","07","08","09"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adadpterItems;
    AutoCompleteTextView inputtable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.auto_Initial);
        adadpterItems = new ArrayAdapter<String>(this, R.layout.list_item, tables);
        autoCompleteTextView.setAdapter(adadpterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
        inputtable =(AutoCompleteTextView) findViewById(R.id.auto_Initial);
    }
    public void goMenu(View view){
        if(TextUtils.isEmpty(inputtable.getText().toString())){
            Toast.makeText(MainActivity.this, "Nenhuma mesa selecionada!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent in = new Intent (MainActivity.this, Menu.class);
            startActivity(in);
        }

    }


}