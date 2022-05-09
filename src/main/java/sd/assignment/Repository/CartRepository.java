package sd.assignment.Repository;

import sd.assignment.Model.Cart;
import sd.assignment.Model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByOrder(Order order);
}
