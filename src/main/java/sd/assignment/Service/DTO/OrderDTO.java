package sd.assignment.Service.DTO;

public class OrderDTO {

    private Integer id;
    private String rName;
    private Integer cId;
    private Integer price;
    private String date;
    private String status;


    public OrderDTO(Integer id, String rName, Integer price, String date, String status) {
        this.id = id;
        this.rName = rName;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public OrderDTO(Integer id, Integer cId, Integer price, String date, String status) {
        this.id = id;
        this.cId = cId;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public OrderDTO() {}

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
