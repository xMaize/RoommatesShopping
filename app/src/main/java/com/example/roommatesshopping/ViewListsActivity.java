package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewListsActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "ViewListsActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private List<ShoppingList> shoppingLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        shoppingLists = new ArrayList<ShoppingList>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for( DataSnapshot postSnapshot: dataSnapshot.getChildren() ) {
                    ShoppingList shoppingList = postSnapshot.getValue(ShoppingList.class);
                    Log.d(DEBUG_TAG, "Error: " + Boolean.toString(shoppingList == null));
                    shoppingList.setKey(postSnapshot.getKey());
                    shoppingLists.add(shoppingList);
                    Log.d( DEBUG_TAG, "ViewListsActivity.onCreate(): added: " + shoppingList );
                }
                Log.d( DEBUG_TAG, "ViewListsActivity.onCreate(): setting recyclerAdapter" );

                // Now, create a ListsRecyclerAdapter to populate a ReceyclerView to display the job leads.

                recyclerAdapter = new ListsRecyclerAdapter( shoppingLists );
                recyclerView.setAdapter( recyclerAdapter );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

    @Override
    protected void onResume() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onResume()" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onPause()" );
        super.onPause();
    }

    // These activity callback methods are not needed and are for educational purposes only
    @Override
    protected void onStart() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onStart()" );
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onStop()" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onDestroy()" );
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d( DEBUG_TAG, "ViewListsActivity.onRestart()" );
        super.onRestart();
    }
}
