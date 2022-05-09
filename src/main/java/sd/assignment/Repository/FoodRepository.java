package sd.assignment.Repository;

import sd.assignment.Model.Utils.Category;
import sd.assignment.Model.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByCategory(Category category);
}
