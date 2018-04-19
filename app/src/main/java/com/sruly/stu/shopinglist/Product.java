package com.sruly.stu.shopinglist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by stu on 4/17/2018.
 *
 */

public class Product {
    int barcode;
    String name;
    File image;
    Department department;
    boolean isDepartment;

    public Product(int barcode, String name) {
        this.barcode = barcode;
        this.name = name;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(boolean isDepartment) {
        this.isDepartment = isDepartment;
    }

    public JSONObject toJson(){
        try {
            JSONObject productJson = new JSONObject();
            productJson.put("barcode", barcode);
            productJson.put("name", name);
            if (image != null){
                productJson.put("image", image.getAbsolutePath());
            }
            productJson.put("isDepartment", isDepartment);

            return productJson;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Product fromJson(JSONObject jsonObject){
        try {
            String name = jsonObject.getString("name");
            int barcode = jsonObject.getInt("barcode");
            String imagePath = jsonObject.getString("image");
            Product product = new Product(barcode, name);
            if (imagePath != null){
                product.setImage(new File(imagePath));
            }
            return product;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
