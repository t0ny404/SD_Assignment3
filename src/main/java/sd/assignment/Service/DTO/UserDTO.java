package sd.assignment.Service.DTO;


public class UserDTO {

    private Integer id;
    private String name;
    private String type;
    private Integer restaurant;

    public UserDTO(Integer id, String name, String type, Integer r) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.restaurant = r;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }
}
