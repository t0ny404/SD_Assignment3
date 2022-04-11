package sd.assignment.Controller;

import org.springframework.web.bind.annotation.*;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.FoodDTO;

import java.util.List;


@RestController
@RequestMapping(path="/cart")
public class CartController {

    @PutMapping("{restaurant}/add")
    public void add(@PathVariable Integer restaurant, @RequestBody FoodDTO foodDTO) {
        ActiveCart activeCart = ActiveCart.getCart();
        if (activeCart.getRestaurant() == null)
            activeCart.setRestaurant(restaurant);
        ActiveCart.getCart().add(foodDTO);
    }

    @PutMapping("remove")
    public void remove(@RequestBody FoodDTO foodDTO) {
        ActiveCart.getCart().remove(foodDTO);
    }

    @GetMapping("all")
    @ResponseBody
    public List<FoodDTO> getAll() {
        return ActiveCart.getCart().getAll();
    }

    @GetMapping("total")
    @ResponseBody
    public Integer getTotal() {
        return ActiveCart.getCart().getTotal();
    }
}
