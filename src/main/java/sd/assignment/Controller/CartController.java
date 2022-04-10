package sd.assignment.Controller;

import org.springframework.web.bind.annotation.*;
import sd.assignment.Service.ActiveCart;

@RestController
@RequestMapping(path="/cart")
public class CartController {


    @PutMapping("{restaurant}/add/{id}")
    public void add(@PathVariable Integer restaurant, @PathVariable Integer id) {
        ActiveCart activeCart = ActiveCart.getCart();
        if (activeCart.getRestaurant() == null)
            activeCart.setRestaurant(restaurant);
        ActiveCart.getCart().add(id);
    }

    @PutMapping("remove/{id}")
    public void remove(@PathVariable Integer id) {
        ActiveCart.getCart().remove(id);
    }
//
//    @GetMapping("{id}")
//    public get(@PathVariable Integer id) {
//        ActiveCart.getCart().;
//    }
//
//    @GetMapping("all")
//    @ResponseBody
//    public  getAll() {
//        ActiveCart.getCart().add(id);
//    }
}
