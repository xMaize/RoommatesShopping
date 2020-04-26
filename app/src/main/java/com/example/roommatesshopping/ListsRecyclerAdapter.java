package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListsRecyclerAdapter extends RecyclerView.Adapter<ListsRecyclerAdapter.ListHolder> {

    public static final String DEBUG_TAG = "ListsRecyclerAdapter";

    private List<ShoppingList> shoppingLists;

    public ListsRecyclerAdapter(List<ShoppingList> shoppingLists ) {
        this.shoppingLists = shoppingLists;
    }

    class ListHolder extends RecyclerView.ViewHolder {

        TextView listName;
        TextView listKey;
        Button viewList;
        Button purchasedList;

        public ListHolder(View itemView){
            super(itemView);

            listName = itemView.findViewById(R.id.listName);
            listKey = itemView.findViewById(R.id.listKey);
            viewList = itemView.findViewById(R.id.viewList);
            purchasedList = itemView.findViewById(R.id.purchasedList);
        }

    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.shopping_list, parent, false );
        return new ListHolder( view );
    }

    @Override
    public void onBindViewHolder(ListsRecyclerAdapter.ListHolder holder, int position ) {
        ShoppingList shoppingList = shoppingLists.get( position );
        Log.d(DEBUG_TAG, "Error: " + Boolean.toString(shoppingList.getName()==null));
        Log.d( DEBUG_TAG, "onBindViewHolder: " + shoppingList );
        Log.d(DEBUG_TAG, "Error: " + Boolean.toString(holder == null));
        Log.d(DEBUG_TAG, "Error: " + Boolean.toString(holder.listName == null));
        holder.listName.setText("List name: " + shoppingList.getName());
        holder.listKey.setText("List Key: " + shoppingList.getKey());
    }



    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

}
