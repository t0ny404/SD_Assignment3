package sd.assignment;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import sd.assignment.Model.Admin;
import sd.assignment.Model.Restaurant;
import sd.assignment.Repository.AdminRepository;
import sd.assignment.Repository.RestaurantRepository;
import sd.assignment.Service.DTO.RestaurantDTO;
import sd.assignment.Service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private final RestaurantService restaurantService = new RestaurantService();

    private Restaurant restaurant;
    private RestaurantDTO restaurantDTO;

    @Captor
    ArgumentCaptor<Restaurant> restaurantC;
    @Captor
    ArgumentCaptor<Admin> adminC;


    @Before
    public void setup() {
        restaurantDTO = new RestaurantDTO("aa", "aa", "aa");
        restaurantDTO.setAdmin(1);

        restaurant =  new Restaurant();
        restaurant.setName("aa");
        restaurant.setLocation("aa");
        restaurant.setDeliveryzone("aa");
    }

    @Test
    public void successfulAdd() {
        Admin admin = new Admin();
        admin.setId(1);

        Mockito.doReturn(admin).when(adminRepository).findById(restaurantDTO.getAdmin());

        restaurantService.add(restaurantDTO);

        Mockito.verify(restaurantRepository).save(restaurantC.capture());
        assertEquals(restaurant, restaurantC.getValue());

        Mockito.verify(adminRepository).save(adminC.capture());
        assertEquals(admin, adminC.getValue());
        assertEquals(restaurant, adminC.getValue().getRestaurant());
    }

    @Test
    public void successfulGetRestaurantsByName() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);

        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        restaurantDTOList.add(new RestaurantDTO("aa", "aa", "aa"));

        Mockito.doReturn(restaurantList).when(restaurantRepository).findByNameStartingWith("a");

        assertEquals(restaurantDTOList, restaurantService.getRestaurantsByName("a"));

    }

    @Test
    public void unsuccessfulGetRestaurantsByName() {
        List<Restaurant> restaurantList = new ArrayList<>();

        Mockito.doReturn(restaurantList).when(restaurantRepository).findByNameStartingWith("c");

        assertTrue(restaurantService.getRestaurantsByName("c").isEmpty());

    }

    @Test
    public void successfulGetAllRestaurants() {
        Restaurant restaurant1 =  new Restaurant();
        restaurant1.setName("bb");
        restaurant1.setLocation("bb");
        restaurant1.setDeliveryzone("bb");

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);
        restaurantList.add(restaurant1);

        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        restaurantDTOList.add(new RestaurantDTO("aa", "aa", "aa"));
        restaurantDTOList.add(new RestaurantDTO("bb", "bb", "bb"));

        Mockito.doReturn(restaurantList).when(restaurantRepository).findAll();

        assertEquals(restaurantDTOList, restaurantService.getAllRestaurants());
    }
}

