package com.example.roommatesshopping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

/**
 * This is the RecyclerAdapter that is used for our items
 * where the functions of an item are created
 */
public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemHolder>{

    public static final String DEBUG_TAG = "ItemRecyclerAdapter";

    private List<Item> itemList;
    private String listKey;

    /**
     * Constructor
     * @param itemList list of items this item will belong to
     * @param listKey list key for the shopping list this belongs to
     */
    public ItemRecyclerAdapter( List<Item> itemList, String listKey) {
        this.itemList = itemList;
        this.listKey = listKey;
    }

    /**
     * Class for the holder for the items
     */
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

        /**
         * Overridden method for the purchase button listener.
         * Inside here is where the user can purchase an item
         */
        private class PurchaseButtonListener implements View.OnClickListener{
            @Override
            public void onClick(final View v){
                //Creates an alert dialog that allows the user to input a price they purchased for
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirm Purchase");

                final EditText input = new EditText(v.getContext());
                input.setHint("Amount Paid");
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //Once the user inputs a price, it will add the item to the purchased items list
                    //and remove it from the shopping list it is currently in
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("items").child(listKey);
                        Log.d(DEBUG_TAG, "You are here.");

                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            //This is where we clone the item in the shopping list and create one in the
                            //purchased items list and remove it.
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
                        //Intent to refresh the page once an item is purchased to remove it visually
                        Intent intent = new Intent(v.getContext(), ViewListsActivity.class);
                        intent.putExtra("listKey", listKey);
                        v.getContext().startActivity(intent);
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

    /**
     * Overridden method for view holders when this class is called
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.item, parent, false );
        return new ItemHolder( view );
    }

    /**
     * Overridden method that sets the values of what should be displayed
     * @param holder
     * @param position
     */
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
