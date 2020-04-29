package com.example.roommatesshopping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemHolder>{

    public static final String DEBUG_TAG = "ItemRecyclerAdapter";

    private List<Item> itemList;
    private String listKey;

    public ItemRecyclerAdapter( List<Item> itemList, String listKey) {
        this.itemList = itemList;
        this.listKey = listKey;
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView quantity;
        Button purchaseButton;

        public ItemHolder(View itemView){
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantity);
            purchaseButton = itemView.findViewById(R.id.purchase);

            purchaseButton.setOnClickListener(new PurchaseButtonListener());
        }
        private class PurchaseButtonListener implements View.OnClickListener{
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm Purchase");

                final EditText input = new EditText(v.getContext());
                input.setHint("Amount Paid");
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("items").child(listKey);
                        Log.d(DEBUG_TAG, "You are here.");

                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String removeKey = "";
                                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                                    Item item = postSnapshot.getValue(Item.class);
                                    item.setPrice(Double.parseDouble(input.getText().toString()));
                                    item.setNameOfPurchaser(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    Log.d(DEBUG_TAG, "Item 1: " + item.getItemName());
                                    Log.d(DEBUG_TAG, "Item 2: " + itemName.getText().toString());
                                    if(item.getItemName().equals(itemName.getText().toString())){
                                        removeKey = postSnapshot.getKey();
                                        DatabaseReference otherRef = database.getReference("purchaseditems").child(listKey).child(removeKey);
                                        otherRef.setValue(item);
                                        break;
                                    }
                                }
                                Log.d(DEBUG_TAG, "Key: " + removeKey);
                                DatabaseReference otherRef = database.getReference("items").child(listKey).child(removeKey);
                                otherRef.removeValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        }

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.item, parent, false );
        return new ItemHolder( view );
    }

    @Override
    public void onBindViewHolder( ItemHolder holder, int position ) {
        Item item = itemList.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + item );

        holder.itemName.setText( item.getItemName());
        holder.quantity.setText("Quantity: " + Integer.toString(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
