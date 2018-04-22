package com.sruly.stu.shopinglist;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;

import com.sruly.stu.shopinglist.logic.AppShared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button createSoppingListBtn, showShoppingListsBtn, manageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppShared.initializeProgram(getApplicationContext());

        createSoppingListBtn = findViewById(R.id.create_shopping_list_btn);
        showShoppingListsBtn = findViewById(R.id.show_shopping_lists_btn);
        manageBtn = findViewById(R.id.manage_btn);

        createSoppingListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateShoppingList.class);
                startActivity(intent);
            }
        });

        showShoppingListsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowShoppingLists.class);
                startActivity(intent);
            }
        });

/*
        System.out.println("AAA " + AppShared.rootDepartment.toJson().toString());

        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(123,352345);
        sparseIntArray.append(234,667);
        System.out.println("AAA " + sparseIntArray);
        Locale locale = getResources().getConfiguration().getLocales().get(0);
        String lang = locale.getLanguage();
        try {
            AssetManager mgr = getAssets();
            InputStream is = mgr.open("default_settings_" + lang + ".json");
            byte[] buffer = new byte[1024];
            int counter = 0;
            String str = "";
            while ((counter = is.read(buffer)) != -1){
                str += new String(buffer,0, counter);
            }
            System.out.println("AAA " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}
