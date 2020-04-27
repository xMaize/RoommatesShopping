package com.example.roommatesshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinListActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "JoinListActivity";

    private String userID;
    private EditText ID;
    private Button joinList;
    private Boolean listExistence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_list);

        userID = getIntent().getStringExtra("uid");
        ID = findViewById(R.id.listID);
        joinList = findViewById(R.id.joinList);
        joinList.setOnClickListener(new JoinListButtonClickListener());
        listExistence = false;

    }

    private class JoinListButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final String listID = ID.getText().toString();

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("shoppinglists");

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                        if (postSnapshot.getKey().equals(listID)){
                            listExistence = true;
                        }
                    }
                    if(listExistence){
                        DatabaseReference otherRef = database.getReference("shoppinglists").child(listID);

                        otherRef.push().setValue(userID);
                    }
                    else{
                        Log.d(DEBUG_TAG, "Key not found.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
