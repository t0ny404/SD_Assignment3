package sd.assignment.Controller;

import sd.assignment.Service.DTO.RestaurantDTO;
import sd.assignment.Service.RestaurantService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/restaurant")
public class RestaurantController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping({"all", "/"})
    @ResponseBody
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("{name}")
    @ResponseBody
    public List<RestaurantDTO> getRestaurantsByName(@PathVariable Optional<String> name) {
        if (name.isPresent()) {
            logger.info("Filter by keyword {}", name);
            return restaurantService.getRestaurantsByName(name.get());
        } else {
            logger.warn("No filter applied because keyword to filter by is empty!");
            return getRestaurants();
        }
    }

    @PostMapping("add")
    public void add(@RequestBody RestaurantDTO restaurantDTO) {
        logger.info("Add restaurant with name {}", restaurantDTO.getName());
        restaurantService.add(restaurantDTO);
    }
}
