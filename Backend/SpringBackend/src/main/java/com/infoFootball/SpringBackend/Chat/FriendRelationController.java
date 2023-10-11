package com.infoFootball.SpringBackend.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class FriendRelationController {
    @Autowired
    FriendRelationRepository relationRepository;

    @PutMapping(path = "/friend/{friend1}/{friend2}")
    String setFriend(@PathVariable String friend1, @PathVariable String friend2) {
        FriendRelation cur = new FriendRelation();
        cur.setFriend1(friend1);
        cur.setFriend2(friend2);
        cur.setFriendShip(""+friend1+"+"+friend2);
        relationRepository.save(cur);
        return "Success";
    }

    @GetMapping(path = "/get/friend/{friend}")
    List<FriendRelation> getFriends(@PathVariable String friend) {
        List<FriendRelation> friendList = new ArrayList<>();
        List<FriendRelation> allRelations = relationRepository.findAll();

        for (FriendRelation cur : allRelations) {
            if (Objects.equals(cur.getFriend1(), friend)) {
                friendList.add(cur);
            } else if (Objects.equals(cur.getFriend2(), friend)) {
                friendList.add(cur);
            }
        }

        return friendList;
    }
}
