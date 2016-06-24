package com.dojoandroid.dojoapp.entity;

/**
 * Created by vgonsalez on 6/13/16.
 */
public class Product {

    public float price;
    public String name;
    public int status;

    public Product(float price, String name, int status) {
        this.price = price;
        this.name = name;
        this.status = status;
    }
}
