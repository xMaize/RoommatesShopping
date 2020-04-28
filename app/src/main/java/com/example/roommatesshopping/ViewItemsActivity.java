package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewItemsActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ViewItemsActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private List<Item> items;
    private String listKey;
    private Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        recyclerView = findViewById(R.id.recyclerView);
        addItemButton  = findViewById(R.id.addItem);
        addItemButton.setOnClickListener(new AddItemButtonListener());

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listKey = getIntent().getStringExtra("listKey");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("items");

        items = new ArrayList<Item>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Item item = postSnapshot.getValue(Item.class);
                    String tempKey = postSnapshot.getChildren().iterator().next().getValue(String.class);
                    if(tempKey.equals(listKey)){
                        items.add(item);
                    }
                }
                recyclerAdapter = new ItemRecyclerAdapter(items);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(DEBUG_TAG, "Query failed: " + databaseError.toString());
            }
        });

    }

    private class AddItemButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent(v.getContext(), NewItemActivity.class);
            intent.putExtra("listKey", listKey);
            v.getContext().startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ViewItemsActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ViewItemsActivity.onPause()" );
        super.onPause();
    }

    // These activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "ViewItemsActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "ViewItemsActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "ViewItemsActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onRestart()" );
        super.onRestart();
    }

}
