package com.example.roommatesshopping;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemHolder>{

    public static final String DEBUG_TAG = "ItemRecyclerAdapter";

    private List<Item> itemList;

    public ItemRecyclerAdapter( List<Item> itemList ) {
        this.itemList = itemList;
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
