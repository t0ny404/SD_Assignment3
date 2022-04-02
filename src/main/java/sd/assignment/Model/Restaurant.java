package sd.assignment.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Restaurant {
    private Integer id;
    private String name;
    private String location;
    private String deliveryzone;
    private Collection<Admin> adminsById;
    private Collection<Menus> menusById;

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
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "deliveryzone")
    public String getDeliveryzone() {
        return deliveryzone;
    }

    public void setDeliveryzone(String deliveryzone) {
        this.deliveryzone = deliveryzone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (deliveryzone != null ? !deliveryzone.equals(that.deliveryzone) : that.deliveryzone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (deliveryzone != null ? deliveryzone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "restaurantByRestaurant")
    public Collection<Admin> getAdminsById() {
        return adminsById;
    }

    public void setAdminsById(Collection<Admin> adminsById) {
        this.adminsById = adminsById;
    }

    @OneToMany(mappedBy = "restaurantByRestaurant")
    public Collection<Menus> getMenusById() {
        return menusById;
    }

    public void setMenusById(Collection<Menus> menusById) {
        this.menusById = menusById;
    }
}
