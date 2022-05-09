package sd.assignment.Service;

import sd.assignment.Model.Admin;
import sd.assignment.Model.Restaurant;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.RestaurantRepository;
import sd.assignment.Service.DTO.RestaurantDTO;
import sd.assignment.Service.Mappers.RestaurantMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Restaurant service, handling all the logic for the restaurants.
 */
@Service
public class RestaurantService {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AdminRepository adminRepository;


    /**
     * Adds to the database the desired restaurant (to the table containing all the restaurants) and updates
     * to the table containing all the admins by adding the the admin as an admin to this restaurant
     *
     * @param restaurantDTO the restaurantDTO representing the desired restaurant to add
     */
    public void add(RestaurantDTO restaurantDTO) {
        Admin admin = adminRepository.findById(restaurantDTO.getAdmin());

        logger.info("Current admin: {}", admin.getName());

        Restaurant restaurant = new RestaurantMapper().convertFromDTO(restaurantDTO);
        restaurantRepository.save(restaurant);

        logger.info("New restaurant: {}", restaurant.getName());

        admin.setRestaurant(restaurant);
        adminRepository.save(admin);
    }

    /**
     * Gets all the restaurants.
     *
     * @return all restaurants as a List of RestaurantDTO
     */
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(r -> new RestaurantMapper(r).convertToDTO()).collect(Collectors.toList());
    }

    /**
     * Gets all the restaurants whose name starts with a given keyword.
     *
     * @param name the keyword to filter restaurants by their name
     * @return the restaurants starting with the given keyword
     */
    public List<RestaurantDTO> getRestaurantsByName(String name) {
        return restaurantRepository.findByNameStartingWith(name)
                .stream().map(r -> new RestaurantMapper(r).convertToDTO()).collect(Collectors.toList());
    }
}
