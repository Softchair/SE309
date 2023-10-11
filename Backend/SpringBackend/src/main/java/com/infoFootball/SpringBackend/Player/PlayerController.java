package com.infoFootball.SpringBackend.Player;
import java.util.List;

import com.infoFootball.SpringBackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * @todo update all of this to player junk
 * This class is for controlling player entry and all the critical
 * bits for adding, updating, getting, and deleting players
 */
@RestController
public class PlayerController {
    @Autowired
    PlayerRespository playerRespository;

    final private String success = "{\"message\":\"success\"}";
    final private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/players")
    List<Player> getAllPlayers(){
        return playerRespository.findAll();
    }

    /**
     * Returns
     * @param num number of player
     * @return player related to number
     */
    @GetMapping(path = "/players/num/{num}")
    List<Player> getplayersByNumber(@PathVariable String num){
        return playerRespository.findByPlayerNum(num);
    }

    //@todo add getter methods for first and last name

    /**
     * Returns the player with the given ID, returns NULL if there is no player with that ID
     *
     * @param ID - The unique ID number of that player
     * @return The player corresponding to the ID
     */
    @GetMapping(path = "/players/ID/{ID}")
    Player getPlayerByID(@PathVariable Integer ID){
        return playerRespository.findByID(ID);
    }

    /**
     * The mapping to create a new player object
     * @param newPlayer
     * @return
     */
    @PostMapping(path="/players/new")
    public String newPlayer(@RequestBody Player newPlayer) {
        playerRespository.save(newPlayer);
        return "Success";
    }

    /**
     * Updates player using the unique ID and creates a new
     * @param ID unique ID of player
     * @param request the updated player info
     * @return updated player, null if ID doesn't have a player with it
     */
    @PutMapping("/players/update/{ID}")
    Player updatePlayer(@PathVariable int ID, @RequestBody Player request){
        Player player = playerRespository.findByID(ID);
        if(player == null)
            return null;
        request.setID(player.getID());
        playerRespository.save(request);
        return playerRespository.findByID(ID);
    }

    /**
     * Deletes a player by ID
     * @param ID ID of player to be deleted
     * @return if player was successfully deleted
     */
    @DeleteMapping("/players/delete/{ID}")
    String deletePlayer(@PathVariable Integer ID){
        playerRespository.deleteByID(ID);
        return success;
    }
}
