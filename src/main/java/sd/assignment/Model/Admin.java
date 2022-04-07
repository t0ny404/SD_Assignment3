package sd.assignment.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin")
public class Admin {
    private Integer id;
    private String name;
    private Restaurant restaurantByRestaurant;
    private User userByUser;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue()
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (!Objects.equals(id, admin.id)) return false;
        return Objects.equals(name, admin.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    public Restaurant getRestaurantByRestaurant() {
        return restaurantByRestaurant;
    }

    public void setRestaurantByRestaurant(Restaurant restaurantByRestaurant) {
        this.restaurantByRestaurant = restaurantByRestaurant;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    public User getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(User userByUser) {
        this.userByUser = userByUser;
    }
}
