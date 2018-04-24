package com.sruly.stu.shopinglist.guihelpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sruly.stu.shopinglist.OpenShoppingList;
import com.sruly.stu.shopinglist.R;
import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.Department;
import com.sruly.stu.shopinglist.logic.Product;
import com.sruly.stu.shopinglist.logic.ShoppingList;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by stu on 4/24/2018.
 *
 */

public class OpenShoppingListAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Product> products;
    int shoppingListIndex;
    ShoppingList shoppingList;
    public OpenShoppingListAdapter(Context context, Department department, int shoppingListIndex) {
        this.context = context;
        this.shoppingListIndex = shoppingListIndex;
        this.shoppingList = AppShared.shoppingLists.get(shoppingListIndex);
        this.products = department.filteredWithShoppingList(shoppingList);
    }

    @Override
    public int getItemViewType(int position) {
        Product product = products.get(position);
        if (product.isDepartment()){
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new OpenDepartmentHolder((LinearLayout) View.inflate(context, R.layout.open_shopping_list_department_row, null));
        }
        if (viewType == 2) {
            return new OpenProductHolder((LinearLayout) View.inflate(context, R.layout.open_shopping_list_product_row, null));
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

    private class OpenDepartmentHolder extends RecyclerView.ViewHolder implements Updatable{
        ImageView imageView;
        TextView name, barcode, indicator;
        int barcodeInt;
        public OpenDepartmentHolder(LinearLayout itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.open_department_image_view);
            name = itemView.findViewById(R.id.open_department_name_view);
            barcode = itemView.findViewById(R.id.open_department_barcode_view);
            indicator = itemView.findViewById(R.id.open_department_indicator_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OpenShoppingList.class);
                    intent.putExtra("position", shoppingListIndex);
                    intent.putExtra("department", 123456);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void update(int position) {
            Department department = (Department) products.get(position);
            //TODO set image
            imageView.setBackgroundColor(department.getBarcode());
            name.setText(department.getName());
            barcode.setText(String.format(context.getResources().getConfiguration().getLocales().get(0), "%d", department.getBarcode()));
            barcodeInt = department.getBarcode();
            ArrayList<Product> shrinked = department.filteredWithShoppingList(shoppingList);
            int departmentSize = shrinked.size();
            int BoughtYet = shoppingList.productsBoughtFromDepartment(shrinked);
            indicator.setText(String.format(Locale.US,"%d / %d", departmentSize, BoughtYet));
        }
    }

    private class OpenProductHolder extends RecyclerView.ViewHolder implements Updatable{
        ImageView imageView;
        TextView name, barcode, quantity;
        CheckBox isBought;
        int barcodeInt;
        public OpenProductHolder(LinearLayout itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.open_product_image_view);
            name = itemView.findViewById(R.id.open_product_name_view);
            barcode = itemView.findViewById(R.id.open_product_barcode_view);
            quantity = itemView.findViewById(R.id.open_product_quantity_view);
            isBought = itemView.findViewById(R.id.open_product_indicator_view);
            isBought.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        shoppingList.buyProduct(barcodeInt);
                    } else {
                        shoppingList.returnProduct(barcodeInt);
                    }
                }
            });
        }

        @Override
        public void update(int position) {
            Product product = products.get(position);
            //TODO ser image
            name.setText(product.getName());
            barcodeInt = product.getBarcode();
            barcode.setText(String.format(Locale.US, "%d", barcodeInt));
            quantity.setText(String.format(Locale.US, "%d", shoppingList.getProductQuantity(barcodeInt)));
            imageView.setBackgroundColor(barcodeInt);
            isBought.setChecked(shoppingList.isProductBought(barcodeInt));
        }
    }
}
