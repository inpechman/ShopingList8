package com.sruly.stu.shopinglist;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ShowShoppingLists extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shopping_lists);

        fab = findViewById(R.id.new_shopping_list_btn);
        recyclerView = findViewById(R.id.show_shopping_lists_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "bla bla", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}
