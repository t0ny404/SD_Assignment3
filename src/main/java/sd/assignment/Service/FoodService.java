package sd.assignment.Service;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Food service, handling all the logic for the foods.
 */
@Service
public class FoodService {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AdminRepository adminRepository;


    /**
     * Gets all foods.
     *
     * @return an hashmap containing all foods, grouped by their category (the category is the key in the map)
     */
    public HashMap<String, List<String>> getAllFoods() {
        HashMap<String, List<String>> map =  new HashMap<>();
        for (Category c: Category.values()) {
            List<Food> foods = foodRepository.findByCategory(c);
            if (!foods.isEmpty()) {
                map.put(c.toString(), foods.stream().map(Object::toString).collect(Collectors.toList()));
                logger.info("Current category: {}", c.name());
            }
       }
        return map;
    }

    /**
     * Gets foods by the restaurant id.
     *
     * @param rId the restaurant id
     * @return the foods belonging to that restaurant as a List of FoodDTO
     */
    public List<FoodDTO> getFoodsByRId(Integer rId) {
        return menuRepository.findAllByRestaurant(restaurantRepository.findById(rId))
                .stream().map(m -> new FoodMapper(m.getFood()).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Adds to the database the desired food (to the table containing all the foods and to the table
     * containing all the menus, together to its belonging restaurant)
     *
     * @param foodDTO the foodDTO representing the desired food to add
     */
    public void add(FoodDTO foodDTO) {
        Food food = new FoodMapper().convertFromDTO(foodDTO);
        foodRepository.save(food);
        logger.info("Current food to be added: {}", food.getName());
        Menu menu = new Menu();
        menu.setFood(food);
        menu.setRestaurant(adminRepository.findById(foodDTO.getAdmin()).getRestaurant());
        menuRepository.save(menu);
        logger.info("Restaurant of the current food: {}", menu.getRestaurant().getName());
    }

    /**
     * Gets the menu (all the foods) for a given restaurant, as a PDF.
     *
     * @param rId the restaurant id
     * @return the byte array representing the PDF with the Menu
     */
    public byte[] getMenuPDF(Integer rId) {
        WriterPDF writer = new WriterPDF();
        writer.write(getFoodsByRId(rId));
        logger.info("generating PDF");
        return writer.close();
    }
}
