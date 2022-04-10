package sd.assignment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.assignment.Model.Admin;
import sd.assignment.Model.Restaurant;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.RestaurantRepository;
import sd.assignment.Service.DTO.RestaurantDTO;
import sd.assignment.Service.Mappers.RestaurantMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AdminRepository adminRepository;

    public void add(RestaurantDTO restaurantDTO) {
        Admin admin = adminRepository.findById(restaurantDTO.getAdmin());

        Restaurant restaurant = new RestaurantMapper().convertFromDTO(restaurantDTO);
        restaurantRepository.save(restaurant);

        admin.setRestaurant(restaurant);
        adminRepository.save(admin);
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(r -> new RestaurantMapper(r).convertToDTO()).collect(Collectors.toList());
    }

    public List<RestaurantDTO> getRestaurantsByName(String name) {
        return restaurantRepository.findByNameStartingWith(name)
                .stream().map(r -> new RestaurantMapper(r).convertToDTO()).collect(Collectors.toList());
    }
}
