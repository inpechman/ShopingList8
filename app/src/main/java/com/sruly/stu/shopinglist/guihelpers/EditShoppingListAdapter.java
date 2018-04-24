package com.sruly.stu.shopinglist.guihelpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sruly.stu.shopinglist.EditShoppingList;
import com.sruly.stu.shopinglist.R;
import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.Product;
import com.sruly.stu.shopinglist.logic.ShoppingList;

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
    ShoppingList shoppingList;

    public EditShoppingListAdapter(Context context, ArrayList<Product> products, int shoppingListIndex) {
        this.context = context;
        this.products = products;
        this.shoppingListIndex = shoppingListIndex;
        this.shoppingList = AppShared.shoppingLists.get(shoppingListIndex);
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
        ((Updatable) holder).update(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class DepartmentHolder extends RecyclerView.ViewHolder implements Updatable {
        ImageView imageView;
        TextView name, barcode;
        int barcodeInt;

        public DepartmentHolder(LinearLayout itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.department_image_view);
            name = itemView.findViewById(R.id.department_name);
            barcode = itemView.findViewById(R.id.department_barcode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditShoppingList.class);
                    intent.putExtra("position", shoppingListIndex);
                    intent.putExtra("department", barcodeInt);
                    context.startActivity(intent);
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
            barcodeInt = department.getBarcode();
        }
    }

    class ProductHolder extends RecyclerView.ViewHolder implements Updatable {
        ImageView imageView;
        TextView name, barcode, quantity;
        Button add, remove;
        int barcodeInt;

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
                    shoppingList.addProductToListByBarcode(barcodeInt);
//                    quantity.invalidate();
                    setQuantityTextAndColor();
                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shoppingList.removeProductFromListByBarcode(barcodeInt);
                    setQuantityTextAndColor();
                    //                    quantity.invalidate();
                }
            });
        }

        @Override
        public void update(int position) {
            Product product = products.get(position);
            //TODO set image
            imageView.setBackgroundColor(product.getBarcode());
            name.setText(product.getName());
            barcode.setText(String.format(Locale.US, "%d", product.getBarcode()));
            barcodeInt = product.getBarcode();
//            quantity.setText(String.format(Locale.US, "%d",
//                    AppShared.shoppingLists.get(
//                            shoppingListIndex).getProductQuantity(
//                                    barcodeInt)));
            setQuantityTextAndColor();

        }

        public void setQuantityTextAndColor() {
            int productQuantity = shoppingList.getProductQuantity(barcodeInt);
            quantity.setText(String.format(Locale.US, "%d",
                    productQuantity));
            quantity.setBackgroundColor(productQuantity > 0 ? Color.GREEN : 0);


        }
    }
}
