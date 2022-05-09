package sd.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import sd.assignment.Model.*;
import sd.assignment.Model.Utils.Category;
import sd.assignment.Model.Utils.Status;
import sd.assignment.Repository.*;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.OrderDTO;

import sd.assignment.Service.OrderService;
import sd.assignment.Service.Utils.MailSender;

import java.text.SimpleDateFormat;
import java.util.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private final MailSender mailSender = new MailSender();

    @InjectMocks
    private final OrderService orderService = new OrderService();

    @Captor
    ArgumentCaptor<Order> orderC;
    @Captor
    ArgumentCaptor<Cart> cartC;

    private Restaurant restaurant;
    private Customer customer;
    private Food food1, food2;
    private Order order;
    private Cart cart;

    @Before
    public void setup() {
        restaurant =  new Restaurant();
        restaurant.setName("aa");
        restaurant.setLocation("aa");
        restaurant.setDeliveryzone("aa");
        restaurant.setId(1);

        food1 = new Food();
        food1.setName("aa");
        food1.setCategory(Category.DESSERT);
        food1.setDescription("aa");
        food1.setPrice(2);
        food1.setId(1);
        food2 = new Food();
        food2.setName("bb");
        food2.setCategory(Category.DESSERT);
        food2.setDescription("bb");
        food2.setPrice(3);
        food2.setId(2);

        ActiveCart.getCart().setRestaurant(1);
        ActiveCart.getCart().add(new FoodDTO("aa", "DESSERT", "aa", 2, 1, 1));
        ActiveCart.getCart().add(new FoodDTO("bb", "DESSERT", "bb", 3, 2, 1));

        order = new Order(restaurant,
                customer,
                Status.PENDING,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                5);
        order.setId(1);

        cart = new Cart();
        cart.setMenusByMenu(new Menu(restaurant));


        customer =  new Customer();
        customer.setId(1);

        order.setCustomer(customer);
    }

    @Test
    public void successfulOrder() {
        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new Menu()).when(menuRepository).findByRestaurantAndFood(restaurant, food1);
        Mockito.doReturn(new Menu()).when(menuRepository).findByRestaurantAndFood(restaurant, food2);

        orderService.order(1);

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(order.getStatus(), orderC.getValue().getStatus());


        Mockito.verify(cartRepository, times(2)).save(cartC.capture());
        assertEquals(new Cart(new Menu(), order, 1), cartC.getValue());
    }

    @Test
    public void successfulGetHistory() {
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order))).when(orderRepository)
                .findByCustomerAndStatusIn(customer, new Status[]{Status.DECLINED, Status.DELIVERED});
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(cart))).when(cartRepository).findByOrder(order);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                "aa",
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getHistory(1));
    }

    @Test
    public void successfulGetCurrent() {
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order))).when(orderRepository)
                .findByCustomerAndStatusNotIn(customer, new Status[]{Status.DECLINED, Status.DELIVERED});
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(cart))).when(cartRepository).findByOrder(order);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                "aa",
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getCurrent(1));
    }

    @Test
    public void successfulGetAll() {
        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order)))
                .when(orderRepository).findByRestaurant(restaurant);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                1,
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getAll(1));
    }

    @Test
    public void successfulGetAllFilter() {
        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order)))
                .when(orderRepository).findByRestaurant(restaurant);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                1,
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getAllFilter(1, Status.PENDING.name()));
    }

    @Test
    public void successfulChange() {
        Mockito.doReturn(order).when(orderRepository).findById(1);

        orderService.change(1, Status.ACCEPTED.name());

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(Status.ACCEPTED, orderC.getValue().getStatus());
    }

    @Test
    public void worngStatusChange() {
        Mockito.doReturn(order).when(orderRepository).findById(1);

        orderService.change(1, "no status");

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(Status.PENDING, orderC.getValue().getStatus());
    }
}
