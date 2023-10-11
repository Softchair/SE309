package com.infoFootball.SpringBackend.Concessions.FoodItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class FoodItem {

    @Id
    private int foodId;

    //@JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    private FoodMenu menu;

    private String name;
    private long price;
    private int cal;
    private int stock;

    public FoodItem() {
    }

    public FoodItem(FoodMenu menuId, String name, long price, int cal, int stock) {
        this.menu = menuId;
        this.name = name;
        this.price = price;
        this.cal = cal;
        this.stock = stock;
    }

    //Getters and setters

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public FoodMenu getMenu() {
        return menu;
    }

    public void setMenu(FoodMenu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
