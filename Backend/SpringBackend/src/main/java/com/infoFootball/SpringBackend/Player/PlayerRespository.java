package com.infoFootball.SpringBackend.Player;

import com.infoFootball.SpringBackend.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlayerRespository extends JpaRepository<Player, String> {

    Player findByID(Integer ID);

    @Transactional
    void deleteByID(Integer ID );

    List<Player> findByPlayerNum(String number);
}

