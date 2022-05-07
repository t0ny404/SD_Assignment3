package sd.assignment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.assignment.Model.Utils.Category;
import sd.assignment.Model.Food;
import sd.assignment.Model.Menu;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.FoodRepository;
import sd.assignment.Repository.MenuRepository;
import sd.assignment.Repository.RestaurantRepository;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.Mappers.FoodMapper;
import sd.assignment.Service.Utils.WriterPDF;

import java.util.Arrays;
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

    public HashMap<String, List<String>> getAllFoods() {
        HashMap<String, List<String>> map =  new HashMap<>();
        for (Category c: Category.values()) {
            List<Food> foods = foodRepository.findByCategory(c);
            if (!foods.isEmpty())
                map.put(c.toString(), foods.stream().map(Object::toString).collect(Collectors.toList()));
       }
        return map;
    }

    public List<FoodDTO> getFoodsByRId(Integer rId) {
        return menuRepository.findAllByRestaurant(restaurantRepository.findById(rId))
                .stream().map(m -> new FoodMapper(m.getFood()).convertToDTO()).collect(Collectors.toList());
    }

    public void add(FoodDTO foodDTO) {
        Food food = new FoodMapper().convertFromDTO(foodDTO);
        foodRepository.save(food);
        Menu menu = new Menu();
        menu.setFood(food);
        menu.setRestaurant(adminRepository.findById(foodDTO.getAdmin()).getRestaurant());
        menuRepository.save(menu);
    }

    public byte[] getMenuPDF(Integer rId) {
        WriterPDF writer = new WriterPDF();
        writer.write(getFoodsByRId(rId));
        return writer.close();
    }
}
