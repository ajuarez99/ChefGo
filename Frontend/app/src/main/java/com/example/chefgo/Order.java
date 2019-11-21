package com.example.chefgo;

import org.json.JSONException;
import org.json.JSONObject;

public class Order {

    private int oid, isActive;
    private double price;
    private String dish, date;
    private JSONObject review, customer, chef;

    public Order(JSONObject order){
        try{
            oid = order.getInt("oid");
            isActive = order.getInt("isActive");
            price = order.getDouble("price");
            dish = order.getString("dish");
            date = order.getString("date");
            if (order.isNull("review")) {
                review = null;
            }
            else {
                review = order.getJSONObject("review");
            }
            if (order.isNull("customer")) {
                customer = null;
            }
            else {
                customer = order.getJSONObject("customer");
            }
            if (order.isNull("chef")) {
                chef = null;
            }
            else {
                chef = order.getJSONObject("chef");
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public int getOID() {
        return oid;
    }

    public int isActive() {
        return isActive;
    }

    public void setActive(int isActive) {
        this.isActive = isActive;
    }

    public double getPrice() {
        return price;
    }

    public String getDish() {
        return dish;
    }

    public String getDate() {
        return date;
    }

    public JSONObject getReview() {
        return review;
    }

    public void setReview(JSONObject review) {
        this.review = review;
    }

    public JSONObject getCustomer() {
        return customer;
    }

    public JSONObject getChef() {
        return chef;
    }

    public void setChef(JSONObject chef) {
        this.chef = chef;
    }

}
