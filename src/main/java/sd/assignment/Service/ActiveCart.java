package sd.assignment.Service;

import sd.assignment.Service.DTO.FoodDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The type Active cart, representing the active cart for the current user
 * It uses a Creational Design Pattern: Singleton
 */
public final class ActiveCart {

    private static final ActiveCart CART = new ActiveCart();

    private final Logger logger = LogManager.getLogger();
    private HashMap<FoodDTO, Integer> cart;
    private Integer restaurant;


    private ActiveCart() {
        cart = new HashMap<>();
    }

    /**
     * Gets the current cart.
     *
     * @return the cart
     */
    public static ActiveCart getCart() {
        return CART;
    }


    /**
     * Add a current food to the cart (as a new key with value 1 if not present
     * or increments the value if already present).
     *
     * @param key the FoodDTO as the key of the hashmap
     */
    public void add(FoodDTO key) {
        logger.info("Add 1 buc of food {} to cart", key.getName());
        cart.computeIfPresent(key, (k, v) -> v + 1);
        cart.putIfAbsent(key, 1);
    }

    /**
     * Remove a current food (decrements the value in the hashmap and deletes it if the value is 0).
     *
     * @param key the key
     */
    public void remove(FoodDTO key) {
        logger.info("Remove 1 buc of food {} to cart", key.getName());
        cart.computeIfPresent(key, (k, v) -> v - 1);
        if (cart.get(key) == 0)
            cart.remove(key);
    }

    /**
     * Get the quantity added to the cart of a given food.
     *
     * @param key the food
     * @return the quantity of the given food in the cart
     */
    public Integer get(FoodDTO key) {
        return cart.get(key);
    }

    /**
     * Gets the restaurant of the current cart.
     *
     * @return the restaurant id
     */
    public Integer getRestaurant() {
        return restaurant;
    }

    /**
     * Sets the restaurant.
     *
     * @param restaurant the restaurant id
     */
    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Gets all the foods in the cart.
     *
     * @return all the foods as List of FoodDTO
     */
    public List<FoodDTO> getAll() {
        logger.info("get all the foods in the cart");
        cart.forEach(FoodDTO::setQuantity);
        return new ArrayList<>(cart.keySet());
    }

    /**
     * Gets total.
     *
     * @return the total price
     */
    public Integer getTotal() {
        return getAll().stream().mapToInt(f -> f.getPrice() * f.getQuantity()).sum();
    }

    /**
     * Empties the cart.
     */
    public void empty() {
        cart = new HashMap<>();
        restaurant = null;
        logger.info("empty the cart");
    }
}
