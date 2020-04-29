package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListManagementActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ShoppingListManagement";
    private Button createList;
    private Button viewLists;
    private Button joinList;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_management);

        createList = findViewById(R.id.create);
        viewLists = findViewById(R.id.view);
        joinList = findViewById(R.id.join);

        createList.setOnClickListener(new CreateButtonListener());
        viewLists.setOnClickListener(new ViewButtonListener());
        joinList.setOnClickListener(new JoinButtonListener());

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    private class CreateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){

            Intent intent = new Intent(v.getContext(), NewListActivity.class);
            intent.putExtra("uid", userID);
            v.getContext().startActivity(intent);

        }
    }
    private class ViewButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){

            final Intent intent = new Intent(v.getContext(), ViewListsActivity.class);
            intent.putExtra("uid", userID);
            v.getContext().startActivity(intent);

        }
    }
    private class JoinButtonListener implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(v.getContext(), JoinListActivity.class);
            intent.putExtra("uid", userID);
            v.getContext().startActivity(intent);
        }
    }

}
