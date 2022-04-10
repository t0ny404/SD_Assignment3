package sd.assignment.Service.Mappers;

import sd.assignment.Model.Restaurant;
import sd.assignment.Service.DTO.RestaurantDTO;

public class RestaurantMapper {

    public Restaurant convertFromDTO(RestaurantDTO restaurantDTO) {
        Restaurant restaurant =  new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setDeliveryzone(restaurantDTO.getZones());

        return restaurant;
    }

    public RestaurantDTO convertToDTO(Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getName(), restaurant.getLocation(), restaurant.getDeliveryzone(), restaurant.getId());
    }
}
