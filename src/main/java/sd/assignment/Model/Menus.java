package sd.assignment.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Menus {
    private Integer id;
    private Collection<Cart> cartsById;
    private Restaurant restaurantByRestaurant;
    private Food foodByFood;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue()
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menus menus = (Menus) o;

        if (id != null ? !id.equals(menus.id) : menus.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToMany(mappedBy = "menusByMenu")
    public Collection<Cart> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<Cart> cartsById) {
        this.cartsById = cartsById;
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
    @JoinColumn(name = "food", referencedColumnName = "id")
    public Food getFoodByFood() {
        return foodByFood;
    }

    public void setFoodByFood(Food foodByFood) {
        this.foodByFood = foodByFood;
    }
}
