package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShoppingListManagementActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ShoppingListManagement";
    private Button createList;
    private Button viewLists;
    private Button joinList;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_management);

        createList = findViewById(R.id.create);
        viewLists = findViewById(R.id.view);
        joinList = findViewById(R.id.join);

        createList.setOnClickListener(new CreateButtonListener());
        viewLists.setOnClickListener(new CreateButtonListener2());

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("Email");


    }

    private class CreateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){

            Intent intent = new Intent(v.getContext(), NewListActivity.class);
            intent.putExtra("Email", userEmail);
            v.getContext().startActivity(intent);

        }
    }
    private class CreateButtonListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v){

            Intent intent = new Intent(v.getContext(), ViewListsActivity.class);
            intent.putExtra("Email", userEmail);
            v.getContext().startActivity(intent);

        }
    }
}
