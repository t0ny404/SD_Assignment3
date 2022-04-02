package sd.assignment.Model;

import javax.persistence.*;

@Entity
public class Cart {
    private Integer id;
    private Customer customerByClient;
    private Menus menusByMenu;
    private Order orderByOrder;

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

        if (id != null ? !id.equals(cart.id) : cart.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    public Customer getCustomerByClient() {
        return customerByClient;
    }

    public void setCustomerByClient(Customer customerByClient) {
        this.customerByClient = customerByClient;
    }

    @ManyToOne
    @JoinColumn(name = "menu", referencedColumnName = "id")
    public Menus getMenusByMenu() {
        return menusByMenu;
    }

    public void setMenusByMenu(Menus menusByMenu) {
        this.menusByMenu = menusByMenu;
    }

    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "id")
    public Order getOrderByOrder() {
        return orderByOrder;
    }

    public void setOrderByOrder(Order orderByOrder) {
        this.orderByOrder = orderByOrder;
    }
}
