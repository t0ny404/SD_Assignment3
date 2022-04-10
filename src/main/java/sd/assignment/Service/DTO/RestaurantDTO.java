package sd.assignment.Service.DTO;


public class RestaurantDTO {
    private String name;
    private String location;
    private String zones;
    private Integer admin;
    private Integer id;

    public RestaurantDTO(String name, String location, String zones, Integer id) {
        this.name = name;
        this.location = location;
        this.zones = zones;
        this.id = id;
    }

    public RestaurantDTO(String name, String location, String zones) {
        this.name = name;
        this.location = location;
        this.zones = zones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
