package com.example.roommatesshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewItemActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "NewItemActivity";

    private Button addItemButton;
    private EditText itemName;
    private EditText quantity;
    private String listKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        addItemButton = findViewById(R.id.addItem);
        addItemButton.setOnClickListener(new AddItemButtonListener());
        itemName = findViewById(R.id.itemName);
        quantity = findViewById(R.id.quantity);

        listKey = getIntent().getStringExtra("listKey");
        Log.d(DEBUG_TAG, "List Key: " + listKey);

    }

    private class AddItemButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

            final Item item = new Item();
            item.setItemName(itemName.getText().toString());
            item.setQuantity(Integer.parseInt(quantity.getText().toString()));

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("items");

            DatabaseReference newRef = myRef.push();

            newRef.setValue(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText( getApplicationContext(), "Successfully created a Shopping list for " + item.getItemName(),
                                        Toast.LENGTH_SHORT).show();
                                // Clear the EditTexts for next use.
                                itemName.setText("");
                                quantity.setText("");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText( getApplicationContext(), "Failed to create a Shopping list for " + item.getItemName(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            myRef = myRef.child(newRef.getKey());
            myRef.push().setValue(listKey);
        }
    }

}
