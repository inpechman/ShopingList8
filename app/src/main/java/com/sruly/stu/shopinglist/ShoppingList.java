package com.sruly.stu.shopinglist;

import android.util.SparseIntArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stu on 4/17/2018.
 *
 */

public class ShoppingList {
    SparseIntArray productsQuantity = new SparseIntArray();
    String name;
    int id;

    static int idBase = 1000;

    public ShoppingList(String name) {
        this.name = name;
        this.id = ++idBase;
    }

    public SparseIntArray getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(SparseIntArray productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdBase() {
        return idBase;
    }

    public static void setIdBase(int idBase) {
        ShoppingList.idBase = idBase;
    }

    public void addProductToList(Product product){
        int barcode = product.getBarcode();
        productsQuantity.append(barcode, productsQuantity.get(barcode) + 1);
    }

    public void removeProductFromList(Product product){
        int barcode = product.getBarcode();
        int oldValue = productsQuantity.get(barcode);
        if (oldValue <= 1) {
            productsQuantity.delete(barcode);
        } else {
            productsQuantity.put(barcode, oldValue - 1);
        }
    }

    public JSONObject toJson(){
        try {
            JSONObject shoppingListJson = new JSONObject();
            shoppingListJson.put("name", this.name);
            shoppingListJson.put("id", this.id);

            JSONArray allProductsQuantityJson = new JSONArray();

            for (int i = 0; i < productsQuantity.size(); i++) {
                int key = productsQuantity.keyAt(i);
                int value = productsQuantity.get(key);
                JSONObject singleProductsQuantityJson = new JSONObject();
                singleProductsQuantityJson.put("key", key);
                singleProductsQuantityJson.put("value", value);

                allProductsQuantityJson.put(singleProductsQuantityJson);
            }

            shoppingListJson.put("productsQuantity", allProductsQuantityJson);
            return shoppingListJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ShoppingList fromJson(JSONObject jsonObject){
        try {
            String name = jsonObject.getString("name");
            int id = jsonObject.getInt("id");
            JSONArray allProductsQuantityJson = jsonObject.getJSONArray("productsQuantity");

            ShoppingList shoppingList = new ShoppingList(name);
            shoppingList.setId(id);
            for (int i = 0; i < allProductsQuantityJson.length(); i++) {
                JSONObject singleProductJson = allProductsQuantityJson.getJSONObject(i);
                int key = singleProductJson.getInt("key");
                int value = singleProductJson.getInt("value");
                shoppingList.productsQuantity.append(key, value);
            }
            return shoppingList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
