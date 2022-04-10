package sd.assignment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.assignment.Model.Category;
import sd.assignment.Model.Food;
import sd.assignment.Model.Menu;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.FoodRepository;
import sd.assignment.Repository.MenuRepository;
import sd.assignment.Repository.RestaurantRepository;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.MenuDTO;
import sd.assignment.Service.Mappers.FoodMapper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AdminRepository adminRepository;

    private final FoodMapper foodMapper = new FoodMapper();

    public MenuDTO getAllFoods() {
        HashMap<String, List<String>> map =  new HashMap<>();
        for (Category c: Category.values()) {
            List<Food> foods = foodRepository.findByCategory(c);
            if (!foods.isEmpty())
                map.put(c.toString(), foods.stream().map(Object::toString).collect(Collectors.toList()));
       }
        return new MenuDTO(map);
    }

    public MenuDTO getFoodsByRId(Integer rId) {
        return new MenuDTO(menuRepository.findAllByRestaurant(restaurantRepository.findById(rId))
                .stream().map(m -> m.getFood().toString()).collect(Collectors.toList()));
    }

    public void add(FoodDTO foodDTO) {
        Food food = foodMapper.convertFromDTO(foodDTO);
        foodRepository.save(food);
        Menu menu = new Menu();
        menu.setFood(food);
        menu.setRestaurant(adminRepository.findById(foodDTO.getAdmin()).getRestaurant());
        menuRepository.save(menu);
    }
}
