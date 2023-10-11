package com.infoFootball.SpringBackend.Concessions.FoodItem;

import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenuController;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.MenuRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodItemController {

    @Autowired
    FoodItemRepository foodItemRepository;
    @Autowired
    MenuRepository menuRepository;

    @GetMapping(path = "/item/{id}")
    FoodItem getItem(@PathVariable int id) {
        FoodItem cur = foodItemRepository.findByFoodId(id);
        cur.setMenu(null);
        return cur;
    }

    @PostMapping(path = "/item/create/{menuId}")
    String createItem(@RequestBody FoodItem newItem, @PathVariable int menuId) {
        newItem.setMenu(menuRepository.findByMenuId(menuId));
        menuRepository.findByMenuId(menuId).addItem(newItem);
        foodItemRepository.save(newItem);
        return "Success";
    }

    @PutMapping(path = "/item/update/{id}")
    FoodItem updateItem(@RequestBody FoodItem newItem, @PathVariable int id, @RequestParam int menuId) {
        FoodItem oldItem = foodItemRepository.findByFoodId(id);
        if (oldItem == null) {
            return null;
        }
        newItem.setMenu(menuRepository.findByMenuId(menuId));
        foodItemRepository.save(newItem);
        return foodItemRepository.findByFoodId(id);
    }

    //@Transactional
    @DeleteMapping(path = "/item/delete/{id}")
    String delete(@PathVariable int id) {
        FoodItem cur = foodItemRepository.findByFoodId(id);
        //cur.setFoodId(-1);
        cur.setMenu(null);
        cur.setName("null");
        cur.setPrice(-1);
        cur.setCal(-1);
        cur.setStock(-1);
        foodItemRepository.save(cur); //test
        return "Success";
    }
//    String delete(@PathVariable int id) {
//        foodItemRepository.deleteByFoodId(id);
//        return "Success";
//    }

}
