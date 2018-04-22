package com.sruly.stu.shopinglist.guihelpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sruly.stu.shopinglist.EditShoppingList;
import com.sruly.stu.shopinglist.OpenShoppingList;
import com.sruly.stu.shopinglist.R;
import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.ShoppingList;

/**
 * Created by stu on 4/22/2018.
 *
 */

public class ShoppingListsAdapter extends RecyclerView.Adapter {
    Context context;

    public ShoppingListsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingListsViewHolder(View.inflate(context, R.layout.show_shopping_lists_row, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ShoppingListsViewHolder) holder).update(AppShared.shoppingLists.get(position), position);
    }

    @Override
    public int getItemCount() {
        return AppShared.shoppingLists.size();
    }

    class ShoppingListsViewHolder extends RecyclerView.ViewHolder {
        TextView listName;
        int position;
        Button openList, editList;
        public ShoppingListsViewHolder(View itemView) {
            super(itemView);
            openList = itemView.findViewById(R.id.open_shopping_list_btn);
            editList = itemView.findViewById(R.id.edit_shopping_list_btn);
            listName = itemView.findViewById(R.id.shopping_list_name);

            openList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OpenShoppingList.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
            editList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EditShoppingList.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });

//            itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        void update(ShoppingList shoppingList, int position){
            listName.setText(shoppingList.getName());
            this.position = position;
        }
    }
}
