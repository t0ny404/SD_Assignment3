package sd.assignment.Service.DTO;

import java.util.HashMap;
import java.util.List;

public class MenuDTO {

    private HashMap<String, List<String>> menu;
    private List<String> foods;

    public MenuDTO(HashMap<String, List<String>> menu) {
        this.menu = menu;
    }

    public MenuDTO(List<String> foods) {
        this.foods = foods;
    }

    public HashMap<String, List<String>> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, List<String>> menu) {
        this.menu = menu;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
