package com.infoFootball.SpringBackend.Concessions.Vendor;

import com.infoFootball.SpringBackend.Concessions.FoodMenu.FoodMenu;
import com.infoFootball.SpringBackend.Concessions.FoodMenu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    MenuRepository menuRepository;

    Vendor removeMenu(Vendor vendor) {
        vendor.setMenu(null);
        return vendor;
    }

    /**
     * Gets a single vendor by name
     * @param vendorName name of vendor
     * @return JSON object of vendor
     */
    @GetMapping(path = "/vendor/{vendorName}")
    Vendor getOneVendor(@PathVariable String vendorName) {
        return removeMenu(vendorRepository.findByName(vendorName));
    }

    @GetMapping(path = "/vendor/getMenu/{vendorName}")
    String getMenuVendor(@PathVariable String vendorName) {
        Vendor cur = vendorRepository.findByName(vendorName);
        String id = "" + cur.getMenu().getMenuId();
        return id;
    }

    /**
     * Gets all vendors
     * @return List of vendors
     */
    @GetMapping(path = "/vendor/all")
    List<Vendor> getAllVendors() {
        List<Vendor> allVendors = vendorRepository.findAll();
        for (Vendor cur : allVendors) {
            removeMenu(cur);
        }
        return allVendors;
    }

    /**
     * Creates and saves a new vendor to the database
     * @param newVendor JSON vendor object
     * @return Success string if completed
     */
    @PostMapping(path = "/vendor/register")
    String createVendor(@RequestBody Vendor newVendor) {
        vendorRepository.save(newVendor);
        return "Success";
    }

    @PutMapping(path = "/vendor/saveMenu/{vendor}/{menuId}")
    String setMenu(@PathVariable String vendor, @PathVariable int menuId) {
        Vendor cur = vendorRepository.findByName(vendor);
        cur.setMenu(menuRepository.findByMenuId(menuId));
        vendorRepository.save(cur);
        return "Success";
    }

    /**
     * Updates a vendor with a new vendor
     * @param newVendor JSON object of new vendor
     * @param oldVendorName String of old vendor name
     * @return JSON of updated vendor object
     */
    @PutMapping(path = "/vendor/update/{oldVendorName}")
    Vendor updateVendor(@RequestBody Vendor newVendor, @PathVariable String oldVendorName) {
        Vendor oldVendor = vendorRepository.findByName(oldVendorName);

        if (oldVendor == null) {
            return null;
        } else {
            oldVendor.setId(newVendor.getVendorId());
            oldVendor.setName(newVendor.getName());
            oldVendor.setLocation(newVendor.getLocation());
            oldVendor.setStatus(newVendor.getStatus());
            oldVendor.setMaintainerUsername(newVendor.getMaintainerUsername());
            vendorRepository.save(oldVendor);
        }
        return oldVendor;
    }

    /**
     * Removes a vendor from the database
     * @param vendorName name of vendor to be removed
     * @return Success string if completed
     */
    //@Transactional
    @DeleteMapping(path = "/vendor/delete/{vendorName}")
    String deleteVendor(@PathVariable String vendorName) {
        vendorRepository.deleteByName(vendorName);
        return "Success";
    }
}
