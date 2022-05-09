package sd.assignment.Service.DTO;

public class PlaceOrderDTO {
    private Integer id;
    private String address;
    private String details;


    public PlaceOrderDTO(Integer id, String address, String details) {
        this.id = id;
        this.address = address;
        this.details = details;
    }

    public PlaceOrderDTO() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
