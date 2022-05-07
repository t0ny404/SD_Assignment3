package sd.assignment.Model;

import sd.assignment.Model.Utils.UserI;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin")
public class Admin implements UserI {
    private Integer id;
    private String name;
    private User user;
    private Restaurant restaurant;
    private String email;

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

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @JoinColumn(name = "user", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User userByUser) {
        this.user = userByUser;
    }

    @OneToOne
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    public Restaurant getRestaurant() { return restaurant; }

    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant;}
}
