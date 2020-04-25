package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ShoppingListManagementActivity extends AppCompatActivity {

    private Button createList;
    private Button viewLists;
    private Button joinList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_management);

        createList = findViewById(R.id.create);
        viewLists = findViewById(R.id.view);
        joinList = findViewById(R.id.join);

    }
}
