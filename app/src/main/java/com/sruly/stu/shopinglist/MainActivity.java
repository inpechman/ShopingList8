package com.sruly.stu.shopinglist;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(123,352345);
        sparseIntArray.append(234,667);
        System.out.println("AAA " + sparseIntArray);
        try {
            AssetManager mgr = getAssets();
            InputStream is = mgr.open("default_settings.json");
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
    }
}
