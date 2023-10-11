package com.infoFootball.SpringBackend.User;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class is the base for creating a user.
 * Through it, you can set name email, phone number, and password, which then can be saved into the database
 */
@Entity
public class User {

    //@ID marks the below field as the primary key for the table
    @Id
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private String permLv;

    /**
     * Creates a null user object
     */
    public User() {
        this.username = null;
        this.email = null;
        this.password = null;
        this.phoneNum = null;
    }

    /**
     * Creates a new userID object
     * Does not set email of user
     * @param username User's name
     * @param email User's email
     * @param password User's password
     * @param phoneNum User's phone number
     */
    public User(String username, String password, String email, String phoneNum, String permLv ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.permLv = permLv;
    }

    // SET FUNCTIONS //

    /**
     * Sets the first name of a user
     * @param username new first name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the email of a user
     * @param email email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password of a user
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the phone number of a user
     * @param phoneNum new phone number
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Sets the user perm level
     * @param permLv user perm level
     */
    public void setPermLv(String permLv) { this.permLv = permLv;}

    //GET FUNCTIONS //

    /**
     * Returns the value of the private name variable
     * @return string name
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the value of the private email variable
     * @return string email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns a string containing the password
     * @return string password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the string of phone number
     * @return string phone numbers
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * Returns the perm level
     * @return string perm level
     */
    public String getPermLv() {
        return this.permLv;
    }

}