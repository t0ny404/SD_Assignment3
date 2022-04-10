package sd.assignment.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {
    private Integer id;
    private Customer customer;
    private Menu menu;
    private Order order;

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

        Cart cart = (Cart) o;

        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerByClient) {
        this.customer = customerByClient;
    }

    @ManyToOne
    @JoinColumn(name = "menu", referencedColumnName = "id")
    public Menu getMenusByMenu() {
        return menu;
    }

    public void setMenusByMenu(Menu menuByMenu) {
        this.menu = menuByMenu;
    }

    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderByOrder) {
        this.order = orderByOrder;
    }
}
