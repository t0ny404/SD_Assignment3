package sd.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.assignment.Model.Food;
import sd.assignment.Model.Menu;
import sd.assignment.Model.Restaurant;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByRestaurant(Restaurant restaurant);
    Menu findByRestaurantAndFood(Restaurant restaurant, Food food);
}
