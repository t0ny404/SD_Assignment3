package sd.assignment.Service.Mappers;

import sd.assignment.Model.Category;
import sd.assignment.Model.Food;
import sd.assignment.Service.DTO.FoodDTO;

public class FoodMapper {

    public Food convertFromDTO(FoodDTO foodDTO) {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(foodDTO.getPrice());
        food.setCategory(Category.valueOf(foodDTO.getCategory()));
        return food;
    }
}
