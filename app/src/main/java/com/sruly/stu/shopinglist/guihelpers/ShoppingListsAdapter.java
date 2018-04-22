package com.sruly.stu.shopinglist.guihelpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by stu on 4/22/2018.
 *
 */

public class ShoppingListsAdapter extends RecyclerView.Adapter {
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ShoppingListsViewHolder extends RecyclerView.ViewHolder {
        public ShoppingListsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
