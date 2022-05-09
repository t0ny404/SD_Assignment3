package sd.assignment.Repository;

import sd.assignment.Model.Admin;
import sd.assignment.Model.Restaurant;
import sd.assignment.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUser(User user);
    Admin findById(Integer id);
    Admin findByRestaurant(Restaurant restaurant);
}
