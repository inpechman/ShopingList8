package com.sruly.stu.shopinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sruly.stu.shopinglist.guihelpers.EditShoppingListAdapter;
import com.sruly.stu.shopinglist.logic.AppShared;

public class EditShoppingList extends AppCompatActivity {
    RecyclerView recyclerView;
    int shoppingListIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shopping_list);

        recyclerView = findViewById(R.id.edit_shopping_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        shoppingListIndex = getIntent().getIntExtra("position", 0);
        recyclerView.setAdapter(new EditShoppingListAdapter(getApplicationContext(), AppShared.rootDepartment.products, shoppingListIndex));
    }
}
