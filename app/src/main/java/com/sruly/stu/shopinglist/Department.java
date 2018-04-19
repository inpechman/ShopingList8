package com.sruly.stu.shopinglist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by stu on 4/17/2018.
 *
 */

public class Department extends Product {
    ArrayList<Product> products = new ArrayList<>();

    public Department(int barcode, String name) {
        super(barcode, name);
        isDepartment = true;
    }

    public boolean addProduct(Product product) {
        product.setDepartment(this);
        return products.add(product);
    }

    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public JSONObject toJson(){
        try {
            JSONObject departmentJson = super.toJson();
            JSONArray productsJson = new JSONArray();
            for (Product product : products) {
                JSONObject productJson = product.toJson();
                productsJson.put(productJson);
            }
            departmentJson.put("products", productsJson);
            return departmentJson;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Department fromJson(JSONObject jsonObject){
        try {
            String name = jsonObject.getString("name");
            int barcode = jsonObject.getInt("barcode");
            String imagePath = jsonObject.getString("image");
            Department department = new Department(barcode, name);
            if (imagePath != null){
                department.setImage(new File(imagePath));
            }
            department.setIsDepartment(true);

            JSONArray productsJson = jsonObject.getJSONArray("products");
            for (int i = 0; i < productsJson.length(); i++) {
                JSONObject productJson = productsJson.getJSONObject(i);
                if (productJson.getBoolean("isDepartment")){
                    Department innerDepartment = Department.fromJson(productJson);
                    department.addProduct(innerDepartment);
                } else {
                    Product innerProduct = Product.fromJson(productJson);
                    department.addProduct(innerProduct);
                }
            }
            return department;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
