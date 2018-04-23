package com.sruly.stu.shopinglist.guihelpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sruly.stu.shopinglist.R;
import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.Department;
import com.sruly.stu.shopinglist.logic.Product;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by stu on 4/23/2018.
 *
 */

public class EditShoppingListAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Product> products;
    int shoppingListIndex;
    public EditShoppingListAdapter(Context context, ArrayList<Product> products, int shoppingListIndex) {
        this.context = context;
        this.products = products;
        this.shoppingListIndex = shoppingListIndex;
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position).isDepartment() ? 1 : 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new DepartmentHolder((LinearLayout) View.inflate(context, R.layout.edit_shopping_list_department_row, null));
        }
        if (viewType == 2) {
            return new ProductHolder((ConstraintLayout) View.inflate(context, R.layout.edit_shopping_list_product_row, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Updatable)holder).update(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class DepartmentHolder extends RecyclerView.ViewHolder implements Updatable {
        ImageView imageView;
        TextView name, barcode;
        public DepartmentHolder(LinearLayout itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.department_image_view);
            name = itemView.findViewById(R.id.department_name);
            barcode = itemView.findViewById(R.id.department_barcode);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void update(int position) {
            Product department = products.get(position);
            //TODO set image
            imageView.setBackgroundColor(department.getBarcode());
            name.setText(department.getName());
            barcode.setText(String.format(context.getResources().getConfiguration().getLocales().get(0), "%d", department.getBarcode()));
        }
    }

    class ProductHolder extends RecyclerView.ViewHolder implements Updatable {
        ImageView imageView;
        TextView name, barcode, quantity;
        Button add, remove;

        public ProductHolder(ConstraintLayout itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image_view);
            name = itemView.findViewById(R.id.product_name_text);
            barcode = itemView.findViewById(R.id.product_barcode_text);
            quantity = itemView.findViewById(R.id.product_quantity_input_field);
            add = itemView.findViewById(R.id.add_product_to_shopping_list_btn);
            remove = itemView.findViewById(R.id.remove_product_from_shopping_list_btn);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void update(int position) {
            Product product = products.get(position);
            //TODO set image
            imageView.setBackgroundColor(product.getBarcode());
            name.setText(product.getName());
            barcode.setText(String.format(Locale.US,"%d", product.getBarcode()));
            quantity.setText(String.format(Locale.US, "%d",
                    AppShared.shoppingLists.get(
                            shoppingListIndex).getProductQuantity(
                                    product.getBarcode())));

        }
    }
}
