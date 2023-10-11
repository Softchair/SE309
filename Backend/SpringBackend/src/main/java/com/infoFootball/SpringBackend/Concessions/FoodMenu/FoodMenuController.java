package com.infoFootball.SpringBackend.Concessions.FoodMenu;

import com.infoFootball.SpringBackend.Concessions.FoodItem.FoodItem;
import com.infoFootball.SpringBackend.Concessions.Vendor.Vendor;
import com.infoFootball.SpringBackend.Concessions.Vendor.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodMenuController {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    FoodMenuService foodMenuService;

    @GetMapping(path = "/menu/{id}")
    FoodMenu getOneMenu(@PathVariable int id) {
        return menuRepository.findByMenuId(id);
    }

    @GetMapping(path = "/menu/all")
    List<FoodMenu> getAllMenus() {
        return menuRepository.findAll();
    }

    @GetMapping(path = "/menu/foodItems/{id}")
    List<FoodItem> getAllItemsOfMenu(@PathVariable int id) {
        FoodMenu menu = menuRepository.findByMenuId(id);
        List<FoodItem> items = menu.getItems();

        for(int i =0; i< menu.getItems().size(); i++) {
            items.get(i).setMenu(null);
        }

        return items;
    }

    @PostMapping(path = "/menu/create")
    String createMenu(@RequestParam String menuName, @RequestParam String menuDesc, @RequestParam int menuId, @RequestParam int vendorId) {
        Vendor vendor = vendorRepository.findByVendorId(vendorId);
        return foodMenuService.createNewMenu(menuName, menuDesc, menuId, vendor);
    }

    @PutMapping(path = "/menu/update/{id}")
    FoodMenu updateMenu(@RequestParam String menuName, @RequestParam String menuDesc, @RequestParam int menuId, @RequestParam int vendorId) {
        FoodMenu oldMenu = menuRepository.findByMenuId(menuId);
        if (oldMenu == null) {
            return null;
        } else {
            oldMenu.setName(menuName);
            oldMenu.setDesc(menuDesc);
            oldMenu.setMenuId(menuId);
            oldMenu.setVendor(vendorRepository.findByVendorId(vendorId));
            menuRepository.save(oldMenu);
        }
        return menuRepository.findByMenuId(menuId);
    }

    //@Transactional
    @DeleteMapping(path = "/menu/delete/{id}")
    String deleteMenu(@PathVariable int id) {
        menuRepository.deleteByMenuId(id);
        return "Success";
    }
}
