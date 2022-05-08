package sd.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import sd.assignment.Model.*;
import sd.assignment.Model.Utils.Category;
import sd.assignment.Model.Utils.Status;
import sd.assignment.Repository.*;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.PlaceOrderDTO;
import sd.assignment.Service.OrderService;
import sd.assignment.Service.Utils.MailSender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Captor
    ArgumentCaptor<String> toC;
    @Captor
    ArgumentCaptor<List<FoodDTO>> foodsC;
    @Captor
    ArgumentCaptor<Integer> priceC;
    @Captor
    ArgumentCaptor<PlaceOrderDTO> placeOrderC;

    private Restaurant restaurant;
    private Customer customer;
    private Food food1, food2;

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

        customer =  new Customer();
        customer.setId(1);
    }

    @Test
    public void successfulOrder() {
        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new Menu()).when(menuRepository).findByRestaurantAndFood(restaurant, food1);
        Mockito.doReturn(new Menu()).when(menuRepository).findByRestaurantAndFood(restaurant, food2);

        Order order = new Order(restaurant,
                customer,
                Status.PENDING,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                5);

        orderService.order(1);

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(order, orderC.getValue());

        Mockito.verify(cartRepository, times(2)).save(cartC.capture());
        assertEquals(new Cart(new Menu(), order, 1), cartC.getValue());
    }

    @Test
    public void successfulMail() {
        Admin admin = new Admin();
        admin.setEmail("aa@gmail.com");

        List<FoodDTO> foods = new ArrayList<>();
        foods.add(new FoodDTO("aa", "DESSERT", "aa", 2, 1, 1));
        foods.add(new FoodDTO("bb", "DESSERT", "bb", 3, 2, 1));

        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(admin).when(adminRepository).findByRestaurant(restaurant);

        orderService.sendEmail(new PlaceOrderDTO());

        Mockito.verify(mailSender).send(toC.capture(), foodsC.capture(), priceC.capture(), placeOrderC.capture());
        assertEquals("aa@gmail.com", toC.getValue());
        assertEquals(foods, foodsC.getValue());
        assertEquals(5, priceC.getValue().intValue());
    }

}
