package sd.assignment.Repository;

import sd.assignment.Model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findById(Integer id);
    List<Restaurant> findByNameStartingWith(String name);
}
