package com.infoFootball.SpringBackend.Pins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PinController {

    @Autowired
    PinRepository pinRepository;

    @GetMapping(path= "/pins")
    List<Pin> getAllPins(){
        return pinRepository.findAll();
    }

    @PostMapping(path="/pins/new")
    public String newPin(@RequestBody Pin newPin){
        pinRepository.save(newPin);
        return "Success";
    }

    @GetMapping(path= "/pins/{username}")
    List<Pin> getPinsByUsername(@PathVariable String username){
        return pinRepository.findByUsername(username);
    }

    @PutMapping("/pins/{IDNum}")
    Pin updatePin(@PathVariable Integer IDNum, @RequestBody Pin newPin){
        Pin oldPin = pinRepository.findByID(IDNum);
        if(oldPin == null){
            return null;
        }
        newPin.setIDNum(oldPin.getIDNum());
        pinRepository.save(newPin);
        return pinRepository.findByID(newPin.getIDNum());
    }

    @DeleteMapping(path="/pins/delete/{ID}")
    String deletePin(@PathVariable Integer ID){
        pinRepository.deleteByID(ID);
        return "success";
    }
}