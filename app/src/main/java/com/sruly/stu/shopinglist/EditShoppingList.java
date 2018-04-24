package com.sruly.stu.shopinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sruly.stu.shopinglist.guihelpers.EditShoppingListAdapter;
import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.Department;

public class EditShoppingList extends AppCompatActivity {
    RecyclerView recyclerView;
    int shoppingListIndex;
    Department departmentToView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        recyclerView = findViewById(R.id.edit_shopping_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        shoppingListIndex = getIntent().getIntExtra("position", 0);
        int department = getIntent().getIntExtra("department", 0);
        if (department != 0){
            departmentToView = (Department) AppShared.rootDepartment.findProductByBarcode(department);
        } else {
            departmentToView = AppShared.rootDepartment;
        }
        recyclerView.setAdapter(new EditShoppingListAdapter(getApplicationContext(), departmentToView.products, shoppingListIndex));
    }
}
