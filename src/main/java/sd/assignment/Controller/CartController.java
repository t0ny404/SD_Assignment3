package sd.assignment.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.FoodDTO;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path="/cart")
public class CartController {

    private final Logger logger = LogManager.getLogger();

    @PutMapping("{restaurant}/add")
    public void add(@PathVariable Integer restaurant, @RequestBody FoodDTO foodDTO) {
        if (ActiveCart.getCart().getRestaurant() == null) {
            ActiveCart.getCart().setRestaurant(restaurant);
            logger.warn("Restaurant was not set");
        }
        ActiveCart.getCart().add(foodDTO);
        logger.info("Adding food {}, to restaurant: {}", foodDTO.getName(), ActiveCart.getCart().getRestaurant());
    }

    @PutMapping("remove")
    public void remove(@RequestBody FoodDTO foodDTO) {
        ActiveCart.getCart().remove(foodDTO);
        logger.info("Removed 1 buc. of food {} from cart", foodDTO.getName());
    }

    @GetMapping("all")
    @ResponseBody
    public List<FoodDTO> getAll() {
        return ActiveCart.getCart().getAll();
    }

    @GetMapping("total")
    @ResponseBody
    public Integer getTotal() {
        logger.info("Cart total: {}", ActiveCart.getCart().getTotal());
        return ActiveCart.getCart().getTotal();
    }
}
