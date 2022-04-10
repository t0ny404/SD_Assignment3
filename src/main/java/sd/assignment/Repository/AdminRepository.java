package sd.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.assignment.Model.Admin;
import sd.assignment.Model.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUser(User user);
    Admin findById(Integer id);
}
