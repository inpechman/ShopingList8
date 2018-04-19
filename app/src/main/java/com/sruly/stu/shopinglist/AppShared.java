package com.sruly.stu.shopinglist;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by stu on 4/18/2018.
 *
 */

public class AppShared {
    public static Department rootDepartment;
    public static ArrayList<ShoppingList> shoppingLists = new ArrayList<>();
    public static File defaultSettings, savedSettings;


    public static void initializeProgram(Context context){
        File rootAppFolder = context.getFilesDir();
        File settingsFolder = new File(rootAppFolder, "settings");
        settingsFolder.mkdir();
        savedSettings = new File(settingsFolder, "saved_settings.json");
        if (savedSettings.isFile()){
            loadFromFile(context);
        } else {
            loadFromAsset(context);
        }
    }

    private static void loadFromAsset(Context context) {
        try {
            AssetManager mgr = context.getAssets();
            InputStream inputStream = mgr.open("default_settings.json");
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int counter = 0;
            while ((counter = inputStream.read(buffer)) != -1){
                stringBuilder.append(new String(buffer, 0, counter));
            }
            loadFromJsonObject(new JSONObject(stringBuilder.toString()));
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("general IO Exception");
        }
    }

    private static void loadFromFile(Context context) {
        try {
            FileInputStream inputStream = new FileInputStream(savedSettings);
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int counter = 0;
            while ((counter = inputStream.read(buffer)) != -1){
                stringBuilder.append(new String(buffer, 0, counter));
            }
            loadFromJsonObject(new JSONObject(stringBuilder.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("general IO Exception");
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("json exception");
        }
    }

    private static void loadFromJsonObject(JSONObject jsonObject) {
        try {
            rootDepartment = Department.fromJson(jsonObject.getJSONObject("rootDepartment"));
            JSONArray listsJson = jsonObject.getJSONArray("shoppingLists");
            for (int i = 0; i < listsJson.length(); i++) {
                JSONObject listJson = listsJson.getJSONObject(i);
                shoppingLists.add(ShoppingList.fromJson(listJson));
            }
            ShoppingList.setIdBase(jsonObject.getInt("idBase"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveProgramState(Context context){
        saveToFile();
    }

    private static void saveToFile() {
        FileOutputStream fileOutputStream = null;
        try {
            JSONObject forFile = new JSONObject();
            JSONArray shoppinglistsJson = new JSONArray();
            for (ShoppingList shoppingList : shoppingLists) {
                JSONObject listJson = shoppingList.toJson();
                shoppinglistsJson.put(listJson);
            }
            forFile.put("rooDepartment", rootDepartment.toJson());
            forFile.put("shoppingLists", shoppinglistsJson);
            forFile.put("idBase", ShoppingList.getIdBase());

            fileOutputStream = new FileOutputStream(savedSettings);
            byte[] jsonAsBytes = forFile.toString().getBytes();
            fileOutputStream.write(jsonAsBytes);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
