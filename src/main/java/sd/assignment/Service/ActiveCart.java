package sd.assignment.Service;

import sd.assignment.Service.DTO.FoodDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Creational DP: Singleton
public final class ActiveCart {

    private static final ActiveCart CART = new ActiveCart();

    private HashMap<FoodDTO, Integer> cart;
    private Integer restaurant;

    private ActiveCart() {
        cart = new HashMap<>();
    }

    public static ActiveCart getCart() {
        return CART;
    }


    public void add(FoodDTO key) {
        cart.computeIfPresent(key, (k, v) -> v + 1);
        cart.putIfAbsent(key, 1);
    }

    public void remove(FoodDTO key) {
        cart.computeIfPresent(key, (k, v) -> v - 1);
        if (cart.get(key) == 0)
            cart.remove(key);
    }

    public Integer get(FoodDTO key) {
        return cart.get(key);
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public List<FoodDTO> getAll() {
        cart.forEach(FoodDTO::setQuantity);
        return new ArrayList<>(cart.keySet());
    }

    public Integer getTotal() {
        return getAll().stream().mapToInt(f -> f.getPrice() * f.getQuantity()).sum();
    }

    public void empty() {
        cart = new HashMap<>();
        restaurant = null;
    }
}
