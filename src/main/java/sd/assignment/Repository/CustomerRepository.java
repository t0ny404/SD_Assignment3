package sd.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.assignment.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
