package com.infoFootball.SpringBackend.Concessions.FoodItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, String> {

    FoodItem findByFoodId(int foodId);

    @Transactional
    void deleteByFoodId(int foodId);
}
