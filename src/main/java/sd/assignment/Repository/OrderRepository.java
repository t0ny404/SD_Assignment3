package sd.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.assignment.Model.Customer;
import sd.assignment.Model.Order;
import sd.assignment.Model.Restaurant;
import sd.assignment.Model.Utils.Status;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerAndStatusNotLike(Customer customer, Status status);
    List<Order> findByCustomerAndStatusLike(Customer customer, Status status);
    List<Order> findByRestaurant(Restaurant restaurant);
    Order findById(Integer id);
}
