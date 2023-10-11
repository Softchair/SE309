package com.infoFootball.SpringBackend.Chat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FriendRelation {

    @Id
    private String friendShip;
    private String friend1;
    private String friend2;

    public FriendRelation() {

    }

    public String getFriendShip() {
        return friendShip;
    }

    public void setFriendShip(String friendShip) {
        this.friendShip = friendShip;
    }

    public String getFriend1() {
        return friend1;
    }

    public void setFriend1(String friend1) {
        this.friend1 = friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public void setFriend2(String friend2) {
        this.friend2 = friend2;
    }
}
