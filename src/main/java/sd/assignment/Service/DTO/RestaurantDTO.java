package sd.assignment.Service.DTO;

import java.util.Objects;


public class RestaurantDTO {
    private String name;
    private String location;
    private String zones;
    private Integer admin;
    private Integer id;


    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String location, String zones, Integer admin) {
        this.name = name;
        this.location = location;
        this.zones = zones;
        this.admin = admin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantDTO that = (RestaurantDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(location, that.location)) return false;
        return Objects.equals(zones, that.zones);
    }
}
