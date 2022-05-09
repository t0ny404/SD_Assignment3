package sd.assignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import sd.assignment.Model.Admin;
import sd.assignment.Model.Food;
import sd.assignment.Model.Menu;
import sd.assignment.Model.Restaurant;
import sd.assignment.Model.Utils.Category;
import sd.assignment.Repository.*;
import sd.assignment.Service.ActiveCart;
import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.FoodService;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private final FoodService foodService = new FoodService();

    @Captor
    ArgumentCaptor<Food> foodC;
    @Captor
    ArgumentCaptor<Menu> menuC;

    private Restaurant restaurant;
    private Food food1, food2;
    private List<Menu> menus;
    private Admin admin;

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

        Menu menu1 = new Menu(restaurant);
        menu1.setFood(food1);
        Menu menu2 = new Menu(restaurant);
        menu2.setFood(food2);

        menus = new ArrayList<>();
        menus.add(menu1);
        menus.add(menu2);

        admin = new Admin();
        admin.setRestaurant(restaurant);
    }

    @Test
    public void successfulGetAllFoods() {
        for (Category c: Category.values()) {
            if (c.equals(Category.DESSERT))
               Mockito.doReturn(new ArrayList<>(Arrays.asList(food1, food2))).when(foodRepository).findByCategory(c);
            else Mockito.doReturn(new ArrayList<>()).when(foodRepository).findByCategory(c);
        }
        Map<String, List<String>> map = new HashMap<>();
        map.put("DESSERT", new ArrayList<>(Arrays.asList(food1.toString(), food2.toString())));
        assertEquals(map, foodService.getAllFoods());
    }

    @Test
    public void successfulGetFoodsByR() {
        List<FoodDTO> foodDTOList = new ArrayList<>();
        foodDTOList.add(new FoodDTO("aa", "DESSERT", "aa", 2, 1, 1));
        foodDTOList.add(new FoodDTO("bb", "DESSERT", "bb", 3, 2, 1));

        Mockito.doReturn(restaurant).when(restaurantRepository).findById(1);
        Mockito.doReturn(menus).when(menuRepository).findAllByRestaurant(restaurant);

        assertEquals(foodDTOList, foodService.getFoodsByRId(1));
    }

    @Test
    public void successfulAdd() {
        Mockito.doReturn(admin).when(adminRepository).findById(1);

        Menu menu1 = new Menu(restaurant);
        menu1.setFood(food1);

        foodService.add(new FoodDTO("aa", "DESSERT", "aa", 2, 1, 1));

        Mockito.verify(foodRepository).save(foodC.capture());
        assertEquals(food1, foodC.getValue());

        Mockito.verify(menuRepository).save(menuC.capture());
        assertEquals(menu1, menuC.getValue());
    }

}
