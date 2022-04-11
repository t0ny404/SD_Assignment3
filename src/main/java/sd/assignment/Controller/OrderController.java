package sd.assignment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.OrderDTO;
import sd.assignment.Service.OrderService;

import java.util.List;


@RestController
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("order")
    public void order(@RequestBody Integer user) {
        orderService.order(user);
        ActiveCart.getCart().empty();
    }

    @GetMapping("history/{user}")
    @ResponseBody
    public List<OrderDTO> getHistory(@PathVariable Integer user) {
        return orderService.getHistory(user);
    }

    @GetMapping("pending/{user}")
    @ResponseBody
    public List<OrderDTO> getPending(@PathVariable Integer user) {
        return orderService.getCurrent(user);
    }

    @GetMapping("all/{restaurant}")
    @ResponseBody
    public List<OrderDTO> getAll(@PathVariable Integer restaurant) {
        return orderService.getAll(restaurant);
    }

    @GetMapping("all/{restaurant}/filter/{status}")
    @ResponseBody
    public List<OrderDTO> getAll(@PathVariable Integer restaurant, @PathVariable String status) {
        return orderService.getAllFilter(restaurant, status);
    }

    @PutMapping("change/{id}/{s}")
    public void change(@PathVariable Integer id, @PathVariable String s) {
        orderService.change(id, s);
    }
}
