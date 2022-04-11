package sd.assignment.Service.Mappers;

import sd.assignment.Model.Utils.Category;
import sd.assignment.Model.Food;
import sd.assignment.Service.DTO.FoodDTO;

public class FoodMapper implements Mapper<Food, FoodDTO> {

    private Food food;

    public FoodMapper() {}

    public FoodMapper(Food food) {
        this.food = food;
    }

    public Food convertFromDTO(FoodDTO foodDTO) {
        Food food = new Food();
        if (foodDTO.getId() != null)
            food.setId(foodDTO.getId());
        food.setName(foodDTO.getName());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(foodDTO.getPrice());
        food.setCategory(Category.valueOf(foodDTO.getCategory()));
        return food;
    }

    public FoodDTO convertToDTO() {
        return new FoodDTO(food.getName(), food.getCategory().name(), food.getDescription(), food.getPrice(), food.getId(), null);
    }
}
