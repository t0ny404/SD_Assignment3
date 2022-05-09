package sd.assignment.Service;

import sd.assignment.Model.Admin;
import sd.assignment.Model.Cart;
import sd.assignment.Model.Order;
import sd.assignment.Model.Restaurant;
import sd.assignment.Model.Utils.Status;
import sd.assignment.Repository.*;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.OrderDTO;
import sd.assignment.Service.DTO.PlaceOrderDTO;
import sd.assignment.Service.Mappers.FoodMapper;
import sd.assignment.Service.Utils.MailSender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Order service, handling all the logic for the orders.
 */
@Service
public class OrderService {

    private final Logger logger = LogManager.getLogger();
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
    @Autowired
    private AdminRepository adminRepository;
    private final MailSender mailSender = new MailSender();


    /**
     * Makes a new order adding it to the database and updates the current cart for given user.
     *
     * @param user the user id
     */
    public void order(Integer user) {
        Restaurant restaurant = restaurantRepository.findById(ActiveCart.getCart().getRestaurant());

        logger.info("Current restaurant: {} and current total: {}",
                restaurant.getName(), ActiveCart.getCart().getTotal());

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

    /**
     * Sends an email to the admin that manages the restaurant from where the food was selected.
     *
     * @param order all the extra information for the order
     */
    public void sendEmail(PlaceOrderDTO order) {
        Admin admin = adminRepository.findByRestaurant(
                restaurantRepository.findById(ActiveCart.getCart().getRestaurant()));
        logger.info("Admin to send email to: {} and address: {}", admin.getName(), admin.getEmail());
        mailSender.send(admin.getEmail(), ActiveCart.getCart().getAll(), ActiveCart.getCart().getTotal(), order);
    }

    /**
     * Gets the history of all completed (i.e. with status Declined or Delivered) orders of the given user.
     *
     * @param user the user id
     * @return the history of all orders of the given user
     */
    public List<OrderDTO> getHistory(Integer user) {
        return orderRepository.findByCustomerAndStatusIn(
                customerRepository.findById(user),
                new Status[]{Status.DECLINED, Status.DELIVERED})
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o).get(0).getMenusByMenu().getRestaurant().getName(),
                    o.getPrice(),
                    o.getDate(),
                    o.getStatus().name())).collect(Collectors.toList());
    }

    /**
     * Gets the current orders of the given user (i.e. with status Pending)
     *
     * @param user the user id
     * @return the current orders of the given user
     */
    public List<OrderDTO> getCurrent(Integer user) {
        return orderRepository.findByCustomerAndStatusNotIn(
                customerRepository.findById(user),
                new Status[]{Status.DECLINED, Status.DELIVERED})
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o).get(0).getMenusByMenu().getRestaurant().getName(),
                                o.getPrice(),
                                o.getDate(),
                                o.getStatus().name()))
                .collect(Collectors.toList());
    }

    /**
     * Gets all orders for the given restaurant.
     *
     * @param restaurant the restaurant id
     * @return all orders for the given restaurant as a List of OrderDTO
     */
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

    /**
     * Gets all orders for a given restaurant with a given status.
     *
     * @param restaurant the restaurant id
     * @param status     the status to filter by
     * @return all orders for the given restaurant that have a given status
     */
    public List<OrderDTO> getAllFilter(Integer restaurant, String status) {
        return getAll(restaurant).stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }

    /**
     * Change the status of the given order to a given status.
     *
     * @param id     the order id
     * @param status the status
     */
    public void change(Integer id, String status) {
        Order order = orderRepository.findById(id);

        logger.info("Current status: {}", order.getStatus().name());
        for (Status s: Status.values()) {
            if (status.equals(s.name())) {
                order.setStatus(s);
                break;
            }
        }
        logger.info("Next status: {}", order.getStatus().name());
        orderRepository.save(order);
    }
}
