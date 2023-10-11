package com.infoFootball.SpringBackend.Concessions.Vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenu;
import com.infoFootball.SpringBackend.User.User;

import javax.persistence.*;

/**
 * This class is the base for creating a Vendor.
 * Lets you set menu, and what level of maintainer you need to edit
 */
@Entity
public class Vendor {

    //@ID marks the below field as the primary key for the table
    @Id
    private int vendorId;
    private String name;
    private boolean OC; //True for open - false for closed
    private String location;
    private String maintainerUsername;

    @OneToOne(cascade = CascadeType.REMOVE)
    private FoodMenu menu;

    /**
     * Creates a null Vendor object
     */
    public Vendor() {
        this.vendorId = -1;
        this.name = null;
        this.OC = false;
        this.location = null;
        this.menu = null;
    }

    /**
     * Creates a new vendor object
     * @param Id vendor id num (int)
     * @param name vendor name (string)
     * @param status vendor status (open closed, true false) (bool)
     * @param location vendor location (string)
     * @param menuId vendor menu (table)
     */
    public Vendor(int Id, String name, boolean status, String location, FoodMenu menuId) {
        this.vendorId = Id;
        this.name = name;
        this.OC = status;
        this.location = location;
        this.menu = menuId;
    }


    // SET FUNCTIONS //

    public void setMaintainerUsername(String maintainerUsername) {
        this.maintainerUsername = maintainerUsername;
    }

    /**
     * Sets ID of vendor
     * @param VendorId vendor ID (int)
     */
    public void setId(int VendorId) {
        this.vendorId = VendorId;
    }

    /**
     * Sets name of vendor
     * @param name vendor name (string)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets open/closed status of vendor (true open, false closed)
     * @param status vendor status (bool)
     */
    public void setStatus(boolean status) {
        this.OC = status;
    }

    /**
     * Sets location of vendor
     * @param location vendor location (string)
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the menuDB key of the vendor
     * @param menuId vendor menu (table ID)
     */
    public void setMenu(FoodMenu menuId) {
        this.menu = menuId;
    }

    // GET FUNCTIONS //

    /**
     * Gets vendor ID
     * @return vendor ID (int)
     */
    public int getVendorId() {
        return this.vendorId;
    }

    /**
     * Gets vendor name
     * @return vendor name (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets vendor status (true open, false closed)
     * @return vendor status (bool)
     */
    public boolean getStatus() {
        return this.OC;
    }

    /**
     * Gets vendor location
     * @return vendor location (string)
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Gets vendor menuDB key
     * @return vendor menuDB key
     */
    public FoodMenu getMenu() {
        return this.menu;
    }

    public String getMaintainerUsername() {
        return this.maintainerUsername;
    }

}
