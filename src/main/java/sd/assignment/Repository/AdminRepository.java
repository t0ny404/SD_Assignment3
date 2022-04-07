package sd.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sd.assignment.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
