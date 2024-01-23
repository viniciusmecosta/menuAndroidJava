package com.vinicius.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    String[] tables = {"01","02","03","04","05"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView inputTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = this.<AutoCompleteTextView>findViewById(R.id.auto_initial);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, tables);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
        inputTable =(AutoCompleteTextView) findViewById(R.id.auto_initial);
    }
    public void goMenu(View view){
        if(TextUtils.isEmpty(inputTable.getText().toString())){
            Toast.makeText(MainActivity.this, "SELECIONE UMA MESA!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent in = new Intent (MainActivity.this, Menu.class);
            startActivity(in);
        }
    }
}