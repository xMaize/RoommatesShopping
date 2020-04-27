package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewListActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "NewListActivity";

    private EditText listNameView;
    private Button createButton;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        createButton = findViewById(R.id.create);
        listNameView = findViewById(R.id.listName);

        createButton.setOnClickListener(new CreateButtonListener());
        Intent intent = getIntent();
        userID = intent.getStringExtra("uid");
        Log.d(DEBUG_TAG, "ID: " + userID);


    }


    private class CreateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){

            String listName = listNameView.getText().toString();
            final ShoppingList shoppingList = new ShoppingList(listName);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("shoppinglists");

            Log.d(DEBUG_TAG, "Key: " + myRef.getKey());

            DatabaseReference newRef = myRef.push();

            newRef.setValue( shoppingList )
                    .addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText( getApplicationContext(), "Successfully created a Shopping list for " + shoppingList.getName(),
                                    Toast.LENGTH_SHORT).show();
                            // Clear the EditTexts for next use.
                            listNameView.setText("");
                        }
                    })
                    .addOnFailureListener( new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText( getApplicationContext(), "Failed to create a Shopping list for " + shoppingList.getName(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

            myRef = database.getReference("users").child(userID).child("shoppinglists");

            myRef.push().setValue(newRef.getKey());

            Log.d(DEBUG_TAG, "Key: " + newRef.getKey());

        }
    }

}
