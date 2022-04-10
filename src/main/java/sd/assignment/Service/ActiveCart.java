package sd.assignment.Service;

import java.util.HashMap;

//Creational DP: Singleton
public final class ActiveCart {

    private static final ActiveCart CART = new ActiveCart();

    private final HashMap<Integer, Integer> cart;
    private Integer restaurant;

    private ActiveCart() {
        cart = new HashMap<>();
    }

    public static ActiveCart getCart() {
        return CART;
    }


    public void add(Integer id) {
        cart.computeIfPresent(id, (k, v) -> v + 1);
        cart.putIfAbsent(id, 1);
    }

    public void remove(Integer id) {
        cart.computeIfPresent(id, (k, v) -> v - 1);
        if (cart.get(id) == 0)
            cart.remove(id);
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }
}
