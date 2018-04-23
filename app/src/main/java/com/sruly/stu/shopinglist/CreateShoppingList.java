package com.sruly.stu.shopinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sruly.stu.shopinglist.logic.AppShared;
import com.sruly.stu.shopinglist.logic.ShoppingList;

public class CreateShoppingList extends AppCompatActivity {
    EditText listName;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);

        listName = findViewById(R.id.enter_list_name_field);
        start = findViewById(R.id.enter_list_name_btn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingList shoppingList = new ShoppingList(listName.getText().toString());
                AppShared.shoppingLists.add(shoppingList);
                int position = AppShared.shoppingLists.indexOf(shoppingList);

                Intent intent = new Intent(getApplicationContext(), EditShoppingList.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}
