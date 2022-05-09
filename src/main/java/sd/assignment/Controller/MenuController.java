package sd.assignment.Controller;

import sd.assignment.Service.DTO.FoodDTO;
import sd.assignment.Service.DTO.ResponseDTO;
import sd.assignment.Service.FoodService;
import sd.assignment.Service.Utils.Severity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path="/menu")
public class MenuController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private FoodService foodService;


    @GetMapping("all")
    @ResponseBody
    public HashMap<String, List<String>> getFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("{rId}")
    @ResponseBody
    public List<FoodDTO> getFoodsByRId(@PathVariable Integer rId) {
        return foodService.getFoodsByRId(rId);
    }

    @GetMapping("pdf/{rId}")
    @ResponseBody
    public byte[] getMenuPDF(@PathVariable Integer rId) {
        logger.info("Get menu for the restaurant {}", rId);
        return foodService.getMenuPDF(rId); }

    @PostMapping("add")
    public ResponseEntity addFood(@RequestBody FoodDTO foodDTO) {
        try {
            foodService.add(foodDTO);
        } catch(Exception e) {
            logger.error("Food {} could not be added because {}", foodDTO.getName(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage(), Severity.FAILURE));
        }
        logger.info("Food {} added", foodDTO.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO("Food added!", Severity.SUCCESS));
    }
}
