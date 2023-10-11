package com.infoFootball.SpringBackend.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRelationRepository extends JpaRepository<FriendRelation, String> {
    //FriendRelation findByUsername(String username);
    //void deleteByUsername(String username);
}
