package sd.assignment.Service.DTO;

public class FoodDTO {

    private String name;
    private String category;
    private String description;
    private Integer price;
    private Integer admin;
    private Integer id;
    private Integer quantity;

    public FoodDTO(String name, String category, String description, Integer price, Integer id, Integer admin) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.admin = admin;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer id) {
        this.admin = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FoodDTO) {
            FoodDTO foodDTO = (FoodDTO) obj;
            return foodDTO.getId().equals(id);
        }
        return false;
    }
}
