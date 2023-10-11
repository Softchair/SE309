package com.infoFootball.SpringBackend.Concessions.FoodMenu;

import com.infoFootball.SpringBackend.Concessions.Vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FoodMenuService {

    @Autowired
    MenuRepository menuRepository;


    public String createNewMenu(String menuName, String menuDesc, int menuId, Vendor vendor) {
        FoodMenu newMenu = new FoodMenu(menuName, menuDesc, menuId);
        newMenu.setVendor(vendor);

        try {
            menuRepository.save(newMenu);
            vendor.setMenu(newMenu);
        } catch (Exception e) {
            return "Failed";
        }

        return "Success";
    }

}
