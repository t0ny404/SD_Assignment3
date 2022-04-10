package sd.assignment.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sd.assignment.Service.DTO.RestaurantDTO;
import sd.assignment.Service.RestaurantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/restaurant")
public class RestaurantController {

    @Autowired
    public RestaurantService restaurantService;

    @GetMapping({"all", "/"})
    @ResponseBody
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("{name}")
    @ResponseBody
    public List<RestaurantDTO> getRestaurantsByName(@PathVariable Optional<String> name) {
        if (name.isPresent())
            return restaurantService.getRestaurantsByName(name.get());
        else return getRestaurants();
    }

    @PostMapping("add")
    public void add(@RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.add(restaurantDTO);
    }
}
