package com.infoFootball.SpringBackend.Player;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author bdpope
 * This class is to create a player object. This contains a unique player IDnum, their first and last name,
 * the player's number. We use this as the ID to the stats table
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private String firstName;
    private String lastName;
    private String playerNum;

    public Player(){
        this.firstName = null;
        this.lastName = null;
        this.playerNum = null;
    }
    public Player(String firstName, String lastName, String playerNum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerNum = playerNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPlayerNum(){
        return playerNum;
    }
    public void setPlayerNum(String playerNum){
        this.playerNum = playerNum;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public Integer getID(){
        return ID;
    }

}
