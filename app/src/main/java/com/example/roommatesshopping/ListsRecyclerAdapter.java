package com.example.roommatesshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        Button viewItems;
        Button purchasedList;

        public ListHolder(View itemView){
            super(itemView);

            listName = itemView.findViewById(R.id.listName);
            listKey = itemView.findViewById(R.id.listKey);
            viewItems = itemView.findViewById(R.id.viewList);
            purchasedList = itemView.findViewById(R.id.purchasedList);

            viewItems.setOnClickListener(new ViewItemsButtonListener());
        }

        private class ViewItemsButtonListener implements View.OnClickListener {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(v.getContext(), ViewItemsActivity.class);
                intent.putExtra("listKey", listKey.getText().toString().substring(10));
                v.getContext().startActivity(intent);

            }
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
        holder.listName.setText("List Name: " + shoppingList.getName());
        holder.listKey.setText("List Key: " + shoppingList.getKey());
    }



    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

}
