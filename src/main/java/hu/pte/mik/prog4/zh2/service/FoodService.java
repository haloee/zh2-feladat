package hu.pte.mik.prog4.zh2.service;

import hu.pte.mik.prog4.zh2.entity.FoodEntity;
import hu.pte.mik.prog4.zh2.repository.FoodRepository;

import java.util.List;

public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService() {
        this.foodRepository = new FoodRepository();
    }

    public List<FoodEntity> listAll() {
        return this.foodRepository.listAll();
    }

    public FoodEntity findById(Long id) {
        return this.foodRepository.findById(id);
    }

    public FoodEntity save(FoodEntity food) {
        return this.foodRepository.save(food);
    }

    public FoodEntity update(FoodEntity food) {
        return this.foodRepository.update(food);
    }

    public long getFoodPortion(String foodId) {
        throw new UnsupportedOperationException(); // 6. feladathoz fog kelleni
    }
}
