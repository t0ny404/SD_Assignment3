package sd.assignment.Service.Mappers;

import sd.assignment.Model.Restaurant;
import sd.assignment.Service.DTO.RestaurantDTO;

public class RestaurantMapper implements Mapper<Restaurant, RestaurantDTO> {

    private Restaurant restaurant;


    public RestaurantMapper() {}

    public RestaurantMapper(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO) {
        Restaurant restaurant =  new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setDeliveryzone(restaurantDTO.getZones());

        return restaurant;
    }

    public RestaurantDTO convertToDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getDeliveryzone());
        restaurantDTO.setId(restaurant.getId());
        return restaurantDTO;
    }
}
