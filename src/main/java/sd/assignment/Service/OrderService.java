package sd.assignment.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.assignment.Model.Cart;
import sd.assignment.Model.Menu;
import sd.assignment.Model.Order;
import sd.assignment.Model.Restaurant;
import sd.assignment.Model.Utils.Status;
import sd.assignment.Repository.*;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.OrderDTO;
import sd.assignment.Service.Mappers.FoodMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void order(Integer user) {
        Restaurant restaurant = restaurantRepository.findById(ActiveCart.getCart().getRestaurant());

        Order order = orderRepository.save(new Order(restaurant,
                customerRepository.findById(user),
                Status.PENDING,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                ActiveCart.getCart().getTotal()));


        for (FoodDTO foodDTO: ActiveCart.getCart().getAll()) {
            cartRepository.save(new Cart(
                    menuRepository.findByRestaurantAndFood(restaurant,
                            new FoodMapper().convertFromDTO(foodDTO)),
                    order,
                    foodDTO.getQuantity()));
        }
    }

    public List<OrderDTO> getHistory(Integer user) {
        return orderRepository.findByCustomerAndStatusNotLike(customerRepository.findById(user), Status.PENDING)
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o).get(0).getMenusByMenu().getRestaurant().getName(),
                    o.getPrice(),
                    o.getDate(),
                    o.getStatus().name())).collect(Collectors.toList());
    }

    public List<OrderDTO> getCurrent(Integer user) {
        return orderRepository.findByCustomerAndStatusLike(customerRepository.findById(user), Status.PENDING)
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o).get(0).getMenusByMenu().getRestaurant().getName(),
                                o.getPrice(),
                                o.getDate(),
                                o.getStatus().name()))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAll(Integer restaurant) {
        return orderRepository.findByRestaurant(restaurantRepository.findById(restaurant))
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        o.getCustomer().getId(),
                        o.getPrice(),
                        o.getDate(),
                        o.getStatus().name()))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllFilter(Integer restaurant, String status) {
        return getAll(restaurant).stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }

    public void change(Integer id, String status) {
        Order order = orderRepository.findById(id);
        for (Status s: Status.values()) {
            if (status.equals(s.name())) {
                order.setStatus(s);
                break;
            }
        }
        orderRepository.save(order);
    }
}
