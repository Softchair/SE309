package com.infoFootball.SpringBackend.Concessions.FoodMenu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infoFootball.SpringBackend.Concessions.FoodItem.FoodItem;
import com.infoFootball.SpringBackend.Concessions.Vendor.Vendor;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class FoodMenu {

    @Id
    private int menuId; //Id for menu
    private String menuName; //Name of menu
    private String menuDesc; //Description of menu/season

    //@JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE)
    private Vendor vendor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<FoodItem> items; //List of items in menu

    public FoodMenu() {
        this.menuId = -1;
        this.menuName = null;
        this.menuDesc = null;
        this.vendor = null;
        this.items = null;
    }

    public FoodMenu(String name, String desc, int menuId) {
        this.menuName = name;
        this.menuDesc = desc;
        this.menuId = menuId;
    }

    public void removeItems(FoodItem foodItem) {
        items.remove(foodItem);
    }

    //Getters and setters

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return menuName;
    }

    public void setName(String name) {
        this.menuName = name;
    }

    public String getDesc() {
        return menuDesc;
    }

    public void setDesc(String desc) {
        this.menuDesc = desc;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setItems(List<FoodItem> items) {
        this.items = items;
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }
}
