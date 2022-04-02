package sd.assignment.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Food {
    private Integer id;
    private Object category;
    private String name;
    private String description;
    private Integer price;
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
    @Column(name = "category")
    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != null ? !id.equals(food.id) : food.id != null) return false;
        if (category != null ? !category.equals(food.category) : food.category != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (description != null ? !description.equals(food.description) : food.description != null) return false;
        if (price != null ? !price.equals(food.price) : food.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "foodByFood")
    public Collection<Menus> getMenusById() {
        return menusById;
    }

    public void setMenusById(Collection<Menus> menusById) {
        this.menusById = menusById;
    }
}
