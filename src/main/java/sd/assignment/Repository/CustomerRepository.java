package sd.assignment.Repository;

import sd.assignment.Model.Customer;
import sd.assignment.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUser(User user);
    Customer findById(Integer id);
}
